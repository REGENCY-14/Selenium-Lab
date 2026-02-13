package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsletterPage extends BasePage {

    @FindBy(css = "input[type='email']")
    private WebElement emailInput;

    @FindBy(css = "button[type='submit']")
    private WebElement subscribeButton;

    @FindBy(css = "label[for='email']")
    private WebElement validationMessage;

    public NewsletterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterEmail(String email) {
        waitUntilVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickSubscribe() {
        subscribeButton.click();
    }

    public void subscribeWithEmail(String email) {
        enterEmail(email);
        clickSubscribe();
    }

    public SuccessPage waitForSuccess() {
        SuccessPage successPage = new SuccessPage(driver, wait);
        successPage.waitUntilLoaded();
        return successPage;
    }

    public boolean isValidationMessageDisplayed() {
        try {
            return validationMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getValidationMessage() {
        waitUntilVisible(validationMessage);
        return validationMessage.getText();
    }

    public String getEmailInputValidationMessage() {
        return emailInput.getAttribute("validationMessage");
    }
}
