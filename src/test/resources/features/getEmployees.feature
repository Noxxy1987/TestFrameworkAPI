Feature: Get List of Employees

  @Scenario1
  Scenario: GET Employees endpoint
    When Get request on v1/employees
    Then Response status is 200 OK
    And Response headers are returned:
      | Cache-Control          | no-cache, private, max-age=31536000 |
      | Content-Encoding       | gzip                                |
      | Content-Length         | 631                                 |
      | Content-Type           | application/json                    |
      | Date                   | Tue, 01 Feb 2022 08:03:14 GMT       |
      | Display                | staticcontent_sol                   |
      | Expires                | Wed, 01 Feb 2023 08:03:14 GMT       |
      | Host-Header            | c2hhcmVkLmJsdWVob3N0LmNvbQ==        |
      | Referrer-Policy        | [blank]                             |
      | Response               | 200                                 |
      | Server                 | nginx                               |
      | Vary                   | Accept-Encoding,User-Agent,Origin   |
      | X-Ezoic-Cdn            | Miss                                |
      | X-Middleton-Display    | staticcontent_sol                   |
      | X-Middleton-Response   | 200                                 |
      | X-Origin-Cache-Control | no-cache, private                   |
      | X-Ratelimit-Limit      | 60                                  |
      | X-Ratelimit-Remaining  | 58                                  |
      | X-Server-Cache         | false                               |
      | X-Sol                  | pub_site                            |
    And Response schema corresponds with baseline: baselines/getEmployeesSchema.json
    And Response body contains expected number of employees: 24
