package poc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class waits {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        System.out.println("Page Titile " + " " + driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        WebElement textinput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-text-id")));
        textinput.click();
        textinput.sendKeys("Manju Rajamani");
        System.out.println("Text input is filled");

        WebElement submitbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        submitbutton.click();
        System.out.println("Submitted");

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        System.out.println("Form submitted: " + message.getText());

        driver.quit();
    }
}