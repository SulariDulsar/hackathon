package com.qa.support;

import com.qa.util.Configuration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {

    private String reportName;
    private String reportPath;
    ExtentReports extent;
    private static Report self;


    private  Report () {
        String datepattern = new SimpleDateFormat("yyyy_MM_dd-hh_mm_ss").format(new Date());
        reportName = Configuration.getSafeString("reportName") + datepattern + ".html";
        reportPath = Configuration.getSafeString("reportPath") + reportName;
        System.out.println(reportPath);

        try {
                extent = new ExtentReports(reportPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //To add system or environment info by using the addSystemInfo method.
        extent.addSystemInfo("Environment", Configuration.getSafeString("reportEnvironment"));
        extent.addSystemInfo("Created By", Configuration.getSafeString("reportOwner"));
        extent.addSystemInfo("Selenium Java Version", Configuration.getSafeString("seleniumJavaVersion"));
        extent.addSystemInfo("Appium Java Version", Configuration.getSafeString("appiumJavaVersion"));
        extent.addSystemInfo("Appium Server Version", Configuration.getSafeString("appiumServerVersion"));
        extent.config().reportName(Configuration.getSafeString("reportName"));
        extent.config().reportHeadline(Configuration.getSafeString("reportHeadline"));
    }

    public static Report getInstance() {
        if (self == null){
            self = new Report();
        }

        return (self);

    }

    public ExtentTest getReportTest(String name, String description) {
        ExtentTest test;
        test = extent.startTest(name,description);
        return test;
    }

    public void endReportTest(ExtentTest test){
        //System.out.println(test.getClass()+"  "+test.getTest().getName()+" Start---------------");
         extent.endTest(test);
        //System.out.println(test.getClass()+"  "+test.getTest().getName()+" End---------------");

    }

    public void flushReport(boolean report){
        //System.out.println("IN");
        try {
            ImplicitWait.sleepFor(2000);
            extent.flush();
            //System.out.println("MID");
            ImplicitWait.sleepFor(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Out");
    }



    public void getResultStatus(LogStatus status, String stepName, String detail, ExtentTest test) throws Exception {
        if (status == LogStatus.FAIL) {
            test.log(LogStatus.FAIL, stepName, detail);
        } else if (status == LogStatus.INFO) {
            test.log(LogStatus.INFO, stepName, detail);
        } else if (status == LogStatus.PASS) {
            test.log(LogStatus.PASS, stepName, detail);
        } else if (status == LogStatus.ERROR){
            test.log(LogStatus.ERROR,stepName,detail);

        }
    }


    //Add screen shot to report
    public ReturnMultipleValues addScreenShotToReport(WebDriver driver,ExtentTest test) throws Exception {
        ReturnMultipleValues  returnValue = new ReturnMultipleValues();

        String imagePath = ScreenShot.createScreenShot(driver);
        String image = test.addScreenCapture("./" + Report.getInstance().getReportName() + imagePath);

        returnValue.setImagePath(imagePath);
        returnValue.setImageString(image);

        return returnValue;
    }

    public String getReportPath() {
        return reportPath;
    }

    public String getReportName(){

        return reportName ;
    }


}
