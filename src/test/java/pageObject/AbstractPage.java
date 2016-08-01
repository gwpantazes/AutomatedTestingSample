package pageObject;

import org.openqa.selenium.WebDriver;

public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void threadWait(long seconds) throws Throwable{
        Thread.sleep(seconds * 1000);    // TODO: Convert to thread safe wait
    }
    public void threadWait(String seconds) throws Throwable{
        Thread.sleep(Long.valueOf(seconds) * 1000);    // TODO: Convert to thread safe wait
    }

    public YoutubeLandingPage navigateToYoutube() {
        driver.get("https://www.youtube.com");  // home page
        return new YoutubeLandingPage(driver);
    }

    public YoutubeVideoPage navigateToAVideo() {
        driver.get("https://youtu.be/dPeUd5hN3vs"); // An ad, so it won't show ads
        return new YoutubeVideoPage(driver);
    }

    public void closeDriver() {
        driver.quit();
    }
}


