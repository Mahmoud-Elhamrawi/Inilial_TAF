package Pages;

import Utilities.LogUtility;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_cartPage {
    private static float price = 0;
    private final By priceOfItemsOnCart = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");
    private WebDriver driver;

    public P03_cartPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getPriceOfItemsOnCart() {
        try {
            List<WebElement> priceOnCart = driver.findElements(priceOfItemsOnCart);
            for (int i = 1; i < priceOnCart.size(); i++) {
                By prodSelected = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String fullStr = Utility.getText(driver, prodSelected);
                price += Float.parseFloat(fullStr.replace("$", ""));
            }

        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            return "0";
        }
        LogUtility.info("total price on cart :" + price);
        return String.valueOf(price);
    }

    public boolean compare(String price) {
        return getPriceOfItemsOnCart().equals(price);
    }


}
