package org.example.utils;

/**
 * Formats test results for detailed Slack notifications.
 * Ensures both passed and failed tests have consistent, detailed output.
 */
public class TestResultFormatter {

    private static final String PASS_ICON = "✅";
    private static final String FAIL_ICON = "❌";
    private static final String STEP_PREFIX = "📌";

    /**
     * Formats a passed test result for Slack.
     *
     * @param testName the name of the test
     * @param steps list of steps executed during the test
     * @param testData relevant test data (e.g., email used)
     * @return formatted message for Slack
     */
    public static String formatPassedTest(String testName, String[] steps, String testData) {
        StringBuilder message = new StringBuilder();
        message.append(PASS_ICON).append(" *TEST PASSED*\n");
        message.append("*Test:* ").append(testName).append("\n\n");
        message.append("*Execution Steps:*\n");

        for (int i = 0; i < steps.length; i++) {
            message.append(STEP_PREFIX).append(" Step ").append(i + 1).append(": ").append(steps[i]).append("\n");
        }

        if (testData != null && !testData.isEmpty()) {
            message.append("\n*Test Data:*\n");
            message.append("• ").append(testData).append("\n");
        }

        message.append("\n*Status:* PASSED\n");
        message.append("*Time:* ").append(System.currentTimeMillis()).append("\n");

        return message.toString();
    }

    /**
     * Formats a failed test result for Slack.
     *
     * @param testName the name of the test
     * @param steps list of steps executed before failure
     * @param failedAtStep the step number where failure occurred
     * @param errorType the type of exception (e.g., NoSuchElementException)
     * @param errorMessage the error message
     * @param testData relevant test data (e.g., email used)
     * @return formatted message for Slack
     */
    public static String formatFailedTest(String testName, String[] steps, int failedAtStep,
                                          String errorType, String errorMessage, String testData) {
        StringBuilder message = new StringBuilder();
        message.append(FAIL_ICON).append(" *TEST FAILED*\n");
        message.append("*Test:* ").append(testName).append("\n\n");
        message.append("*Execution Steps:*\n");

        for (int i = 0; i < steps.length; i++) {
            if (i + 1 == failedAtStep) {
                message.append("❗ Step ").append(i + 1).append(": ").append(steps[i])
                        .append(" ← *FAILED HERE*\n");
            } else if (i + 1 < failedAtStep) {
                message.append(STEP_PREFIX).append(" Step ").append(i + 1).append(": ").append(steps[i])
                        .append(" ✓\n");
            } else {
                message.append("⏭️ Step ").append(i + 1).append(": ").append(steps[i]).append(" (not executed)\n");
            }
        }

        message.append("\n*Error Details:*\n");
        message.append("• *Error Type:* ").append(errorType).append("\n");
        message.append("• *Error Message:* ").append(errorMessage).append("\n");

        if (testData != null && !testData.isEmpty()) {
            message.append("\n*Test Data:*\n");
            message.append("• ").append(testData).append("\n");
        }

        message.append("\n*Status:* FAILED\n");
        message.append("*Failure Step:* ").append(failedAtStep).append(" of ").append(steps.length).append("\n");
        message.append("*Time:* ").append(System.currentTimeMillis()).append("\n");

        return message.toString();
    }

    /**
     * Formats a simple test summary for Slack.
     *
     * @param testName the name of the test
     * @param passed whether the test passed
     * @param message the message to display
     * @return formatted message for Slack
     */
    public static String formatTestSummary(String testName, boolean passed, String message) {
        StringBuilder summary = new StringBuilder();
        if (passed) {
            summary.append(PASS_ICON).append(" *TEST PASSED*\n");
        } else {
            summary.append(FAIL_ICON).append(" *TEST FAILED*\n");
        }
        summary.append("*Test:* ").append(testName).append("\n");
        summary.append("*Details:* ").append(message).append("\n");
        return summary.toString();
    }
}
