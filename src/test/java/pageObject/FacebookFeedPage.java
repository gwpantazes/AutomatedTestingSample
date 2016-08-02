package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookFeedPage extends FacebookCommonPage {

    public FacebookFeedPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyFeedPresent() {
        return driver.getTitle().equals("Facebook") && driver.findElements(By.id("stream_pagelet")).size() > 0;
    }

}
