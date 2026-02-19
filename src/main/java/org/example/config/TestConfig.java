package org.example.config;

/**
 * Holds configuration values used by UI tests.
 */
public class TestConfig {
    private final String baseUrl;
    private final boolean headless;
    private final long timeoutSeconds;
    private final long delayMillis;

    /**
     * Creates a configuration object with explicit values.
     *
     * @param baseUrl the base URL for the tests
     * @param headless whether to run the browser in headless mode
     * @param timeoutSeconds explicit wait timeout in seconds
     * @param delayMillis delay between actions in milliseconds
     */
    public TestConfig(String baseUrl, boolean headless, long timeoutSeconds, long delayMillis) {
        this.baseUrl = baseUrl;
        this.headless = headless;
        this.timeoutSeconds = timeoutSeconds;
        this.delayMillis = delayMillis;
    }

    /**
     * Loads configuration from system properties with defaults.
     *
     * @return a populated configuration instance
     */
    public static TestConfig fromSystemProperties() {
        String baseUrl = System.getProperty("baseUrl", "https://super-florentine-1aef16.netlify.app/");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        long timeoutSeconds = Long.parseLong(System.getProperty("timeoutSeconds", "10"));
        long delayMillis = Long.parseLong(System.getProperty("delayMillis", "1000"));
        return new TestConfig(baseUrl, headless, timeoutSeconds, delayMillis);
    }

    /**
     * @return the base URL for the tests
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @return true when headless mode is enabled
     */
    public boolean isHeadless() {
        return headless;
    }

    /**
     * @return explicit wait timeout in seconds
     */
    public long getTimeoutSeconds() {
        return timeoutSeconds;
    }

    /**
     * @return delay between actions in milliseconds
     */
    public long getDelayMillis() {
        return delayMillis;
    }
}
