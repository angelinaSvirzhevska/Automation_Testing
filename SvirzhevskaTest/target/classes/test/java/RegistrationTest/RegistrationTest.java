package RegistrationTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void checkValidationErrorsOnRegistrationForm(){
        String locator = loginPage.errorMessagesRegistrationLocator;
        final String errorMessages = "Username must be at least 3 characters.;" +
                "You must provide a valid email address.;" +
                "Password must be at least 12 characters.";
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPasswordRegistration("123");
        loginPage.clickOnButtonSignUp();
        loginPage.checkErrors(locator, errorMessages);
    }

    @Test
    public void  invalidRegistrationWithValidPass(){
        String locator = loginPage.errorMessagesRegistrationLocator;
        loginPage.fillRegistrationFormAndClick("","", TestData.VALID_PASSWORD);
        loginPage.checkErrors(locator,"Username must be at least 3 characters.;" +
                "You must provide a valid email address.");
    }
}
