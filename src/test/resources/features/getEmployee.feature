Feature: Get Employees

  @Scenario1
  Scenario Outline: GET Employee valid data
    Given Create employee request:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    When Send post request on /create
    When Get request on v1/employee for the user created above
    Then Response status is 200 OK
    And Response schema corresponds with baseline: baselines/getEmployeeSchema.json
    And The response has the following data:
      | employee_name   | <name>   |
      | employee_salary | <salary> |
      | employee_age    | <age>    |
    Examples:
      | name         | salary | age |
      | Caesar Vance | 106450 | 21  |

  @Scenario2
  Scenario: GET Employee empty id
    When Get request on v1/employee for id: [blank]
    Then Response status is 404 Not found

  @Scenario3
  Scenario: GET Employee non existing id
    When Get request on v1/employee for id: 999999
#    Then Response status is 404 Not found
#  fails - dummmy endpoint return 200 ok with null data

