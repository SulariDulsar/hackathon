package com.qa.test;

import com.qa.app.Base;
import com.qa.element.RegistrationElement;
import com.qa.support.*;
import com.qa.util.TestData;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Registration extends Base {
    AndroidDriver<AndroidElement> androidDriver;
    ExtentTest test;
    ReturnMultipleValues imageDetails  = null;
    String image;

    @BeforeClass
    public void setUp() throws Exception {
        androidDriver = createDriver();
    }

    @AfterClass
    public void tearDown() throws Exception {
        ImplicitWait.sleepFor(1000);
    }

    @Test(priority = 0)
    public void registration(){
        test = Report.getInstance().getReportTest("Registration.registration","Registration with email");

        try {

            //Registration page verification
             RegistrationElement.registerLink(androidDriver).click();
             ImplicitWait.sleepFor(2000);

            try {
                Assert.assertTrue(RegistrationElement.registrationNameTxt(androidDriver).isDisplayed());
                Report.getInstance().getResultStatus(LogStatus.PASS,"Verify registration page","Navigate to Registration page successful",test);
            } catch (Exception e) {
                Report.getInstance().getResultStatus(LogStatus.FAIL, "Verify registration page", "Navigate to Registration page got failed", test);
                Report.getInstance().getResultStatus(LogStatus.ERROR, "Error  :", e.getMessage(), test);
            }

                RegistrationElement.registrationNameTxt(androidDriver).sendKeys(TestData.getSafeString("name"));
             ImplicitWait.sleepFor(2000);

             RegistrationElement.registrationEmailTxt(androidDriver).sendKeys(TestData.getSafeString("email"));
             ImplicitWait.sleepFor(2000);

             RegistrationElement.registrationPasswordTxt(androidDriver).sendKeys(TestData.getSafeString("password"));
             ImplicitWait.sleepFor(2000);

             RegistrationElement.registrationConfirmPasswordTxt(androidDriver).sendKeys(TestData.getSafeString("confirmPassword"));
             ImplicitWait.sleepFor(2000);

            //Page scroll
            try {
                PageScroll.PageScrollUp(androidDriver);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Screen shot before SignUp
             imageDetails = Report.getInstance().addScreenShotToReport(androidDriver,test);
             Report.getInstance().getResultStatus(LogStatus.INFO,"Before SignUp Page",imageDetails.getImageString(),test);

             //Click on registration button
             RegistrationElement.registrationBtn(androidDriver).click();
             ImplicitWait.sleepFor(1000);

            //Screen shot after SignUp
            //String screenText = null;
            imageDetails = Report.getInstance().addScreenShotToReport(androidDriver,test);

            /*try {
                screenText = ScreenRead.getText(imageDetails.getImagePath());
                System.out.println("$$$$ "+screenText);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            ImplicitWait.sleepFor(2000);



            try {
                Assert.assertEquals(RegistrationElement.registrationNameTxt(androidDriver).getText(),"");
                Assert.assertEquals(RegistrationElement.registrationEmailTxt(androidDriver).getText(),"");
                Report.getInstance().getResultStatus(LogStatus.PASS,"Verify new registration ","Registration got successful",test);
                Report.getInstance().getResultStatus(LogStatus.INFO,"After registration",imageDetails.getImageString(),test);

            } catch (Exception e) {
                Report.getInstance().getResultStatus(LogStatus.FAIL,"Verify new registration ","Registration got failed",test);
                Report.getInstance().getResultStatus(LogStatus.INFO,"After registration",imageDetails.getImageString(),test);
                Report.getInstance().getResultStatus(LogStatus.ERROR,"Error  :",e.getMessage(),test);

            }catch (AssertionError e) {
                Report.getInstance().getResultStatus(LogStatus.FAIL,"Verify successful registration","Registration got failed",test);
                Report.getInstance().getResultStatus(LogStatus.INFO,"After registration",imageDetails.getImageString(),test);
                Report.getInstance().getResultStatus(LogStatus.ERROR,"Error  ",e.getMessage(),test);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        Report.getInstance().endReportTest(test);
    }



}
