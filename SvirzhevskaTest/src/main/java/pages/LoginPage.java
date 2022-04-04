package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e){
            logger.error("Can not work with LoginPage");
            Assert.fail("Can not work with LoginPage");
            webDriver.quit();
        }
    }

    public void enterLoginInSignIn(String login) {
        try {
            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
            element.clear();
            element.sendKeys(login);
            logger.info(login + " was inputted in Sign In input login");
        } catch (Exception e){
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
            webDriver.quit();
        }
    }
}
