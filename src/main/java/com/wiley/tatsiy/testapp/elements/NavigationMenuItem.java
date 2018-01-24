package com.wiley.tatsiy.testapp.elements;

import com.wiley.tatsiy.testapp.elements.interfaces.ISubItemHolder;
import com.wiley.tatsiy.testapp.util.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class NavigationMenuItem extends TypifiedElement implements ISubItemHolder {

    private static final String TITLE_XPATH = ".//A";
    private static final String CHILD_ITEMS_XPATH = ".//h3";
    private static final String CHILD_TITLE_XPATH = ".//h3/A";

    public NavigationMenuItem(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getTitle() {
        return getWrappedElement()
                .findElement(By.xpath(TITLE_XPATH))
                .getText();
    }

    @Override
    public WebElement getAnchor() {
        return getWrappedElement().findElement(By.xpath(TITLE_XPATH));
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
        WebElement item = items()
                .filter(x ->
                {
                    String itemTitle = ElementUtils.xpath(x, ".//A").getAttribute("title");
                    return title.equalsIgnoreCase(itemTitle);
                }).findFirst().orElse(null);
        if (ElementUtils.exists(item, ".//ul")) {
            return new NavigationMenuSubItem(item); // If it's sub menu.
        }
        return item;
    }

    public Stream<WebElement> items() {
        return getWrappedElement().findElements(By.xpath(CHILD_ITEMS_XPATH)).stream();
    }

    public Stream<String> titles() {
        return getWrappedElement().findElements(By.xpath(CHILD_TITLE_XPATH)).stream().map(x -> x.getAttribute("title"));
    }
}
