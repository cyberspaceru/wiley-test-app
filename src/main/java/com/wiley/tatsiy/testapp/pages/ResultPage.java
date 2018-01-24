package com.wiley.tatsiy.testapp.pages;

import com.wiley.tatsiy.testapp.elements.product.ProductsList;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by cyberspace on 1/24/2018.
 */
@PageEntry(title = "Results | Wiley")
public class ResultPage extends HeaderPage {

    @ElementTitle("Products List")
    @FindBy(xpath = "*//DIV[@class = 'products-list']")
    public ProductsList productsList;

    public ResultPage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

}
