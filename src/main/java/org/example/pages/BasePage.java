package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base page object with shared driver and wait utilities.
 */
public abstract class BasePage {

    protected final WebDriver driver;

    protected final WebDriverWait wait;

    protected long delayMillis = 1000;


    /**
     * Initializes common page state and web element bindings.
     *
     * @param driver the WebDriver instance
     * @param wait the WebDriverWait instance
     */
    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


    /**
     * Sets the delay used by {@link #sleep()}.
     *
     * @param delayMillis delay between actions in milliseconds
     */
    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }


    /**
     * Pauses execution for the provided duration.
     *
     * @param millis duration in milliseconds
     */
    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Pauses execution using {@link #delayMillis}.
     */
    protected void sleep() {
        sleep(delayMillis);
    }


    /**
     * Navigates to the provided URL.
     *
     * @param url destination URL
     */
    public void open(String url) {
        driver.get(url);
    }


    /**
     * Waits until the given element is visible.
     *
     * @param element the element to wait for
     */
    protected void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
