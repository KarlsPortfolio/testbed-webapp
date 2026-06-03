@regression @cart
Feature: Cart Management Operations
  As an e-store customer
  I want to manage items in my shopping cart
  So that I can review and adjust my order details before checkout

  Background:
    Given I am securely logged into the e-store application
    And I am viewing the book catalog

  @smoke @atomic
  Scenario Outline: Add distinct book types to the cart to verify locator resilience
    When I add the book "<bookTitle>" to my cart
    Then my shopping cart badge should display "1"

    Examples:
      | bookTitle                                              | description                      |
      | Hidden Letters                                       | Baseline alphanumeric title      |
      | C++: The Complete Reference                            | Special characters & punctuation |
      | Designing Data-Intensive Applications: The Big Guide   | Extreme string boundary length   |

  @atomic
  Scenario: Remove a book from the cart
    Given I have the book "C++: The Complete Reference" in my cart
    When I remove the book "C++: The Complete Reference" from my cart
    Then my shopping cart should be completely empty

  @atomic
  Scenario Outline: Dynamic subtotal calculation when updating item quantities
    Given I have the following item in my cart:
      | bookTitle   | unitPrice   | quantity   |
      | <bookTitle> | <unitPrice> | <startQty> |
    When I change the quantity of "<bookTitle>" to "<newQty>"
    Then my cart subtotal should be "<expectedTotal>"

    Examples:
      | bookTitle                   | unitPrice | startQty | newQty | expectedTotal |
      | C++: The Complete Reference | $33.20    | 1        | 3      | $99.60         |
      | The Silent River            | $12.99    | 1        | 2      | $25.98         |

  @atomic
  Scenario: Clear the entire cart contents
    Given the following items are in my cart:
      | bookTitle                | quantity  |
      | The Silent River           | 1 |
      | C++: The Complete Reference  | 2 |
      | Moonlit Stories       | 3 |
    When I clear all items from my cart
    Then my shopping cart should be completely empty