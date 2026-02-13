package org.example.config;

public class TestConfig {
    private final String baseUrl;
    private final boolean headless;
    private final long timeoutSeconds;

    public TestConfig(String baseUrl, boolean headless, long timeoutSeconds) {
        this.baseUrl = baseUrl;
        this.headless = headless;
        this.timeoutSeconds = timeoutSeconds;
    }

    public static TestConfig fromSystemProperties() {
        String baseUrl = System.getProperty("baseUrl", "https://super-florentine-1aef16.netlify.app/");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        long timeoutSeconds = Long.parseLong(System.getProperty("timeoutSeconds", "10"));
        return new TestConfig(baseUrl, headless, timeoutSeconds);
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
}
