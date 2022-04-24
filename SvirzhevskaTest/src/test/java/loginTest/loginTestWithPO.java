package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class loginTestWithPO extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN + "111");
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }


    @Test
    @Parameters({
            "auto, 123",
            ",",
            "tr, 123456qwerty"
    })
    @TestCaseName("Invalid login: login = {0}, password = {1}")
    public void invalidLogin(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Error message is visible", loginPage.isErrorMessageSignInPresent(), true);
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

}
