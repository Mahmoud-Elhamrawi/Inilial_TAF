package TestCases;

import LIsteners.IInvokedListeners;
import LIsteners.ITestResult;
import Pages.P01_LoginPage;
import Utilities.DataUtility;
import Utilities.LogUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import static DriverFactory.DriverFactory.*;


@Listeners({ITestResult.class, IInvokedListeners.class})
public class TC01_ValidLoginTest {

    private final String USERNAME = DataUtility.getJsonData("userDataLogin", "userName");
    private final String PASSWORD = DataUtility.getJsonData("userDataLogin", "Password");


    @BeforeMethod
    public void setUp() {
        setUpDriver(DataUtility.getDataFromFileProperty("dataEnv", "browserName"));
        getDriver().get(DataUtility.getDataFromFileProperty("dataEnv", "baseUrl"));
        LogUtility.info("open base url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void validLoginTC() {
        LogUtility.info("user enter valid credential");
        new P01_LoginPage(getDriver())
                .enterUserName(USERNAME)
                .enterPasswordName(PASSWORD)
                .loginButton();

        Assert.assertTrue(new P01_LoginPage(getDriver()).assertLogin(DataUtility.getDataFromFileProperty("dataEnv", "homeUrl")));
        LogUtility.info("TC pass");

    }


    @AfterMethod
    public void tearDownDriver() {
        tearDown();
    }

}
