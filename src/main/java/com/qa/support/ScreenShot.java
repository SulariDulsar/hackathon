package com.qa.support;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScreenShot {

     private static String imageLocation = "_image/";

     public static String createScreenShot(WebDriver driver) {

        UUID uuid = UUID.randomUUID();


        try {
            // generate screenshot as a file object
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // copy file object to designated location
            FileUtils.copyFile(scrFile, new java.io.File(Report.getInstance().getReportPath() + imageLocation + uuid + ".png"));

        } catch (IOException e) {
            System.out.println("Error while generating screenshot:\n" + e.toString());
        }

        return imageLocation + uuid + ".png";
    }



}
