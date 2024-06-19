package Listeners;


import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;


public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }


    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (method.isTestMethod()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                LogsUtils.info("Test Case " + testResult.getName() + " failed");
                WebDriver driver = getDriver();
                if (driver != null) {
                    Utility.takeScreenshot(driver, testResult.getName());
                    Utility.takeFullScreenshot(getDriver());
                } else {
                    LogsUtils.error("Driver is not initialized, cannot take screenshot");
                }
            }
        }
    }
}


