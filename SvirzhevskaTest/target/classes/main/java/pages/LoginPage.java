package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement errorMessageSignIn;
    @FindBy(xpath = ".//*[@id = 'username-register']")
    private WebElement loginRegistration;
    @FindBy(xpath = ".//*[@id = 'email-register']")
    private WebElement emailRegistration;
    @FindBy(xpath = ".//*[@id = 'password-register']")
    private WebElement passwordRegistration;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;


    public String errorMessagesRegistrationLocator =
            ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isErrorMessageSignInPresent() {
        return isElementPresent(errorMessageSignIn);
    }


    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
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

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public void enterLoginRegistration(String login) {
        enterTextToElement(loginRegistration, login);
    }

    public void enterEmailRegistration(String email) {
        enterTextToElement(emailRegistration, email);
    }

    public void enterPasswordRegistration(String password) {
        enterTextToElement(passwordRegistration, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void fillRegistrationFormAndClick(String login, String email, String pass){
        openLoginPage();
        enterLoginRegistration(login);
        enterEmailRegistration(email);
        enterPasswordRegistration(pass);
        clickOnButtonSignUp();
    }


    public LoginPage checkErrors(String locator,String messages) {
        List <WebElement> listOfErrors = webDriver.findElements(By.xpath(locator));
        String [] messagesArray = messages.split(";");
        boolean resultOfCheck = true;
        if(messagesArray.length == listOfErrors.size()){
            for (int i = 0; i < messagesArray.length; i++) {
                if (!messagesArray[i].equals(listOfErrors.get(i).getText())){
                    resultOfCheck = false;
                }
            }

        } else{
            resultOfCheck = false;
        }
        Assert.assertTrue("Messages is not equal", resultOfCheck);
        return this;
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

}
