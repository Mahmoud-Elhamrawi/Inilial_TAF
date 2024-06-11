package Pages;

import Utilities.LogUtility;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P02_HomePage {
    private static List<WebElement> allProdBtn;
    private static List<WebElement> prodSelectedList;
    private final WebDriver driver;
    private final By ProdBtn = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By iconCount = By.className("shopping_cart_badge");

    private final By prodSelected = By.xpath("//button[.=\"Remove\"]");


    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public P02_HomePage addAllProductsToCart() {
        allProdBtn = driver.findElements(ProdBtn);
        for (int i = 1; i < allProdBtn.size(); i++) {
            By ProdBtn = By.xpath("(//button[contains(@class,'btn_inventory')])[" + i + "]");
            Utility.Clicking(driver, ProdBtn);
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
        prodSelectedList = driver.findElements(prodSelected);
        return String.valueOf(prodSelectedList.size());
    }


    public boolean comparingProdSelectedWithCountOnCart() {
        return getCountOfProdOnCart().equals(getProdSelected());
    }

}
