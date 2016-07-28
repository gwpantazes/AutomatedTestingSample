import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FacebookStepDefinitions {

    WebDriver driver;

    @Before
    public void testSetUp() {
        if ( driver == null ) {
            driver = new FirefoxDriver();
        }
    }

    @After
    public void testShutDown() {
        driver.quit();
    }

    @Given("^The user is navigated to the Facebook website$")
    public void shouldNavigateToFacebook() throws Throwable {
        driver.navigate().to("https://www.facebook.com/");
    }

    @Given("^The user is signed out of their account$")
    public void ensureSignedOut() throws Throwable {
        Assert.assertTrue(driver.findElement(By.id("loginbutton")) != null );
    }

    @When("^The user enters a valid email$")
    public void shouldEnterEmail() throws Throwable {
        driver.findElement(By.id("email")).sendKeys("testuser@gmail.com");
    }

    @When("^and the user enters a valid password$")
    public void shouldEnterPassword() throws Throwable {
        driver.findElement(By.id("pass")).sendKeys("testPW");
    }

    @When("^and the user presses the Log In button$")
    public void shouldPressLogin() throws Throwable {
        driver.findElement(By.id("loginbutton")).click();
    }

    @Then("^The user should be logged in$")
    public void checkSuccessfulLogin() throws Throwable {
        Assert.assertTrue("User has not been logged in.", driver.findElement(By.id("logoutMenu")) != null);
    }

    @Then("^and brought to their newsfeed$")
    public void checkNavigateToFeed() throws Throwable {
        // The title of the newsfeed page is simply "Facebook"
        // And on the newsfeed there is a stream_pagelet
        Assert.assertTrue("User has not been navigated to feed.",
                driver.getTitle() != "Facebook"
                        && driver.findElement(By.id("stream_pagelet")) != null );
    }
}
