

package poc;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;

    public class Bug_reports1 {

        WebDriver driver;
        WebDriverWait wait;

        @BeforeTest

        public void Setup() {

            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        }

        public void takeScreenshot(String name) throws IOException {

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File folder = new File("screenshots");
            folder.mkdirs();

            File dest = new File(folder, name + ".png");
            String s = dest.getAbsolutePath();

            Files.copy(src.toPath(), dest.toPath());

            System.out.println("Screenshot saved:" + s);

        }

        // BUG-01: Username and password fields should be cleared after a failed login
        @Test

        public void testFieldsNotCleared() throws IOException {

            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            username.sendKeys("standard_user");

            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("wrong_password");

            driver.findElement(By.id("login-button")).click();

            // Read the username and password field values after the failed login
            String usernameValue = username.getAttribute("value");
            String passwordValue = password.getAttribute("value");

            takeScreenshot("fields-not-cleared");

            // Print the bug report with the real values
            System.out.println("=== BUG-01 ===");
            System.out.println("Title: Username and password fields are not cleared after a failed login");
            System.out.println("Steps: Open saucedemo.com -> username standard_user -> password wrong_password -> click Login");
            System.out.println("Expected: Error shown and both fields cleared");
            System.out.println("Actual: Error shown, but username field still contains: " + usernameValue + ", password field still contains: " + passwordValue);
            System.out.println("Severity: Medium");
            System.out.println("Screenshot: screenshots/fields-not-cleared.png");

            Assert.assertEquals(usernameValue, "", "BUG-01: Username field is not cleared after a failed login");
            Assert.assertEquals(passwordValue, "", "BUG-01: Password field is not cleared after a failed login");
        }
        // BUG-02: Products page should load within 3 seconds after login
        @Test
        public void testSlowPageLoad() throws IOException {

            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            username.sendKeys("performance_glitch_user");

            driver.findElement(By.id("password")).sendKeys("secret_sauce");

            long start = System.currentTimeMillis();
            driver.findElement(By.id("login-button")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            long loadTime = System.currentTimeMillis() - start;

            takeScreenshot("slow-page-load");

            // Print the bug report with the real values
            System.out.println("=== BUG-02 ===");
            System.out.println("Title: Products page loads very slowly after login (performance_glitch_user)");
            System.out.println("Steps: Open saucedemo.com -> username performance_glitch_user -> password secret_sauce -> click Login");
            System.out.println("Expected: Products page loads within 3 seconds");
            System.out.println("Actual: Products page took " + loadTime + " ms to load");
            System.out.println("Severity: Medium");
            System.out.println("Screenshot: screenshots/slow-page-load.png");

            Assert.assertTrue(loadTime < 3000, "BUG-02: Products page took too long to load: " + loadTime + " ms");
        }

        // BUG-03: Error message should be clear, without "Epic sadface:"
        @Test
        public void testErrorMessageWording() throws IOException {

            driver.get("https://www.saucedemo.com/");

            // Leave the username empty, enter only the password
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
            String errorText = error.getText();

            takeScreenshot("error-message-wording");

            // Print the bug report with the real values
            System.out.println("=== BUG-03 ===");
            System.out.println("Title: Error messages show 'Epic sadface:' - confusing wording for users");
            System.out.println("Steps: Open saucedemo.com -> leave username empty -> password secret_sauce -> click Login");
            System.out.println("Expected: Username is required");
            System.out.println("Actual: " + errorText);
            System.out.println("Severity: Low");
            System.out.println("Screenshot: screenshots/error-message-wording.png");

            Assert.assertEquals(errorText, "Username is required", "BUG-03: Error message wording is confusing");
        }

        // Example: a test that FAILS on purpose, so a screenshot is taken
        @Test
        public void testFailingExample() throws Exception {
            // Open SauceDemo and log in with valid credentials
            driver.get("https://www.saucedemo.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Get the page title after login
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            String actual = title.getText();

            // Take the screenshot before the assert, so the page is saved
            takeScreenshot("failing-example");

            // Print the bug report with the real values
            System.out.println("=== EXAMPLE (fails on purpose) ===");
            System.out.println("Title: Page title after login is not 'Wrong Title'");
            System.out.println("Steps: Open saucedemo.com -> login standard_user / secret_sauce -> read the page title");
            System.out.println("Expected: Wrong Title");
            System.out.println("Actual: " + actual);
            System.out.println("Severity: Low");
            System.out.println("Screenshot: screenshots/failing-example.png");

            // Deliberately wrong expected value — this will FAIL
            Assert.assertEquals(actual, "Wrong Title", "Example failure: title is not 'Wrong Title'");
        }

        @AfterTest

        public void teardown() {

            driver.quit();

        }
    }
