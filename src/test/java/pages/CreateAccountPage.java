package pages;

import model.PersonalInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;


public class CreateAccountPage extends BasePage {


    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "SubmitCreate")
    WebElement createAnAccountButton;

    @FindBy(id = "submitAccount")
    WebElement registerAnAccountButton;

    @FindBy(id = "create-account_form")
    WebElement createAccountForm;

    @FindBy(id = "create_account_error")
    WebElement redAlertBox;

    @FindBy(className = "alert-danger")
    WebElement createAccountAlertBox;

    @FindBy(id = "account-creation_form")
    WebElement personalInformationForm;

    @FindBy(className = "alert-success")
    WebElement greenSuccessBox;

    @FindBy(id = "customer_firstname")
    WebElement customerFirstName;

    @FindBy(id = "customer_lastname")
    WebElement customerLastName;

    @FindBy(id = "passwd")
    WebElement passwd;

    @FindBy(id = "days")
    WebElement days;

    @FindBy(id = "months")
    WebElement months;

    @FindBy(id = "years")
    WebElement years;


    public void clickOnCreateAnAccountButton() {
        createAnAccountButton.click();
    }

    public void clickOnRegisterAnAccountButton() {
        registerAnAccountButton.click();
    }

    public WebElement getCreateAccountForm() {
        return createAccountForm;
    }

    public WebElement getPersonalInformationForm() {
        return personalInformationForm;
    }

    public boolean isRedAlertBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(redAlertBox));
        boolean isDisplayed = false;
        try {
            isDisplayed = redAlertBox.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }

    public boolean isCreateAccountAlertBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(createAccountAlertBox));
        boolean isDisplayed = false;
        try {
            isDisplayed = createAccountAlertBox.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }

    public boolean isGreenSuccessBoxDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(greenSuccessBox));
        boolean isDisplayed = false;
        try {
            isDisplayed = greenSuccessBox.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }


    public void sendCreateAccountForm(PersonalInformation personalInformation) {

        customerFirstName.sendKeys(personalInformation.getFirstName());
        customerLastName.sendKeys(personalInformation.getLastName());
        passwd.sendKeys(personalInformation.getPassword());
        days.sendKeys(personalInformation.getDay());
        months.sendKeys(personalInformation.getMonth());
        years.sendKeys(personalInformation.getYear());

        clickOnRegisterAnAccountButton();

    }

}
