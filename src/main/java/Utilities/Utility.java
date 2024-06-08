package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utility {
    //TODO:take screen shots
    public static final String screenshot_Path = "test-outputs/screenShots/";

    //TODO:click on button
    public static void Clicking(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }

    //TODO:Enter Text
    public static void enterTxt(WebDriver driver, By loctaor, String txt) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loctaor));
        driver.findElement(loctaor).sendKeys(txt);
    }

    //toDO:get text from element
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    //TODO:general wait
    public static void generalWait(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //TODO:Scrolling
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getTOWebElement(driver, locator));
    }

    //TODO:convert By to Web Element
    public static WebElement getTOWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    //TODO:time stamp
    public static String timeStemp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void takeScreenShot(WebDriver driver, String screenName) {
        try {

            File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenDis = new File(screenshot_Path + screenName + "-" + timeStemp() + ".png");
            FileUtils.copyFile(screenSrc, screenDis);
            Allure.addAttachment(screenName, Files.newInputStream(Path.of(screenDis.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //TODO:select drop-down
    public static void selectFromDropDown(WebDriver driver, By locator, String option) {
        new Select(getTOWebElement(driver, locator)).selectByVisibleText(option);
    }
}
