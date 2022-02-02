Feature: Delete Employee

  @Scenario1
  Scenario: delete valid employee
    When Create delete request: 2
    Then Response status is 200 OK
    And The response returns message: Successfully! Record has been deleted
#    When Get request on v1/employee for id: 2
#    Then Response status is 404 Not found

  @Scenario2
  Scenario: delete request with invalid id
    When Create delete request: invalidID
#    Then Response status is 400 Bad Request

  @Scenario3
  Scenario: delete request for an id that does not exits
    When Create delete request: 123123213
#    Then Response status is 404 Not found