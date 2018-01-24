package com.wiley.tatsiy.testapp.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class ElementUtils {

    private ElementUtils() {
    }

    public static boolean exists(WebElement root, By locator) {
        try {
            return root.findElement(locator) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean exists(WebElement root, String xpath) {
        return exists(root, By.xpath(xpath));
    }

    public static WebElement xpath(WebElement root, String xpath) {
        return root.findElement(By.xpath(xpath));
    }

    public static List<WebElement> xpaths(WebElement root, String xpath) {
        return root.findElements(By.xpath(xpath));
    }
}
