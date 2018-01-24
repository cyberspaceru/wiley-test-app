package com.wiley.tatsiy.testapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by cyberspace on 1/23/2018.
 */
@PageEntry(title = "Students | Wiley")
public class StudentsPage extends HeaderPage {

    @ElementTitle("Header")
    @FindBy(xpath = "*//P[contains(text(), 'Students')]")
    public WebElement header;

    public StudentsPage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

}
