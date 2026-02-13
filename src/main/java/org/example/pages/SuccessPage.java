package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessPage extends BasePage {

    @FindBy(css = "h1")
    private WebElement heading;

    @FindBy(css = "button")
    private WebElement dismissButton;

    public SuccessPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SuccessPage waitUntilLoaded() {
        waitUntilVisible(heading);
        return this;
    }

    public String getHeadingText() {
        return heading.getText();
    }

    public void dismiss() {
        dismissButton.click();
    }
}
