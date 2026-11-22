package poc;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Alerts_frames_windows {

    public static void main(String[] args) throws InterruptedException{

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(2000));

        Thread.sleep(3000);

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        Alert alert= driver.switchTo().alert();

        System.out.println("Alert text"+ " " + alert.getText());

        alert.accept();

        Thread.sleep(3000);

        String result= driver.findElement(By.id("result")).getText();

        System.out.println("Result:" + " " + result);



        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();

        Alert alert1 = driver.switchTo().alert();

        String s= alert1.getText();

        System.out.println("Alert : " + " " + s);

        Thread.sleep(3000);

        alert1.dismiss();

        String s1 = driver.findElement(By.id("result")).getText();

        System.out.println("result:" + " " + s1);



        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();

        Alert alert2 =driver.switchTo().alert();

        alert2.sendKeys("Manju");

         String s2= alert2.getText();

         System.out.println("Alert:" + " " + s2);

        Thread.sleep(3000);

         alert2.accept();

         String s3 = driver.findElement(By.id("result")).getText();

         System.out.println("Result:" + "" + s3);

        Thread.sleep(3000);



       driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr");

        WebElement element = driver.findElement(By.id("tinymce"));

        Thread.sleep(2000);

        element.clear();

        Thread.sleep(2000);

        element.sendKeys("Hello World");

        System.out.println("Edited content:" + " " + element.getText());

        driver.switchTo().defaultContent();

        String oldcontent= driver.findElement(By.tagName("h3")).getText();

        System.out.println("Default content: " + " " + oldcontent);



        driver.get("https://the-internet.herokuapp.com/windows");

        String mainWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[@target='_blank']")).click();

         String name = driver.findElement(By.xpath("//div[@class='example']")).getText();

         System.out.println("Name of the new window:" + " " + name);

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        System.out.println("New window title: " + driver.getTitle());

        Thread.sleep(7000);
        System.out.println("New window heading: " + driver.findElement(By.tagName("h3")).getText());
        Thread.sleep(7000);
        driver.close();
        driver.switchTo().window(mainWindow);
        Thread.sleep(7000);
        System.out.println("Back on main window: " + driver.getTitle());

        driver.quit();
    }
}

