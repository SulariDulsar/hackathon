package com.qa.support;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {

    public static MobileElement mobileElement;



    public static MobileElement waitForPresence(AndroidDriver androidDriver, MobileElement element){
        MobileElement mobileElement = null;
        try{
                WebDriverWait wait = new WebDriverWait(androidDriver, 30);
                mobileElement = (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));

        }catch(Exception e){
            e.printStackTrace();
        }
        return mobileElement;
    }


    public static boolean waitForElementPresence(IOSDriver iosDriver, AndroidDriver androidDriver, int timeLimitInSeconds, String targetResourceId){
        boolean isElementPresent = true;
        System.out.println(targetResourceId);

        try {
                mobileElement = (MobileElement) androidDriver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + targetResourceId + "\")");
                WebDriverWait wait = new WebDriverWait(iosDriver, timeLimitInSeconds);
                wait.until(ExpectedConditions.visibilityOf(mobileElement));
                isElementPresent = mobileElement.isDisplayed();

            return isElementPresent;
        }catch(Exception e){
            isElementPresent = false;
            System.out.println(e.getMessage());
            return isElementPresent;
        } }
}
