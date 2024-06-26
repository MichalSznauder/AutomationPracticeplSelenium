package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;


    protected static final String BASE_URL = "http://www.automationpractice.pl/index.php";
    protected static final String HOME_PAGE_TITLE = "My Shop";


    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();

        driver.get(BASE_URL);
        Assertions.assertThat(driver.getTitle()).isEqualTo(HOME_PAGE_TITLE);

    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
