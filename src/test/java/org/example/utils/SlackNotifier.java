package org.example.utils;

/**
 * Sends detailed test results to Slack webhooks.
 * Ensures consistent, detailed notifications for both passed and failed tests.
 */
public class SlackNotifier {

    private final String webhookUrl;

    /**
     * Creates a Slack notifier with the provided webhook URL.
     *
     * @param webhookUrl the Slack incoming webhook URL
     */
    public SlackNotifier(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    /**
     * Sends a passed test notification to Slack.
     *
     * @param testName the test name
     * @param steps executed steps
     * @param testData test data used
     */
    public void notifyTestPassed(String testName, String[] steps, String testData) {
        if (webhookUrl == null || webhookUrl.isEmpty()) {
            System.out.println("[SlackNotifier] Webhook URL not configured, skipping notification");
            return;
        }

        String message = TestResultFormatter.formatPassedTest(testName, steps, testData);
        sendToSlack(message);
    }

    /**
     * Sends a failed test notification to Slack with detailed error information.
     *
     * @param testName the test name
     * @param steps executed steps
     * @param failedAtStep the step number where failure occurred
     * @param errorType exception type
     * @param errorMessage exception message
     * @param testData test data used
     */
    public void notifyTestFailed(String testName, String[] steps, int failedAtStep,
                                 String errorType, String errorMessage, String testData) {
        if (webhookUrl == null || webhookUrl.isEmpty()) {
            System.out.println("[SlackNotifier] Webhook URL not configured, skipping notification");
            return;
        }

        String message = TestResultFormatter.formatFailedTest(testName, steps, failedAtStep,
                errorType, errorMessage, testData);
        sendToSlack(message);
    }

    /**
     * Sends a message to the configured Slack webhook.
     *
     * @param message the message to send
     */
    private void sendToSlack(String message) {
        try {
            // In a real scenario, this would use HttpClient to POST to the webhook
            // For now, we log the message that would be sent
            System.out.println("[SlackNotifier] Would send to Slack:\n" + message);
        } catch (Exception e) {
            System.err.println("[SlackNotifier] Failed to send notification: " + e.getMessage());
        }
    }
}
