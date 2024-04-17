Feature: Dashboard view
  As a registered user
  I want to see Dashboard page
  So that I can interact with it

  Background:
    Given I am logged in to RP with valid credentials
      | username | USER_NAME_PROPERTY |
      | password | PASSWORD_PROPERTY  |
    Then I should see the Dashboard page is opened

  Scenario Outline: Verify dashboard data
    Given I can chose the project
      | projectName | RP_PROJECT_NAME |
    Then I should see that dashboard data is present "<dashboardName>" and "<dashboardOwner>"
    Examples:
      | dashboardName  | dashboardOwner  |
      | dashboardName1 | dashboardOwner1 |
      | dashboardName2 | dashboardOwner2 |
      | dashboardName3 | dashboardOwner2 |

  Scenario Outline: Verify specified dashboard data
    Given I can chose the project
      | projectName | RP_PROJECT_NAME |
    Then I should see the Dashboard page is opened
    When I search "<dashboardName>"
    Then I should see that dashboard data is present in first lines "<dashboardName>" and "<dashboardOwner>"
    Examples:
      | dashboardName  | dashboardOwner  |
      | dashboardName1 | dashboardOwner1 |
      | dashboardName2 | dashboardOwner2 |
      | dashboardName3 | dashboardOwner2 |