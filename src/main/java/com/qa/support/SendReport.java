package com.qa.support;

import com.qa.app.AndroidDrv;
import org.testng.annotations.AfterSuite;

public class SendReport {
    boolean report = true;

    @AfterSuite
    public void sendReportMail() {
        try {
            //System.out.println("Before");

            //ExtentTest test = Report.getInstance().getReportTest("End","End of Automation");
            //Report.getInstance().endReportTest(test);

            Report.getInstance().flushReport(report);
            //System.out.println("After");
            //ImplicitWait.sleepFor(5000);
            try {
                ImplicitWait.sleepFor(1000);
                AndroidDrv.getInstance().getDriver().closeApp();
                ImplicitWait.sleepFor(1000);
                AndroidDrv.getInstance().getDriver().quit();
                ImplicitWait.sleepFor(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //Report.getInstance().flushReport();
            ImplicitWait.sleepFor(5000);

            //comment below to stop sending mails
            SendEmail.getInstance().sendEmailWithAttachments();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
