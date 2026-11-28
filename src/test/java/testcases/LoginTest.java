package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod

    public void setup(){

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    @DataProvider(name = "logindata")

    public Object[][] getLogindata() throws Exception {

        return ExcelUtils.getTestData("src/test/resources/Testdata/Logindata.xlsx", "Logindata");
    }

        // Step 3: Execute login test using Excel data
        @Test(dataProvider = "logindata")
        public void loginTest(
                String testCase,
                String username,
                String password,
                String expected) {

            System.out.println("Executing Test Case: " + testCase);

            driver.findElement(By.id("user-name")).sendKeys(username);

            driver.findElement(By.id("password")).sendKeys(password);

            driver.findElement(By.id("login-button")).click();

            if (expected.equalsIgnoreCase("success")) {

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

                String actualTitle = driver.findElement(By.className("title")).getText();

                Assert.assertEquals(actualTitle, "Products");

                System.out.println("Login successful");

            } else {

                String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))).getText();

                if (expected.equalsIgnoreCase("locked")) {

                    Assert.assertTrue(errorMessage.toLowerCase().contains("locked out"), "Expected locked-out error message");

                } else if (expected.equalsIgnoreCase("error")) {

                    Assert.assertTrue(!errorMessage.isEmpty(), "Expected an error message");

                } else {

                    Assert.fail("Unknown expected result: " + expected);
                }

                System.out.println("Error message: " + errorMessage);
            }
        }

        // Step 4: Close browser
        @AfterMethod
        public void tearDown() {

            if (driver != null) {
                driver.quit();
            }
        }
    }

