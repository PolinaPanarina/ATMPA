Feature: User Login
  As a registered user
  I want to log in to the RP system
  So that I can access my account

  Scenario: Valid login credentials
    Given I am on the login page
    When I enter valid credentials:
      | username | USER_NAME_PROPERTY |
      | password | PASSWORD_PROPERTY  |
    And I click the login button
    Then I should be logged in successfully

  Scenario Outline: Invalid login credentials
    Given I am on the login page
    When I enter invalid credentials "<username>" and "<password>"
    And I click the login button
    Then I shouldn't be logged to the system
    Examples:
      | username | password |
      | A        | B        |
      | C        | D        |
      | B        | Y        |