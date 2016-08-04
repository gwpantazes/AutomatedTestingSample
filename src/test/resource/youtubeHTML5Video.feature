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

    # VIDEO END TESTS: TODO: do step defs and functions for these video end tests
    #Video Replay
  Scenario: Test video replay via loop
    Given user context clicks
    And user enables "loop" in context menu
    When user skips to the end of the video
    Then validate video replays

  Scenario:  Test video replay via replay button
    Given video is not in a playlist
    When user skips to the end of the video
    And user presses the video controls replay button
    # <button class="ytp-play-button" aria-label="Play" || title="Replay">
    Then validate video replays

    #End Card and Video Thumbs
  Scenario: Test video end card, disabling Up Next Autoplay
    Given video is not in a playlist
    And user disables Up Next Autoplay in the sidebar
    When user skips to the end of the video
    And validate video end card displays
    And video suggestions appear as thumbnails

  Scenario: Test video end card, cancelling Up Next Autoplay
    Given video is not in a playlist
    And user makes sure to enable Up Next Autoplay in the sidebar
    When user skips to the end of the video
    And user cancels up next in the end card
    Then validate video end card displays
    And video suggestions appear as thumbnails

  Scenario Outline: Test video end card thumbnails, disabling Up Next Autoplay
    Given video is not in a playlist
    When user disables Up Next Autoplay in the sidebar
    And user skips to the end of the video
    Then validate video end card displays
    And video suggestions appear as thumbnails
    When user clicks on the <nth> thumbnail
    Then user will be navigated to video in thumbnail
    And the video auto plays

    Examples:
      | nth      |
      | 1st      |
      | 2nd last |
      | last     |

  Scenario Outline: Test video end card thumbnails, cancelling Up Next Autoplay
    Given video is not in a playlist
    And user makes sure to enable Up Next Autoplay in the sidebar
    When user skips to the end of the video
    And user cancels up next in the end card
    Then validate video end card displays
    And video suggestions appear as thumbnails
    When user clicks on the <nth> thumbnail
    Then user will be navigated to video in thumbnail
    And the video auto plays

    Examples:
      | nth  |
      | 1st  |
      | 3rd  |
      | last |

  #Autoplay on Video End
  Scenario: Test video end Up Next AutoPlay
    Given video is not in a playlist
    And there is a video up next in the sidebar
    When user skips to the end of the video
    Then

  Scenario: Test video end Up Next AutoPlay button
  # <a class="ytp-upnext-autoplay-icon" href="https://www.youtube.com/watch?v=ug-CyGXMabg">
    Given video is not in a playlist
    And there is a video up next in the sidebar
    When user skips to the end of the video
    And user presses the Up Next button

    # TODO: Search tests, different feature file though
    # TODO: Landing page tests, different feature file though