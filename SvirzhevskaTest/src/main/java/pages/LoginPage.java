package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;



import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private TextBlock errorMessageSignIn;

    @FindBy(xpath = ".//*[@id = 'username-register']")
    private TextInput loginRegistration;

    @FindBy(xpath = ".//*[@id = 'email-register']")
    private TextInput emailRegistration;

    @FindBy(xpath = ".//*[@id = 'password-register']")
    private TextInput passwordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUp;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List <WebElement> listOfErrors;


    final String errorMessagesRegistrationLocator =
            ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }


    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isErrorMessageSignInPresent() {
        return isElementPresent(errorMessageSignIn);
    }


    public void openLoginPage() {
        try {
            webDriver.get(configProperties.base_url());
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


    public void checkErrors(String messages) {
        String [] messagesArray = messages.split(";");
        webDriverWait10.withMessage("NumberOfMessages").until(ExpectedConditions
                .numberOfElementsToBe(By.xpath(errorMessagesRegistrationLocator), messagesArray.length ));
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions.assertThat(messagesArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

}
