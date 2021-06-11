Feature: Using Selenium

  Scenario Template: user opens film list, opens list of actors from film
    Given user goes to the site
    When user clicks on "<entity>" button
    And user clicks on show actors of film <id>
    Then "<landing>" page opens
    And one or more actors displayed

    Examples:
      |entity|id |landing|
      |films| 2   | films/actors|
      |films| 14  | films/actors |
      |films| 37  | films/actors |
