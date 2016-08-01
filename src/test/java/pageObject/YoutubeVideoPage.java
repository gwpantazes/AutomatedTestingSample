package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YoutubeVideoPage extends AbstractPage{

    public YoutubeVideoPage(WebDriver driver) {
        super(driver);
    }

    private final String videoElementJSLocatorString = "document.getElementById('player').getElementsByClassName('html5-main-video')[0]";

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
        return driver.findElements(By.className("watch-stage-mode")).size() > 0;
    }

    public boolean isVideoFullscreen() {
        return driver.findElements(By.className("ytp-fullscreen")).size() > 0;
    }

    public boolean isVideoPaused() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return " + videoElementJSLocatorString + ".paused;");
    }

    public WebElement getVideoPlayer() {
        return driver.findElement(By.className("html5-video-player"));
    }

    public YoutubeVideoPage waitForTheaterMode(boolean goingIntoTheaterMode){
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

}
