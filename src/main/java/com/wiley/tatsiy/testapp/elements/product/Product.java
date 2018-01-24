package com.wiley.tatsiy.testapp.elements.product;

import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.stream.Stream;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class Product extends TypifiedElement {
    private static final String PRODUCT_TABLE_ROW_XPATH = ".//DIV[@class = 'table-row-content']";

    public Product(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Stream<ProductTableRow> rows() {
        return ElementUtils.xpaths(getWrappedElement(), PRODUCT_TABLE_ROW_XPATH).stream().map(ProductTableRow::new);
    }

}
