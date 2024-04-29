package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PageHeader extends BasePage {

    @FindBy(className = "login")
    WebElement signInButton;

    @FindBy(xpath = "//form[@id='create-account_form'] // input[@class='is_required validate account_input form-control']")
    WebElement emailInputField;


    public PageHeader(WebDriver driver) {
        super(driver);
    }


    public void clickOnSignInButton() {
        signInButton.click();

    }

    public void fillEmailInputField(String email) {
        emailInputField.sendKeys(email);

    }
}
