package com.wiley.tatsiy.testapp.elements;

import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class PreviewProductItem extends TypifiedElement {

    private static final String TITLE_XPATH = ".//H3[@class = 'product-title']/A";

    public PreviewProductItem(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getTitle() {
        return ElementUtils.xpath(getWrappedElement(), TITLE_XPATH).getText();
    }

}
