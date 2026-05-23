const books = [
  { id: 1, title: "The Silent River", author: "Maya Collins", price: 12.99, genre: "Fiction", stock: 5 },
  { id: 2, title: "JavaScript in Action", author: "Ethan Reid", price: 29.5, genre: "Tech", stock: 1 },
  { id: 3, title: "Stars and Beyond", author: "Nina Holt", price: 18.75, genre: "Science", stock: 0 },
  { id: 4, title: "Hidden Letters", author: "Oliver Dane", price: 10.0, genre: "Fiction", stock: 1 },
  { id: 5, title: "Clean API Design", author: "Priya Nair", price: 33.2, genre: "Tech", stock: 5 },
  { id: 6, title: "The Quantum Trail", author: "Rhea Summers", price: 21.49, genre: "Science", stock: 0 },
  { id: 7, title: "Moonlit Stories", author: "Daniel Frost", price: 14.25, genre: "Fiction", stock: 5 },
  { id: 8, title: "Data Systems Basics", author: "Sofia Chen", price: 26.8, genre: "Tech", stock: 1 }
];

const SESSION_KEY = "bookstore_session_user";
const MESSAGE_KEY = "bookstore_messages";

const state = {
  searchTerm: "",
  selectedGenres: new Set(),
  cart: [],
  messages: [],
  renderTimer: null,
  activeBookId: null,
  currentUser: null,
  currentView: "store",
  pendingProtectedView: null,
  toastTimer: null
};

const formMap = {
  "full-name": "full-name-error",
  "email": "email-error",
  "address": "address-error",
  "city": "city-error",
  "zip-code": "zip-code-error",
  "credit-card": "credit-card-error",
  "cvv": "cvv-error"
};

const storeView = document.getElementById("store-view");
const loginView = document.getElementById("login-view");
const checkoutView = document.getElementById("checkout-view");
const orderSuccessView = document.getElementById("order-success-view");
const messagesView = document.getElementById("messages-view");
const checkoutSummary = document.getElementById("checkout-summary");
const messagesList = document.getElementById("messages-list");
const orderSuccessMessage = document.getElementById("order-success-message");

const checkoutForm = document.getElementById("checkout-form");
const fullNameInput = document.getElementById("full-name");
const emailInput = document.getElementById("email");
const addressInput = document.getElementById("address");
const creditCardInput = document.getElementById("credit-card");
const cvvInput = document.getElementById("cvv");
const placeOrderButton = document.getElementById("place-order-button");
const processingOverlay = document.getElementById("processing-overlay");

const storeTab = document.getElementById("store-tab");
const checkoutTab = document.getElementById("checkout-tab");
const messagesTab = document.getElementById("messages-tab");
const authButton = document.getElementById("auth-button");
const headerUserName = document.getElementById("header-user-name");

const loginForm = document.getElementById("login-form");
const loginUsername = document.getElementById("login-username");
const loginPassword = document.getElementById("login-password");
const loginError = document.getElementById("login-error");

const bookGrid = document.getElementById("book-grid");
const searchInput = document.getElementById("search-input");
const cartIcon = document.getElementById("cart-icon");
const cartCount = document.getElementById("cart-count-badge");
const cartModal = document.getElementById("cart-modal");
const closeCartButton = document.getElementById("close-cart");
const cartItemsContainer = document.getElementById("cart-items");
const cartTotal = document.getElementById("cart-total");
const checkoutButton = document.getElementById("checkout-button");
const searchLoadingSpinner = document.getElementById("search-loading-spinner");
const toastNotification = document.getElementById("toast-notification");

const pdpModal = document.getElementById("pdp-modal");
const closePdpButton = document.getElementById("close-pdp");
const pdpBody = document.getElementById("pdp-body");
const genreCheckboxes = [
  document.getElementById("genre-fiction"),
  document.getElementById("genre-tech"),
  document.getElementById("genre-science")
];

function formatPrice(price) {
  return `$${price.toFixed(2)}`;
}

function getBookById(bookId) {
  return books.find((book) => book.id === bookId);
}

function getCartItem(bookId) {
  return state.cart.find((item) => item.id === bookId);
}

function getCurrentQuantityInCart(bookId) {
  const item = getCartItem(bookId);
  return item ? item.quantity : 0;
}

function getStockErrorContainer() {
  return document.getElementById("stock-error");
}

function showStockError(message) {
  const stockError = getStockErrorContainer();
  if (stockError) {
    stockError.textContent = message;
  }
  const pdpError = pdpBody.querySelector(".pdp-modal__stock-error");
  if (pdpError) {
    pdpError.textContent = message;
  }
}

function clearStockError() {
  const stockError = getStockErrorContainer();
  if (stockError) {
    stockError.textContent = "";
  }
  const pdpError = pdpBody.querySelector(".pdp-modal__stock-error");
  if (pdpError) {
    pdpError.textContent = "";
  }
}

function showToast(message) {
  toastNotification.textContent = message;
  toastNotification.classList.remove("hidden");
  if (state.toastTimer) {
    clearTimeout(state.toastTimer);
  }
  state.toastTimer = setTimeout(() => {
    toastNotification.classList.add("hidden");
  }, 2200);
}

function saveSession() {
  if (state.currentUser) {
    localStorage.setItem(SESSION_KEY, state.currentUser);
  } else {
    localStorage.removeItem(SESSION_KEY);
  }
}

function loadSession() {
  state.currentUser = localStorage.getItem(SESSION_KEY);
}

function saveMessages() {
  localStorage.setItem(MESSAGE_KEY, JSON.stringify(state.messages));
}

function loadMessages() {
  const raw = localStorage.getItem(MESSAGE_KEY);
  if (!raw) {
    state.messages = [];
    return;
  }
  try {
    const parsed = JSON.parse(raw);
    state.messages = Array.isArray(parsed) ? parsed : [];
  } catch (error) {
    state.messages = [];
  }
}

function isAuthenticated() {
  return Boolean(state.currentUser);
}

function updateAuthUi() {
  if (isAuthenticated()) {
    headerUserName.textContent = `Hi, ${state.currentUser}`;
    authButton.textContent = "Logout";
    authButton.dataset.testid = "logout-btn";
  } else {
    headerUserName.textContent = "";
    authButton.textContent = "Login";
    authButton.dataset.testid = "login-btn";
  }
}

function renderMessages() {
  if (state.messages.length === 0) {
    messagesList.innerHTML = '<p class="panel__text" data-testid="messages-empty">No messages yet.</p>';
    return;
  }

  messagesList.innerHTML = state.messages
    .map(
      (message) => `
        <article class="panel__message" data-testid="message-item-${message.id}">
          <p class="panel__message-title">${message.title}</p>
          <p class="panel__message-body">${message.body}</p>
        </article>
      `
    )
    .join("");
}

function renderCheckoutSummary() {
  if (state.cart.length === 0) {
    checkoutSummary.textContent = "Your cart is empty.";
    placeOrderButton.disabled = true;
    return;
  }
  const totalItems = state.cart.reduce((total, item) => total + item.quantity, 0);
  checkoutSummary.textContent = `${totalItems} item(s) in cart. Total: ${formatPrice(getCartTotal())}. Complete Shipping & Payment details to place order.`;
  validateCheckoutForm();
}

function resetCheckoutForm() {
  checkoutForm.reset();
  Object.values(formMap).forEach((id) => {
    const el = document.getElementById(id);
    if (el) {
      el.textContent = "";
    }
  });
  placeOrderButton.disabled = true;
}

function formatCreditCardInput(inputElement) {
  const digits = inputElement.value.replace(/\D/g, "").slice(0, 16);
  const groups = [];
  for (let i = 0; i < digits.length; i += 4) {
    groups.push(digits.slice(i, i + 4));
  }
  inputElement.value = groups.join("-");
}

function validateField(inputElement, showUI = true) {
  const value = inputElement.value.trim();
  const errorId = formMap[inputElement.id];
  let message = "";
  let isValid = true;

  // Updated to match your exact HTML input IDs
  if (inputElement.id === "full-name") {
    if (value.length < 2) { message = "Enter a valid full name."; isValid = false; }
  } else if (inputElement.id === "email") {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) { message = "Enter a valid email address."; isValid = false; }
  } else if (inputElement.id === "address"){
    const addressRegex = /^(?=.*\d)(?=.*[a-zA-Z]).+$/;
    
  if (value.length < 6) { 
    message = "Address must be at least 6 characters."; 
    isValid = false; 
  } else if (!addressRegex.test(value)) {
    message = "Address must include both a street number and name."; 
    isValid = false; 
  }
} else if (inputElement.id === "city") {
    if (value.length < 2) { message = "Enter a valid city."; isValid = false; }
  } else if (inputElement.id === "zip-code") {
    if (!/^\d{5}$/.test(value)) { message = "Zip code must be exactly 5 digits."; isValid = false; }
  } else if (inputElement.id === "credit-card") {
    const cardDigits = value.replace(/\D/g, "");
    if (cardDigits.length !== 16) { message = "Credit card must be 16 digits (####-####-####-####)."; isValid = false; }
  } else if (inputElement.id === "cvv") {
    if (!/^\d{3}$/.test(value)) { message = "CVV must be exactly 3 digits."; isValid = false; }
  }

  // Only update the UI text if showUI is true
  if (showUI) {
    setFieldError(errorId, message);
  }
  
  return isValid;
}



function setFieldError(errorId, message) {
  const errorElement = document.getElementById(errorId);
  if (!errorElement) {
    return;
  }
  errorElement.textContent = message;
}
/*
function validateCheckoutForm() {
  const fullName = fullNameInput.value.trim();
  const email = emailInput.value.trim();
  const address = addressInput.value.trim();
  const creditCard = creditCardInput.value.trim();
  const cvv = cvvInput.value.trim();

  let isValid = true;

  if (fullName.length < 2) {
    setFieldError("full-name-error", "Enter a valid full name.");
    isValid = false;
  } else {
    setFieldError("full-name-error", "");
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    setFieldError("email-error", "Enter a valid email address.");
    isValid = false;
  } else {
    setFieldError("email-error", "");
  }

  if (address.length < 6) {
    setFieldError("address-error", "Address must be at least 6 characters.");
    isValid = false;
  } else {
    setFieldError("address-error", "");
  }

  if (!/^\d{16}$/.test(creditCard)) {
    setFieldError("credit-card-error", "Credit card must be exactly 16 digits.");
    isValid = false;
  } else {
    setFieldError("credit-card-error", "");
  }

  if (!/^\d{3}$/.test(cvv)) {
    setFieldError("cvv-error", "CVV must be exactly 3 digits.");
    isValid = false;
  } else {
    setFieldError("cvv-error", "");
  }

  placeOrderButton.disabled = !isValid || state.cart.length === 0;
  return isValid;
}
*/



function validateCheckoutForm(showErrors = false) {
  let isFormValid = true;

  // Check every field in our map
  Object.keys(formMap).forEach(inputId => {
    const el = document.getElementById(inputId);
    // If showErrors is true, the user sees red text. If false, it's a silent check.
    const isFieldValid = validateField(el, showErrors);
    if (!isFieldValid) isFormValid = false;
  });

  // Enable/Disable button based on validity and cart state
  placeOrderButton.disabled = !isFormValid || state.cart.length === 0;
  return isFormValid;
}

function setupValidationListeners() {
  const inputs = Object.keys(formMap).map((id) => document.getElementById(id));

  inputs.forEach((input) => {
    if (!input) {
      return;
    }

    input.addEventListener("blur", () => {
      validateField(input, true);
      validateCheckoutForm(false);
    });

    input.addEventListener("input", () => {
      if (input.id === "credit-card") {
        formatCreditCardInput(input);
      }

      const errorId = formMap[input.id];
      if (document.getElementById(errorId).textContent !== "") {
        validateField(input, true);
      }

      validateCheckoutForm(false);
    });
  });
}

function setActiveTab() {
  [storeTab, checkoutTab, messagesTab].forEach((tab) => tab.classList.remove("header__nav-btn--active"));
  if (state.currentView === "store") {
    storeTab.classList.add("header__nav-btn--active");
  }
  if (state.currentView === "checkout") {
    checkoutTab.classList.add("header__nav-btn--active");
  }
  if (state.currentView === "messages") {
    messagesTab.classList.add("header__nav-btn--active");
  }
}

function navigateTo(viewName) {
  const protectedViews = new Set(["checkout", "messages"]);
  if (protectedViews.has(viewName) && !isAuthenticated()) {
    state.pendingProtectedView = viewName;
    state.currentView = "login";
    loginError.textContent = `Login required to access ${viewName}.`;
  } else {
    state.currentView = viewName;
    loginError.textContent = "";
  }

  storeView.classList.toggle("hidden", state.currentView !== "store");
  loginView.classList.toggle("hidden", state.currentView !== "login");
  checkoutView.classList.toggle("hidden", state.currentView !== "checkout");
  messagesView.classList.toggle("hidden", state.currentView !== "messages");
  orderSuccessView.classList.toggle("hidden", state.currentView !== "order-success");
  setActiveTab();
  renderCheckoutSummary();
  renderMessages();
  if (state.currentView === "checkout") {
    resetCheckoutForm();
  }
}

function getFilteredBooks() {
  const query = state.searchTerm.trim().toLowerCase();
  return books.filter((book) => {
    const matchesSearch =
      query.length === 0 ||
      book.title.toLowerCase().includes(query) ||
      book.author.toLowerCase().includes(query);
    const matchesGenre =
      state.selectedGenres.size === 0 || state.selectedGenres.has(book.genre);
    return matchesSearch && matchesGenre;
  });
}

function renderBooks() {
  const visibleBooks = getFilteredBooks();
  if (visibleBooks.length === 0) {
    bookGrid.innerHTML = '<p id="no-results-message" data-testid="no-results-message">No books found.</p>';
    return;
  }

  bookGrid.innerHTML = visibleBooks
    .map(
      (book, index) => `
        <article class="book-card ${index % 5 === 0 ? "book-card--wide" : ""}" id="book-card-${book.id}">
          <span class="book-card__genre-tag" data-testid="genre-tag">${book.genre}</span>
          <button
            id="book-title-${book.id}"
            class="book-card__title-button"
            type="button"
            data-book-id="${book.id}"
            data-testid="book-title-${book.id}"
          >
            ${book.title}
          </button>
          <p class="book-card__author">by ${book.author}</p>
          <p class="book-card__price">${formatPrice(book.price)}</p>
          <p class="book-card__stock" data-testid="stock-label-${book.id}">Stock: ${book.stock}</p>
          ${
            book.stock === 0
              ? `
              <button
                id="add-to-cart-${book.id}"
                class="button"
                type="button"
                data-book-id="${book.id}"
                data-testid="add-to-cart-btn-${book.id}"
                disabled
              >
                Out of Stock
              </button>
              <button
                id="alert-me-${book.id}"
                class="button button--secondary"
                type="button"
                data-book-id="${book.id}"
                data-testid="alert-me-btn-${book.id}"
              >
                Alert Me
              </button>
            `
              : `
              <button
                id="add-to-cart-${book.id}"
                class="button button--primary"
                type="button"
                data-book-id="${book.id}"
                data-testid="add-to-cart-btn-${book.id}"
              >
                Add to Cart
              </button>
            `
          }
        </article>
      `
    )
    .join("");
}

function getCartCount() {
  return state.cart.reduce((total, item) => total + item.quantity, 0);
}

function getCartTotal() {
  return state.cart.reduce((total, item) => total + item.price * item.quantity, 0);
}

function updateCartBadge() {
  cartCount.textContent = String(getCartCount());
}

function animateCartBadge() {
  cartCount.classList.add("header__cart-count--flash");
  setTimeout(() => {
    cartCount.classList.remove("header__cart-count--flash");
  }, 350);
}

function validateRequestedQuantity(bookId, incrementBy) {
  const book = getBookById(bookId);
  if (!book) {
    return { valid: false, message: "Book not found." };
  }
  const currentInCart = getCurrentQuantityInCart(bookId);
  const requestedTotal = currentInCart + incrementBy;
  if (book.stock === 0) {
    return { valid: false, message: "This title is currently out of stock." };
  }
  if (requestedTotal > book.stock) {
    return { valid: false, message: `Only ${book.stock} item(s) available for this book.` };
  }
  return { valid: true, message: "" };
}

function addToCart(bookId, quantity = 1) {
  const validation = validateRequestedQuantity(bookId, quantity);
  if (!validation.valid) {
    showStockError(validation.message);
    return false;
  }

  const book = getBookById(bookId);
  const existing = getCartItem(bookId);
  if (existing) {
    existing.quantity += quantity;
  } else {
    state.cart.push({ ...book, quantity });
  }
  clearStockError();
  updateCartBadge();
  animateCartBadge();
  renderCart();
  renderCheckoutSummary();
  renderBooks();
  return true;
}

function updateCartItemQuantity(bookId, requestedQuantity) {
  const book = getBookById(bookId);
  const existing = getCartItem(bookId);
  if (!book || !existing) {
    return;
  }
  if (requestedQuantity <= 0) {
    state.cart = state.cart.filter((item) => item.id !== bookId);
    clearStockError();
  } else if (requestedQuantity > book.stock) {
    showStockError(`Only ${book.stock} item(s) available for "${book.title}".`);
    return;
  } else {
    existing.quantity = requestedQuantity;
    clearStockError();
  }
  updateCartBadge();
  renderCart();
  renderCheckoutSummary();
  renderBooks();
}

function removeFromCart(bookId) {
  state.cart = state.cart.filter((item) => item.id !== bookId);
  clearStockError();
  updateCartBadge();
  renderCart();
  renderCheckoutSummary();
  renderBooks();
}

function renderCart() {
  if (state.cart.length === 0) {
    cartItemsContainer.innerHTML =
      '<p class="cart-modal__empty" id="empty-cart-message" data-testid="empty-cart-message">Your cart is empty.</p>';
  } else {
    cartItemsContainer.innerHTML = state.cart
      .map(
        (item) => `
          <div class="cart-modal__item" id="cart-item-${item.id}">
            <div class="cart-modal__item-info">
              <p class="cart-modal__item-title">${item.title}</p>
              <p class="cart-modal__item-meta">${formatPrice(item.price)} each</p>
            </div>
            <input
              id="cart-qty-${item.id}"
              class="cart-modal__qty-input"
              type="number"
              min="0"
              max="${item.stock}"
              value="${item.quantity}"
              data-book-id="${item.id}"
              data-testid="cart-quantity-input-${item.id}"
            />
            <button
              id="remove-item-${item.id}"
              class="button button--danger"
              type="button"
              data-book-id="${item.id}"
              data-testid="remove-cart-item-${item.id}"
            >
              Remove
            </button>
          </div>
        `
      )
      .join("");
  }
  cartTotal.textContent = formatPrice(getCartTotal());
}

function openCart() {
  cartModal.classList.remove("hidden");
}

function closeCart() {
  cartModal.classList.add("hidden");
}

function openPdp(bookId) {
  const book = getBookById(bookId);
  if (!book) {
    return;
  }
  state.activeBookId = bookId;
  const stockControls =
    book.stock === 0
      ? `
        <button id="pdp-add-to-cart-${book.id}" class="button" type="button" data-book-id="${book.id}" data-testid="pdp-add-to-cart-${book.id}" disabled>
          Out of Stock
        </button>
        <button id="pdp-alert-me-${book.id}" class="button button--secondary" type="button" data-book-id="${book.id}" data-testid="pdp-alert-me-btn-${book.id}">
          Alert Me
        </button>
      `
      : `
        <button id="pdp-add-to-cart-${book.id}" class="button button--primary" type="button" data-book-id="${book.id}" data-testid="pdp-add-to-cart-${book.id}">
          Add to Cart
        </button>
      `;

  pdpBody.innerHTML = `
    <p class="pdp-modal__genre-tag" data-testid="pdp-genre-tag-${book.id}">${book.genre}</p>
    <h3 class="pdp-modal__book-title">${book.title}</h3>
    <p class="pdp-modal__author">by ${book.author}</p>
    <p class="pdp-modal__price">${formatPrice(book.price)}</p>
    <p class="pdp-modal__stock" data-testid="pdp-stock-label-${book.id}">Stock available: ${book.stock}</p>
    <label for="quantity-input" class="pdp-modal__quantity-label">Quantity</label>
    <input
      id="quantity-input"
      class="pdp-modal__quantity-input"
      type="number"
      min="1"
      max="${book.stock}"
      value="1"
      data-testid="quantity-input-${book.id}"
      ${book.stock === 0 ? "disabled" : ""}
    />
    <p class="pdp-modal__stock-error" data-testid="stock-error-${book.id}"></p>
    ${stockControls}
  `;
  pdpModal.classList.remove("hidden");
}

function closePdp() {
  pdpModal.classList.add("hidden");
  pdpBody.innerHTML = "";
  state.activeBookId = null;
}

function runSearchRenderWithLoading() {
  searchLoadingSpinner.classList.remove("hidden");
  if (state.renderTimer) {
    clearTimeout(state.renderTimer);
  }
  state.renderTimer = setTimeout(() => {
    renderBooks();
    searchLoadingSpinner.classList.add("hidden");
  }, 250);
}

function attemptLogin(username, password) {
  if (username === "validUser" && password === "validPassword") {
    state.currentUser = username;
    saveSession();
    updateAuthUi();
    loginError.textContent = "";
    const target = state.pendingProtectedView || "store";
    state.pendingProtectedView = null;
    navigateTo(target);
    showToast("Login successful.");
    return;
  }
  loginError.textContent = "Invalid credentials. Use validUser / validPassword.";
}

function logout() {
  state.currentUser = null;
  saveSession();
  updateAuthUi();
  navigateTo("store");
  showToast("You have been logged out.");
}

function createOrderMessage(orderId) {
  const message = {
    id: Date.now(),
    title: `Order #${orderId} confirmed`,
    body: `Your order ${orderId} was placed successfully and is being prepared.`
  };
  state.messages.unshift(message);
  saveMessages();
  renderMessages();
}

function completeCheckout() {
  closeCart();
  navigateTo("checkout");
}

function submitOrderWithDelay() {
  if (!isAuthenticated()) {
    navigateTo("login");
    return;
  }
  if (state.cart.length === 0) {
    showToast("Your cart is empty.");
    return;
  }
  if (!validateCheckoutForm()) {
    return;
  }

  processingOverlay.classList.remove("hidden");
  setTimeout(() => {
    processingOverlay.classList.add("hidden");
    const orderId = `ORD-${Date.now().toString().slice(-6)}`;
    createOrderMessage(orderId);
    state.cart = [];
    clearStockError();
    updateCartBadge();
    renderCart();
    renderCheckoutSummary();
    renderBooks();
    orderSuccessMessage.textContent = `Order ${orderId} completed successfully. A confirmation message has been added to your inbox.`;
    navigateTo("order-success");
    showToast(`Order successful. Message sent with ID ${orderId}.`);
  }, 2500);
}

searchInput.addEventListener("input", (event) => {
  state.searchTerm = event.target.value;
  runSearchRenderWithLoading();
});

genreCheckboxes.forEach((checkbox) => {
  checkbox.addEventListener("change", () => {
    if (checkbox.checked) {
      state.selectedGenres.add(checkbox.value);
    } else {
      state.selectedGenres.delete(checkbox.value);
    }
    runSearchRenderWithLoading();
  });
});

bookGrid.addEventListener("click", (event) => {
  const titleButton = event.target.closest(".book-card__title-button");
  if (titleButton) {
    openPdp(Number(titleButton.dataset.bookId));
    return;
  }
  const alertButton = event.target.closest("button[id^='alert-me-']");
  if (alertButton) {
    showToast("Success: You will be notified when this book is back in stock.");
    return;
  }
  const addButton = event.target.closest("button[data-book-id]");
  if (!addButton) {
    return;
  }
  addToCart(Number(addButton.dataset.bookId), 1);
});

cartItemsContainer.addEventListener("click", (event) => {
  const removeButton = event.target.closest("button[data-book-id]");
  if (!removeButton) {
    return;
  }
  removeFromCart(Number(removeButton.dataset.bookId));
});

cartItemsContainer.addEventListener("change", (event) => {
  const quantityInput = event.target.closest("input[data-book-id]");
  if (!quantityInput) {
    return;
  }
  updateCartItemQuantity(Number(quantityInput.dataset.bookId), Number(quantityInput.value));
});

pdpBody.addEventListener("click", (event) => {
  const alertButton = event.target.closest("button[id^='pdp-alert-me-']");
  if (alertButton) {
    showToast("Success: We will alert you when this item is restocked.");
    return;
  }
  const addButton = event.target.closest("button[data-book-id]");
  if (!addButton || addButton.disabled) {
    return;
  }
  const quantityField = document.getElementById("quantity-input");
  const quantity = Number(quantityField ? quantityField.value : 1);
  const safeQuantity = Number.isNaN(quantity) ? 1 : quantity;
  if (safeQuantity < 1) {
    showStockError("Quantity must be at least 1.");
    return;
  }
  if (addToCart(Number(addButton.dataset.bookId), safeQuantity)) {
    closePdp();
  }
});

pdpBody.addEventListener("input", (event) => {
  const quantityInput = event.target.closest("#quantity-input");
  if (!quantityInput || state.activeBookId === null) {
    return;
  }
  const requested = Number(quantityInput.value);
  const book = getBookById(state.activeBookId);
  if (!book) {
    return;
  }
  if (requested > book.stock) {
    showStockError(`Only ${book.stock} item(s) available for "${book.title}".`);
  } else {
    clearStockError();
  }
});

storeTab.addEventListener("click", () => navigateTo("store"));
checkoutTab.addEventListener("click", () => navigateTo("checkout"));
messagesTab.addEventListener("click", () => navigateTo("messages"));

authButton.addEventListener("click", () => {
  if (isAuthenticated()) {
    logout();
  } else {
    navigateTo("login");
  }
});

placeOrderButton.addEventListener("click", () => {
  // Force show all errors if they exist
  if (validateCheckoutForm(true)) {
    placeOrderWithDelay();
  }
});

loginForm.addEventListener("submit", (event) => {
  event.preventDefault();
  attemptLogin(loginUsername.value.trim(), loginPassword.value.trim());
});

checkoutForm.addEventListener("submit", (event) => {
  event.preventDefault();
  submitOrderWithDelay();
});
/*
[fullNameInput, emailInput, addressInput, creditCardInput, cvvInput].forEach((input) => {
  input.addEventListener("input", validateCheckoutForm);
});
*/

cartIcon.addEventListener("click", openCart);
closeCartButton.addEventListener("click", closeCart);
closePdpButton.addEventListener("click", closePdp);
checkoutButton.addEventListener("click", completeCheckout);

cartModal.addEventListener("click", (event) => {
  if (event.target === cartModal) {
    closeCart();
  }
});

pdpModal.addEventListener("click", (event) => {
  if (event.target === pdpModal) {
    closePdp();
  }
});


loadSession();
loadMessages();
updateAuthUi();
renderBooks();
renderCart();
renderMessages();
renderCheckoutSummary();
updateCartBadge();
navigateTo("store");

document.addEventListener("DOMContentLoaded", () => {
  setupValidationListeners();
});