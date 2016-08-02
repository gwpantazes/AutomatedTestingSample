package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookLandingPage extends FacebookCommonPage {

    public FacebookLandingPage(WebDriver driver) {
        super(driver);
    }

    public FacebookLandingPage enterEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        return new FacebookLandingPage(driver);
    }

    public FacebookLandingPage enterPassword(String password) {
        driver.findElement(By.id("pass")).sendKeys(password);
        return new FacebookLandingPage(driver);
    }

    public FacebookFeedPage pressLogin() {
        driver.findElement(By.id("loginbutton")).click();
        return new FacebookFeedPage(driver);
    }
}
