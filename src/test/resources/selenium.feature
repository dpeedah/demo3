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


  Scenario Template: user edits actor firstname
    Given user goes to the site
    When user clicks on actor button
    And user clicks edit on actor with id <id>
    And user enters "<firstname>" into firstname textbox and clicks submits
    Then the firstname for actor <id> is updated to "<firstname>"

    Examples:
      |firstname|id|
      |harry|1 |
      |peedah|2|
