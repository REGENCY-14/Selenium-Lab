package org.example.tests;

import org.example.config.TestConfig;
import org.example.driver.DriverFactory;
import org.example.pages.NewsletterPage;
import org.example.pages.SuccessPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NewsletterSignUpTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private TestConfig config;

    @BeforeEach
    void setUp() {
        config = TestConfig.fromSystemProperties();
        driver = new DriverFactory().createChromeDriver(config);
        wait = new WebDriverWait(driver, Duration.ofSeconds(config.getTimeoutSeconds()));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void userCanSubscribeToNewsletter() {
        NewsletterPage newsletterPage = new NewsletterPage(driver, wait);
        newsletterPage.open(config.getBaseUrl());
        newsletterPage.subscribeWithEmail("qa+selenium@example.com");

        SuccessPage successPage = newsletterPage.waitForSuccess();
        assertTrue(successPage.getHeadingText().toLowerCase().contains("thanks"));
    }
}
