package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class AbstractPage {

    protected WebDriver driver;
    // TODO: Use SharedDriver to save on time: https://github.com/cucumber/cucumber-jvm/blob/master/examples/java-webbit-websockets-selenium/src/test/java/cucumber/examples/java/websockets/SharedDriver.java

    public AbstractPage(WebDriver driver) {
        if (driver == null) {
            this.driver = new FirefoxDriver();
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            this.driver = driver;
        }
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
        driver.get("https://www.youtube.com");
        return new YoutubeLandingPage(driver);
    }

    public YoutubeVideoPage navigateToAVideo() {
        driver.get("https://youtu.be/3h1wQUPHIy4"); // Anamanaguchi Capsule Silence
        // driver.get("https://youtu.be/dPeUd5hN3vs"); // An ad, so it won't show ads
        return new YoutubeVideoPage(driver);
    }

    public FacebookLandingPage navigateToFacebookLogin() {
        driver.get("https://www.facebook.com");
        return new FacebookLandingPage(driver);
    }

    public void closeDriver() {
        driver.quit();
    }
}


