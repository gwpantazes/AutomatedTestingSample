package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

}
