package TestCases;

import DriverFactory.DriverFactory;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Utilities.DataUtility;
import Utilities.LogUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setUpDriver;

public class TC02_AddProductsToCartTest {

    @BeforeMethod
    public void setUp() {
        setUpDriver(DataUtility.getDataFromFileProperty("dataEnv", "browserName"));
        getDriver().get(DataUtility.getDataFromFileProperty("dataEnv", "baseUrl"));
        LogUtility.info("open base url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void addProductsToCart() {
        new P01_LoginPage(getDriver())
                .enterUserName(DataUtility.getJsonData("userDataLogin", "userName"))
                .enterPasswordName(DataUtility.getJsonData("userDataLogin", "Password"))
                .loginButton().addAllProductsToCart();
        Assert.assertTrue(new P02_HomePage(getDriver()).comparingProdSelectedWithCountOnCart());


    }


    @AfterMethod
    public void rearDown() {
        DriverFactory.tearDown();
    }


}
