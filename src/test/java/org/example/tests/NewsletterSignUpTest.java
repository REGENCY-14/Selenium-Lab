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
            System.out.println("Step 1: Entering email address: " + testEmail);
            newsletterPage.subscribeWithEmail(testEmail);
            
            System.out.println("Step 2: Waiting for success page to load");
            SuccessPage successPage = newsletterPage.waitForSuccess();
            
            System.out.println("Step 3: Retrieving success page text");
            String pageText = successPage.getHeadingText().toLowerCase();
            
            System.out.println("Step 4: Verifying success message contains 'thanks'");
            assertTrue(pageText.contains("thanks"), "Success message should contain 'thanks'");
            
            System.out.println("Step 5: Verifying success message displays email: " + testEmail);
            assertTrue(pageText.contains(testEmail.toLowerCase()), "Success message should display the email: " + testEmail);
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED at test step.");
            System.out.println("Error Type: " + e.getClass().getSimpleName());
            System.out.println("Error Message: " + e.getMessage());
            System.out.println("Test Email Used: " + testEmail + "\n");
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
            System.out.println("Step 1: Clicking subscribe button without email");
            newsletterPage.clickSubscribe();
            
            System.out.println("Step 2: Checking if validation message is displayed");
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            
            System.out.println("Step 3: Retrieving HTML5 validation message");
            String message = newsletterPage.getEmailInputValidationMessage();
            
            System.out.println("Step 4: Verifying validation message exists");
            System.out.println("Message Displayed: " + messageDisplayed + ", Message Text: " + message);
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for empty email");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED at test step.");
            System.out.println("Error Type: " + e.getClass().getSimpleName());
            System.out.println("Error Message: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior for an invalid email format.
     */
    @Test
    void userSeesValidationMessageForInvalidEmailFormat() {
        System.out.println("[TEST] Invalid Email Format Validation Test");
        String invalidEmail = "123456789";
        
        try {
            System.out.println("Step 1: Entering invalid email format: " + invalidEmail);
            newsletterPage.enterEmail(invalidEmail);
            
            System.out.println("Step 2: Clicking subscribe button");
            newsletterPage.clickSubscribe();
            
            System.out.println("Step 3: Checking if validation message is displayed");
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            
            System.out.println("Step 4: Retrieving HTML5 validation message");
            String message = newsletterPage.getEmailInputValidationMessage();
            
            System.out.println("Step 5: Verifying validation message exists");
            System.out.println("Message Displayed: " + messageDisplayed + ", Message Text: " + message);
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for invalid email format");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED at test step.");
            System.out.println("Error Type: " + e.getClass().getSimpleName());
            System.out.println("Error Message: " + e.getMessage());
            System.out.println("Email Tested: " + invalidEmail + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior for an incomplete email.
     */
    @Test
    void userSeesValidationMessageForIncompleteEmail() {
        System.out.println("[TEST] Incomplete Email Validation Test");
        String incompleteEmail = "test@";
        
        try {
            System.out.println("Step 1: Entering incomplete email: " + incompleteEmail);
            newsletterPage.enterEmail(incompleteEmail);
            
            System.out.println("Step 2: Clicking subscribe button");
            newsletterPage.clickSubscribe();
            
            System.out.println("Step 3: Checking if validation message is displayed");
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            
            System.out.println("Step 4: Retrieving HTML5 validation message");
            String message = newsletterPage.getEmailInputValidationMessage();
            
            System.out.println("Step 5: Verifying validation message exists");
            System.out.println("Message Displayed: " + messageDisplayed + ", Message Text: " + message);
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for incomplete email");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED at test step.");
            System.out.println("Error Type: " + e.getClass().getSimpleName());
            System.out.println("Error Message: " + e.getMessage());
            System.out.println("Email Tested: " + incompleteEmail + "\n");
            throw e;
        }
    }

    /**
     * Verifies validation behavior for an email without a proper domain.
     */
    @Test
    void userSeesValidationMessageForEmailWithoutDomain() {
        System.out.println("[TEST] Email Without Domain Validation Test");
        String emailWithoutDomain = "test@domain";
        
        try {
            System.out.println("Step 1: Entering email without proper domain: " + emailWithoutDomain);
            newsletterPage.enterEmail(emailWithoutDomain);
            
            System.out.println("Step 2: Clicking subscribe button");
            newsletterPage.clickSubscribe();
            
            System.out.println("Step 3: Checking if validation message is displayed");
            boolean messageDisplayed = newsletterPage.isValidationMessageDisplayed();
            
            System.out.println("Step 4: Retrieving HTML5 validation message");
            String message = newsletterPage.getEmailInputValidationMessage();
            
            System.out.println("Step 5: Verifying validation message exists");
            System.out.println("Message Displayed: " + messageDisplayed + ", Message Text: " + message);
            // HTML5 validation prevents form submission
            assertTrue(messageDisplayed || !message.isEmpty(),
                       "Validation message should be displayed for email without proper domain");
            
            System.out.println("✅ PASSED\n");
        } catch (AssertionError | Exception e) {
            System.out.println("❌ FAILED at test step.");
            System.out.println("Error Type: " + e.getClass().getSimpleName());
            System.out.println("Error Message: " + e.getMessage());
            System.out.println("Email Tested: " + emailWithoutDomain + "\n");
            throw e;
        }
    }
}
