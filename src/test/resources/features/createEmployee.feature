Feature: create Employees

  @Scenario1
  Scenario Outline: post Employee valid data
    Given Create employee request:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    When Send post request on /create
    Then Response status is 200 OK
    And The response has the following data:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    And Get request on v1/employee for the user created above
    #additional assertion because of endpoint instability - frequenst 429
    Then Response status is 200 OK
    And The response has the following data:
      | employee_name   | <name>   |
      | employee_salary | <salary> |
      | employee_age    | <age>    |
    Examples:
      | name         | salary | age |
      | Caesar Vance | 106450 | 21  |

  @Scenario2
  Scenario: post Employee missing parameters from body
    Given Create employee request:
      | salary | 133 |
      | age    | 18  |
    When Send post request on /create
#    Then Response status is 400 - negative scenarion not handled on mockapi

  @Scenario3
  Scenario: post Employee empty body
    Given Create empty request:
     When Send post request on /create
#    Then Response status is 400 - negative scenarion not handled on mockapi