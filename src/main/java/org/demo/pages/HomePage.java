package org.demo.pages;

import org.openqa.selenium.By;

/**
 * Practice Software Testing home page (header + product grid).
 */
public class HomePage extends BasePage {

    private static final By SIGN_IN_LINK = By.cssSelector("[data-test='nav-sign-in']");

    public LoginPage openSignIn() {
        click(SIGN_IN_LINK);
        waitForUrlContains("/auth/login");
        return new LoginPage();
    }

    public boolean isSignInVisible() {
        return isDisplayed(SIGN_IN_LINK);
    }
}
