@regression @authentication
Feature: User Authentication Portal
  As an e-store customer
  I want to log into my account
  So that I can access my personal dashboard and cart

  Testscenario 1: Log in with correct username and password
  Testscenario 2: Log in with incorrect username and correct password
  Testscenario 3: Log in with correct username and incorrect password
  Testscenario 4: Log in with incorrect username and incorrect password

  @smoke @atomic
  Scenario: Log in as a valid user
    Given I am on the login page "<page>"
    When I login with valid credentials "validUser" "validPassword"
    Then I should be redirected to landing page
    And the logout button should be displayed


  Scenario Outline: Log in with invalid credentials
    Given I am on the login page
    When I attempt to log in with credentials "<username>" "<password>"
    Then I should see error message stating "Invalid credentials."


    Examples:
      |username         |password       |description              |
      |validUser        |wrongPassword  |incorrect password       |
      |invalidUser      |validPassword  |incorrect username       |
      |nonExistentUser  |expiredPassword|both fields incorrect    |
      |                 |validPassword  |missing username edgecase|




  @smoke @atomic
  Scenario: Successful logout and session destruction
    Given I am securely logged into the e-store application
    When I click the logout button
    Then the login button should be displayed
    And the logout button should not be displayed





