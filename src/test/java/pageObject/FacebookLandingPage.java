package pageObject;

import driver.SharedDriver;
import org.openqa.selenium.By;

public class FacebookLandingPage extends FacebookCommonPage {

    public FacebookLandingPage(SharedDriver driver) {
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
