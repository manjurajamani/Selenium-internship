package poc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasicActions {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        System.out.println("Title" + "" + driver.getTitle());

        driver.manage().window().maximize();

        System.out.println("Form is opened successfully");

        WebElement element=driver.findElement(By.id("my-text-id"));
        element.click();
        element.sendKeys("Manju Rajamani");
        System.out.println("Text is written successfully");

        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropButton = driver.findElement(By.xpath("//select[@class='form-select']"));
        dropButton.click();

        WebElement option = driver.findElement(By.xpath("//option[@value='2']"));
        option.click();
        System.out.println("drop down is clicked and selected two");

        WebElement checkbox = driver.findElement(By.id("my-check-2"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        System.out.println("Checkbox checked: " + checkbox.isSelected());

        driver.quit();
    }
}

