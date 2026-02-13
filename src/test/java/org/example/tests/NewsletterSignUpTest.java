package org.example.tests;

import java.time.Duration;

import org.example.config.TestConfig;
import org.example.driver.DriverFactory;
import org.example.pages.NewsletterPage;
import org.example.pages.SuccessPage;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

class NewsletterSignUpTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private TestConfig config;
    private NewsletterPage newsletterPage;

    @BeforeEach
    void setUp() {
        config = TestConfig.fromSystemProperties();
        driver = new DriverFactory().createChromeDriver(config);
        wait = new WebDriverWait(driver, Duration.ofSeconds(config.getTimeoutSeconds()));
        newsletterPage = new NewsletterPage(driver, wait);
        newsletterPage.setDelayMillis(config.getDelayMillis());
        newsletterPage.open(config.getBaseUrl());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test Case: Successful Newsletter Subscription
     * Verifies that a user can successfully subscribe to the newsletter with a valid email address.
     * Expected: Success message is displayed containing "thanks" and the submitted email address.
     */
    @Test
    void userCanSubscribeWithValidEmail() {
        String testEmail = "qa+selenium@example.com";
        
        newsletterPage.subscribeWithEmail(testEmail);
        SuccessPage successPage = newsletterPage.waitForSuccess();
        
        String pageText = successPage.getHeadingText().toLowerCase();
        assertTrue(pageText.contains("thanks"), "Success message should contain 'thanks'");
        assertTrue(pageText.contains(testEmail.toLowerCase()), "Success message should display the email: " + testEmail);
    }

    /**
     * Test Case: Empty Email Validation
     * Verifies that validation error is displayed when user attempts to submit without entering an email.
     * Expected: HTML5 validation message appears preventing form submission.
     */
    @Test
    void userSeesValidationMessageForEmptyEmail() {
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for empty email");
    }

    /**
     * Test Case: Invalid Email Format Validation
     * Verifies that validation error is displayed when user enters an invalid email format (missing @ symbol).
     * Expected: HTML5 validation message appears preventing form submission.
     */
    @Test
    void userSeesValidationMessageForInvalidEmailFormat() {
        newsletterPage.enterEmail("invalid-email");
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for invalid email format");
    }

    /**
     * Test Case: Incomplete Email Validation
     * Verifies that validation error is displayed when user enters an incomplete email (e.g., "test@").
     * Expected: HTML5 validation message appears preventing form submission.
     */
    @Test
    void userSeesValidationMessageForIncompleteEmail() {
        newsletterPage.enterEmail("test@");
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for incomplete email");
    }

    /**
     * Test Case: Email Without Proper Domain Validation
     * Verifies that validation error is displayed when user enters an email without a proper domain extension.
     * Expected: HTML5 validation message appears preventing form submission.
     */
    @Test
    void userSeesValidationMessageForEmailWithoutDomain() {
        newsletterPage.enterEmail("test@domain");
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for email without proper domain");
    }
}
