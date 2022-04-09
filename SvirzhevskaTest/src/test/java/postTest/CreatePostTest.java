package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.Test;

public class CreatePostTest  extends BaseTest {
    private static final String POST_BODY = "Angelina Body of post" + Util.getDateAndTimeFormated();
    final String POST_TITLE = "Angelina Title of post " + Util.getDateAndTimeFormated();

    @Test
    public void createPost(){

        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .clickOnTheSaveButton()
                .checkIsButtonDeletePresent()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonMyProfile()
                .checkIsPostWasAdded(POST_TITLE)
                ;




    }
}
