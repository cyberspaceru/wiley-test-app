package com.wiley.tatsiy.testapp.pages;

import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by cyberspace on 1/23/2018.
 */
@PageEntry(title = "Homepage | Wiley")
public class HomePage extends HeaderPage {

    public HomePage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

}
