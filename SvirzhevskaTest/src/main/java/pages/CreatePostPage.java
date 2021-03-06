package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='title']")
    private TextInput inputTitle;
    @FindBy(xpath = ".//textarea[@name='body']")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSave;
    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;
    @FindBy (xpath = ".//input[@id='”UniquePost”']")
    private CheckBox checkboxUniquePost;

    private String dropDownVariantsLocator = ".//option[text() ='%s']";

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
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

    public CreatePostPage selectTextInDDSelectValue(String text) {
        selectTextInDD(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDD(dropDownSelectValue, value);
        return this;
    }

    public  CreatePostPage setCheckboxStateInUniquePost(String expectedState){
        setCheckboxState(checkboxUniquePost, expectedState);
        return this;
    }

    public CreatePostPage selectTextInDropDownByClick(String text){
        clickOnElement(dropDownSelectValue);
        WebElement option = webDriver.findElement(By.xpath(String.format(dropDownVariantsLocator, text)));
        clickOnElement(option);
        return this;
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        return this;
    }
}
