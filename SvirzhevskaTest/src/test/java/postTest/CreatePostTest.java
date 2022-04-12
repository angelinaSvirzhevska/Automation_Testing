package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
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
                .checkIsRedirectOnCreatePostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .setCheckboxStateInUniquePost("check")
                .selectTextInDropDownByClick("Частное сообщение")
                //.selectTextInDDSelectValue("Частное сообщение")
                //.selectValueInDDSelectValue("One Person")
                .clickOnTheSaveButton()
                .checkIsButtonDeletePresent()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonMyProfile()
                .checkIsPostWasAdded(POST_TITLE)
                ;
    }
    @After
    public void deletePost(){
        homePage
                .openHomepage()
                .checkIsButtonSignOutVisible()
                .clickOnButtonMyProfile()
                .deletePostWithTitleWhilePresent(POST_TITLE);
    }
}
