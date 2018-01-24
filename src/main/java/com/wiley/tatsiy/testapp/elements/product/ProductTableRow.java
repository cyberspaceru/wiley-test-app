package com.wiley.tatsiy.testapp.elements.product;

import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class ProductTableRow  extends TypifiedElement {
    private static final String ADD_TO_CART_BUTTON_XPATH = ".//BUTTON[node() = 'Add to cart']";

    public ProductTableRow(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public boolean addToCartButtonExists() {
        return ElementUtils.exists(getWrappedElement(), ADD_TO_CART_BUTTON_XPATH);
    }

}