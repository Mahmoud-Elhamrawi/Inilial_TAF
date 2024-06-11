package Pages;

import Utilities.LogUtility;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

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

    public By getIconCount() {
        return iconCount;
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
            By ProdBtn = By.xpath("(//button[contains(@class,'btn_inventory')])[" + random + "]");
            Utility.Clicking(driver, ProdBtn);
        }

        return this;
    }

    public boolean comparingProdSelectedWithCountOnCart() {
        return getCountOfProdOnCart().equals(getProdSelected());
    }

}
