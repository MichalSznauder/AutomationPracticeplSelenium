package tests;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class CreateAccountTest extends BaseTest{


@Test
public void createAccountWithInvalidEmailNotPossible(){


    driver.findElement(By.className("login")).click();

    WebElement createAccountForm = driver.findElement(By.id("create-account_form"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait.until(ExpectedConditions.visibilityOf(createAccountForm));

    WebElement emailInputField = driver.findElement(By.xpath("//form[@id='create-account_form'] // input[@class='is_required validate account_input form-control']"));

    emailInputField.sendKeys("");

    driver.findElement(By.id("SubmitCreate")).click();

    WebElement redAlertBox = driver.findElement(By.id("create_account_error"));
    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait1.until(ExpectedConditions.visibilityOf(redAlertBox));

    Assertions.assertThat(redAlertBox.isDisplayed()).isTrue();


    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    var emailWithoutAtSymbol = randomNumber +"test"+  "." + randomNumber;

    System.out.println(emailWithoutAtSymbol);

    emailInputField.sendKeys(emailWithoutAtSymbol);

    driver.findElement(By.id("SubmitCreate")).click();

    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait2.until(ExpectedConditions.visibilityOf(redAlertBox));


    Assertions.assertThat(redAlertBox.isDisplayed()).isTrue();

}

@Test
    public void createAccountWithoutMandatoryFieldsShouldNotBePossible(){

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


    driver.findElement(By.className("login")).click();

    WebElement createAccountForm = driver.findElement(By.id("create-account_form"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait.until(ExpectedConditions.visibilityOf(createAccountForm));

    WebElement emailInputField = driver.findElement(By.xpath("//form[@id='create-account_form'] // input[@class='is_required validate account_input form-control']"));

    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    var correctEmail = randomNumber +"@test"+  ".com";

    System.out.println(correctEmail);

    emailInputField.sendKeys(correctEmail);

    driver.findElement(By.id("SubmitCreate")).click();



    WebElement personalInformation = driver.findElement(By.id("account-creation_form"));
    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait1.until(ExpectedConditions.visibilityOf(personalInformation));

    driver.findElement(By.id("submitAccount")).click();

    WebElement redAlertBox = driver.findElement(By.className("alert-danger"));
    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait2.until(ExpectedConditions.visibilityOf(redAlertBox));

    Assertions.assertThat(redAlertBox.isDisplayed()).isTrue();
}

@Test
        public void createAccountWithProperAndFullDataShouldBePossible(){

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    driver.findElement(By.className("login")).click();

    WebElement createAccountForm = driver.findElement(By.id("create-account_form"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait.until(ExpectedConditions.visibilityOf(createAccountForm));

    WebElement emailInputField = driver.findElement(By.xpath("//form[@id='create-account_form'] // input[@class='is_required validate account_input form-control']"));

    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    var correctEmail = randomNumber +"@test"+  ".com";

    System.out.println(correctEmail);

    emailInputField.sendKeys(correctEmail);

    driver.findElement(By.id("SubmitCreate")).click();


    WebElement personalInformation = driver.findElement(By.id("account-creation_form"));
    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait1.until(ExpectedConditions.visibilityOf(personalInformation));

    driver.findElement(By.id("id_gender1")).click();

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();

    String password = faker.internet().password(5,6);

    driver.findElement(By.id("id_gender1"));


    driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
    driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
    driver.findElement(By.id("passwd")).sendKeys(password);
    driver.findElement(By.id("days")).sendKeys("5");
    driver.findElement(By.id("months")).sendKeys("May");

   WebElement yearBox = driver.findElement(By.id("years"));
    yearBox.sendKeys("1985");

    driver.findElement(By.id("submitAccount")).click();



   WebElement greenSuccessBox = driver.findElement(By.className("alert-success"));

    Assertions.assertThat(greenSuccessBox.isDisplayed()).isTrue();

    driver.findElement(By.className("logout")).click();


    }

}
