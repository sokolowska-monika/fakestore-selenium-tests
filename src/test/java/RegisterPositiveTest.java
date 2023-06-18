import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.HomePage;

public class RegisterPositiveTest extends SeleniumBaseTest {

    @Test
    public void shouldRegisterNewUserTest() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "1A!" + faker.lorem().characters(10);

        new HomePage(driver)
                .goToMyAccountPage()
                .typeRegisterEmail(email)
                .typeRegisterPassword(password)
                .submitRegister()
                .assertLogOutTextIsShown();
    }
}
