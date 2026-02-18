# Selenium Newsletter Automation Project

A comprehensive web automation testing project using **Selenium WebDriver**, **JUnit 5**, and **GitHub Actions** CI/CD pipeline. This project demonstrates best practices for test automation including Page Object Model (POM), explicit waits, and automated continuous integration.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Test Cases](#test-cases)
- [CI/CD Pipeline](#cicd-pipeline)
- [Project Components](#project-components)
- [Page Object Model](#page-object-model)
- [Configuration](#configuration)
- [Logging & Reporting](#logging--reporting)
- [Troubleshooting](#troubleshooting)
- [Best Practices](#best-practices)

---

## 🎯 Project Overview

This project automates testing of a **newsletter subscription feature** on a web application. It verifies:

✅ Valid email subscription success  
✅ Email field validation (empty, invalid format, incomplete)  
✅ Success page confirmation  
✅ Error message display for invalid inputs  

**Target Website:** [https://super-florentine-1aef16.netlify.app/](https://super-florentine-1aef16.netlify.app/)

**Key Features:**
- 5 comprehensive test cases
- Page Object Model for maintainability
- Explicit waits for reliability
- CI/CD integration with GitHub Actions
- Automated reporting and notifications
- Cross-platform compatibility

---

## 🛠️ Technologies Used

### Core Technologies

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 21 LTS | Programming language |
| **Selenium WebDriver** | 4.40.0 | Browser automation |
| **JUnit 5 (Jupiter)** | 5.10.2 | Test framework |
| **Maven** | 3.x | Build automation |
| **Chrome/Chromium** | Latest stable | Test browser |

### CI/CD & Automation

| Tool | Purpose |
|------|---------|
| **GitHub Actions** | CI/CD pipeline |
| **Maven Surefire** | Test execution & reporting |
| **Python** | Test result parsing |
| **Slack API** | Team notifications |
| **SMTP** | Email notifications |

### IDE & Development

| Tool | Purpose |
|------|---------|
| **IntelliJ IDEA** / **Eclipse** | Java IDE |
| **Git** | Version control |
| **GitHub** | Repository hosting |

---

## 📁 Project Structure

```
Introductiontoselenium/
│
├── README_FULL.md                          ← This file
├── pom.xml                                 ← Maven configuration
│
├── .github/
│   └── workflows/
│       └── ci.yml                          ← GitHub Actions CI/CD pipeline
│
├── src/
│   ├── main/java/org/example/
│   │   ├── App.java                        ← Main application (template)
│   │   ├── config/
│   │   │   └── TestConfig.java             ← Test configuration management
│   │   ├── driver/
│   │   │   └── DriverFactory.java          ← WebDriver creation & setup
│   │   └── pages/
│   │       ├── BasePage.java               ← Base page object class
│   │       ├── NewsletterPage.java         ← Newsletter form page object
│   │       └── SuccessPage.java            ← Success confirmation page object
│   │
│   └── test/java/org/example/
│       ├── AppTest.java                    ← Placeholder test
│       └── tests/
│           └── NewsletterSignUpTest.java   ← Main test suite (5 test cases)
│
└── target/
    ├── classes/                            ← Compiled classes
    ├── test-classes/                       ← Compiled test classes
    └── surefire-reports/                   ← Test execution reports (XML)
```

---

## ✅ Prerequisites

Before setting up the project, ensure you have:

- **Java 21 JDK** installed
  ```powershell
  java -version
  # Output: openjdk version "21.0.x"
  ```

- **Maven 3.9+** installed
  ```powershell
  mvn -version
  # Output: Apache Maven 3.9.x
  ```

- **Git** installed
  ```powershell
  git --version
  ```

- **Chrome/Chromium browser** installed
  - For headless testing (CI/CD): Handled automatically
  - For local GUI testing: Install Chrome separately

- **GitHub account** (for CI/CD pipeline)

---

## 🚀 Installation & Setup

### Step 1: Clone the Repository

```powershell
git clone https://github.com/yourusername/Introductiontoselenium.git
cd Introductiontoselenium
```

### Step 2: Verify Dependencies

Maven automatically downloads dependencies from `pom.xml`:

```powershell
mvn clean install
```

**What this does:**
- Downloads Selenium 4.40.0
- Downloads JUnit 5.10.2
- Compiles all source code
- Creates `target/` directory

### Step 3: Verify Setup

```powershell
mvn test -Dtest=AppTest
```

Expected output:
```
[INFO] Running org.example.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 🧪 Running Tests

### Run All Tests Locally

```powershell
mvn test
```

**Output:**
```
[INFO] Running org.example.tests.NewsletterSignUpTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 45.123 s
[INFO] BUILD SUCCESS
```

### Run Specific Test

```powershell
mvn test -Dtest=NewsletterSignUpTest#userCanSubscribeWithValidEmail
```

### Run with Custom Configuration

```powershell
mvn test \
  -DbaseUrl=https://yourwebsite.com \
  -Dheadless=false \
  -Dtimeout=15 \
  -Ddelay=500
```

**Configuration Options:**

| Parameter | Default | Description |
|-----------|---------|-------------|
| `baseUrl` | https://super-florentine-1aef16.netlify.app/ | Website URL to test |
| `headless` | false | Run browser invisibly |
| `timeout` | 10 | Wait timeout in seconds |
| `delay` | 1000 | Delay between actions in ms |

### Run Tests in Headless Mode

```powershell
mvn test -Dheadless=true
```

Useful for:
- CI/CD pipelines
- Running on servers without display
- Faster execution
- Resource efficiency

### View Test Reports

After running tests, review reports:

```powershell
# HTML Report (if configured)
cd target/surefire-reports
# Open: TEST-org.example.tests.NewsletterSignUpTest.xml

# Or view in IDE
Right-click project → Run "org.example.tests.NewsletterSignUpTest"
```

---

## 📊 Test Cases

### Test Suite: NewsletterSignUpTest

Located in: `src/test/java/org/example/tests/NewsletterSignUpTest.java`

---

#### **Test 1: Valid Email Subscription** ✅

```java
@Test
void userCanSubscribeWithValidEmail() {
    String testEmail = "qa+selenium@example.com";
    newsletterPage.subscribeWithEmail(testEmail);
    SuccessPage successPage = newsletterPage.waitForSuccess();
    
    String pageText = successPage.getHeadingText().toLowerCase();
    assertTrue(pageText.contains("thanks"));
    assertTrue(pageText.contains(testEmail.toLowerCase()));
}
```

**What it does:**
1. Open website
2. Enter valid email: `qa+selenium@example.com`
3. Click subscribe button
4. Wait for success page
5. Verify success message contains "thanks" and email

**Expected Result:** ✅ PASS

---

#### **Test 2: Empty Email Validation** ✅

```java
@Test
void userSeesValidationMessageForEmptyEmail() {
    newsletterPage.clickSubscribe();
    
    assertTrue(newsletterPage.isValidationMessageDisplayed() || 
               !newsletterPage.getEmailInputValidationMessage().isEmpty(),
               "Validation message should be displayed for empty email");
}
```

**What it does:**
1. Open website
2. Click subscribe WITHOUT entering email
3. Verify validation error message appears

**Expected Result:** ✅ HTML5 validation prevents form submission

---

#### **Test 3: Invalid Email Format Validation** ✅

```java
@Test
void userSeesValidationMessageForInvalidEmailFormat() {
    newsletterPage.enterEmail("123456789");  // No @ symbol
    newsletterPage.clickSubscribe();
    
    assertTrue(newsletterPage.isValidationMessageDisplayed() || 
               !newsletterPage.getEmailInputValidationMessage().isEmpty(),
               "Validation message should be displayed for invalid email format");
}
```

**What it does:**
1. Open website
2. Enter invalid email (no @ symbol): `123456789`
3. Click subscribe
4. Verify validation error appears

**Expected Result:** ✅ Validation error displayed

---

#### **Test 4: Incomplete Email Validation** ✅

```java
@Test
void userSeesValidationMessageForIncompleteEmail() {
    newsletterPage.enterEmail("test@");  // Missing domain
    newsletterPage.clickSubscribe();
    
    assertTrue(newsletterPage.isValidationMessageDisplayed() || 
               !newsletterPage.getEmailInputValidationMessage().isEmpty(),
               "Validation message should be displayed for incomplete email");
}
```

**What it does:**
1. Enter incomplete email: `test@`
2. Click subscribe
3. Verify validation error

**Expected Result:** ✅ Validation error displayed

---

#### **Test 5: Email Without Domain Extension Validation** ✅

```java
@Test
void userSeesValidationMessageForEmailWithoutDomain() {
    newsletterPage.enterEmail("test@domain");  // No .com/.org/etc
    newsletterPage.clickSubscribe();
    
    assertTrue(newsletterPage.isValidationMessageDisplayed() || 
               !newsletterPage.getEmailInputValidationMessage().isEmpty(),
               "Validation message should be displayed for email without proper domain");
}
```

**What it does:**
1. Enter email without domain extension: `test@domain`
2. Click subscribe
3. Verify validation error

**Expected Result:** ✅ Validation error displayed

---

### Test Execution Summary

```
Test Suite: NewsletterSignUpTest
├─ Test 1: userCanSubscribeWithValidEmail ..................... ✅ PASS (12s)
├─ Test 2: userSeesValidationMessageForEmptyEmail ............. ✅ PASS (8s)
├─ Test 3: userSeesValidationMessageForInvalidEmailFormat ..... ✅ PASS (9s)
├─ Test 4: userSeesValidationMessageForIncompleteEmail ........ ✅ PASS (8s)
└─ Test 5: userSeesValidationMessageForEmailWithoutDomain .... ✅ PASS (8s)

Total Tests: 5
Passed: 5 ✅
Failed: 0
Duration: ~45 seconds
```

---

## 🔄 CI/CD Pipeline

### GitHub Actions Workflow

File: `.github/workflows/ci.yml`

The pipeline automatically runs on:
- **Push events** to `main`, `dev`, or `feature/**` branches
- **Pull requests** targeting `main` or `dev` branches

### Pipeline Stages

```
┌─────────────────────────────────────┐
│  Developer pushes code              │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│  GitHub Actions Triggered           │
│  (Event: push or pull_request)      │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│  Stage 1: Environment Setup (2m)    │
│  ✅ Checkout code                   │
│  ✅ Install Chrome                  │
│  ✅ Install Java 21                 │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│  Stage 2: Test Execution (3-5m)     │
│  ✅ Compile source code             │
│  ✅ Compile test code               │
│  ✅ Run 5 Selenium tests            │
│  ✅ Generate XML reports            │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│  Stage 3: Reporting (10s)           │
│  ✅ Upload test reports             │
│  ✅ Parse test results              │
│  ✅ Extract commit info             │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│  Stage 4: Gating (instant)          │
│  ✅ Fail job if tests failed        │
│  ✅ Block PR merge if needed        │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│  Stage 5: Notifications (10s)       │
│  ✅ Slack message                   │
│  ✅ Email notification              │
│  ✅ GitHub status update            │
└─────────────────────────────────────┘

Total Duration: 5-8 minutes per run
```

### Pipeline Steps Detailed

| Step | Time | Action |
|------|------|--------|
| **Checkout** | 30s | Download repository code |
| **Chrome** | 1m | Install Chrome browser |
| **JDK** | 30s | Install Java 21 (cached) |
| **Tests** | 3-5m | Run Maven test suite |
| **Reports** | 10s | Upload XML reports |
| **Metadata** | 5s | Parse results & commits |
| **Gate** | instant | Fail if needed |
| **Slack** | 5s | Send Slack message |
| **Email** | 5s | Send email notification |

### GitHub Actions Triggers

```yaml
on:
  push:
    branches: ["main", "dev", "feature/**"]
  pull_request:
    branches: ["main", "dev"]
```

**Triggered when:**

✅ Code pushed to `main`  
✅ Code pushed to `dev`  
✅ Code pushed to any `feature/something` branch  
✅ Pull request opened/updated targeting `main`  
✅ Pull request opened/updated targeting `dev`  

**Not triggered:**

❌ Push to other branches (e.g., `bugfix/`, `research/`)  
❌ Push to non-watched branches  

### Notifications

#### Slack Notification (if configured)

```
✅ CI Success
Repository: Introductiontoselenium
Author: Zakaria Osman
Source Branch: feature/validation
Target Branch: main
Commit Message: Fix email validation
Tests Passed: 5
Tests Failed: 0
Overall Status: SUCCESS
Workflow URL: github.com/.../actions/runs/12345
```

#### Email Notification (if configured)

```
Subject: ✅ CI Success #42: Introductiontoselenium

CI Success

Repository Details
- Repository: org/Introductiontoselenium
- URL: https://github.com/org/project

Branch Details
- Source: feature/validation
- Target: main

Commit Details
- Message: Fix email validation
- SHA: a1b2c3d4e5
- Author: Zakaria Osman

Test Results
- Passed: 5
- Failed: 0
- Status: SUCCESS
```

---

## 🏗️ Project Components

### 1. Configuration Management

**File:** `src/main/java/org/example/config/TestConfig.java`

Centralized test configuration:

```java
public class TestConfig {
    private final String baseUrl;
    private final boolean headless;
    private final long timeoutSeconds;
    private final long delayMillis;
    
    public static TestConfig fromSystemProperties() {
        // Reads from system properties or uses defaults
    }
}
```

**Properties:**
- `baseUrl`: Website URL
- `headless`: Browser visibility
- `timeoutSeconds`: Element wait timeout
- `delayMillis`: Action delays

---

### 2. Driver Factory

**File:** `src/main/java/org/example/driver/DriverFactory.java`

WebDriver creation and configuration:

```java
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
```

**Chrome Options:**
- `--headless=new`: Invisible browser mode
- `--window-size`: Viewport dimensions
- `--disable-gpu`: Disable GPU acceleration
- `--no-sandbox`: Allow root execution (CI/CD)
- `--disable-dev-shm-usage`: Fix memory issues

---

### 3. Base Page Object

**File:** `src/main/java/org/example/pages/BasePage.java`

Parent class for all page objects:

```java
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    
    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    
    public void open(String url) {
        driver.get(url);
    }
    
    protected void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
```

**Functionality:**
- Initialize WebDriver & WebDriverWait
- Auto-bind `@FindBy` annotations
- Navigate to URLs
- Provide element wait methods

---

## 📄 Page Object Model

Page Objects encapsulate web page interactions:

### Newsletter Page Object

**File:** `src/main/java/org/example/pages/NewsletterPage.java`

```java
public class NewsletterPage extends BasePage {
    @FindBy(css = "input[type='email']")
    private WebElement emailInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement subscribeButton;
    
    public void enterEmail(String email) {
        waitUntilVisible(emailInput);
        emailInput.sendKeys(email);
    }
    
    public void clickSubscribe() {
        subscribeButton.click();
    }
}
```

**Benefits:**
✅ Maintainability: Change selectors in one place  
✅ Readability: Clear method names  
✅ Reusability: Use across multiple tests  
✅ Separation: Tests don't know HTML details  

### Success Page Object

**File:** `src/main/java/org/example/pages/SuccessPage.java`

```java
public class SuccessPage extends BasePage {
    @FindBy(tagName = "body")
    private WebElement body;
    
    public SuccessPage waitUntilLoaded() {
        wait.until(ExpectedConditions.textToBePresentInElement(body, "Thanks"));
        return this;
    }
    
    public String getHeadingText() {
        return body.getText();
    }
}
```

---

## ⚙️ Configuration

### Maven Configuration

**File:** `pom.xml`

#### Project Identity

```xml
<groupId>org.example</groupId>
<artifactId>demo1</artifactId>
<version>1.0-SNAPSHOT</version>
```

#### Java Version

```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

#### Dependencies

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.40.0</version>
</dependency>

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

#### Build Plugins

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

### Test Configuration

Pass parameters at runtime:

```powershell
mvn test \
  -DbaseUrl=https://yoursite.com \
  -Dheadless=true \
  -DtimeoutSeconds=15 \
  -DdelayMillis=500
```

### CI/CD Configuration

**File:** `.github/workflows/ci.yml`

```yaml
jobs:
  test:
    runs-on: ubuntu-latest
    timeout-minutes: 30
    env:
      SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK_URL }}
```

**Required GitHub Secrets:**
- `SLACK_WEBHOOK_URL` (optional)
- `SMTP_SERVER` (optional)
- `SMTP_PORT` (optional)
- `SMTP_USERNAME` (optional)
- `SMTP_PASSWORD` (optional)
- `SMTP_TO` (optional)

---

## 📊 Logging & Reporting

### Test Reports

Reports are generated in: `target/surefire-reports/`

```
target/surefire-reports/
├─ org.example.AppTest.xml
├─ org.example.tests.NewsletterSignUpTest.xml
├─ TEST-org.example.AppTest.xml
└─ TEST-org.example.tests.NewsletterSignUpTest.xml
```

### Report Contents

XML reports contain:
- Test names
- Pass/fail status
- Execution time
- Error messages
- Stack traces

### CI/CD Logs

View in GitHub Actions:
1. Go to repo → **Actions** tab
2. Click workflow run
3. Expand **Run tests** step
4. See full console output

### Local Logging

Enable verbose output:

```powershell
mvn test -X
# Shows debug information
```

---

## 🐛 Troubleshooting

### Common Issues

#### 1. Java Version Mismatch

**Error:**
```
COMPILATION ERROR: error: invalid source release
```

**Solution:**
```powershell
java -version  # Should show Java 21
javac -version
mvn clean install
```

#### 2. Chrome Driver Not Found

**Error:**
```
org.openqa.selenium.WebDriverException: 
chromedriver not found
```

**Solution:**
- Ensure Chrome is installed
- For CI/CD: Pipeline installs it automatically
- Local: Run with `mvn test`

#### 3. Tests Timeout

**Error:**
```
Timed out waiting for visibility
```

**Solution:**
```powershell
mvn test -DtimeoutSeconds=20
# Increase timeout from 10s to 20s
```

#### 4. Port Already in Use

**Error:**
```
Address already in use
```

**Solution:**
```powershell
# Check running Java processes
jps -l

# Kill process holding port 9515 (ChromeDriver default)
netstat -ano | findstr :9515
taskkill /PID <pid> /F
```

#### 5. Network Issues (CI/CD)

**Error:**
```
Connection refused / DNS lookup failed
```

**Solution:**
- Check website is accessible
- Check CI/CD runner has internet
- Check firewall rules
- Use VPN if needed

---

## ✨ Best Practices

### 1. Test Naming

Tests should clearly describe what they test:

✅ `userCanSubscribeWithValidEmail()`  
✅ `userSeesValidationMessageForEmptyEmail()`  
❌ `test1()`, `testEmail()`  

### 2. Assertions

Use clear assertion messages:

```java
assertTrue(pageText.contains("thanks"), 
    "Success message should contain 'thanks' text");

// Better than just:
assertTrue(pageText.contains("thanks"));
```

### 3. Wait Strategies

Use explicit waits, not Thread.sleep():

✅ `wait.until(ExpectedConditions.visibilityOf(element));`  
❌ `Thread.sleep(5000);`  

### 4. Page Objects

Keep page objects focused:
- One page object per page
- Only include relevant elements
- Provide high-level methods
- Hide HTML selector details

### 5. Test Independence

Each test should be independent:
- Not depend on execution order
- Have their own setup/teardown
- Not share state

### 6. CI/CD Practices

- Use feature branches for development
- Create PRs for code review
- Require tests to pass before merge
- Monitor CI pipeline status

### 7. Documentation

Keep README updated:
- Add new test descriptions
- Document configuration options
- Explain CI/CD changes
- Update troubleshooting section

### 8. Error Handling

Provide descriptive error messages:

```java
// Include what was expected
assertTrue(isValidationMessageDisplayed() || !validationMessage.isEmpty(),
    "Expected validation message for invalid email but found none");

// Show actual vs expected
assertEquals("Expected email format", actualError);
```

---

## 📚 Additional Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)
- [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

## 📞 Support & Contact

For questions or issues:
1. Check **Troubleshooting** section
2. Review GitHub Issues
3. Check CI/CD logs in Actions tab
4. Contact project maintainer

---

## 📄 License

This project is provided as an educational example for learning Selenium WebDriver automation testing.

---

## 🙏 Acknowledgments

Built with:
- Selenium WebDriver for browser automation
- JUnit 5 for testing framework
- GitHub Actions for CI/CD
- Maven for build automation

---

**Last Updated:** February 18, 2026  
**Maintainer:** Zakaria Osman  
**Repository:** https://github.com/yourusername/Introductiontoselenium
