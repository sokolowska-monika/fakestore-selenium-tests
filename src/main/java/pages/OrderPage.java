package pages;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OrderPage extends HomePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-empty")
    private WebElement emptyCartInfo;

    public OrderPage assertOrderPageUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }
}
