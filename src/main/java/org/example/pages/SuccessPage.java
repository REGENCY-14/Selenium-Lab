package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object representing the success page after form submission.
 */
public class SuccessPage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(css = "button")
    private WebElement dismissButton;

    /**
     * Creates a page object bound to the current browser session.
     *
     * @param driver the WebDriver instance
     * @param wait the WebDriverWait instance
     */
    public SuccessPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Waits until the success message appears in the page body.
     *
     * @return this page instance for chaining
     */
    public SuccessPage waitUntilLoaded() {
        wait.until(ExpectedConditions.textToBePresentInElement(body, "Thanks"));
        return this;
    }

    /**
     * Returns all text from the page body.
     *
     * @return body text content
     */
    public String getHeadingText() {
        return body.getText();
    }

    /**
     * Clicks the dismiss button on the success page.
     */
    public void dismiss() {
        dismissButton.click();
    }
}
