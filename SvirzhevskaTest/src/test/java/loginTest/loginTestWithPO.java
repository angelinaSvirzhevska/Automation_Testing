package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class loginTestWithPO extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_LOGIN);
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Error message is visible", loginPage.isErrorMessageSignInPresent(), true);
    }

}
