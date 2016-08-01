package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YoutubeLandingPage extends AbstractPage {

        public YoutubeLandingPage(WebDriver driver) {
            super(driver);
        }

        public YoutubeVideoPage navigateToVideo() {
            WebElement videoThumb = driver.findElements(By.className("video-thumb")).get(0);
            new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(videoThumb));
            videoThumb.click();
            return new YoutubeVideoPage(driver);
        }
}

