package com.wiley.tatsiy.testapp.elements;

import com.wiley.tatsiy.testapp.elements.interfaces.ISubItemHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class NavigationMenuSubItem extends TypifiedElement implements ISubItemHolder {

    private static final String TITLE_XPATH = ".//A";
    private static final String CHILD_TITLE_XPATH = ".//li/A";

    public NavigationMenuSubItem(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getTitle() {
        return getWrappedElement()
                .findElement(By.xpath(TITLE_XPATH))
                .getText();
    }

    @Override
    public WebElement getAnchor() {
        return getWrappedElement().findElement(By.xpath(".//A"));
    }

    @Override
    public List<String> getTitles() {
        return titles().collect(Collectors.toList());
    }

    @Override
    public List<WebElement> getItems() {
        return items().collect(Collectors.toList());
    }

    @Override
    public WebElement getSubItem(String title) {
        return items().filter(x -> title.equalsIgnoreCase(x.getAttribute("title"))).findFirst().orElse(null);
    }

    public Stream<WebElement> items() {
        return getWrappedElement().findElements(By.xpath(CHILD_TITLE_XPATH)).stream();
    }

    public Stream<String> titles() {
        return items().map(x -> x.getAttribute("title"));
    }

}
