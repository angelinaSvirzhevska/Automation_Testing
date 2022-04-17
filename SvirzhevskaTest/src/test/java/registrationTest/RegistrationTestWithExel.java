package RegistrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ParentPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.ParentPage.configProperties;

@RunWith(Parameterized.class)
@Category(SmokeTestFilter.class)
public class RegistrationTestWithExel extends BaseTest {
    private String login, email, password, errors;

    public RegistrationTestWithExel(String login, String email, String password, String errors) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.errors = errors;
    }
    @Parameterized.Parameters
    public static Collection testData() throws IOException{
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return  new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }

    @Test
    public void checkValidationErrorsOnRegistrationForm(){
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration(login);
        loginPage.enterEmailRegistration(email);
        loginPage.enterPasswordRegistration(password);
        loginPage.clickOnButtonSignUp();
        loginPage.checkErrors(errors);
    }

}
