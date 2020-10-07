package com.qa.test;

import com.qa.element.LoginElement;
import com.qa.element.RegistrationElement;
import com.qa.support.ImplicitWait;
import com.qa.support.Report;
import com.qa.support.ReturnMultipleValues;
import com.qa.util.ElementData;
import com.qa.util.TestData;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qa.app.Base.createDriver;

public class Login {
    AndroidDriver<AndroidElement> androidDriver;
    ExtentTest test;
    ReturnMultipleValues imageDetails  = null;

    @BeforeClass
    public void setUp() throws Exception {
        androidDriver = createDriver();
    }

    @AfterClass
    public void tearDown() throws Exception {
        ImplicitWait.sleepFor(1000);
    }

    @Test(priority = 0)
    public void login(){
        test = Report.getInstance().getReportTest("Login.LoginToAppUsingRegisteredCredentials","Login to application using registered credentials");

        try {

            //Click on login page
            LoginElement.loginLink(androidDriver).click();
            ImplicitWait.sleepFor(2000);

            try {
                Assert.assertTrue(LoginElement.loginEmailTxt(androidDriver).isDisplayed());
                Report.getInstance().getResultStatus(LogStatus.PASS,"Verify login page","Navigate to login page successful",test);

            } catch (Exception e) {
                Report.getInstance().getResultStatus(LogStatus.FAIL, "Verify login page ", "Navigate to login page failed", test);
                Report.getInstance().getResultStatus(LogStatus.ERROR, "Error  :", e.getMessage(), test);

            }

            LoginElement.loginEmailTxt(androidDriver).sendKeys(TestData.getSafeString("email"));
            ImplicitWait.sleepFor(2000);

            LoginElement.loginPasswordTxt(androidDriver).sendKeys(TestData.getSafeString("password"));
            ImplicitWait.sleepFor(2000);

            //Screen shot before Login
            imageDetails = Report.getInstance().addScreenShotToReport(androidDriver,test);
            Report.getInstance().getResultStatus(LogStatus.INFO,"Login Page",imageDetails.getImageString(),test);

            LoginElement.loginBtn(androidDriver).click();
            ImplicitWait.sleepFor(2000);

            //Screen shot after Login
            imageDetails = Report.getInstance().addScreenShotToReport(androidDriver,test);
            ImplicitWait.sleepFor(1000);

            try {
                Assert.assertEquals(LoginElement.nameTxt(androidDriver).getText(),TestData.getSafeString("name"));
                Assert.assertEquals(LoginElement.emailTxt(androidDriver).getText(),TestData.getSafeString("email"));
                Report.getInstance().getResultStatus(LogStatus.PASS,"Verify login details","Login got successful",test);
                Report.getInstance().getResultStatus(LogStatus.INFO,"After login",imageDetails.getImageString(),test);

            } catch (Exception e) {
                Report.getInstance().getResultStatus(LogStatus.FAIL,"Verify login details ","Login got failed",test);
                Report.getInstance().getResultStatus(LogStatus.INFO,"After login",imageDetails.getImageString(),test);
                Report.getInstance().getResultStatus(LogStatus.ERROR,"Error  :",e.getMessage(),test);

            }catch (AssertionError e) {
                Report.getInstance().getResultStatus(LogStatus.FAIL,"Verify successful login ","Login got failed",test);
                Report.getInstance().getResultStatus(LogStatus.INFO,"After login",imageDetails.getImageString(),test);
                Report.getInstance().getResultStatus(LogStatus.ERROR,"Error  ",e.getMessage(),test);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Report.getInstance().endReportTest(test);
    }
}
