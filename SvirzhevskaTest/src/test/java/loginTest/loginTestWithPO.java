package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class loginTestWithPO extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
    }
}
