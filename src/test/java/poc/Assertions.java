package poc;//package selenium-setup.Selenium-Setup
//package Selenium-internship.Selenium-Setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assertions {

    WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test
    public void testPageTitle() {
        Assert.assertEquals(driver.getTitle(), "Web form", "Page title is wrong");
    }

    @Test
    public void testFormSubmission() {
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys("Manju Rajamani");
        System.out.println("User name is written successfully");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Received!", "Confirmation message is wrong");

        Assert.assertTrue(driver.getCurrentUrl().contains("submitted-form"),
                "URL did not change to the submitted page");
    }

        @AfterTest
        public void tearDown() {
            driver.quit();
        }
    }

