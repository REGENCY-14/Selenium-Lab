package org.example.tests;

import org.example.config.TestConfig;
import org.example.driver.DriverFactory;
import org.example.pages.NewsletterPage;
import org.example.pages.SuccessPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void userCanSubscribeWithValidEmail() {
        String testEmail = "qa+selenium@example.com";
        
        newsletterPage.subscribeWithEmail(testEmail);
        SuccessPage successPage = newsletterPage.waitForSuccess();
        
        String pageText = successPage.getHeadingText().toLowerCase();
        assertTrue(pageText.contains("thanks"), "Success message should contain 'thanks'");
        assertTrue(pageText.contains(testEmail.toLowerCase()), "Success message should display the email: " + testEmail);
    }

    @Test
    void userSeesValidationMessageForEmptyEmail() {
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for empty email");
    }

    @Test
    void userSeesValidationMessageForInvalidEmailFormat() {
        newsletterPage.enterEmail("invalid-email");
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for invalid email format");
    }

    @Test
    void userSeesValidationMessageForIncompleteEmail() {
        newsletterPage.enterEmail("test@");
        newsletterPage.clickSubscribe();
        
        // HTML5 validation prevents form submission
        assertTrue(newsletterPage.isValidationMessageDisplayed() || 
                   !newsletterPage.getEmailInputValidationMessage().isEmpty(),
                   "Validation message should be displayed for incomplete email");
    }

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
