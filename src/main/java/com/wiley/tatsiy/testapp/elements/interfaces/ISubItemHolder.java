package com.wiley.tatsiy.testapp.elements.interfaces;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by cyberspace on 1/23/2018.
 */
public interface ISubItemHolder {
    WebElement getAnchor();
    List<String> getTitles();
    List<WebElement> getItems();
    WebElement getSubItem(String title);
}
