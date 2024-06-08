package LIsteners;

import Utilities.LogUtility;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedListeners implements IInvokedMethodListener {


    public void beforeInvocation(IInvokedMethod method, org.testng.ITestResult testResult) {
        LogUtility.info("before execute tc" + testResult.getName());
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogUtility.info("TC" + testResult.getName() + "_" + "failure");
            Utility.takeScreenShot(getDriver(), testResult.getName());
        }


    }

}
