Feature: GET for Films
  Scenario : user makes a call to GET /apis
    When user calls /apis
    Then user receives error of code 200
  Scenario: client makes call to GET api
    When user calls /apis
    Then user receives error of code 200

  Scenario Outline: user makes call to GET film by id api
    When user makes call to GET film by <id> api
    Then user receives response code <code>

    Examples:
      | id  | code|
      | 10  | 202 |
      | 1000|500  |

 # Scenario Outline : A user attempts to add a unique film to the database
   # Given the user provides a valid film "<title>", "<description>", <lengthMinutes>,  and <year> of release
   # And film with title "<title>" is unique
   # And film "<lengthMinutes>", "<description>" and <year> are valid
   # Then user receives error of code 201 in response
   # Then user receives back a film with title "<title>" and description <description>
   # Examples:
    #  | title   | description | lengthMinutes | year |
     # | Dawn of the chickens of the apes | Chickens rule the world?    | 107 | 2007 |
      #| Superman kills again  | Superman got dumped...what now | 156         | 2001 |
      #| FREEEEDOM             | A story of the wills of the dead living on | 199 | 2000|
      #| Utility House         | A smarthouse becomes self-aware             |124  |2007|

 # Scenario Outline : A user creates a film and calls api/films/create to add them to the database
  #  Given the user provides name <name> and last_name <last_name>
   # And user with name <name> and last_name <last_name> does not exist
   # When user calls /apis/create with body of Film
   # Then user receives error of code 201
   # Then user with name <name> and last name <last_name> received
   # Examples:
   #   | name   | last_name |
   #   | daniel | janake    |
   #   | john   | jillenghal|