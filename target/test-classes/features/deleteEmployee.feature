Feature: Delete Employee

  @Scenario1
  Scenario: delete Employee valid data
    When Create delete request: 2
    Then Response status is 200 OK