package poc;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;

public class Bug_reports_trail {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest

    public void Setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.amazon.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

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

    @Test

    public void testpriceMismatch() throws IOException {

        WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

        searchbox.sendKeys("Laptop");

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nav-search-submit nav-sprite']")));

        search.click();

        WebElement Firstpoductprice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-price-whole']")));
        Firstpoductprice.getText();
        System.out.println("First product price:" + Firstpoductprice.getText());


//        WebElement Firstproduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[@aria-label='Sponsored Ad - 15.6&quot; FHD Laptop, Pentium 6500Y, 16GB RAM 512GB SSD PC Computer']")));
//        Firstproduct.click();

//         System.out.println("Firstproduct name:" + Firstproduct.getText());
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Laptop')]")));
//        firstProduct.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        js.executeScript("arguments[0].click();", firstProduct);

        WebElement productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-price-whole']")));
        String pagePrice = productPrice.getText();

//        System.out.println("Search page price: " + Firstpoductprice);
        System.out.println("Product page price: " + pagePrice);

        takeScreenshot("amazon-price-mismatch");

        Assert.assertEquals(pagePrice, Firstpoductprice, "BUG-01: Price mismatch between search page and product page");
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
        System.out.println("Page title: " + actual);

        // Take the screenshot before the assert, so the page is saved
        takeScreenshot("failing-example");

        // Deliberately wrong expected value — this will FAIL
        Assert.assertEquals(actual, "Wrong Title", "Example failure: title is not 'Wrong Title'");
    }
}