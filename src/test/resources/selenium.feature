Feature: Using Selenium

  Scenario Template: user opens lists
    Given user goes to the site
    When user clicks on "<entity>" button
    Then "<entity>" page opens

    Examples:
      |entity|
      |actors|
      |categories|
      |films|
      |invalid|