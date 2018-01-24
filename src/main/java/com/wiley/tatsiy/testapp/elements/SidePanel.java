package com.wiley.tatsiy.testapp.elements;

import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.stream.Stream;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class SidePanel extends TypifiedElement {
    private static final String ITEM_XPATH = ".//ul/li/a";

    public SidePanel(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Stream<String> titlies() {
        return items().map(WebElement::getText);
    }

    public Stream<WebElement> items() {
        return ElementUtils.xpaths(getWrappedElement(), ITEM_XPATH).stream();
    }

}
