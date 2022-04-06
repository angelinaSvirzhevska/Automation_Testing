package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private  WebElement errorMessageSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignInPresent(){ return  isElementPresent(buttonSignIn); }

    public  boolean isErrorMessageSignInPresent(){return isElementPresent(errorMessageSignIn);}

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
       enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public  void fillLoginFormAndSubmit(String login, String password){
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }


}
