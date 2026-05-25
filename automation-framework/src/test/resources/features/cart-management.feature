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
      | Stars and Beyond                                       | Baseline alphanumeric title      |
      | C++: The Complete Reference                            | Special characters & punctuation |
      | Designing Data-Intensive Applications: The Big Guide   | Extreme string boundary length   |

  @atomic
  Scenario: Remove a book from the cart
    Given I have the book "Introduction to Java" in my cart
    When I remove the book "Introduction to Java" from my cart
    Then my shopping cart should be completely empty

  @atomic
  Scenario: Adjust item quantity within the cart
    Given I have the book "Introduction to Java" in my cart
    When I change the quantity of "Introduction to Java" to "3"
    Then my cart subtotal should dynamically update for "3" items

  @atomic
  Scenario: Clear the entire cart contents
    Given I have multiple books in my shopping cart
    When I clear all items from my cart
    Then my shopping cart should be completely empty