Feature: HTML5 Video Player functionality on Youtube

  #Basic Set of Functionality. Video, Autoplay, Play, and Pause
  Background:
    #Given user maximizes the window
    Given user is navigated to a Youtube video

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

  #Skipping through a video and testing the end card.
    #Scenario: Test video end
    #Scenario: Test video end card
    #Scenario: Test video replay

  #Tests for click and drag forward and back on the status bar with validation
  @Youtube
  Scenario Outline: Click and drag forward on the video tracking bar
    When user drags the scrubber from <start> forward to <end>
    Then validate video playback position is set to correct point based on <end>

    Examples:
      | start | end |
      | 0     | 20  |
      | 1     | 99  |
      | 50    | 100 |
      | 0     | 999 |

  @Youtube
  Scenario Outline: Click and dragging back on the video tracking bar
    When user drags the scrubber from <start> back to <end>
    Then validate video playback position is set to correct point based on <end>

    Examples:
      | start | end  |
      | 70    | 20   |
      | 99    | 1    |
      | 100   | 0    |
      | 100   | -999 |

  @Youtube
  Scenario Outline: Click on the tracking bar
    When user clicks on the tracking bar at <point>
    Then validate video playback position is set to correct point based on <point>

    Examples:
      | point |
      | 50    |
      | 0     |
      | 1     |
      | 99    |
      | 100   |