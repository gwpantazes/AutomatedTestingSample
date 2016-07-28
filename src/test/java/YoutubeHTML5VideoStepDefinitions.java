import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

    @When("^the page loads$")
    public void the_page_loads() throws Throwable {
        //If video element is on the page, then page is considered loaded
        Assert.assertTrue("Video element does not exist", driver.findElement(By.tagName("video")) != null);
    }

    @Then("^(?:validate )?the video (?:auto ?)?plays")
    public void validate_the_video_begins_playing() throws Throwable {
        Assert.assertFalse("Video element did not play", (Boolean) js.executeScript( "return " + videoElement + ".paused;" ));
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
        Assert.assertTrue((Boolean) js.executeScript("return " + videoElement + ".paused;"));
    }

    @When("^user waits for \"([^\"]*)\" seconds$")
    public void the_user_waits_for_seconds(String waitTime) throws Throwable {
        Thread.sleep(Integer.valueOf(waitTime) * 1000);
    }

    @When("^user presses the fullscreen button$")
    public void user_presses_the_fullscreen_button() throws Throwable {
        driver.findElement(By.className("ytp-fullscreen-button")).click();
    }

    @Then("^(?:validate )?video is (not)? ?fullscreen$")
    public void validate_video_is_fullscreen(String shouldTestForFullscreenOrNot) throws Throwable {
        //The captured parameter notPresent inverts the assumption of fullscreen being present
        System.out.println("DEBUGGING: FULLSCREEN TEST:" + shouldTestForFullscreenOrNot);
        if (shouldTestForFullscreenOrNot.contains("not")) {
            Assert.assertTrue(null != driver.findElement(By.className("ytp-fullscreen")));
        } else {
            Assert.assertTrue(null == driver.findElement(By.className("ytp-fullscreen")));
        }

        // document.fullscreenElement: https://developer.mozilla.org/en-US/docs/Web/API/Document/fullscreenElement

    }

}
