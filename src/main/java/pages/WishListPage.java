package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;

import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class WishListPage extends HomePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public WishListPage assertWishListPageUrl(String actualUrl) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }
}
