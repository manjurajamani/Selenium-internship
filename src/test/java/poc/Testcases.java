package poc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Testcases {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod

    public void browserlaunch()
    {
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

        driver.get("https://www.saucedemo.com/");

    }

    // TC_LOGIN_01 – Valid login

    @Test

    public void login() {

        login("standard_user", "secret_sauce");
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        Assert.assertEquals(title.getText(), "Products", "Login did not reach Products page");
    }

    // TC_LOGIN_03 – Invalid password
    @Test
    public void testInvalidPassword() {
        login("standard_user", "wrong_password");

        Assert.assertTrue(getErrorText().contains("Username and password do not match"),
                "Expected invalid-credentials error");
        wait = new WebDriverWait(driver, Duration.ofSeconds(7000));
    }

    // TC09 – Empty username
    @Test
    public void testEmptyUsername() {
        login("", "secret_sauce");
        Assert.assertTrue(getErrorText().contains("Username is required"),
                "Expected 'Username is required' error");
        wait = new WebDriverWait(driver, Duration.ofSeconds(7000));
    }
    // TC10 – Empty password
    @Test
    public void testEmptyPassword() {
        login("standard_user", "");

        wait = new WebDriverWait(driver, Duration.ofSeconds(7000));
        Assert.assertTrue(getErrorText().contains("Password is required"),
                "Expected 'Password is required' error");
    }

    // TC12 – Locked user
    @Test
    public void testLockedUser() {
        login("locked_out_user", "secret_sauce");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        Assert.assertTrue(getErrorText().contains("locked out"),
                "Expected locked-out error");
    }

    private void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    private String getErrorText() {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[@data-test='error']")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        return error.getText();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}



