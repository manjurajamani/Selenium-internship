package poc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

//import static sun.security.jgss.GSSUtil.login;

public class Test_Framework_LoginTest extends Test_Framework_BaseTest{

//    WebDriver driver ;
//    WebDriverWait wait;


    @Test(groups = {"smoke","regression"})

    public void testvalidlogin(){

       login("standard_user", "secret_sauce");

        wait=new WebDriverWait(driver, Duration.ofSeconds(2000));

         WebElement title=driver.findElement(By.xpath("//span[@class='title']"));

         System.out.println("Title:" + title.getText());

         if(title.getText().equals("Products")){

             System.out.println("Pass login reached product page");

         }
         else{
             throw new AssertionError("Fail:Login didnot react the product page");
         }
    }
    @Test(groups = {"regression"})

    public void testInvalidPassword() {

        login("standard_user", "wrong_password");

        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));

        System.out.println(error.getText());

        if (error.getText().contains("Epic sadface: Username and password do not match any user in this service"))
        {
            System.out.println("PASS: Invalid password error shown");
        }
        else{
            throw new AssertionError("FAIL: Expected invalid-credentials error. Actual error: " + error.getText());

        }
    }

    @Test(groups = {"regression"})

    public void test_emptyusername(){

        login("" , "secret_sauce");
        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));

        System.out.println(error.getText());

        if(error.getText().contains("Username is required")){

            System.out.println("Pass:Empty user name shown");
        }
        else{
            throw new AssertionError("Fail:Expected 'Username is required' error. Actual error: " + error.getText());
        }

    }

    @Test(groups = {"regression"})
    public void testLockedUser() {
        login("locked_out_user", "secret_sauce");

        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));

        System.out.println(error.getText());

        if(error.getText().contains("locked out")) {

            System.out.println("Pass:Locked out error shown");

        }
        else{

            throw new AssertionError("Fail:Expected locked-out error. Actual error:" + error.getText());
        }
    }
    private void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username); // type the username

        driver.findElement(By.id("password")).sendKeys(password);  // type the password

        driver.findElement(By.id("login-button")).click();         // click the Login button
    }

}


