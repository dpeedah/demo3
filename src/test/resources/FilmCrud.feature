Feature: CRUD for Films
  Scenario : user makes a call to GET /apis
    When user calls /apis
    Then user receives error of code 200
  Scenario: client makes call to GET api
    When user calls /apis
    Then user receives error of code 200