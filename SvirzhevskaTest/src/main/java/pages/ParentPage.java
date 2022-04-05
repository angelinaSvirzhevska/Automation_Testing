package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void enterTextToElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element ");
        } catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }
    protected void  clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    protected  boolean isElementPresent(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            if(state){
                logger.info("Element present");
            } else {
                logger.info("Element is not present");
            }
            return state;
        } catch (Exception e){
            logger.info("Element is not present");
            return false;
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work wit element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
