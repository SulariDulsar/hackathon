package com.qa.support;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class PageScroll {
    public static void PageScrollUp(AndroidDriver<AndroidElement> driver) throws Exception {
        TouchAction ts = new TouchAction(driver);
        ts.press(PointOption.point(207, 582)).moveTo(PointOption.point(8, -360)).release().perform();

    }

}
