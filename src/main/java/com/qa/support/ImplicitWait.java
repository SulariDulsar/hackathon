package com.qa.support;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class ImplicitWait {
    public static void waitFor(AndroidDriver driver, int time) throws Exception {

        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void sleepFor(long time) throws Exception {

        Thread.sleep(time);
    }
}
