import org.testng.annotations.Test;
import pages.HomePage;

public class MenuTest extends SeleniumBaseTest {

    @Test
    public void shouldMenuRedirectToMainPageTest() {
        new HomePage(driver)
                .goToMainPage()
                .assertHomePageUrl(config.getApplicationUrl());
    }

    @Test
    public void shouldMenuRedirectToShopPageTest() {
        new HomePage(driver)
                .goToShopPage()
                .assertShopPageUrl(config.getApplicationUrl() + "shop/");
    }

    @Test
    public void shouldMenuRedirectToOrderPageTest() {
        new HomePage(driver)
                .goToOrderPage()
                .assertOrderPageUrl(config.getApplicationUrl() + "koszyk/");
    }

    @Test
    public void shouldMenuRedirectToCartPageTest() {
        new HomePage(driver)
                .goToCartPage()
                .assertCartPageUrl(config.getApplicationUrl() + "koszyk/");
    }

    @Test
    public void shouldMenuRedirectToMyAccountPageTest() {
        new HomePage(driver)
                .goToMyAccountPage()
                .assertMyAccountPageUrl(config.getApplicationUrl() + "moje-konto/");
    }

    @Test
    public void shouldMenuRedirectToWishListPageTest() {
        new HomePage(driver)
                .goToWishListPage()
                .assertWishListPageUrl(config.getApplicationUrl() + "wishlist/");
    }
}
