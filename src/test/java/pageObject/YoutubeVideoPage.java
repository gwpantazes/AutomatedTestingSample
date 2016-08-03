package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.Utils.MathUtils;
import shareddriver.SharedDriver;

public class YoutubeVideoPage extends AbstractPage {

    public YoutubeVideoPage(SharedDriver driver) {
        super(driver);
    }

    public YoutubeVideoPage pressPlayButton() {
        driver.findElement(By.className("ytp-play-button")).click();
        return new YoutubeVideoPage(driver);
    }

    public YoutubeVideoPage pressTheaterModeButton() {
        driver.findElement(By.className("ytp-size-button")).click();
        return new YoutubeVideoPage(driver);
    }

    public YoutubeVideoPage pressFullscreenButton() {
        driver.findElement(By.className("ytp-fullscreen-button")).click();
        return new YoutubeVideoPage(driver);
    }

    public boolean isTheaterMode() {
        // class "watch-non-stage-mode" marks that the video is in default mode, not theater mode,
        // but the class is not present initially. It only appears after theater mode has been turned off and on.
        // Thus, we must only detect for watch-stage-mode.
        return driver.findElements(By.className("watch-stage-mode")).size() > 0;
    }


    public boolean isVideoFullscreen() {
        // TODO: use document.fullscreenElement: https://developer.mozilla.org/en-US/docs/Web/API/Document/fullscreenElement
        return driver.findElements(By.className("ytp-fullscreen")).size() > 0;
    }

    public boolean isVideoPaused() {
        JavascriptExecutor js = driver;
        String videoElementJSLocatorString = "document.getElementById('player').getElementsByClassName('html5-main-video')[0]";
        return (Boolean) js.executeScript("return " + videoElementJSLocatorString + ".paused;");
    }

    private WebElement getVideoPlayer() {
        return driver.findElement(By.className("html5-video-player"));
    }

    public YoutubeVideoPage waitForTheaterMode(boolean goingIntoTheaterMode) {
        // TODO: instead of if-else construct, use map<string,runnable>
        // Wait for theater mode transition because it takes some time
        if (goingIntoTheaterMode) {
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(
                    driver.findElement(By.id("page")),
                    "class",
                    "watch-stage-mode"));
        } else {    // This one inverts the assumption with ExpectedConditions.not()
            new WebDriverWait(driver, 5).until(ExpectedConditions.not(ExpectedConditions.attributeContains(
                    driver.findElement(By.id("page")),
                    "class",
                    "watch-stage-mode")));
        }
        return new YoutubeVideoPage(driver);
    }

    // TODO: instead of if else, use map<string,runnable>
    public YoutubeVideoPage waitForFullscreen(boolean goingIntoFullscreen) {
        if (goingIntoFullscreen) {
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(
                    this.getVideoPlayer(),
                    "class",
                    "ytp-fullscreen"));
        } else {    // This one inverts the assumption with ExpectedConditions.not()
            new WebDriverWait(driver, 5).until(ExpectedConditions.not(ExpectedConditions.attributeContains(
                    this.getVideoPlayer(),
                    "class",
                    "ytp-fullscreen")));
        }
        return new YoutubeVideoPage(driver);
    }

    public YoutubeVideoPage clickOnProgressBar(int percentOffset) {
        WebElement progressBar = driver.findElement(By.className("ytp-progress-bar"));
        final int progressBarWidth = progressBar.getSize().getWidth();
        // Adjust progressBarWidth by -1, because clicking at full width clicks outside of element
        int clickXOffset = (int) (percentOffset / 100f * (progressBarWidth - 1));

        new Actions(driver)
                .click(this.getVideoPlayer())
                .moveToElement(progressBar, clickXOffset, 1)
                .clickAndHold()
                .release()
                .perform();

        return new YoutubeVideoPage(driver);
    }

    public YoutubeVideoPage clickAndDragVideoScrubber(int startPoint, int endPoint) {
        WebElement progressBar = driver.findElement(By.className("ytp-progress-bar"));
        final int progressBarWidth = progressBar.getSize().getWidth();
        // Adjust progressBarWidth by -1, because clicking at full width clicks outside of element
        int startXOffset = (int) (startPoint / 100f * (progressBarWidth - 1));
        int endXOffset = (int) (endPoint / 100f * (progressBarWidth - 1));

        new Actions(driver)
                .click(this.getVideoPlayer())
                .moveToElement(progressBar, startXOffset, 1)
                .clickAndHold()
                .moveByOffset(endXOffset - startXOffset, 0)
                .release()
                .perform();

        return new YoutubeVideoPage(driver);
    }

    public boolean verifyPlaybackPosition(int assumedPercentProgressed) {
        WebElement progressBar = driver.findElement(By.className("ytp-progress-bar"));
        int maxProgress = Integer.valueOf(progressBar.getAttribute("aria-valuemax"));
        int currentProgress = Integer.valueOf(progressBar.getAttribute("aria-valuenow"));

        int assumedProgress = MathUtils.clamp((int) ((assumedPercentProgressed / 100f) * maxProgress), 0, maxProgress);

        // See if clicked progress matches up to the actual currentProgress value
        final float errorMargin = maxProgress / 100f;  // 1% error margin
        return MathUtils.isInRange(assumedProgress,
                (int) Math.floor(currentProgress - errorMargin),
                (int) Math.ceil(currentProgress + errorMargin));
    }

}
