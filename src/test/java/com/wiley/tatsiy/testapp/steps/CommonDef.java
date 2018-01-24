package com.wiley.tatsiy.testapp.steps;

import com.wiley.tatsiy.testapp.util.XBuilder;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;
import ru.sbtqa.tag.pagefactory.support.Environment;

import java.util.List;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class CommonDef {
    private static final Logger LOGGER = Logger.getLogger(CommonDef.class);

    @And("^user is on the page \"(.*?)\"$")
    public void openPage(String title) throws PageInitializationException {
        if (PageFactory.getEnvironment() != Environment.MOBILE && !PageFactory.getWebDriver().getWindowHandles().isEmpty()) {
            for (String windowHandle : PageFactory.getWebDriver().getWindowHandles()) {
                PageFactory.getWebDriver().switchTo().window(windowHandle);
            }
        }
        PageFactory.getInstance().getPage(title);
    }

    @And("^user \\$([^\"]*)$")
    public void userActionNoParam(String action) throws PageInitializationException, NoSuchMethodException {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action);
    }

    @And("^user \\$([^\"]*) \"([^\"]*)\"$")
    public void userActionOneParam(String action, String param) throws PageInitializationException, NoSuchMethodException {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, param);
    }

    @And("^user \\$([^\"]*) \"([^\"]*)\" \"([^\"]*)\"$")
    public void userActionTwoParams(String action, String param1, String param2) throws PageInitializationException, NoSuchMethodException {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, param1, param2);
    }

    @And("^element \"(.*?)\" is displayed$")
    public void elementIsDisplayed(String title) throws PageException, InterruptedException {
        WebElement webElement = PageFactory.getInstance().getCurrentPage().getElementByTitle(title);
        Assert.assertTrue("Element with name \"" + title + "\" not displayed on the page.", webElement.isDisplayed());
    }

    @And("^current URL is \"(.*?)\"$")
    public void checkCurrentUrl(String url) {
        String actual = PageFactory.getDriver().getCurrentUrl();
        Assert.assertTrue("Current URL not equals \"" + url + "\", actual value is: " + actual, actual.equalsIgnoreCase(url));
    }

    @And("^current page has the link: \"([^\"]*)\" \"([^\"]*)\"$")
    public void linkExists(String url, String text) {
        List<WebElement> links = PageFactory.getDriver().findElements(XBuilder.forA(url));
        WebElement result = links.stream().filter(x -> text.equalsIgnoreCase(x.getText())).findFirst().orElse(null);
        Assert.assertNotNull("The link(" + url + ", " + text + ") not exists on the current page.", result);
    }
}
