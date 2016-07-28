Feature: HTML5 Video Player functionality on Youtube

  #Basic Set of Functionality. Video, Autoplay, Play, and Pause
  Background:
    Given user is navigated to a Youtube video
    #And the page loads

  @Youtube
  Scenario: Test that video autoplays
    Then validate the video autoplays

  @Youtube
  Scenario: Test pausing video during playback
    Given the video autoplays
    And user presses the pause button
    Then validate the video is paused

  @Youtube
  Scenario: Test unpausing the video while paused
    Given the video autoplays
    When user presses the pause button
    And user presses the play button
    Then validate the video plays

  @Youtube
  Scenario: Test entering fullscreen
    Given video is not fullscreen
    When user presses the fullscreen button
    Then validate video is fullscreen

  @Youtube
  Scenario: Test exiting fullscreen
    Given user presses the fullscreen button
    And video is fullscreen
    When user presses the fullscreen button
    Then validate video is not fullscreen

  @Youtube
  Scenario: Test entering Theater Mode
    Given player is in Default mode
    When user presses the size button
    Then validate player is in Theater mode

  @Youtube
  Scenario: Test exiting Theater Mode
    Given user presses the size button
    And player is in Theater mode
    When user presses the size button
    Then validate player is in Default mode

  #Skipping through a video and testing the endcard.
    #Scenario: Test video end
    #Scenario: Test video end card
    #Scenario: Test video replay

  #Tests for click and drag forward and back on the status bar with validation
    #Scenario: Test click and dragging forward on the progress bar
    #Scenario: Test click and dragging back on the progress bar