package pageObject;

import org.openqa.selenium.By;
import shareddriver.SharedDriver;

public class FacebookFeedPage extends FacebookCommonPage {

    public FacebookFeedPage(SharedDriver driver) {
        super(driver);
    }

    public boolean verifyFeedPresent() {
        return driver.getTitle().equals("Facebook") && driver.findElements(By.id("stream_pagelet")).size() > 0;
    }

}
