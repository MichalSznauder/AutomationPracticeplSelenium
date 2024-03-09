import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class CreateAccountTest {


    private WebDriver driver;
    private WebElement element;

    protected WebDriverWait wait;



    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setupTest(){
        driver = new ChromeDriver();

    }

    @AfterEach
    public void teardown(){
        if (driver != null){
            driver.quit();
        }
    }

@Test
public void createAccountWithInvalidEmailNotPossible(){

    driver.get("http://www.automationpractice.pl/index.php");

    Assertions.assertThat(driver.getTitle()).isEqualTo("My Shop");

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

}
