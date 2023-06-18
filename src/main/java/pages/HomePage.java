package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".custom-logo-link")
    private WebElement fakeStoreLogo;

    @FindBy(css = "#menu-item-197")
    private WebElement mainPageElm;

    @FindBy(css = "#menu-item-198")
    private WebElement shopElm;

    @FindBy(css = "#menu-item-199")
    private WebElement orderElm;

    @FindBy(css = "#menu-item-200")
    private WebElement cartElm;

    @FindBy(css = "#menu-item-201")
    private WebElement myAccountElm;

    @FindBy(css = "#menu-item-248")
    private WebElement wishListElm;

    public HomePage goToMainPage() {
        mainPageElm.click();
        return new HomePage(driver);
    }

    public ShopPage goToShopPage() {
        shopElm.click();
        return new ShopPage(driver);
    }

    public OrderPage goToOrderPage() {
        orderElm.click();
        return new OrderPage(driver);
    }

    public CartPage goToCartPage() {
        cartElm.click();
        return new CartPage(driver);
    }

    public MyAccountPage goToMyAccountPage() {
        myAccountElm.click();
        return new MyAccountPage(driver);
    }

    public WishListPage goToWishListPage() {
        wishListElm.click();
        driver.switchTo();
        return new WishListPage(driver);
    }

    public HomePage assertHomePageUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }
}
