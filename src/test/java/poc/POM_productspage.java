package poc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class POM_productspage {

    WebDriver driver;
    WebDriverWait wait;

    By title = By.className("title");

    public POM_productspage(WebDriver driver,WebDriverWait wait){

        this.driver = driver;
        this.wait =wait;
    }

    public String gettitletext(){

        WebElement productpage = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return productpage.getText();

    }

}
