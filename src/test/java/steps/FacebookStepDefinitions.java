package steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class FacebookStepDefinitions {
    private WebDriver driver;

    // TODO: Create a shared driver for all step files
    /*@Before
    public void initFeatureTest() {
        if (driver == null) {
            driver = new FirefoxDriver();
            js = (JavascriptExecutor) driver;
        }
    }

    @After
    public void shutdownFeatureTest() {
        driver.quit();
    }*/

    @Given("^The user is navigated to the Facebook website$")
    public void shouldNavigateToFacebook() throws Throwable {
        driver.navigate().to("https://www.facebook.com/");
    }

    @Given("^The user is signed out of their account$")
    public void ensureSignedOut() throws Throwable {
        assertTrue(driver.findElement(By.id("loginbutton")) != null);
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
        assertTrue("User has not been logged in.", driver.findElement(By.id("logoutMenu")) != null);
    }

    @Then("^and brought to their feed$")
    public void checkNavigateToFeed() throws Throwable {
        // The title of the feed page is simply "Facebook"
        // And on the feed there is a stream_pagelet
        assertTrue("User has not been navigated to feed.",
                driver.getTitle().equals("Facebook")
                        && driver.findElement(By.id("stream_pagelet")) != null );
    }
}
