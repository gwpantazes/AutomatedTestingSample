package pageObject;

import driver.SharedDriver;
import org.openqa.selenium.By;

public class FacebookFeedPage extends FacebookCommonPage {

    public FacebookFeedPage(SharedDriver driver) {
        super(driver);
    }

    public boolean verifyFeedPresent() {
        return driver.getTitle().equals("Facebook") && driver.findElements(By.id("stream_pagelet")).size() > 0;
    }

}
