package org.demo.tests;

import org.demo.base.BaseTest;
import org.demo.pages.HomePage;
import org.demo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Smoke checks for Practice Software Testing home and header navigation.
 */
public class SmokeTest extends BaseTest {

    @Test(description = "Verify Practice Software Testing home page title/url load")
    public void homePageLoads() {
        String currentUrl = getDriver().getCurrentUrl();
        String title = getDriver().getTitle();

        log.info("Loaded URL: {}", currentUrl);
        log.info("Page title: {}", title);

        Assert.assertTrue(currentUrl.contains("practicesoftwaretesting.com"),
                "Unexpected URL: " + currentUrl);
        Assert.assertFalse(title == null || title.isBlank(), "Page title should not be empty");
    }

    @Test(description = "Click Sign in in the header and land on the login page")
    public void signInFromHeaderNavigatesToLoginPage() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isSignInVisible(), "Sign in link should be visible in the header");

        LoginPage loginPage = homePage.openSignIn();

        Assert.assertTrue(loginPage.isLoaded(),
                "Login page should load after clicking Sign in. URL=" + loginPage.getCurrentUrl());
        log.info("Login page URL: {}", loginPage.getCurrentUrl());
        log.info("Login page heading: {}", loginPage.getHeading());
        Assert.assertTrue(loginPage.getHeading().toLowerCase().contains("login"),
                "Expected Login heading, got: " + loginPage.getHeading());
    }
}
