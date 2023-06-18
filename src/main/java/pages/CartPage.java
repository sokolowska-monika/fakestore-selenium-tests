package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage extends HomePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage assertCartPageUrl(String actualUrl){
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }
}
