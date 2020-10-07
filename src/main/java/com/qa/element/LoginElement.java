package com.qa.element;

import com.qa.util.ElementData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginElement {
    public static AndroidElement loginLink(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("LoginLink"));
        return locator;
    }

    public static AndroidElement loginEmailTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("LoginEmailTxt"));
        return locator;
    }

    public static AndroidElement loginPasswordTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("LoginPasswordTxt"));
        return locator;
    }

    public static AndroidElement loginBtn(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("LoginBtn"));
        return locator;
    }

    public static AndroidElement nameTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementByXPath(ElementData.getSafeString("NameTxt"));
        return locator;
    }

    public static AndroidElement emailTxt(AndroidDriver<AndroidElement> driver) throws Exception {
        AndroidElement locator = driver.findElementById(ElementData.getSafeString("EmailTxt"));
        return locator;
    }
}
