package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.grid.config.Config;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;

import static org.openqa.selenium.devtools.v110.browser.Browser.close;

public class MyAccountPage extends HomePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#username")
    private WebElement loginEmailTxt;

    @FindBy(css = "#password")
    private WebElement loginPasswordTxt;

    @FindBy(css = "#rememberme")
    private WebElement loginRememberMeCheckbox;

    @FindBy(css = "button[name=login]")
    private WebElement loginBtn;

    @FindBy(css = ".woocommerce-LostPassword")
    private WebElement lostPasswordLnk;

    @FindBy(css = "#reg_email")
    private WebElement registerEmailTxt;

    @FindBy(css = "#reg_password")
    private WebElement registerPasswordTxt;

    @FindBy(css = "button[name=register]")
    private WebElement registerBtn;

    @FindBy(css = ".woocommerce-error")
    private WebElement registerError;

    @FindBy(xpath = "//a[contains(text(), 'Wyloguj')]")
    private WebElement logOutLnk;

    @FindBy(xpath = "//p[contains(text(), 'Witaj')]")
    private WebElement welcomeInfo;

    @FindBy(css = ".woocommerce-form-register div.woocommerce-password-strength")
    private WebElement wrongPasswordErrorElm;

    @FindBy(xpath = "//form[contains(@class, 'woocommerce-form-register')]//div[contains(@class, 'woocommerce-password-strength') and contains(text(), '.')]")
    private WebElement wrongPasswordErrorElmPresent;

    private WebDriverWait wait;

    public MyAccountPage typeLoginEmail(String email) {
        loginEmailTxt.clear();
        loginEmailTxt.sendKeys(email);
        return this;
    }

    public MyAccountPage typeLoginPassword(String password) {
        loginPasswordTxt.clear();
        loginPasswordTxt.sendKeys(password);
        return this;
    }

    public MyAccountPage checkRememberMe() {
        boolean selectedStatus = loginRememberMeCheckbox.isSelected();
        if (selectedStatus == false) {
            loginRememberMeCheckbox.click();
        }
        return this;
    }

    public MyAccountPage submitLogin() {
        loginBtn.click();
        return new MyAccountPage(driver);
    }

    public MyAccountPage typeRegisterEmail(String email) {
        registerEmailTxt.clear();
        registerEmailTxt.sendKeys(email);
        return this;
    }

    public MyAccountPage typeRegisterPassword(String password) {
        registerPasswordTxt.clear();
        registerPasswordTxt.sendKeys(password);
        return this;
    }

    public MyAccountPage submitRegister() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector("button[name=register]"));
        jse.executeScript("arguments[0].scrollIntoView()", element);
        registerBtn.click();
        return new MyAccountPage(driver);
    }

    public MyAccountPage submitRegisterWithFailure() {
        registerBtn.click();
        return new MyAccountPage(driver);
    }

    public MyAccountPage closeTab() {
        close();
        return new MyAccountPage(driver);
    }

    public MyAccountPage assertLogOutTextIsShown() {
        Assert.assertTrue(logOutLnk.isDisplayed(), "Log out link is displayed");
        Assert.assertTrue(logOutLnk.getText().contains("Wyloguj"), "Element: '" + logOutLnk.getText() + "' does not contain word 'Wyloguj");
        return this;
    }

    public MyAccountPage assertValidationMessageIsShown(String email, String expErrorMessage) {
        String message = driver.findElement(By.xpath("//input[@id='reg_email']")).getAttribute("validationMessage");
        Assert.assertEquals(message, expErrorMessage);
        return this;
    }

    public MyAccountPage assertWelcomeTextIsShown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//p[contains(text(), 'Witaj')]"));
        jse.executeScript("arguments[0].scrollIntoView()", element);
        Assert.assertTrue(welcomeInfo.isDisplayed(), "WelcomeInfo is displayed");
        Assert.assertTrue(welcomeInfo.getText().contains("Witaj"), "Element: '" + welcomeInfo.getText() + "' does not contain word 'Witaj");
        return this;
    }

    public MyAccountPage assertErrorAccountRegistered() {
        Assert.assertTrue(registerError.isDisplayed(), "RegisterError link is displayed");
        Assert.assertTrue(registerError.getText().contains("Konto z Twoim adresem e-mail jest już zarejestrowane"),
                "Błąd: '" + registerError.getText() + "' does not contain 'Konto z Twoim adresem e-mail jest już zarejestrowane");
        return this;
    }

    public MyAccountPage assertErrorAccountNotRegistered() {
        Assert.assertTrue(registerError.isDisplayed(), "RegisterError link is displayed");
        Assert.assertTrue(registerError.getText().contains("Nieznany adres e-mail. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika."),
                "Błąd: '" + registerError.getText() + "' does not contain 'Nieznany adres e-mail. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.");
        return this;
    }

    public MyAccountPage assertErrorInvalidPassword(String email) {
        Assert.assertTrue(registerError.isDisplayed(), "InvalidPasswordError link is displayed");
        Assert.assertTrue(registerError.getText().contains("Błąd: Dla adresu e-mail " + email + " podano nieprawidłowe hasło. Nie pamiętasz hasła?"),
                "Błąd: '" + registerError.getText() + "' does not contain 'Błąd: Dla adresu e-mail '" + email + "' podano nieprawidłowe hasło. Nie pamiętasz hasła?");
        return this;
    }

    public MyAccountPage assertErrorEmptyPassword() {
        Assert.assertTrue(registerError.isDisplayed(), "InvalidPasswordError link is displayed");
        Assert.assertTrue(registerError.getText().contains("Błąd: Proszę wpisać hasło."),
                "Błąd: '" + registerError.getText() + "' does not contain 'Błąd: Proszę wpisać hasło.");
        return this;
    }

    public MyAccountPage assertErrorEmptyEmail() {
        Assert.assertTrue(registerError.isDisplayed(), "InvalidEmailError link is displayed");
        Assert.assertTrue(registerError.getText().contains("Błąd: Podaj poprawny adres e-mail."),
                "Błąd: '" + registerError.getText() + "' does not contain 'Błąd: Podaj poprawny adres e-mail.");
        return this;
    }

    public MyAccountPage assertErrorInvalidEmail(String wrongEmail) {
        Assert.assertTrue(registerError.isDisplayed(), "InvalidEmailError link is displayed");
        Assert.assertTrue(registerError.getText().contains("Błąd: Brak " + wrongEmail + " wśród zarejestrowanych w witrynie użytkowników. Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail."),
                "Błąd: Brak " + registerError.getText() + " wśród zarejestrowanych w witrynie użytkowników. Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.");
        return this;
    }

    public MyAccountPage assertErrorPasswordIsShown(String expErrorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wrongPasswordErrorElm.isDisplayed());
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return wrongPasswordErrorElm.getText().length() > 0;
            }
        });
        return this;
    }

    public MyAccountPage assertRegisterButtonIsDisabled() {
        Assert.assertTrue(!registerBtn.isEnabled());
        return this;
    }

    public MyAccountPage assertMyAccountPageUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }

}

