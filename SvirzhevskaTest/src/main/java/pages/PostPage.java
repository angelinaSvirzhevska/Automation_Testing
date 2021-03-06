package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button buttonDelete;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextBlock successMessageElement;
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonMyProfile;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage(){
        checkUrlWithPattern();
        checkIsButtonDeletePresent();
        return this;
    }

    public PostPage checkIsButtonDeletePresent(){
        Assert.assertTrue("Button Delete is not present", isElementPresent(buttonDelete));
        return this;
    }

    public PostPage checkIsSuccessMessagePresent(){
        Assert.assertTrue("Success message is not present", isElementPresent(successMessageElement));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedText){
        String actualText = successMessageElement.getText();
        Assert.assertEquals("Text in message", expectedText, actualText);
        return this;
    }

    public ProfilePage clickOnButtonMyProfile(){
        clickOnElement(buttonMyProfile);
        return new ProfilePage(webDriver);
    }


    public ProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new ProfilePage(webDriver);
    }
}
