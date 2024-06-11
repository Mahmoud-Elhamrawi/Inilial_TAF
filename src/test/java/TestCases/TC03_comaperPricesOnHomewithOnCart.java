package TestCases;

import DriverFactory.DriverFactory;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_cartPage;
import Utilities.DataUtility;
import Utilities.LogUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setUpDriver;

public class TC03_comaperPricesOnHomewithOnCart {


    @BeforeMethod
    public void setUp() {
        setUpDriver(DataUtility.getDataFromFileProperty("dataEnv", "browserName"));
        getDriver().get(DataUtility.getDataFromFileProperty("dataEnv", "baseUrl"));
        LogUtility.info("open base url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void comparingPricesOnHomeWithCart() {
        String totalPrice = new P01_LoginPage(getDriver())
                .enterUserName(DataUtility.getJsonData("userDataLogin", "userName"))
                .enterPasswordName(DataUtility.getJsonData("userDataLogin", "Password"))
                .loginButton()
                .addProductsRandom(3, 6)
                .getPricesOfProductSelected();

        new P02_HomePage(getDriver())
                .clickingOnCartIcon();


        Assert.assertTrue(new P03_cartPage(getDriver()).compare(totalPrice));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.tearDown();
    }


}
