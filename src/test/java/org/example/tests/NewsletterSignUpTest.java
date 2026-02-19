package org.example.tests;

import org.example.pages.NewsletterPage;
import org.example.pages.SuccessPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * UI tests for the newsletter sign-up flow.
 */
class NewsletterSignUpTest extends BaseTest {

    private NewsletterPage newsletterPage;

    /**
     * Initializes the page object and navigates to the base URL.
     */
    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
        newsletterPage = new NewsletterPage(driver, wait);
        newsletterPage.setDelayMillis(config.getDelayMillis());
        newsletterPage.open(config.getBaseUrl());
    }

    /**
     * Verifies that a valid email address completes the sign-up successfully.
     */
    @Test
    void userCanSubscribeWithValidEmail() {
        System.out.println("[TEST] Valid Email Subscription Test");
        String testEmail = "qaselenium";
        
        try {
            newsletterPage.subscribeWithEmail(testEmail);
            SuccessPage successPage = newsletterPage.waitForSuccess();
            String pageText = successPage.getHeadingText().toLowerCase();
            
            assertTrue(pageText.contains("thanks"), "Success message should contain 'thanks'");
            assertTrue(pageText.contains(testEmail.toLowerCase()), "Success message should display the email: " + testEmail);
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior when the email input is empty.
     */
    @Test
    void userSeesValidationMessageForEmptyEmail() {
        System.out.println("[TEST] Empty Email Validation Test");
        
        try {
            newsletterPage.clickSubscribe();
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            String message = newsletterPage.getEmailInputValidationMessage();
            
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for empty email");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior for an invalid email format.
     */
    @Test
    void userSeesValidationMessageForInvalidEmailFormat() {
        System.out.println("[TEST] Invalid Email Format Validation Test");
        
        try {
            newsletterPage.enterEmail("123456789");
            newsletterPage.clickSubscribe();
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            String message = newsletterPage.getEmailInputValidationMessage();
            
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for invalid email format");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior for an incomplete email.
     */
    @Test
    void userSeesValidationMessageForIncompleteEmail() {
        System.out.println("[TEST] Incomplete Email Validation Test");
        
        try {
            newsletterPage.enterEmail("test@");
            newsletterPage.clickSubscribe();
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            String message = newsletterPage.getEmailInputValidationMessage();
            
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for incomplete email");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior for an email without a proper domain.
     */
    @Test
    void userSeesValidationMessageForEmailWithoutDomain() {
        System.out.println("[TEST] Email Without Domain Validation Test");
        
        try {
            newsletterPage.enterEmail("test@domain");
            newsletterPage.clickSubscribe();
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            String message = newsletterPage.getEmailInputValidationMessage();
            
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for email without proper domain");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage() + "\n");
            throw e;
        }
    }
}
