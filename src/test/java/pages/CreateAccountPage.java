package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class CreateAccountPage {

   private static WebDriverWait wait;

    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    @FindBy(id = "SubmitCreate")
    static
    WebElement createAnAccountButton;

    @FindBy(id = "submitAccount")
    static
    WebElement registerAnAccountButton;


    @FindBy(id = "create-account_form")
    static
    WebElement createAccountForm;

    @FindBy(id = "create_account_error")
    static
    WebElement redAlertBox ;

    @FindBy(className = "alert-danger")
    static
    WebElement createAccountAlertBox ;


    @FindBy(id = "account-creation_form")
    static
    WebElement personalInformationForm ;



    public static void clickOnCreateAnAccountButton(){
        createAnAccountButton.click();

    }

    public static void clickOnRegisterAnAccountButton(){
        registerAnAccountButton.click();
    }

    public static WebElement getCreateAccountForm() {
        return createAccountForm;
    }

    public static WebElement getRedAlertBox() {
        return redAlertBox;
    }
    public static WebElement getCreateAccountAlertBox() {
        return createAccountAlertBox;
    }

    public static WebElement getPersonalInformationForm() {
        return personalInformationForm;
    }

public static boolean isRedAlertBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(redAlertBox));
boolean isDisplayed = false;
try {
    isDisplayed = redAlertBox.isDisplayed();
} catch (NoSuchElementException e) {}
return isDisplayed;

}

    public static boolean isCreateAccountAlertBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(createAccountAlertBox));
        boolean isDisplayed = false;
        try {
            isDisplayed = createAccountAlertBox.isDisplayed();
        } catch (NoSuchElementException e) {}
        return isDisplayed;

    }




}
