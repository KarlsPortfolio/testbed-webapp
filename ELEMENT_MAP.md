\# Bookstore Technical Map (V1.1 - Advanced)



\## 1. Authentication Layer

| Element Name | Selector (data-testid) | Type | Purpose |

| :--- | :--- | :--- | :--- |

| Login Username | `login-username` | Input | Enter 'testuser' |

| Login Password | `login-password` | Input | Enter 'password123' |

| Login Submit | `login-submit` | Button | Triggers authentication |

| Logout Button | `logout-btn` | Button | Clears session/localStorage |

| User Greeting | `user-greeting` | Text | Displays "Welcome, Test User" |



\## 2. Product Detail Page (PDP)

| Element Name | Selector (data-testid) | Type | Purpose |

| :--- | :--- | :--- | :--- |

| Product Title | `pdp-title` | Text | Verify correct book is opened |

| Stock Level | `pdp-stock-count` | Text | Verify remaining inventory |

| Quantity Input | `pdp-qty-input` | Input | Test boundary values (1 to N) |

| Add to Cart | `pdp-add-to-cart` | Button | Check if disabled for 0 stock |

| Stock Error | `stock-error-msg` | Text | Appears when Qty > Stock |



\## 3. Checkout Form (`#checkout-form`)

| Element Name | Input ID | Error Boundary ID | Selector (data-testid) | Type | Purpose |

| :--- | :--- | :--- | :--- | :--- | :--- |

| Full Name | `full-name` | `full-name-error` | `full-name-input` / `full-name-error` | Input | Min 2 characters |

| Email | `email` | `email-error` | `email-input` / `email-error` | Input | Email regex validation |

| Address | `address` | `address-error` | `address-input` / `address-error` | Input | Min 6 characters |

| City | `city` | `city-error` | `city-input` / `city-error` | Input | Min 2 characters |

| Zip Code | `zip-code` | `zip-code-error` | `zip-code-input` / `zip-code-error` | Input | Exactly 5 numeric digits |

| Credit Card | `credit-card` | `credit-card-error` | `credit-card-input` / `credit-card-error` | Input | 16 digits, auto-formatted as `####-####-####-####` (max 19 chars with hyphens) |

| CVV | `cvv` | `cvv-error` | `cvv-input` / `cvv-error` | Input | Exactly 3 numeric digits |

| Place Order | — | — | `place-order-btn` | Button | Enabled only when form is valid |



\## 4. Async \& Post-Checkout

| Element Name | Selector (data-testid) | Type | Purpose |

| :--- | :--- | :--- | :--- |

| Loading Spinner | `loading-spinner` | Overlay | Active for 2.5 seconds |

| Order Success | `order-success-msg` | Text | Verify page transition |

| Order ID | `order-id-display` | Text | Capture dynamic ID for assertion |

| Inbox Link | `inbox-tab` | Link | View internal notifications |

