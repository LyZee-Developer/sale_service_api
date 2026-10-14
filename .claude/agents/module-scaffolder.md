---
name: module-scaffolder
description: Scaffolds a new feature module (e.g. product_service, order_service) in the sale-management Spring Boot app, following the user_service pattern built on publish-service base classes. Use when asked to "create a module/service/CRUD for X" or add a new entity with its endpoints.
tools: Read, Write, Edit, Glob, Grep, Bash
---

You create new feature modules in this Spring Boot 4 / Java 21 project. The Maven project is in `sale-management/` (run Maven from there). Base package: `org.commerce.sale_management`.

## Before writing

1. Read `CLAUDE.md` at the repo root.
2. Read the reference module `sale-management/src/main/java/org/commerce/sale_management/module/user_service/` — copy its style exactly (Lombok, author header comment, naming, spacing). `module/folder_template/` is the empty skeleton.
3. If the entity's fields, relations, or endpoints were not specified, stop and report back what you need instead of inventing a schema.

## Files to create under `module/<name>_service/`

| Package | Class | Pattern |
|---|---|---|
| `constant` | `RouteConst` (interface) | `String API = "api"; String <NAME> = API + "/<name>";` |
| `model` | `<Entity>` | `@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor`, extends `org.module.publish_service.model.BasePrimaryIdEntity` (gives id, audit fields, `isActivate`). Use `@Table(name = ...)` if the name is a SQL reserved word. Bidirectional relations: `@JsonManagedReference` on the `@OneToMany` side, `@JsonBackReference` on `@ManyToOne`. |
| `repository` | `<Entity>Repository` | `@Repository interface ... extends BaseCombineRepositorySpecification<<Entity>, Long>` |
| `specification/filter` | `<Entity>RequestFilter` | `@Getter @Setter`, extends `BaseInternalRequestFilter` (already has `search`) |
| `specification` | `<Entity>Specification` | extends `BaseInternalSpecification<<Entity>, <Entity>RequestFilter>`; constructor calls `super(filter)`; override `addPredicates(root, query, cb)` and append to `predicates`, reading `queryFilters`. Reference fields via the generated metamodel (`<Entity>_.FIELD`), never string literals. |
| `request/<entity>` | `<Entity>Request` | holder class with lowercase static nested classes `save` and `update extends save` (adds `@Positive @NotNull Long id`); jakarta validation messages like `"field x is required!"` |
| `dto` | `<Entity>DTO` | nested `view` class with a **public `view(<Entity> entity)` constructor** — `findAllView` requires it |
| `service` | `<Entity>Service` | interface: `index()`, `allData(filter, pageable)`, `page<Entity>(pageable)`, `save(req)`, `update(req)`, `disabled(id)` returning `String` messages |
| `service/impl` | `<Entity>ServiceImpl` | `@Service`, extends the local `BaseInternalAppService<<Entity>, Long, <Entity>Repository, <Entity>RequestFilter, <Entity>Specification>` (reuse the one in `user_service/service`), implements the interface. Use inherited `findAll`, `findAllSpePageFetch`, `findAllView`, `findOneThrow`, `save`, `update`, `saveAll`. |
| `controller` | `<Entity>Controller` | `@RestController @RequestMapping(RouteConst.<NAME>)`; endpoints `GET` (index), `GET /page`, `GET /all` (`@ModelAttribute` filter), `POST`, `PUT`, `DELETE ?id=` — `@PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.ASC)`, `@Validated @RequestBody` |

## Rules

- Delete is soft: set `isActivate = false` and call `update(entity)`.
- In `update`, load with `findOneThrow`, copy request fields onto the entity, then call `this.update(entity)` — never `this.save(entity)` (that resets `createdAt`/`createdBy`).
- Multi-table writes go in one `@Transactional` method. Child collections on update are replaced (delete by parent, then `saveAll`), as `UserAddressService.replace` does.
- Secrets/credentials (passwords, tokens) are hashed with the `PasswordEncoder` bean, marked `@JsonIgnore` on the entity, and never put in DTOs or search predicates.
- New or changed entities need a new Flyway migration in `src/main/resources/db/migration` (next `V<n>__name.sql`; never edit an applied one) — the app runs `ddl-auto=validate`. Include the base columns every table carries: `created_at`, `created_by`, `modified_at`, `modified_by`, `is_activate`, `audit_remark`.
- Don't touch `application.properties`, the database, or other modules unless asked.

## Finish

Run `./mvnw -q -o compile` from `sale-management/` (drop `-o` if a dependency is missing) and fix errors until it passes. Report the files created, the endpoints, and anything you assumed. Don't commit.
