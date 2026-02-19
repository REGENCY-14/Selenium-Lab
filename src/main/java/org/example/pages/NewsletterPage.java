package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object for the newsletter sign-up page.
 */
public class NewsletterPage extends BasePage {

    @FindBy(css = "input[type='email']")
    private WebElement emailInput;

    @FindBy(css = "button[type= 'submit']")
    private WebElement subscribeButton;

    @FindBy(css = "label[for='email']")
    private WebElement validationMessage;

    /**
     * Creates a page object bound to the current browser session.
     *
     * @param driver the WebDriver instance
     * @param wait the WebDriverWait instance
     */
    public NewsletterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Enters the provided email into the sign-up input.
     *
     * @param email email address to type
     */
    public void enterEmail(String email) {
        waitUntilVisible(emailInput);
        sleep();
        emailInput.clear();
        emailInput.sendKeys(email);
        sleep();
    }

    /**
     * Clicks the subscribe button.
     */
    public void clickSubscribe() {
        sleep();
        subscribeButton.click();
        sleep();
    }

    /**
     * Completes the sign-up flow with the provided email.
     *
     * @param email email address to use
     */
    public void subscribeWithEmail(String email) {
        enterEmail(email);
        clickSubscribe();
    }

    /**
     * Waits for the success page to load after submission.
     *
     * @return a loaded {@link SuccessPage}
     */
    public SuccessPage waitForSuccess() {
        SuccessPage successPage = new SuccessPage(driver, wait);
        successPage.waitUntilLoaded();
        return successPage;
    }

    /**
     * Checks if the validation message element is currently displayed.
     *
     * @return true if displayed; otherwise false
     */
    public boolean isValidationMessageDisplayed() {
        try {
            return validationMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the validation message text associated with the email input.
     *
     * @return validation message text
     */
    public String getValidationMessage() {
        waitUntilVisible(validationMessage);
        return validationMessage.getText();
    }

    /**
     * Returns the browser-provided HTML5 validation message.
     *
     * @return the input validation message string
     */
    public String getEmailInputValidationMessage() {
        return emailInput.getAttribute("validationMessage");
    }
}
