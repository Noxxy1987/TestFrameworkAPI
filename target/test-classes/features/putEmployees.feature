Feature: Put Employees

  @Scenario1
  Scenario: PUT Employee endpoint
    When Put request on v1/update with ID : 2
    Then Response code is 200

