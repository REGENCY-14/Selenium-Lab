package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessPage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(css = "button")
    private WebElement dismissButton;

    public SuccessPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SuccessPage waitUntilLoaded() {
        wait.until(ExpectedConditions.textToBePresentInElement(body, "Thanks"));
        return this;
    }

    public String getHeadingText() {
        return body.getText();
    }

    public void dismiss() {
        dismissButton.click();
    }
}
