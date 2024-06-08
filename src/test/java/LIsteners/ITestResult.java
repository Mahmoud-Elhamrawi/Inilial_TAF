package LIsteners;

import Utilities.LogUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class ITestResult implements ITestListener {

    public void onTestSuccess(org.testng.ITestResult result) {
        LogUtility.info("TC"+result.getName()+"Success");
    }

    public void onStart(ITestContext context) {
        LogUtility.info("TC"+context.getName()+"start");
    }

    public void onTestSkipped(org.testng.ITestResult result) {
        LogUtility.info("TC"+result.getName()+"Skipped");
    }
}
