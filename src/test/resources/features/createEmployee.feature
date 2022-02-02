Feature: create Employees

  @Scenario1
  Scenario Outline: post Employee valid data
    Given Create post request:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    When Send post request on /create
    Then Response status is 200 OK
    And The response has the following data:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    Examples:
      | name         | salary | age |
      | Hunor Morocz | 999000 | 18  |

  @Scenario2
  Scenario: post Employee missing parameters from body
    Given Create post request:
      | salary | 133 |
      | age    | 18    |
    When Send post request on /create
#    Then Response status is 400 - negative scnearion not handled on mockapi
