package com.qa.element;

import com.qa.util.ElementData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class RegistrationElement {

    public static AndroidElement registerLink(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("RegisterLink"));
        return locator;
    }

    public static AndroidElement registrationNameTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("RegistrationNameTxt"));
        return locator;
    }

    public static AndroidElement registrationEmailTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("RegistrationEmailTxt"));
        return locator;
    }

    public static AndroidElement registrationPasswordTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("RegistrationPasswordTxt"));
        return locator;
    }

    public static AndroidElement registrationConfirmPasswordTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("RegistrationConfirmPasswordTxt"));
        return locator;
    }

    public static AndroidElement registrationBtn(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("RegistrationBtn"));
        return locator;
    }
}
