package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setUpDriver(String browser)
    {
        switch (browser.toLowerCase())
        {
            case "chrome":
                driverThreadLocal.set(new ChromeDriver());
                break;
            default:
                EdgeOptions options= new EdgeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(options));
        }
    }

    public static WebDriver getDriver()
    {
        return driverThreadLocal.get();
    }


    private static void tearDown()
    {
        getDriver().quit();
        driverThreadLocal.remove();
    }




}
