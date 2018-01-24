package com.wiley.tatsiy.testapp.elements.product;

import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.stream.Stream;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class ProductsList extends TypifiedElement {
    private static final String PRODUCT_ITEM_XPATH = "./SECTION";

    public ProductsList(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Stream<Product> products() {
        return ElementUtils.xpaths(getWrappedElement(), PRODUCT_ITEM_XPATH).stream().map(Product::new);
    }

    @Override
    public boolean isDisplayed() {
        long expected = products().count();
        long actual = products().filter(TypifiedElement::isDisplayed).count();
        return expected == actual;
    }
}
