package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookCommonPage extends AbstractPage {

    public FacebookCommonPage(WebDriver driver) {
        super(driver);
    }

    public boolean isButtonPresent(String buttonPrefix) {
        return driver.findElements(By.id(buttonPrefix + "button")).size() > 0;
    }
}
