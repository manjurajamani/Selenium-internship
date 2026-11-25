package poc;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.devtools.v128.filesystem.model.File;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;


public class Test_Framework_BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod

    public void setup() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) { // if the test failed
            takeScreenshot(result.getName());            // take a screenshot
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