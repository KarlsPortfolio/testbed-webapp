@regression @checkout
Feature: Checkout Input Validation
  As an authenticated e-store customer
  I want my shipping information validated during checkout
  So that I can prevent shipping errors and ensure accurate order delivery

  Background:
    Given I am securely logged into the e-store application
    And I have the following items in my shopping cart:
      | bookTitle                | quantity | price  |
      | The Silent River           | 1        | $12.99 |
      | C++: The Complete Reference          | 2        | $33.20 |
    And I am proceeding through the checkout process

  @atomic @validation
  Scenario Outline: Verify descriptive mandatory field validation on shipping form
    When I submit the shipping form with a missing "<missingField>"
    Then the checkout submission should be blocked
    And the system should flag the "<missingField>" with the message "<expectedError>"

    Examples:
      | missingField | expectedError                          | description                               |
      | Full-Name    | Enter a valid full name.               | Verifies empty name field constraint      |
      | Email        | Enter a valid email address.           | Verifies empty name field constraint      |
      | Address      | Address must be at least 6 characters. | Verifies empty street address constraint  |
      | Zip-Code     | Zip code must be exactly 5 digits.     | Verifies empty zip/postal code constraint |
      | City         | Enter a valid city.                    | Verifies empty city field constraint      |
      | Credit-Card  | Card number must be 16 digits (####-####-####-####).         | Verifies empty city field constraint      |
      | CVV          | CVV must be exactly 3 digits.          | Verifies empty city field constraint      |