import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.HomePage;

public class MyAccountTest extends SeleniumBaseTest {

    @Test
    public void shouldRegisterNewUserTest() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "1A!" +faker.lorem().characters(10);

        new HomePage(driver)
                .goToMyAccountPage()
                .typeRegisterEmail(email)
                .typeRegisterPassword(password)
                .submitRegister()
                .assertLogOutTextIsShown();
    }

    @Test
    public void shouldNotRegisterWhenRegisteredBeforeTest() {
        String email = config.getApplicationUser();
        String password = config.getApplicationPassword();

        new HomePage(driver)
                .goToMyAccountPage()
                .typeRegisterEmail(email)
                .typeRegisterPassword(password)
                .submitRegister()
                .assertErrorAccountRegistered();
    }

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
    public void shouldNotLoginWhenNotRegisteredBeforeTest() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "1A!" +faker.lorem().characters(10);

        new HomePage(driver)
                .goToMyAccountPage()
                .typeLoginEmail(email)
                .typeLoginPassword(password)
                .submitLogin()
                .assertErrorAccountNotRegistered();
    }

    @Test
    public void shouldNotLoginWithInvalidPasswordWhenRegisteredBeforeTest() {
        Faker faker = new Faker();
        String password = "1A!" +faker.lorem().characters(10);
        new HomePage(driver)
                .goToMyAccountPage()
                .typeLoginEmail(config.getApplicationUser())
                .typeLoginPassword(password)
                .submitLogin()
                .assertErrorInvalidPassword(config.getApplicationUser());
    }
}