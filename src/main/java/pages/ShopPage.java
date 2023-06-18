package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShopPage extends HomePage {

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public ShopPage assertShopPageUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }
}
