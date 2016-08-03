package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.FacebookFeedPage;
import pageObject.FacebookLandingPage;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FacebookStepDefinitions {
    private FacebookLandingPage facebookLandingPage;
    private FacebookFeedPage facebookFeedPage;

    @Before
    public void initFeatureTest() {
        facebookLandingPage = new FacebookLandingPage(null);
    }

    @Given("^The user is navigated to the Facebook website$")
    public void shouldNavigateToFacebook() throws Throwable {
        facebookLandingPage.navigateToFacebookLogin();
    }

    @Given("^The user is signed out of their account$")
    public void ensureSignedOut() throws Throwable {
        assertFalse("User is signed into their account when they should be signed out.",
                facebookLandingPage.isButtonPresent("logout"));
    }

   /*@When("^The user enters a valid email$")
    public void shouldEnterEmail(String email) throws Throwable {
        facebookLandingPage.enterEmail(email);
    }

    @When("^and the user enters a valid password$")
    public void shouldEnterPassword(String password) throws Throwable {
        facebookLandingPage.enterPassword(password);
    }

    @When("^and the user presses the Log In button$")
    public void shouldPressLogin() throws Throwable {
        facebookLandingPage.pressLogin();
    }*/

    @When("^user enters email and password and logs in$")
    public void formFillAndLogin(List<String> formInfo) {
        facebookFeedPage = facebookLandingPage.enterEmail(formInfo.get(0)).enterPassword(formInfo.get(1)).pressLogin();
    }

    @Then("^The user should be logged in$")
    public void checkSuccessfulLogin() throws Throwable {
        assertTrue("User has not been logged in.", facebookFeedPage.isButtonPresent("logout"));
    }

    @Then("^and brought to their feed$")
    public void checkNavigateToFeed() throws Throwable {
        // The title of the feed page is simply "Facebook"
        // And on the feed there is a stream_pagelet
        assertTrue("User has not been navigated to feed.", facebookFeedPage.verifyFeedPresent());
    }
}
