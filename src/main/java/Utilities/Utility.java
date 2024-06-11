package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    public static void generalWait(WebDriver driver) {
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

    //TODO:take screen shot
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


    //TODO:take full screen shot
    public static void takeFullScreenShot(WebDriver driver, By locator) {

        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(getTOWebElement(driver, locator))
                    .save(screenshot_Path);
        } catch (Exception e) {
            LogUtility.error(e.getMessage());
        }
    }

    //TODO:select drop-down
    public static void selectFromDropDown(WebDriver driver, By locator, String option) {
        new Select(getTOWebElement(driver, locator)).selectByVisibleText(option);
    }


    //TODO:generate random numbers
    public static int generateRandomNumbers(int upper) {
        return new Random().nextInt(upper) + 1;
    }

    public static Set<Integer> generateUniqueNumbers(int numNeed, int totalNum) {
        Set<Integer> generateNumber = new HashSet<>();
        while (generateNumber.size() < numNeed) {
            int random = generateRandomNumbers(totalNum);
            generateNumber.add(random);
        }
        return generateNumber;

    }


}
