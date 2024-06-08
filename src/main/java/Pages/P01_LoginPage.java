package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private final WebDriver driver;
    private final By userNameInp = By.id("user-name");
    private final By UserPasswordInp = By.id("password");
    private final By loginBtn = By.id("login-button");

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterUserName(String name) {
        Utility.enterTxt(driver, userNameInp, name);
        return this;
    }

    public P01_LoginPage enterPasswordName(String password) {
        Utility.enterTxt(driver, UserPasswordInp, password);
        return this;
    }

    public P02_HomePage loginButton() {
        Utility.Clicking(driver, loginBtn);
        return new P02_HomePage(driver);
    }


    public boolean assertLogin(String expectUrl) {
        return driver.getCurrentUrl().equals(expectUrl);
    }
}
