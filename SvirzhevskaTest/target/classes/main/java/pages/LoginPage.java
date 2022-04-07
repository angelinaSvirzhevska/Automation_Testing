package pages;

import libs.TestData;
import org.junit.Assert;
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
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> errorMessagesRegistration;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isErrorMessageSignInPresent() {
        return isElementPresent(errorMessageSignIn);
    }

    public boolean isErrorMessageSignUpPresent(int index) {
        return isElementPresent(errorMessagesRegistration.get(index));
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


    public int countRegistrationErrors() {
        boolean error;
        int errorCount = 0;
        for (int i = 0; i < errorMessagesRegistration.size(); i++) {
            error = isErrorMessageSignUpPresent(i);
            if (error) {
                errorCount += 1;
            }
        }
        return errorCount;
    }


    public boolean isErrorsEquals(String messages) {
        String [] messagesArray = messages.split(";");
        boolean resultOfCheck = true;
        if(messagesArray.length == countRegistrationErrors()){
            for (int i = 0; i < messagesArray.length - 1; i++) {
                if (!messagesArray[i].equals(errorMessagesRegistration.get(i).getText())){
                    resultOfCheck = false;
                }
            }

        }
        return resultOfCheck;
    }


    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

}
