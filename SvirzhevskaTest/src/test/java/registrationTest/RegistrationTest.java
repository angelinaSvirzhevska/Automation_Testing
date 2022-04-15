package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class RegistrationTest extends BaseTest {
    @Test
    @Parameters({
            "tr, qqq, 345, Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "tr, qqq, 123456qwerty, Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors: login = {0}, email = {1}, password = {2}")
    public void checkValidationErrorsOnRegistrationForm(String login, String email, String pass, String errors){
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration(login);
        loginPage.enterEmailRegistration(email);
        loginPage.enterPasswordRegistration(pass);
        loginPage.clickOnButtonSignUp();
        loginPage.checkErrors(errors);
    }

}
