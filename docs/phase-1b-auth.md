# Phase 1b: JWT login and roles (step by step)

Date: 2026-10-14 · Result: `./mvnw test` → 17 tests, 0 failures, 0 errors · Not committed.

---

## 1. Dependency and configuration

**Why:** the app needs a library to sign and verify JWTs, and the signing key must come from the environment, never from the code.

| File | Change |
|---|---|
| `sale-management/pom.xml` | Added `spring-boot-starter-security-oauth2-resource-server` (version managed by Spring Boot 4.1.1). |
| `src/main/resources/application.properties` | Added the properties below. |
| `src/test/resources/application-test.properties` | Added a test-only `app.jwt.secret`, so tests run without the environment variable. |

```properties
app.jwt.secret=${JWT_SECRET}          # required, at least 32 bytes
app.jwt.expiration=PT8H               # token lifetime: 8 hours
app.bootstrap-admin.username=${BOOTSTRAP_ADMIN_USERNAME:}
app.bootstrap-admin.password=${BOOTSTRAP_ADMIN_PASSWORD:}
```

New property classes: `security/JwtProperties.java` (`secret`, `expiration`) and `security/BootstrapAdminProperties.java` (`username`, `password`).

---

## 2. Security configuration (`security/SecurityConfig.java`)

**Why:** replace "allow every request" with token-based access and role rules.

- **Stateless:** no HTTP session (`SessionCreationPolicy.STATELESS`). CSRF stays disabled because the token is sent in a header, not a cookie.
- **Access rules:**

  | Request | Who can call it |
  |---|---|
  | `POST /api/auth/login` | Anyone |
  | `/api/user/**` | `ADMIN` only (others get 403) |
  | Everything else | Any logged-in user (no token gets 401) |

- **Token signing:** `JwtEncoder` and `JwtDecoder` beans use HS256 with the secret. Startup fails with a clear message if `JWT_SECRET` is missing or shorter than 32 bytes.
- **Roles:** the token's `role` claim (for example `ADMIN`) becomes the Spring authority `ROLE_ADMIN`, so `hasRole("ADMIN")` works.
- **Method security:** `@EnableMethodSecurity` is turned on so later phases can use `@PreAuthorize` per endpoint.
- **Unchanged:** the existing `PasswordEncoder` (BCrypt) bean.

---

## 3. Login API (`module/user_service`)

**Why:** users need a way to swap their username and password for a token.

| File | Purpose |
|---|---|
| `constant/RouteConst.java` | Added `AUTH = "api/auth"`. |
| `request/auth/AuthRequest.java` | `login { username, password }`, both `@NotBlank`. |
| `dto/AuthDTO.java` | `token { accessToken, tokenType, expiresIn, username, role }`. |
| `service/AuthService.java` + `service/impl/AuthServiceImpl.java` | Login and "who am I" logic. |
| `controller/AuthController.java` | `POST /api/auth/login`, `GET /api/auth/me`. |
| `repository/UserRepository.java` | Added `findByUsername(String)` and `existsByRole(UserRole)`. |
| `security/JwtTokenService.java` | Builds the token: `sub` = username, `uid` = user id, `role` = role, expires after 8 hours. |

Login rules:
1. If the username doesn't exist, the password is still checked against a dummy BCrypt hash, so the response takes as long as a wrong password and doesn't reveal which usernames exist. Returns **401**.
2. A disabled user (`isActivate` not `true`) gets **401**.
3. A wrong password gets **401** with `ApiErrorResponse("Invalid username or password")`.
4. A blank field gets **400** from the existing validation handler.
5. Success returns **200** with the token.

```http
POST /api/auth/login
{"username":"admin","password":"..."}

200 → {"accessToken":"eyJ...","tokenType":"Bearer","expiresIn":28800,"username":"admin","role":"ADMIN"}
```

`GET /api/auth/me` (with `Authorization: Bearer <token>`) returns `UserDTO.view` (`id`, `username`, `fullName`, `role`), never the password.

---

## 4. Real current user, audit, and first admin

**Why:** AGENTS.md requires tracking who created orders, changed statuses, and received payments.

- **`service/BaseInternalAppService.java`:** `currentUser()` now returns the logged-in username, which `publish-service` writes to `created_by` and `modified_by`. It returns `USER_APP` only for work done outside a request, such as startup.
- **`security/CurrentUser.java` (new):**
  - `id()` reads the `uid` claim from the token.
  - `reference()` returns a `User` reference without a database query, for user foreign keys in later phases (`orders.created_by_user_id`, `payments.received_by`, `*_status_history.changed_by`).
  - With no logged-in user, both throw an error.
- **`security/AdminBootstrap.java` (new):** at startup, if no `ADMIN` exists and both `BOOTSTRAP_ADMIN_*` variables are set, it creates one through `UserService.save` (password hashed). If no admin exists and the variables are missing, it logs a warning. It never logs the password.

---

## 5. Tests and verification

**New: `src/test/java/.../AuthSecurityTests.java`** (9 tests; MockMvc, `test` profile, each test rolled back)

| # | Test | Expected |
|---|---|---|
| 1 | Login, then `GET /api/auth/me` with the token | 200, correct username and role, no `password` field |
| 2 | Wrong password | 401, no token |
| 3 | Unknown username | 401 |
| 4 | Disabled user | 401 |
| 5 | Blank login body | 400 |
| 6 | No token on `/api/user` and `/api/auth/me` | 401 |
| 7 | Malformed token | 401 |
| 8 | `WAITER` token on `/api/user` | 403 |
| 9 | `ADMIN` creates a `CASHIER` | 200; `created_by` = admin's username; role saved; password stored hashed |

**Changed:** `SaleManagementApplicationTests` now uses `@ActiveProfiles("test")`, because the default profile requires `JWT_SECRET`.

**Result:**

| Test class | Tests | Failures | Errors |
|---|---|---|---|
| AuthSecurityTests | 9 | 0 | 0 |
| RestaurantSchemaTests | 7 | 0 | 0 |
| SaleManagementApplicationTests | 1 | 0 | 0 |
| **Total** | **17** | **0** | **0** |

**Not verified:** starting the app against the dev database. That would create an admin with a password I chose.

**To run the app:**

```powershell
$env:JWT_SECRET = "<random string, 32+ characters>"
$env:BOOTSTRAP_ADMIN_USERNAME = "admin"; $env:BOOTSTRAP_ADMIN_PASSWORD = "<your password>"   # first run only
./mvnw spring-boot:run
```

**Known limitations:** a token stays valid until it expires (8 hours), even if the user is disabled in the meantime. There is no logout or refresh token.
