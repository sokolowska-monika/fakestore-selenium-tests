import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginNegativeTest extends SeleniumBaseTest {

    @Test
    public void shouldNotLoginWhenNotRegisteredBeforeTest() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "1A!" + faker.lorem().characters(10);

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
        String password = "1A!" + faker.lorem().characters(10);
        new HomePage(driver)
                .goToMyAccountPage()
                .typeLoginEmail(config.getApplicationUser())
                .typeLoginPassword(password)
                .submitLogin()
                .assertErrorInvalidPassword(config.getApplicationUser());
    }

    @Test
    public void shouldNotLoginWithInvalidEmailFormatTest() {
        Faker faker = new Faker();
        String email = faker.name().suffix();
        String password = "1A!" + faker.lorem().characters(10);
        new HomePage(driver)
                .goToMyAccountPage()
                .typeLoginEmail(email)
                .typeLoginPassword(password)
                .submitLogin()
                .assertErrorInvalidEmail(email);
    }
}
