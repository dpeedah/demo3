
Feature: Actor CRUD

  Scenario Outline: user makes call to GET all actors api
    When user makes call to GET all actors api
    Then user receives response code <code>

    Examples:
      | code |
      | 200  |


  Scenario Outline: user makes call to GET actor by id api
    When user makes call to GET actor by <id> api
    Then user receives response code <code>

    Examples:
      | id  | code|
      | 10  | 202 |
      | 1000|500  |

#  Scenario Outline: user adds valid actor to the database
#    Given user provides an actor with a valid and unique "<firstName>" and "<lastName>"
#    Then user receives response code 201
#
#    Examples:
#      | firstName | lastName|
#      | cucumber     | test    |



