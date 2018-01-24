package com.wiley.tatsiy.testapp.util;


/**
 * Created by cyberspace on 6/27/2017.
 */
public class Folders {

    private Folders() {}

    public static String toRoot() {
        return System.getProperty("user.dir");
    }

    public static String toTestResources() {
        return toRoot() + "\\src\\test\\resources";
    }

    public static String toDrivers() {
        return toTestResources() + "\\drivers";
    }

    public static String toFeatures() {
        return toTestResources() + "\\features";
    }
}
