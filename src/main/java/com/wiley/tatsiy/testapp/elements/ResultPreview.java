package com.wiley.tatsiy.testapp.elements;

import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.extensions.DriverExtension;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.stream.Stream;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class ResultPreview extends TypifiedElement {

    private static final String LIST_ITEM_XPATH = ".//DIV[@class = 'search-list']/DIV";
    private static final String PREVIEW_PRODUCTS_XPATH = ".//DIV[@class = 'related-content-products']/SECTION";

    public ResultPreview(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Stream<String> titles() {
        return items().map(WebElement::getText);
    }

    public Stream<WebElement> items() {
        DriverExtension.waitUntilElementPresent(getWrappedElement(), PageFactory.getTimeOut());
        return ElementUtils.xpaths(getWrappedElement(), LIST_ITEM_XPATH).stream();
    }

    public Stream<PreviewProductItem> products() {
        DriverExtension.waitUntilElementPresent(getWrappedElement(), PageFactory.getTimeOut());
        return ElementUtils.xpaths(getWrappedElement(), PREVIEW_PRODUCTS_XPATH).stream().map(PreviewProductItem::new);
    }

}
