package poc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POM_Loginpage {

        WebDriver driver;
        WebDriverWait wait;

        By usernameField = By.id("user-name");
        By Passwordfield = By.id("password");
        By loginbutton = By.id("login-button");
        By errormessage = By.xpath("//h3[@data-test='error']");

public  POM_Loginpage (WebDriver driver,WebDriverWait wait){

        this.driver = driver;
        this.wait= wait;
}

public void enterusername( String username){

        WebElement usernameElement= wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.sendKeys(username);
}
public void enterpassword(String password){

        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Passwordfield));
        passwordElement.sendKeys(password);
}
public void clciklogin(){

        WebElement clickloginbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginbutton));
        clickloginbutton.click();
}
public void Login(String username ,String password){

        enterusername(username);
        enterpassword(password);
        clciklogin();
}
public String errortext(){

        WebElement errortextelement = wait.until(ExpectedConditions.visibilityOfElementLocated(errormessage));
        return errortextelement.getText();

}

}