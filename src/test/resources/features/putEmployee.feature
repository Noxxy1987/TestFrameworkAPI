Feature: Put Employee

  @Scenario1
  Scenario Outline: PUT Employee endpoint
    Given Create employee request:
      | name   | <name1>   |
      | salary | <salary1> |
      | age    | <age2>    |
    When Send post request on /create
    Given Create employee request:
      | name   | <name2>   |
      | salary | <salary2> |
      | age    | <age3>    |
    When Put request on v1/update for the created resource
    Then Response status is 200 OK
    And Get request on v1/employee for the user created above
        #additional assertion because of endpoint instability - frequenst 429
    Then Response status is 200 OK
    And The response has the following data:
      | employee_name   | <name2>   |
      | employee_salary | <salary2> |
      | employee_age    | <age2>    |
    Examples:
      | name1     | salary1 | age1 | name2        | salary2 | age2 |
      | Joe Pesci | 1       | 23   | Caesar Vance | 106450  | 21   |

  @Scenario2
  Scenario: Put request with missing data
    Given Create employee request:
      | salary | 123 |
      | age    | 34  |
    When Put request on v1/update for the created resource
#    Then Response status is 400 Bad Request

  @Scenario3
  Scenario: Put request with empty bodyu
    Given Create empty request:
    When Put request on v1/update for the created resource
#    Then Response status is 400 Bad Request
