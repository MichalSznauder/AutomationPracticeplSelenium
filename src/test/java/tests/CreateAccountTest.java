package tests;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAccountPage;
import pages.PageHeader;

import java.time.Duration;
import java.util.Random;

public class CreateAccountTest extends BaseTest{


    private  PageHeader pageHeader;
private CreateAccountPage createAccountPage;

    @BeforeEach
@Override
public void setupTest() {

    super.setupTest();
    pageHeader = new PageHeader(driver);
    createAccountPage = new CreateAccountPage(driver);
}



@Test
public void createAccountWithInvalidEmailNotPossible(){



    PageHeader.clickOnSignInButton();


    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
     wait.until(ExpectedConditions.visibilityOf(CreateAccountPage.getCreateAccountForm()));



    PageHeader.fillEmailInputField("");

    CreateAccountPage.clickOnCreateAnAccountButton();

    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait1.until(ExpectedConditions.visibilityOf(CreateAccountPage.getRedAlertBox()));

    Assertions.assertThat(CreateAccountPage.getRedAlertBox().isDisplayed()).isTrue();


    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    var emailWithoutAtSymbol = randomNumber +"test"+  "." + randomNumber;

    System.out.println(emailWithoutAtSymbol);


    PageHeader.fillEmailInputField(emailWithoutAtSymbol);

    CreateAccountPage.clickOnCreateAnAccountButton();

    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait2.until(ExpectedConditions.visibilityOf(CreateAccountPage.getRedAlertBox()));


    Assertions.assertThat(CreateAccountPage.getRedAlertBox().isDisplayed()).isTrue();

}

@Test
    public void createAccountWithoutMandatoryFieldsShouldNotBePossible(){

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    PageHeader.clickOnSignInButton();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait.until(ExpectedConditions.visibilityOf(CreateAccountPage.getCreateAccountForm()));


    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    var correctEmail = randomNumber +"@test"+  ".com";

    System.out.println(correctEmail);


    PageHeader.fillEmailInputField(correctEmail);


    CreateAccountPage.clickOnCreateAnAccountButton();

    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait1.until(ExpectedConditions.visibilityOf(CreateAccountPage.getPersonalInformationForm()));


    CreateAccountPage.clickOnRegisterAnAccountButton();

    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait2.until(ExpectedConditions.visibilityOf(CreateAccountPage.getCreateAccountAlertBox()));

    Assertions.assertThat(CreateAccountPage.getCreateAccountAlertBox().isDisplayed()).isTrue();
}

@Test
        public void createAccountWithProperAndFullDataShouldBePossible(){

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



    PageHeader.clickOnSignInButton();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait.until(ExpectedConditions.visibilityOf(CreateAccountPage.getCreateAccountForm()));



    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    var correctEmail = randomNumber +"@test"+  ".com";

    System.out.println(correctEmail);

    PageHeader.fillEmailInputField(correctEmail);


    CreateAccountPage.clickOnCreateAnAccountButton();

    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
    wait1.until(ExpectedConditions.visibilityOf(CreateAccountPage.getPersonalInformationForm()));

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

    CreateAccountPage.clickOnRegisterAnAccountButton();

   WebElement greenSuccessBox = driver.findElement(By.className("alert-success"));

    Assertions.assertThat(greenSuccessBox.isDisplayed()).isTrue();

    driver.findElement(By.className("logout")).click();


    }

}
