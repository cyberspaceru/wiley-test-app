package com.wiley.tatsiy.testapp.steps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class StepsDefinitionHelper {

    protected <T> T getElementByTitle(String title) throws PageException {
        return (T) PageFactory.getInstance().getCurrentPage().getElementByTitle(title);
    }

    protected void focusTo(WebElement element) {
        new Actions(PageFactory.getDriver()).moveToElement(element).perform();
    }

}
