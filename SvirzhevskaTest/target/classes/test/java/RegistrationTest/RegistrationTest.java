package RegistrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void checkValidationErrorsOnRegistrationForm(){
        final String errorMessages = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPasswordRegistration("123");
        loginPage.clickOnButtonSignUp();
        checkExpectedResult("Messages is not equals", loginPage.isErrorsEquals(errorMessages), true );

    }
}
