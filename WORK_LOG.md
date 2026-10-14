# Work Log — 2026-10-14

Summary of the changes made with Claude Code in this session. Nothing has been committed yet.

## 1. `CLAUDE.md` (new)

Guidance file for Claude Code: Maven commands (build, run, test, single test), external dependencies (`publish-service` jar in `~/.m2`, local PostgreSQL), and the module architecture built on `publish-service` base classes. Later updated for Flyway and the new modules.

## 2. Password security fixes (`user_service`, `security`)

| Problem | Fix | File |
|---|---|---|
| Passwords stored in plain text | BCrypt hashing via a new `PasswordEncoder` bean | `security/SecurityConfig.java`, `UserServiceImpl.java` |
| Password returned by the API | Removed from `UserDTO.view`; `@JsonIgnore` on `User.password` (the `/page` and `/all` endpoints return entities) | `dto/UserDTO.java`, `model/User.java` |
| Search matched against passwords | Search now matches `username` only | `specification/UserSpecification.java` |

## 3. User update implemented

`PUT /api/user` previously loaded and re-saved the user without applying any changes. It now:

- sets `username`, re-hashes `password`, and (since Phase 1) sets `fullName` and `role`;
- replaces the user's addresses (deletes the old ones, saves the new ones) via the new `UserAddressService.replace` and `UserAddressRepository.deleteByUser`;
- runs in one `@Transactional` method;
- calls `update()` instead of `save()`, so `createdAt`/`createdBy` are no longer reset.

## 4. Database cleanup

On request, all rows were deleted from the dev database `sale-mgmt`, in one transaction: 6 rows from `user_address` (required by its foreign key), then 7 rows from `app_user`. ID sequences were not reset.

## 5. Claude Code subagent (new)

`.claude/agents/module-scaffolder.md` scaffolds a new feature module following the `user_service` pattern (entity, repository, filter, specification, request, DTO, service, controller, routes), applies the rules above (soft delete, `update()` not `save()`, hashed secrets, Flyway migration per entity change), and compiles to verify.

## 6. Phase 0: repository analysis against `AGENTS.md`

Analysis only, no file changes. Main findings:

- **Exists:** Spring Boot 4.1.1 / Java 21 / PostgreSQL 14, a `user_service` module, `publish-service` base CRUD, a shared error handler, pagination and date utilities.
- **Missing:** every restaurant feature, authentication and roles, migrations, optimistic locking, and a frontend.
- **Conflicts:** `ddl-auto=update` instead of migrations; audit timestamps use `LocalDateTime`; `createdBy` is always the fixed string `USER_APP` (no real user); no authentication; endpoint style differs (`PUT` + `DELETE ?id=` vs `PATCH /{id}`); entities are returned from controllers; base `save()`/`update()` turn every error into a 409.
- **Plan:** Phase 1 database → 1b auth and roles → 2 menu → 3 orders → 4 kitchen → 5 payments → 6 reports → 7 hardening.

## 7. Phase 1: database foundation

### Defaults chosen

- New business timestamps use `TIMESTAMPTZ` / `Instant`; inherited audit fields stay `LocalDateTime`.
- Users: the existing `app_user` table is extended with `full_name` and `role` (`ADMIN`, `CASHIER`, `WAITER`, `KITCHEN`).
- The order creator's foreign key is `created_by_user_id`, because `created_by` is already the inherited audit string column.
- The `test_employee_*` tables are kept, since their entity classes still exist.
- Modules: `menu_service`, `table_service`, `order_service`, `payment_service`.

### Changes

- **`pom.xml`:** added `spring-boot-starter-flyway` and `flyway-database-postgresql`.
- **`application.properties`:** `ddl-auto=update` → `validate`; `spring.flyway.baseline-on-migrate=true`, `baseline-version=1`.
- **`db/migration/V1__baseline.sql`:** recreates the pre-Flyway schema. It is skipped on existing databases and only runs on an empty one.
- **`db/migration/V2__restaurant_core.sql`:**
  - Tables: `menu_categories`, `menu_items`, `restaurant_tables`, `orders`, `order_items`, `payments`, `order_status_history`, `order_item_status_history`, plus the new `app_user` columns.
  - Money columns are `NUMERIC(12,2)`, with checks that prices and totals are not negative, quantities and capacity are above 0, and payment amounts are above 0.
  - `line_total = unit_price * quantity` check on `order_items`.
  - Unique `order_number` and `table_number`; check constraints restrict every status column to its allowed values.
  - Indexes on foreign keys, `orders.created_at`, `orders.order_status`, `orders.payment_status`, `order_items.kitchen_status`, and `payments.paid_at`.
  - A `version` column on `orders`, `order_items`, and `payments` for optimistic locking.
- **Entities, enums, repositories:**
  - `menu_service`: `MenuCategory`, `MenuItem`
  - `table_service`: `RestaurantTable`, `TableStatus`
  - `order_service`: `Order`, `OrderItem`, `OrderStatusHistory`, `OrderItemStatusHistory`, `OrderType`, `OrderStatus`, `KitchenStatus`
  - `payment_service`: `Payment`, `PaymentMethod`, `PaymentStatus` (a single payment record), `OrderPaymentStatus` (the order's overall payment status)
  - `user_service`: `UserRole`; `User`, `UserRequest`, `UserDTO`, and `UserServiceImpl` gained `fullName` and `role`
  - Order items are saved with their order (cascade `PERSIST`/`MERGE`, never `REMOVE`).
- **Tests:**
  - Created a local `sale-mgmt-test` database, with `src/test/resources/application-test.properties` pointing to it.
  - New `RestaurantSchemaTests` (7 tests): saves and reloads a full order graph, and checks that the database rejects a negative price, zero quantity, a wrong line total, a duplicate order number, a zero payment, and an unknown status.
- **Docs:** `CLAUDE.md` and `module-scaffolder.md` updated for Flyway.

### Verification

- `./mvnw test`: **8 tests, 0 failures, 0 errors.**
- Fresh `sale-mgmt-test` database: Flyway applied V1 and V2.
- Existing `sale-mgmt` database: Flyway recorded V1 as a baseline and applied V2; `flyway_schema_history` shows both as successful.

## 8. Phase 1b: JWT authentication and roles

- **Login:** `POST /api/auth/login` checks the BCrypt password and rejects disabled users (`isActivate` not true). It returns an HS256 JWT valid for 8 hours, with claims `sub` = username, `uid` = user id, `role` = role. Unknown users are checked against a dummy hash so they take as long as a wrong password. `GET /api/auth/me` returns the caller without the password.
- **Security config (`security/SecurityConfig.java`):** stateless with no session; CSRF is disabled because there is no cookie; `/api/user/**` is ADMIN-only; every other endpoint needs a token; `@EnableMethodSecurity` is on for later phases.
- **New classes:**
  - `security/`: `JwtProperties`, `JwtTokenService`, `CurrentUser` (gives the caller's id or a `User` reference for foreign keys), `AdminBootstrap`, `BootstrapAdminProperties`
  - `user_service/`: `AuthController`, `AuthService`, `AuthServiceImpl`, `AuthRequest.login`, `AuthDTO.token`
- **Audit:** `BaseInternalAppService.currentUser()` now returns the logged-in username instead of the fixed string `USER_APP`, which is kept as the fallback for startup work.
- **Repository:** `UserRepository` gained `findByUsername` and `existsByRole`.
- **Config:**
  - `pom.xml`: added `spring-boot-starter-security-oauth2-resource-server`.
  - `application.properties`: `app.jwt.secret=${JWT_SECRET}` (required, at least 32 bytes), `app.jwt.expiration=PT8H`, and optional `BOOTSTRAP_ADMIN_USERNAME` / `BOOTSTRAP_ADMIN_PASSWORD` to create the first ADMIN when none exists.
- **Tests:** new `AuthSecurityTests` (9 tests):
  - a login token authenticates `/me`, and the password is never returned;
  - 401 for a wrong password, an unknown user, a disabled user, a missing token, and an invalid token;
  - 400 for a blank login;
  - 403 when a WAITER calls `/api/user`;
  - when an ADMIN creates a user, `created_by` records the admin's username and the password is stored hashed.
- `SaleManagementApplicationTests` now uses the `test` profile, because the dev profile requires `JWT_SECRET`.
- **Verification:** `./mvnw test` ran **17 tests, 0 failures, 0 errors**.

## Open items

- Nothing is committed.
- V2 adds required columns to `app_user` with no default, which works only because the table is empty.
- No API endpoints exist yet for the new tables (Phases 2–6).
- Base `save()`/`update()` still turn every error into a 409 and pass raw database messages to clients; this lives in `publish-service`.
- Running the app now requires `JWT_SECRET`. On the empty dev database, also set the `BOOTSTRAP_ADMIN_*` variables once, or no one can log in.
- A token stays valid until it expires (8 hours), even if the user is disabled in the meantime. There is no logout or refresh token.
- **Next is Phase 2, menu management.**
