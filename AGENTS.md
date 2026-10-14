# Restaurant Ordering App --- Agent Instructions

## 1. Project Goal

Build a restaurant ordering and management system where customers can
order food and drinks, kitchen staff receive orders immediately, staff
manage preparation and serving, cashiers record payments, and managers
view sales reports.

Follow the existing repository's conventions and architecture. Before
implementing anything, inspect the project structure, dependencies,
current entities, DTOs, repositories, services, controllers,
authentication, and UI components. Reuse existing functionality where
possible. Do not duplicate existing features.

## 2. Suggested Technology Stack

Use the technologies already present in the repository. If the project
is not initialized, use:

-   Backend: Java with Spring Boot, Spring Web, Spring Data JPA, Bean
    Validation, and Spring Security if authentication is in scope.
-   Database: PostgreSQL.
-   API style: REST JSON APIs.
-   Live kitchen updates: begin with polling if real-time infrastructure
    does not exist; consider WebSocket or Server-Sent Events later.

Do not replace libraries or upgrade major versions without explaining
the reason first.

## 3. Business Scope

### MVP features

1.  Menu categories and menu item management.
2.  Restaurant table management.
3.  Employee accounts and role-based permissions.
4.  Order creation with multiple items, quantities, and special
    instructions.
5.  Kitchen display showing new orders and item preparation status.
6.  Order serving and completion.
7.  Payment recording, starting with cash.
8.  Daily, monthly, and yearly sales reports.
9.  Order status history and audit information.

### Later enhancements

-   Takeaway and delivery workflows.
-   Multiple payment methods, QR payments, card payments, refunds, and
    split payments.
-   Menu variants, sizes, add-ons, and modifiers.
-   Inventory and ingredient tracking.
-   Discounts, taxes, receipts, loyalty points, and customer accounts.
-   Multi-branch restaurant support.

Do not implement later enhancements unless requested or necessary for
the current task.

## 4. Core Business Rules

-   The backend is the source of truth for prices, totals, order status,
    and payment status.
-   Never trust client-supplied prices or totals. Re-read current menu
    prices from the database when creating or updating an order.
-   Create an order and its order items atomically in one database
    transaction.
-   Store the product name and unit price snapshots on each order item
    so historical receipts do not change when menu items are renamed or
    repriced.
-   Use `NUMERIC(12,2)` (Java `BigDecimal`) for monetary values. Do not
    use floating-point types for money.
-   Quantity must be a positive integer. Prices and monetary totals must
    not be negative.
-   Order status and payment status are separate state machines.
-   Food preparation may begin before payment. Do not automatically mark
    an order as paid when it becomes ready or served.
-   Calculate payment status on the backend from persisted successful
    payments and successful refunds.
-   Prevent duplicate payment processing and handle concurrent payment
    requests safely.
-   Do not physically delete orders, payments, or audit history. Use
    cancellation, reversal, or refund records where appropriate.
-   Store timestamps consistently, preferably as `TIMESTAMPTZ` in
    PostgreSQL and `Instant`/`OffsetDateTime` in Java, following
    existing project conventions.
-   Group reports by the restaurant's configured business time zone.
-   Track who created orders and who changed statuses or received
    payments.
-   Validate allowed state transitions in the service layer, not only in
    the UI.

### Suggested order status lifecycle

`PENDING -> CONFIRMED -> PREPARING -> READY -> SERVED -> COMPLETED`

Cancellation may be allowed only from configured states. Define and test
transition rules rather than allowing arbitrary status changes. If the
restaurant needs multiple kitchen stations or independently prepared
items, treat item-level kitchen status as authoritative and derive the
overall kitchen progress from the items.

### Suggested order item kitchen statuses

`PENDING -> PREPARING -> READY -> SERVED`

Allow `CANCELLED` only according to business rules. Record status
transitions in history tables.

### Suggested payment status rules

-   `UNPAID`: no successful payment has been recorded.
-   `PARTIALLY_PAID`: successful payment total is greater than zero but
    below the amount due.
-   `PAID`: successful net payment covers the amount due.
-   `PARTIALLY_REFUNDED`: a portion of captured payment value has been
    refunded.
-   `REFUNDED`: all applicable captured payment value has been refunded.

Specify how overpayments, payment failures, cancelled items, and refunds
are handled. A payment record's status and the order's aggregate payment
status are not interchangeable.

## 5. Database Design

Use `BIGINT` identity primary keys unless the existing project uses
another established ID strategy. Add foreign keys, `NOT NULL`
constraints, unique constraints, check constraints, and indexes where
appropriate.

### 5.1 `menu_categories`

-   `id BIGINT PRIMARY KEY`
-   `name VARCHAR(100) NOT NULL`
-   `description TEXT NULL`

### 5.2 `menu_items`

-   `id BIGINT PRIMARY KEY`
-   `category_id BIGINT NOT NULL REFERENCES menu_categories(id)`
-   `name VARCHAR(150) NOT NULL`
-   `description TEXT NULL`
-   `price NUMERIC(12,2) NOT NULL CHECK (price >= 0)`
-   `image_url TEXT NULL`
-   `is_available BOOLEAN NOT NULL DEFAULT TRUE`

### 5.3 `restaurant_tables`

-   `id BIGINT PRIMARY KEY`
-   `table_number VARCHAR(20) NOT NULL UNIQUE`
-   `capacity INTEGER NOT NULL CHECK (capacity > 0)`
-   `status VARCHAR(20) NOT NULL`

Suggested table statuses: `AVAILABLE`, `OCCUPIED`, `RESERVED`,
`OUT_OF_SERVICE`. Do not use a table's current status as the only source
of truth for order history.

### 5.4 `users`

Adapt this table to the existing authentication model. Do not create a
second user system if one already exists.

Suggested fields: - `id BIGINT PRIMARY KEY` -
`full_name VARCHAR(150) NOT NULL` -
`username VARCHAR(100) NOT NULL UNIQUE` -
`password_hash TEXT NOT NULL` - `role VARCHAR(30) NOT NULL` for a simple
MVP - `is_active BOOLEAN NOT NULL DEFAULT TRUE` 

Suggested roles: `ADMIN`, `CASHIER`, `WAITER`, `KITCHEN`. If the project
supports normalized roles and permissions, use that model rather than a
role string.

### 5.5 `orders`

-   `id BIGINT PRIMARY KEY`
-   `order_number VARCHAR(40) NOT NULL UNIQUE`
-   `table_id BIGINT NULL REFERENCES restaurant_tables(id)`
-   `created_by BIGINT NOT NULL REFERENCES users(id)`
-   `order_type VARCHAR(20) NOT NULL` --- e.g. `DINE_IN`, `TAKEAWAY`,
    `DELIVERY`
-   `order_status VARCHAR(30) NOT NULL`
-   `payment_status VARCHAR(30) NOT NULL`
-   `subtotal NUMERIC(12,2) NOT NULL CHECK (subtotal >= 0)`
-   `discount_amount NUMERIC(12,2) NOT NULL DEFAULT 0 CHECK (discount_amount >= 0)`
-   `tax_amount NUMERIC(12,2) NOT NULL DEFAULT 0 CHECK (tax_amount >= 0)`
-   `total_amount NUMERIC(12,2) NOT NULL CHECK (total_amount >= 0)`
-   `notes TEXT NULL`
-   `completed_at TIMESTAMPTZ NULL`
-   `cancelled_at TIMESTAMPTZ NULL`

For `DINE_IN`, validate whether a table is required. For takeaway or
delivery, `table_id` can be null. Add a customer or delivery-address
model only when those flows are implemented.

### 5.6 `order_items`

-   `id BIGINT PRIMARY KEY`
-   `order_id BIGINT NOT NULL REFERENCES orders(id)`
-   `menu_item_id BIGINT NULL REFERENCES menu_items(id)`
-   `item_name VARCHAR(150) NOT NULL` --- immutable order-time snapshot
-   `quantity INTEGER NOT NULL CHECK (quantity > 0)`
-   `unit_price NUMERIC(12,2) NOT NULL CHECK (unit_price >= 0)` ---
    order-time snapshot
-   `line_total NUMERIC(12,2) NOT NULL CHECK (line_total >= 0)`
-   `special_instructions TEXT NULL`
-   `kitchen_status VARCHAR(20) NOT NULL`

Allow `menu_item_id` to become null only if the product may be
archived/deleted under an approved retention policy. Prefer deactivating
menu items rather than deleting them. Calculate line totals on the
backend.

### 5.7 `payments`

-   `id BIGINT PRIMARY KEY`
-   `order_id BIGINT NOT NULL REFERENCES orders(id)`
-   `amount NUMERIC(12,2) NOT NULL CHECK (amount > 0)`
-   `payment_method VARCHAR(20) NOT NULL` --- e.g. `CASH`, `CARD`, `QR`,
    `OTHER`
-   `payment_status VARCHAR(20) NOT NULL` --- e.g. `PENDING`,
    `COMPLETED`, `FAILED`, `REVERSED`
-   `transaction_reference VARCHAR(150) NULL`
-   `received_by BIGINT NULL REFERENCES users(id)`
-   `paid_at TIMESTAMPTZ NULL`

Add idempotency support for payment endpoints when appropriate. Do not
store card numbers, security codes, or other sensitive payment
credentials.

### 5.8 `order_status_history`

-   `id BIGINT PRIMARY KEY`
-   `order_id BIGINT NOT NULL REFERENCES orders(id)`
-   `old_status VARCHAR(30) NULL`
-   `new_status VARCHAR(30) NOT NULL`
-   `changed_by BIGINT NULL REFERENCES users(id)`
-   `changed_at TIMESTAMPTZ NOT NULL`
-   `note TEXT NULL`

Insert history rows in the same transaction as the status update.

### 5.9 `order_item_status_history`

-   `id BIGINT PRIMARY KEY`
-   `order_item_id BIGINT NOT NULL REFERENCES order_items(id)`
-   `old_status VARCHAR(20) NULL`
-   `new_status VARCHAR(20) NOT NULL`
-   `changed_by BIGINT NULL REFERENCES users(id)`
-   `changed_at TIMESTAMPTZ NOT NULL`
-   `note TEXT NULL`

Insert history rows in the same transaction as the item status update.

### Optional tables

Add only when needed: - `roles` and `permissions` for normalized access
control. - `payment_refunds` for refund transactions and auditability. -
`menu_item_variants` and `order_item_options` for sizes, add-ons, and
modifiers. - `customers` and delivery-address tables for
customer/delivery workflows. - `restaurant_branches` if multiple
branches are required. - `order_events` or an outbox table if reliable
event publication is needed.

## 6. Table Relationships

  -----------------------------------------------------------------------------------------------------------------
  Parent                Child                         Cardinality       Foreign key
  --------------------- ----------------------------- ----------------- -------------------------------------------
  `menu_categories`     `menu_items`                  One-to-many       `menu_items.category_id`

  `restaurant_tables`   `orders`                      One-to-many over  `orders.table_id`
                                                      time              

  `users`               `orders`                      One-to-many       `orders.created_by`

  `orders`              `order_items`                 One-to-many       `order_items.order_id`

  `menu_items`          `order_items`                 One-to-many over  `order_items.menu_item_id`
                                                      time              

  `orders`              `payments`                    One-to-many       `payments.order_id`

  `users`               `payments`                    One-to-many       `payments.received_by`

  `orders`              `order_status_history`        One-to-many       `order_status_history.order_id`

  `users`               `order_status_history`        One-to-many       `order_status_history.changed_by`

  `order_items`         `order_item_status_history`   One-to-many       `order_item_status_history.order_item_id`

  `users`               `order_item_status_history`   One-to-many       `order_item_status_history.changed_by`
  -----------------------------------------------------------------------------------------------------------------

The many-to-many relationship between orders and menu items is resolved
by `order_items`, which stores quantity, order-time name, unit price,
line total, and kitchen status.

## 7. API Design

Follow the project's existing endpoint naming, response envelope, error
format, validation, and pagination conventions. Suggested endpoints:

### Menu

-   `GET /api/menu/categories`
-   `GET /api/menu/items`
-   `POST /api/menu/categories`
-   `POST /api/menu/items`
-   `PATCH /api/menu/items/{id}`
-   `PATCH /api/menu/items/{id}/availability`

### Restaurant tables

-   `GET /api/restaurant-tables`
-   `POST /api/restaurant-tables`
-   `PATCH /api/restaurant-tables/{id}`

### Orders

-   `POST /api/orders`
-   `GET /api/orders/{id}`
-   `GET /api/orders` with status, date, and pagination filters
-   `PATCH /api/orders/{id}/status`
-   `PATCH /api/order-items/{id}/kitchen-status`
-   `GET /api/kitchen/orders`

### Payments

-   `POST /api/orders/{id}/payments`
-   `GET /api/orders/{id}/payments`
-   Add refund endpoints only when refund handling is implemented.

### Reports

-   `GET /api/reports/sales?period=DAILY&date=YYYY-MM-DD`
-   `GET /api/reports/sales?period=MONTHLY&month=YYYY-MM`
-   `GET /api/reports/sales?period=YEARLY&year=YYYY`
-   `GET /api/reports/payment-summary`
-   `GET /api/reports/best-selling-items`

Use DTOs for requests and responses. Do not expose JPA entities directly
from controllers. Validate request DTOs using Bean Validation and return
consistent errors.

## 8. Frontend Screens

Implement screens consistent with the existing UI and responsive
behavior:

1.  Login.
2.  Dashboard summary.
3.  Menu category and item management.
4.  Restaurant table management.
5.  Create order / POS screen.
6.  Order list and order detail.
7.  Kitchen display with clear status columns.
8.  Payment and receipt screen.
9.  Sales reports with daily, monthly, and yearly filters.
10. Staff management, subject to permissions.

Keep UI state separate from server-authoritative business data. Disable
actions the current user cannot perform, but enforce all permissions in
the backend as well.

## 9. Reporting Rules

Define report metrics explicitly: - Gross order sales: qualifying order
totals before refunds, according to the chosen business rule. - Net
sales: qualifying sales minus discounts and refunds as defined by the
business. - Collections: successful payments, grouped by payment date
and method. - Best-selling items: sum of quantities from qualifying
order items. - Order counts: group by order creation or completion date,
explicitly documenting the choice.

Do not mix order totals with payment collections. Use the restaurant's
configured time zone to determine business dates. Exclude cancelled
orders from sales unless a report explicitly needs cancellation data.
Avoid double-counting orders when joining multiple one-to-many tables;
aggregate each source appropriately.

## 10. Security, Reliability, and Data Integrity

-   Use the existing authentication and authorization approach.
-   Hash passwords with a secure password-hashing mechanism; never store
    plain-text passwords.
-   Enforce role permissions on the backend.
-   Validate all inputs and all status transitions.
-   Use database transactions for order creation, status updates plus
    history, and payment updates.
-   Use optimistic locking or suitable locking where concurrent
    edits/payments could cause incorrect totals.
-   Use database constraints for unique order numbers and valid numeric
    values.
-   Add indexes for common lookups, including order creation time, order
    status, payment status, foreign keys, and order-item order IDs.
-   Do not hardcode secrets or credentials.
-   Do not log passwords, payment credentials, or sensitive customer
    data.
-   Use migrations (Flyway or the project's existing migration tool)
    rather than relying on automatic schema changes in production.
-   Use UTC or the project's established timestamp policy consistently.
-   Add audit history for important actions.
-   Keep payment processing idempotent where external providers or
    retryable requests are involved.

## 11. Suggested Implementation Phases

Implement one phase at a time. Finish, test, and summarize each phase
before moving to the next. Do not implement all phases in one large
change.

### Phase 0 --- Repository analysis

-   Inspect the repository, current architecture, dependencies, naming
    conventions, and existing reusable components.
-   Identify what already exists and what is missing.
-   Report a concise implementation plan.
-   Do not modify files until the repository analysis is complete.

### Phase 1 --- Database and entities

-   Add migrations for core tables.
-   Implement JPA entities and relationships.
-   Add repositories.
-   Add indexes and constraints.
-   Verify migrations against PostgreSQL.
-   Do not build all UI screens in this phase.

### Phase 2 --- Menu management

-   Implement category and menu-item APIs, DTOs, validation, services,
    and tests.
-   Add basic menu administration UI if frontend is in scope.

### Phase 3 --- Order creation

-   Implement order creation and retrieval.
-   Recalculate prices and totals on the server.
-   Persist order and items atomically.
-   Add order status history.
-   Test invalid items, unavailable products, invalid quantities, and
    total calculations.

### Phase 4 --- Kitchen display

-   Implement kitchen queue APIs and item status transitions.
-   Add the kitchen screen.
-   Record every status change.
-   Add polling or the project's existing real-time mechanism.

### Phase 5 --- Payment

-   Implement payment recording and order payment-status calculation.
-   Handle partial payment and failed payment safely.
-   Add duplicate-request and concurrency tests.
-   Add refunds only if explicitly in scope.

### Phase 6 --- Reports

-   Implement daily, monthly, and yearly reports.
-   Add payment summaries and best-selling products.
-   Test date boundaries, time zones, cancellations, refunds, and joins
    that could duplicate totals.

### Phase 7 --- Security and hardening

-   Verify role permissions, input validation, error handling, audit
    history, database constraints, and automated tests.
-   Review logging and configuration for secrets.
-   Document setup and API behavior.

## 12. Coding Agent Operating Rules

For every task: 1. Read the relevant existing files before editing. 2.
Identify the smallest safe implementation that meets the requested
requirement. 3. Reuse existing classes, utilities, patterns, and
components. 4. Do not rename or refactor unrelated code. 5. Do not
invent requirements. If a business rule is unclear and materially
affects data integrity, ask a concise question or document a safe
assumption. 6. Keep changes scoped to the current phase. 7. Add or
update tests for business logic and edge cases. 8. Run the relevant
tests and build commands where available. 9. Fix regressions caused by
the changes. 10. Summarize files changed, behavior added, tests run,
results, and remaining issues. 11. Do not claim tests passed if they
were not executed. 12. If a command cannot run because of missing
services or environment variables, report the exact blocker.

## 13. Definition of Done

A task is complete only when: - The implementation follows existing
project conventions. - Request validation and error handling are
included. - Authorization is enforced where required. - Database
relationships and constraints are correct. - Business rules are enforced
on the backend. - Relevant tests are added and pass, or blockers are
clearly reported. - No unrelated features or files are changed. - Setup
or API documentation is updated when necessary. - The final response
includes a concise summary and test results.
