Feature: Test logging in to facebook

  @Facebook
  Scenario: Log In To Facebook with no errors
    Given The user is navigated to the Facebook website
    And The user is signed out of their account
    When The user enters a valid email
    And and the user enters a valid password
    And and the user presses the Log In button
    Then The user should be logged in
    And and brought to their feed
