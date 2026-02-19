package org.example.tests;

import java.time.Duration;

import org.example.config.TestConfig;
import org.example.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base test class that initializes the WebDriver and shared configuration.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected TestConfig config;

    /**
     * Creates the driver and explicit wait before each test.
     */
    @BeforeEach
    void setUp() {
        config = TestConfig.fromSystemProperties();
        driver = new DriverFactory().createChromeDriver(config);
        wait = new WebDriverWait(driver, Duration.ofSeconds(config.getTimeoutSeconds()));
    }

    /**
     * Quits the driver after each test.
     */
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
