package poc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class POM_Logintest {

    WebDriver driver;
    WebDriverWait wait;
    POM_Loginpage loginpage;
    POM_productspage productpage;

    @BeforeMethod

    public void setup(){

          driver = new ChromeDriver();

          wait = new WebDriverWait(driver, Duration.ofSeconds(20));

          driver.manage().window().maximize();

          driver.get("https://www.saucedemo.com/");

          loginpage = new POM_Loginpage(driver,wait);
          productpage=new POM_productspage(driver,wait);
    }

    @Test

    public void testvalidlogin(){

        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        loginpage.Login("standard_user" , "secret_sauce");

        String title = productpage.gettitletext();
        System.out.println("Page title: " + title);                       // print it to the console

        Assert.assertEquals(title, "Products");                           // check: title must be "Products"
    }

    @Test

    public void testinvalidlogin(){

        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        loginpage.Login("standard_user", "wrong_password");

        String error = loginpage.errortext();

        System.out.println("Error massage:" + error);

        Assert.assertEquals(error,"Epic sadface: Username and password do not match any user in this service");
    }

    @Test

    public void emptyusername(){

        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        loginpage.Login("","secret_sauce");

        String error = loginpage.errortext();

        System.out.println("Error message:" + error);

        Assert.assertEquals(error,"Epic sadface: Username is required");

    }

    @Test

    public void testLockedOutUser(){

        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        loginpage.Login("locked_out_user","secret_sauce");

        String error = loginpage.errortext();

        System.out.println("Error massage:" + error);

        Assert.assertEquals(error,"Epic sadface: Sorry, this user has been locked out.");

    }

    @AfterMethod

    public void teardown(){

        driver.quit();
    }
}
