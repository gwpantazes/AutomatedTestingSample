package pageObject;

import org.openqa.selenium.By;
import shareddriver.SharedDriver;

public class FacebookCommonPage extends AbstractPage {

    public FacebookCommonPage(SharedDriver driver) {
        super(driver);
    }

    public boolean isButtonPresent(String buttonPrefix) {
        return driver.findElements(By.id(buttonPrefix + "button")).size() > 0;
    }
}
