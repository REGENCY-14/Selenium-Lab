package org.example.config;

public class TestConfig {
    private final String baseUrl;
    private final boolean headless;
    private final long timeoutSeconds;
    private final long delayMillis;

    public TestConfig(String baseUrl, boolean headless, long timeoutSeconds, long delayMillis) {
        this.baseUrl = baseUrl;
        this.headless = headless;
        this.timeoutSeconds = timeoutSeconds;
        this.delayMillis = delayMillis;
    }

    public static TestConfig fromSystemProperties() {
        String baseUrl = System.getProperty("baseUrl", "https://super-florentine-1aef16.netlify.app/");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        long timeoutSeconds = Long.parseLong(System.getProperty("timeoutSeconds", "10"));
        long delayMillis = Long.parseLong(System.getProperty("delayMillis", "1000"));
        return new TestConfig(baseUrl, headless, timeoutSeconds, delayMillis);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isHeadless() {
        return headless;
    }

    public long getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public long getDelayMillis() {
        return delayMillis;
    }
}
