package org.demo.pages;

import org.openqa.selenium.By;

/**
 * Auth login page opened from header Sign in.
 */
public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.cssSelector("[data-test='email']");
    private static final By PASSWORD_INPUT = By.cssSelector("[data-test='password']");
    private static final By LOGIN_SUBMIT = By.cssSelector("[data-test='login-submit']");
    private static final By PAGE_TITLE = By.cssSelector("[data-test='page-title'], h3");

    public boolean isLoaded() {
        waitForUrlContains("/auth/login");
        return isDisplayed(EMAIL_INPUT)
                && isDisplayed(PASSWORD_INPUT)
                && isDisplayed(LOGIN_SUBMIT);
    }

    public String getHeading() {
        return getText(PAGE_TITLE);
    }
}
