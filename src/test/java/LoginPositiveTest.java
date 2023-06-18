import org.testng.annotations.Test;
import pages.HomePage;

public class LoginPositiveTest extends SeleniumBaseTest {

    @Test
    public void shouldLoginWhenRegisteredBeforeTest() {
        String email = config.getApplicationUser();
        String password = config.getApplicationPassword();

        new HomePage(driver)
                .goToMyAccountPage()
                .typeLoginEmail(email)
                .typeLoginPassword(password)
                .submitLogin()
                .assertLogOutTextIsShown();
    }

    @Test
    public void shouldLoginWithRememberMeWhenRegisteredBeforeTest() {
        String email = config.getApplicationUser();
        String password = config.getApplicationPassword();

        new HomePage(driver)
                .goToMyAccountPage()
                .typeLoginEmail(email)
                .typeLoginPassword(password)
                .checkRememberMe()
                .submitLogin()
                .assertLogOutTextIsShown()
                .closeTab()
                .goToMyAccountPage()
                .assertWelcomeTextIsShown();
    }
}
