package com.qa.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class Base {

    public static AndroidDriver<AndroidElement> createDriver() throws Exception{
        AndroidDriver<AndroidElement> driver;

        driver = AndroidDrv.getInstance().getDriver();

        return driver;
    }
}
