Feature: Test logging in to facebook

  Background:
    Given The user is navigated to the Facebook website

  @Facebook
  Scenario: Log In To Facebook
    Given The user is signed out of their account
    When user enters email and password and logs in
      | fakeemail@fake.com | fakepassword123 |
    Then The user should be logged in
    And and brought to their feed
