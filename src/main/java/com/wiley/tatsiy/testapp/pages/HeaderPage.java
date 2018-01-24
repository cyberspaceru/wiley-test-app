package com.wiley.tatsiy.testapp.pages;

import com.wiley.tatsiy.testapp.elements.NavigationMenuItem;
import com.wiley.tatsiy.testapp.elements.ResultPreview;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class HeaderPage extends BasePage {

    @ElementTitle("Logo")
    @FindBy(xpath = "*//A[@href = '/en-ru/' and ancestor::div[@class = 'main-navigation-menu']]")
    public WebElement logo;

    @ElementTitle("Change Location Confirm")
    @FindBy(xpath = "*//BUTTON[node() = 'YES']")
    public Button changeLocationConfirm;

    @ElementTitle("Search Input")
    @FindBy(xpath = "*//INPUT[@id = 'js-site-search-input']")
    public TextInput searchInput;

    @ElementTitle("Search Submit")
    @FindBy(xpath = "*//BUTTON[node() = 'Search']")
    public Button searchSubmit;

    @ElementTitle("Result Preview")
    @FindBy(xpath = "*//DIV[@id = 'ui-id-2']")
    public ResultPreview resultPreview;

    @ElementTitle("Resources")
    @FindBy(xpath = "*//UL[@class = 'navigation-menu-items']/LI[child::A[node() = 'Resources']]")
    public NavigationMenuItem resourcesMenuItem;

    @ElementTitle("Subjects")
    @FindBy(xpath = "*//UL[@class = 'navigation-menu-items']/LI[child::A[node() = 'Subjects']]")
    public NavigationMenuItem subjectsMenuItem;

    @ElementTitle("About")
    @FindBy(xpath = "*//UL[@class = 'navigation-menu-items']/LI[child::A[node() = 'About']]")
    public NavigationMenuItem aboutMenuItem;

    public HeaderPage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

    @ActionTitle("search")
    public void search(String text) {
        searchInput.sendKeys(text);
        searchSubmit.click();
    }

    @ActionTitle("close the undetected country dialog")
    public void checkLocation() {
        if (changeLocationConfirm.isDisplayed()) {
            changeLocationConfirm.click();
        }
    }

}
