package poc;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

//import static sun.security.jgss.GSSUtil.login;
//

public class ScreenshotOn_Failure {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod

    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }
    @Test

        public void testValidLogin() throws InterruptedException {
            login("standard_user", "secret_sauce");
            wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            Assert.assertEquals(title.getText(), "Products", "Login did not reach Products page");
        }
    @Test
    public void testInvalidLogin() throws InterruptedException {
        login("standard_user", "wrong_password");
        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        Assert.assertTrue(error.getText().contains("Wrong message"),
                "Expected invalid-credentials error");
    }

    private void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
       if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        driver.quit();
    }
    // Takes a screenshot and saves it in the screenshots folder
    private void takeScreenshot(String testName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // take the screenshot
        Path destination = Path.of("screenshots", testName + ".png"); // where to save it
        Files.createDirectories(destination.getParent()); // create the folder if it is missing
        Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING); // save the file
        System.out.println("Screenshot saved: " + destination.toAbsolutePath()); // print the file location
    }

}

