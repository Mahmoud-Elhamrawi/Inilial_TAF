package Pages;

import Utilities.LogUtility;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class P02_HomePage {
    static float price = 0;
    private static List<WebElement> allProdBtn;
    private static List<WebElement> prodSelectedList;
    private final WebDriver driver;
    private final By ProdBtn = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By iconCount = By.className("shopping_cart_badge");
    private final By prodSelected = By.xpath("//button[.=\"Remove\"]");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By priceProdSelected = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");

    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getIconCount() {
        return iconCount;
    }

    public P02_HomePage addAllProductsToCart() {
        allProdBtn = driver.findElements(ProdBtn);
        for (int i = 1; i < allProdBtn.size(); i++) {
            By ProdBtn = By.xpath("(//button[contains(@class,'btn_inventory')])[" + i + "]");
            Utility.Clicking(driver, ProdBtn);
            LogUtility.info("product select" + i);
        }

        return this;
    }

    public String getCountOfProdOnCart() {
        try {
            return Utility.getText(driver, iconCount);

        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            return "0";
        }
    }

    public String getProdSelected() {
        try {
            prodSelectedList = driver.findElements(prodSelected);
            return String.valueOf(prodSelectedList.size());
        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            return "0";
        }
    }

    public P02_HomePage addProductsRandom(int needProd, int totalProd) {

        Set<Integer> randomNumbers = Utility.generateUniqueNumbers(needProd, totalProd);
        for (int random : randomNumbers) {
            LogUtility.info("randomProduct" + random);
            By ProdBtn = By.xpath("(//button[contains(@class,'btn_inventory')])[" + random + "]");
            Utility.Clicking(driver, ProdBtn);
        }

        return this;
    }

    public boolean comparingProdSelectedWithCountOnCart() {
        return getCountOfProdOnCart().equals(getProdSelected());
    }

    public P03_cartPage clickingOnCartIcon() {
        Utility.Clicking(driver, cartIcon);
        return new P03_cartPage(driver);
    }

    public boolean verifyCartUrl(String expectUrl) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(expectUrl));
            return true;
        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            return false;
        }
    }

    public String getPricesOfProductSelected() {
        try {
            List<WebElement> pricesForProdSelect = driver.findElements(priceProdSelected);
            for (int i = 1; i < pricesForProdSelect.size(); i++) {

                By priceItem = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String fullStr = Utility.getText(driver, priceItem);
                price += Float.parseFloat(fullStr.replace("$", ""));

            }
            LogUtility.info("total price on home page : " + price);
            return String.valueOf(price);
        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            return "0";
        }

    }


}