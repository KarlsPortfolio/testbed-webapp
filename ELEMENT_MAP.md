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



\## 3. Advanced Checkout Form

| Element Name | Selector (data-testid) | Type | Purpose |

| :--- | :--- | :--- | :--- |

| Profile Checkbox | `use-profile-chk` | Checkbox | Auto-fills form with profile data |

| Shipping Name | `shipping-name` | Input | Manual entry / Validation test |

| Shipping Email | `shipping-email` | Input | Regex validation test |

| Card Number | `card-number` | Input | 16-digit constraint test |

| Expiry Date | `card-expiry` | Input | Format (MM/YY) test |

| CVV Field | `card-cvv` | Input | 3-digit numeric test |

| Field Error | `field-error` | Text | Specific error message per field |

| Place Order | `place-order-btn` | Button | Enabled only when form is valid |



\## 4. Async \& Post-Checkout

| Element Name | Selector (data-testid) | Type | Purpose |

| :--- | :--- | :--- | :--- |

| Loading Spinner | `loading-spinner` | Overlay | Active for 2.5 seconds |

| Order Success | `order-success-msg` | Text | Verify page transition |

| Order ID | `order-id-display` | Text | Capture dynamic ID for assertion |

| Inbox Link | `inbox-tab` | Link | View internal notifications |

