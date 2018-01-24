package com.wiley.tatsiy.testapp.util;

import org.openqa.selenium.By;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class XBuilder {

    private XBuilder() {
    }

    public static By forA(String href) {
        return By.xpath("*//A[@href = '" + href + "']");
    }

}
