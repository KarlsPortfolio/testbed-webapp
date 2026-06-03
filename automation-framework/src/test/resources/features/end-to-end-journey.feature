Feature: End to end journey

@smoke @critical-path
Scenario: Successful checkout journey of multiple products (Happy Path)
Given I am securely logged into the e-store application
And I have a completely empty shopping cart
When I add the following books to my cart:
| bookTitle                                            | quantity |
| Hidden Letters                                       | 1        |
| C++: The Complete Reference                          | 3        |
| Designing Data-Intensive Applications: The Big Guide | 1        |
And I am proceeding through the checkout process
And I complete the shipping form using a valid profile
And I finalize the transaction by placing the order
Then I should be redirected to the order confirmation summary page