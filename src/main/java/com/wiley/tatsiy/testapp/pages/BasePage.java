package com.wiley.tatsiy.testapp.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.allurehelper.ParamsHelper;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.drivers.TagMobileDriver;
import ru.sbtqa.tag.pagefactory.exceptions.ElementNotFoundException;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.WaitException;
import ru.sbtqa.tag.pagefactory.extensions.DriverExtension;
import ru.sbtqa.tag.pagefactory.support.AdbConsole;
import ru.sbtqa.tag.pagefactory.support.Environment;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class BasePage extends Page {

    private static final Logger LOG = Logger.getLogger(BasePage.class);

    @Override
    @ActionTitle("click on the element by title")
    public void clickElementByTitle(String elementTitle) throws PageException {
        WebElement webElement;
        try {
            webElement = getElementByTitle(elementTitle);
            DriverExtension.waitForElementGetEnabled(webElement, PageFactory.getTimeOut());
        } catch (NoSuchElementException | WaitException | ElementNotFoundException e) {
            LOG.error("Failed to find element by title {" + elementTitle + "}", e);
            webElement = DriverExtension.waitUntilElementAppearsInDom(By.partialLinkText(elementTitle));
        }
        clickWebElement(webElement);
    }

    @Override
    @ActionTitle("fill the input field")
    public void fillField(String elementTitle, String text) throws PageException {
        WebElement webElement = getElementByTitle(elementTitle);
        webElement.click();

        if (PageFactory.getEnvironment() == Environment.WEB) {
            webElement.clear();
        }

        if (PageFactory.getEnvironment() == Environment.MOBILE && TagMobileDriver.getAppiumClickAdb()) {
            // set ADBKeyBoard as default
            AdbConsole.execute("ime set com.android.adbkeyboard/.AdbIME");
            // send broadcast intent via adb
            AdbConsole.execute(String.format("am broadcast -a ADB_INPUT_TEXT --es msg '%s'", text));
        } else {
            webElement.sendKeys(text);
        }
        ParamsHelper.addParam("\"%s\" is filled with text \"%s\"", new String[]{elementTitle, text});
    }
}
