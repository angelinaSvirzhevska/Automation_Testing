package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='title']")
    private WebElement inputTitle;
    @FindBy(xpath = ".//textarea[@name='body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSave;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public CreatePostPage checkIsInputTitlePresent(){
        Assert.assertTrue("Input title is not displayed", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String post_title) {
        enterTextToElement(inputTitle, post_title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        enterTextToElement(inputBody, bodyText);
        return this;
    }

    public PostPage clickOnTheSaveButton() {
        clickOnElement(buttonSave);
        return new PostPage(webDriver);

    }
}
