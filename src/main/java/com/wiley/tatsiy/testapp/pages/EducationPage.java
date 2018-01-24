package com.wiley.tatsiy.testapp.pages;

import com.wiley.tatsiy.testapp.elements.SidePanel;
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
@PageEntry(title = "Education | Subjects | Wiley")
public class EducationPage extends HeaderPage {

    @ElementTitle("Header")
    @FindBy(xpath = "*//SPAN[node() = 'Education']")
    public WebElement header;

    @ElementTitle("Side Panel")
    @FindBy(xpath = "*//DIV[@class = 'side-panel']")
    public SidePanel panel;

    public EducationPage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

}
