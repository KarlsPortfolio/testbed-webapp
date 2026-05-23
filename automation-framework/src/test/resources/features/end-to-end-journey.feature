@smoke @critical-path
Scenario: Successful checkout journey of multiple products (Happy Path)
Given I am securely logged into the e-store application
And I have a completely empty shopping cart
When I add the following books to my cart:
| bookTitle                                            | quantity |
| Hidden Letters                                       | 1        |  # Standard title
| C++: The Complete Reference                          | 3       |  # Special characters + higher quantity
| Designing Data-Intensive Applications: The Big Guide | 1       |  # Long boundary title
And I proceed to the checkout portal
And I complete the shipping form using the "validCustomer" profile
And I finalize the transaction by placing the order
Then I should be redirected to the order confirmation summary page