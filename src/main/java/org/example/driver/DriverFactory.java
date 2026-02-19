package org.example.driver;

import org.example.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Factory class responsible for creating and configuring WebDriver instances.
 * Provides methods to instantiate browser drivers with appropriate options and settings.
 */
public class DriverFactory {

    /**
    * Creates and configures a ChromeDriver instance based on the provided test configuration.
    *
    * <p>When headless mode is enabled in the configuration, the following Chrome options are applied:
    * <ul>
    *   <li>--headless=new: Enables modern headless Chrome mode</li>
    *   <li>--window-size=1200,800: Sets browser window dimensions</li>
    *   <li>--disable-gpu: Disables GPU hardware acceleration</li>
    *   <li>--no-sandbox: Disables Chrome sandbox for compatibility in CI/CD environments</li>
    *   <li>--disable-dev-shm-usage: Prevents shared memory issues in containerized environments</li>
    * </ul>
    *
     * @param config the test configuration containing browser settings such as headless mode
     * @return a configured ChromeDriver instance ready for use
     * @see TestConfig
     */
    public WebDriver createChromeDriver(TestConfig config) {
        ChromeOptions options = new ChromeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1200,800");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        return new ChromeDriver(options);
    }
}
