package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageHeader {

    @FindBy(className = "login")
    static
    WebElement signInButton;

    @FindBy(xpath = "//form[@id='create-account_form'] // input[@class='is_required validate account_input form-control']")
    static
    WebElement emailInputField;



    public PageHeader(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }



public static void clickOnSignInButton(){
    signInButton.click();

}

    public static void fillEmailInputField(String email){
        emailInputField.sendKeys(email);

    }
}
