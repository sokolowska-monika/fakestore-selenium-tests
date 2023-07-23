import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyAccountPage;

public class RegisterNegativeTest extends SeleniumBaseTest {
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();

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

    @DataProvider
    public Object[][] wrongEmails() {
        return new Object[][]{
                {"test", "Uwzględnij znak „@” w adresie e-mail. W adresie „test” brakuje znaku „@”."},                   //invalid email format - no email domain part
                {"@test", "Podaj część przed znakiem „@”. Adres „@test” jest niepełny."},                                //invalid email format - no email prefix part
                {"test.test.com", "Uwzględnij znak „@” w adresie e-mail. W adresie „test.test.com” brakuje znaku „@”."}, //invalid email format - doubled dots
                {"test()@test.com", "Część przed znakiem „@” nie może zawierać symbolu „(”."},                           //invalid email format - invalid prefix part
                {"test@test@", "Część po znaku „@” nie może zawierać symbolu „@”."},                                     //invalid email format - doubled @ on domain part
        };
    }

    @DataProvider
    public Object[][] wrongPasswords() {
        return new Object[][]{
                {"aaaaaaaaaaaaa", "Bardzo słabe - Proszę wpisać mocniejsze hasło."},         //13 char, no uppercase, no digits, no special char ("Bardzo słabe")
                {"AAAAAAAAAAAAA", "Bardzo słabe - Proszę wpisać mocniejsze hasło."},         //13 char, no lowercase, no digits, no special char ("Bardzo słabe")
                {"1111111111111", "Bardzo słabe - Proszę wpisać mocniejsze hasło."},         //13 char, no lowercase, no uppercase, no special char ("Bardzo słabe")
                {"#############", "Bardzo słabe - Proszę wpisać mocniejsze hasło."},         //13 char, no lowercase, no uppercase, no digit ("Bardzo słabe")
                {"aaaaaaaaaaaa1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char, no uppercase, no special char ("Słabe")
                {"AAAAAAAAAAAA1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char, no lowercase, no special char ("Słabe")
                {"############1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char, no lowercase, no uppercase ("Słabe")
                {"aaaaaaaaaaa#1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char, no uppercase ("Słabe")
                {"AAAAAAAAAAA#1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char, no lowercase ("Słabe")
                {"###########A1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char, no lowercase("Słabe")
                {"aaaaaaaaaaA#1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char ("Słabe")
                {"AAAAAAAAAAa#1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char ("Słabe")
                {"##########aA1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char ("Słabe")
                {"aaaaaaaA#1A#1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char ("Słabe")
                {"AAAAAAAa#1a#1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char ("Słabe")
                {"#######aA1aA1", "Słabe - Proszę wpisać mocniejsze hasło."},                //13 char ("Słabe")
        };
    }

    @Test(dataProvider = "wrongEmails")
    public void shouldNotRegisterWithIncorrectEmailTest(String email, String expErrorMessage) {
        new MyAccountPage(driver)
                .goToMyAccountPage()
                .typeRegisterEmail(email)
                .submitRegister()
                .assertValidationMessageIsShown(email, expErrorMessage);
    }

    @Test(dataProvider = "wrongPasswords")
    public void shouldNotRegisterWithIncorrectPasswordTest(String password, String expErrorMessage) {
        new MyAccountPage(driver)
                .goToMyAccountPage()
                .typeRegisterEmail(email)
                .typeRegisterPassword(password)
                .clickOutside()
                .assertErrorPasswordIsShown(expErrorMessage)
                .assertRegisterButtonIsDisabled();
    }

    @Test
    public void shouldNotRegisterWithEmptyEmailTest() {
        new MyAccountPage(driver)
                .goToMyAccountPage()
                .typeRegisterPassword(config.getApplicationPassword())
                .submitRegister()
                .assertErrorEmptyEmail();
    }

    @Test
    public void shouldNotRegisterWithEmptyPasswordTest() {
        new MyAccountPage(driver)
                .goToMyAccountPage()
                .typeRegisterEmail(email)
                .submitRegister()
                .assertErrorEmptyPassword();
    }
}
