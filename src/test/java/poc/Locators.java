package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Locators {

    public static void main(String[] args){

        WebDriver driver =new ChromeDriver();

        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("Laptop");
        searchBox.sendKeys(Keys.ENTER);

        WebElement Xpath = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']"));
        Xpath.click();

        WebElement CssSelector=driver.findElement(By.cssSelector("a[class='a-dropdown-link a-active']"));
        CssSelector.click();

        System.out.println("Dropdown is clicked");

//         WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstLaptop = driver.findElement( By.xpath("//span[contains(text(),'AI Laptop with Office 365')]"));
        firstLaptop.click();

        driver.quit();
    }
}

