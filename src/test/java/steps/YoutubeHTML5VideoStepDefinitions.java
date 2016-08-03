package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.YoutubeVideoPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YoutubeHTML5VideoStepDefinitions {

    private YoutubeVideoPage youtubeVideoPage;

    @Before
    public void initFeatureTest() {
        youtubeVideoPage = new YoutubeVideoPage(null);
    }

    @Given("^user maximizes the window")
    public void maximizeVideo() throws Throwable {
        youtubeVideoPage.maximizeWindow();
    }

    @Given("^user is navigated to a Youtube video$")
    public void navigateToYoutube() throws Throwable {
        youtubeVideoPage.navigateToAVideo();
    }

    @When("^user waits for \"([^\"]*)\" seconds$")
    public void the_user_waits_for_seconds(String waitTime) throws Throwable {
        youtubeVideoPage.threadWait(waitTime);
    }

    @When("^user presses the (?:(?:pause)|(?:play)) button$")
    public void user_presses_the_pause_button() throws Throwable {
        youtubeVideoPage.pressPlayButton();
    }

    @When("^user presses the size button$")
    public void userPressesTheSizeButton() throws Throwable {
        boolean goingIntoTheaterMode = !youtubeVideoPage.isTheaterMode();
        youtubeVideoPage.pressTheaterModeButton();
        // Wait for theater mode transition because it takes some time
        youtubeVideoPage.waitForTheaterMode(goingIntoTheaterMode);
    }

    @When("^user presses the fullscreen button$")
    public void user_presses_the_fullscreen_button() throws Throwable {
        boolean goingIntoFullscreen = !youtubeVideoPage.isVideoFullscreen();
        youtubeVideoPage.pressFullscreenButton();
        // Wait for fullscreen because it takes some time
        youtubeVideoPage.waitForFullscreen(goingIntoFullscreen);
    }

    @When("^user drags the scrubber from (\\d+) (?:(?:forward)|(?:back)) to (-?\\d+)$")
    public void user_drags_the_scrubber_from_forward_to(int start, int end) throws Throwable {
        youtubeVideoPage.clickAndDragVideoScrubber(start, end);
    }

    @When("^user clicks on the tracking bar at (\\d+)$")
    public void user_clicks_on_the_tracking_bar_at(int point) throws Throwable {
        youtubeVideoPage.clickOnProgressBar(point);
    }

    // TODO: Mark argument "is" and "is not" so that we don't have to test for null
    @Then("^(?:validate )?video is (not)? ?fullscreen$")
    public void validate_video_is_fullscreen(String shouldTestForFullscreenOrNot) throws Throwable {
        //The captured parameter notPresent inverts the assumption of fullscreen being present
        if (shouldTestForFullscreenOrNot == null) {
            // Want to find a fullscreen indicator, so list size should be greater than 0
            assertTrue("Video is not indicated to be fullscreen, but should be fullscreen.",
                    youtubeVideoPage.isVideoFullscreen());
        } else if (shouldTestForFullscreenOrNot.contains("not")) {
            // Don't want to find a fullscreen indicator, so we should get empty list
            assertFalse("Video is indicated to be fullscreen, but should be normal size.",
                    youtubeVideoPage.isVideoFullscreen());
        }
    }

    @Then("^(?:validate )?player is in Theater mode$")
    public void validateThePlayerIsInTheaterMode() throws Throwable {
        assertTrue("Player is not in Theater mode. Player is in Default size mode.",
                youtubeVideoPage.isTheaterMode());
    }

    @Then("^(?:validate )?player is in Default mode$")
    public void validateThePlayerIsInDefaultMode() throws Throwable {
        assertFalse("Player is not in Default size mode. Player is in Theater mode.",
                youtubeVideoPage.isTheaterMode());
    }

    @Then("^(?:validate )?the video (?:auto ?)?plays$")
    public void validate_the_video_begins_playing() throws Throwable {
        assertFalse("Video element did not play", youtubeVideoPage.isVideoPaused());
    }

    @Then("^validate the video is paused$")
    public void validateVideoPaused() throws Throwable {
        assertTrue("Video element is not paused", youtubeVideoPage.isVideoPaused() );
    }

    @Then("^validate video playback position is set to correct point based on (-?\\d+)$")
    public void video_playback_position_will_be_set(int position) throws Throwable {
        assertTrue(youtubeVideoPage.verifyPlaybackPosition(position));
    }
}
