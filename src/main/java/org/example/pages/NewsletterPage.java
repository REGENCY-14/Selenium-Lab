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

    public NewsletterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void subscribeWithEmail(String email) {
        waitUntilVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        subscribeButton.click();
    }

    public SuccessPage waitForSuccess() {
        SuccessPage successPage = new SuccessPage(driver, wait);
        successPage.waitUntilLoaded();
        return successPage;
    }
}
