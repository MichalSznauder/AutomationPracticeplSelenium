package tests;

import com.github.javafaker.Faker;
import model.PersonalInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAccountPage;
import pages.PageHeader;

import java.time.Duration;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

public class CreateAccountTest extends BaseTest {


    private PageHeader pageHeader;
    private CreateAccountPage createAccountPage;
    private PersonalInformation personalInformation;
    Faker faker = new Faker();

    @BeforeEach
    @Override
    public void setupTest() {

        super.setupTest();
        pageHeader = new PageHeader(driver);
        createAccountPage = new CreateAccountPage(driver);
        personalInformation = new PersonalInformation();
    }


    @Test
    public void createAccountWithInvalidEmailNotPossible() {


        pageHeader.clickOnSignInButton();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(createAccountPage.getCreateAccountForm()));


        pageHeader.fillEmailInputField("");

        createAccountPage.clickOnCreateAnAccountButton();

        assertThat(createAccountPage.isRedAlertBoxDisplayed()).isTrue();


        Random random = new Random();
        int randomNumber = random.nextInt(1000);

        var emailWithoutAtSymbol = randomNumber + "test" + "." + randomNumber;

        System.out.println(emailWithoutAtSymbol);

        pageHeader.fillEmailInputField(emailWithoutAtSymbol);

        createAccountPage.clickOnCreateAnAccountButton();


        assertThat(createAccountPage.isRedAlertBoxDisplayed()).isTrue();

    }

    @Test
    public void createAccountWithoutMandatoryFieldsShouldNotBePossible() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        pageHeader.clickOnSignInButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(createAccountPage.getCreateAccountForm()));


        Random random = new Random();
        int randomNumber = random.nextInt(1000);

        var correctEmail = randomNumber + "@test" + ".com";

        System.out.println(correctEmail);

        pageHeader.fillEmailInputField(correctEmail);

        createAccountPage.clickOnCreateAnAccountButton();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait1.until(ExpectedConditions.visibilityOf(createAccountPage.getPersonalInformationForm()));

        createAccountPage.clickOnRegisterAnAccountButton();


        assertThat(createAccountPage.isCreateAccountAlertBoxDisplayed()).isTrue();
    }

    @Test
    public void createAccountWithProperAndFullDataShouldBePossible() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        pageHeader.clickOnSignInButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(createAccountPage.getCreateAccountForm()));

        Random random = new Random();
        int randomNumber = random.nextInt(1000);

        var correctEmail = randomNumber + "@test" + ".com";

        System.out.println(correctEmail);

        pageHeader.fillEmailInputField(correctEmail);

        createAccountPage.clickOnCreateAnAccountButton();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait1.until(ExpectedConditions.visibilityOf(createAccountPage.getPersonalInformationForm()));

        driver.findElement(By.id("id_gender1")).click();


        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setFirstName(faker.name().firstName());
        personalInformation.setLastName(faker.name().lastName());
        personalInformation.setPassword(faker.internet().password(5, 6));
        personalInformation.setDay("5");
        personalInformation.setMonth("May");
        personalInformation.setYear("1985");

        createAccountPage.sendCreateAccountForm(personalInformation);

        assertThat(createAccountPage.isGreenSuccessBoxDisplayed()).isTrue();

        driver.findElement(By.className("logout")).click();


    }

}
