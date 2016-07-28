package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YoutubeHTML5VideoStepDefinitions {
    WebDriver driver;
    JavascriptExecutor js;

    private final String videoElement = "document.getElementById('player').getElementsByClassName('html5-main-video')[0]";

    @Before
    public void initFeatureTest() {
        if (driver == null) {
            driver = new FirefoxDriver();
            js = (JavascriptExecutor) driver;
        }
    }

    @After
    public void shutdownFeatureTest() {
        driver.quit();
    }

    @Given("^user is navigated to a Youtube video$")
    public void the_user_is_navigated_to_Youtube() throws Throwable {
        driver.get("https://youtu.be/dPeUd5hN3vs"); // An ad, so it won't show ads
    }

    /*@When("^the page loads$")
    public void the_page_loads() throws Throwable {
        //If video element is on the page, then page is considered loaded
        assertTrue("Video element does not exist", driver.findElement(By.tagName("video")) != null);
    }*/

    @Then("^(?:validate )?the video (?:auto ?)?plays")
    public void validate_the_video_begins_playing() throws Throwable {
        assertFalse("Video element did not play", (Boolean) js.executeScript( "return " + videoElement + ".paused;" ));
    }

    @When("^user presses the pause button$")
    public void user_presses_the_pause_button() throws Throwable {
        js.executeScript(videoElement + ".pause();"); //Note that this is directly using the API to pause, not pressing the pause button
    }

    @When("^user presses the play button$")
    public void the_user_presses_the_play_button() throws Throwable {
        js.executeScript(videoElement + ".play();"); //Note that this is directly using the API to play, not pressing the play button
    }

    @Then("^validate the video is paused$")
    public void validate_the_video_pauses() throws Throwable {
        assertTrue((Boolean) js.executeScript("return " + videoElement + ".paused;"));
    }

    @When("^user waits for \"([^\"]*)\" seconds$")
    public void the_user_waits_for_seconds(String waitTime) throws Throwable {
        new WebDriverWait(driver, Long.valueOf(waitTime));
    }

    @When("^user presses the size button$")
    public void userPressesTheSizeButton() throws Throwable {
        boolean goingIntoTheaterMode = driver.findElements(By.className("watch-stage-mode")).size() == 0;

        driver.findElement(By.className("ytp-size-button")).click();

        // We have to wait for theater mode transition because it takes some time
        if (goingIntoTheaterMode) {
            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.attributeContains(
                            driver.findElement(By.id("page")),
                            "class",
                            "watch-stage-mode"));
        } else {    // This one inverts the assumption with ExpectedConditions.not()
            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.not(ExpectedConditions.attributeContains(
                            driver.findElement(By.id("page")),
                            "class",
                            "watch-stage-mode")));
        }
        // new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("ytp-size-button")));
    }

    @When("^user presses the fullscreen button$")
    public void user_presses_the_fullscreen_button() throws Throwable {
        boolean goingIntoFullscreen = driver.findElements(By.className("ytp-fullscreen")).size() == 0;

        driver.findElement(By.className("ytp-fullscreen-button")).click();

        // We have to Wait for fullscreen because it takes some time
        if (goingIntoFullscreen) {
            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.attributeContains(
                            driver.findElement(By.cssSelector(".html5-video-player")),
                            "class",
                            "ytp-fullscreen"));
        } else {    // This one inverts the assumption with ExpectedConditions.not()
            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.not(ExpectedConditions.attributeContains(
                            driver.findElement(By.cssSelector(".html5-video-player")),
                            "class",
                            "ytp-fullscreen")));
        }
    }



    @Then("^(?:validate )?video is (not)? ?fullscreen$")
    public void validate_video_is_fullscreen(String shouldTestForFullscreenOrNot) throws Throwable {
        //The captured parameter notPresent inverts the assumption of fullscreen being present
        if (shouldTestForFullscreenOrNot == null) {
            // Want to find a fullscreen indicator, so list size should be greater than 0
            assertTrue("Video is not indicated to be fullscreen, but should be fullscreen.",
                    driver.findElements(By.className("ytp-fullscreen")).size() > 0 );
        } else if (shouldTestForFullscreenOrNot.contains("not")) {
            // Don't want to find a fullscreen indicator, so we should get empty list
            assertTrue("Video is indicated to be fullscreen, but should be normal size.",
                    driver.findElements(By.className("ytp-fullscreen")).size() == 0 );
        }

        // Wish: validate using document.fullscreenElement: https://developer.mozilla.org/en-US/docs/Web/API/Document/fullscreenElement
    }

    //TODO: THIS FAILS??? Or is this the one that works correctly?
    @Then("^(?:validate )?player is in Theater mode$")
    public void validateThePlayerIsInTheaterMode() throws Throwable {
        assertTrue("Player is not in Theater mode. Player is in Default size mode.",
                driver.findElements(By.className("watch-stage-mode")).size() > 0);
    }

    //TODO: THIS FAILS
    @Then("^(?:validate )?player is in Default mode$")
    public void validateThePlayerIsInDefaultMode() throws Throwable {
        assertTrue("Player is not in Default size mode. Player is in Theater mode.",
                driver.findElements(By.className("watch-stage-mode")).size() == 0 );
                // driver.findElements(By.className("watch-non-stage-mode")).size() > 0
                // watch-non-stage-mode is not initally there, it only appears after theater mode has been turned off and on
                // Thus, we must only detect for watch-stage-mode.
    }
}
