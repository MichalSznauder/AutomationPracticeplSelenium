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

import java.util.List;
import java.util.stream.Collectors;

public class WomenSectionTest {

    private WebDriver driver;
    private WebElement element;




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
 void pricesOnWomenSectionShouldNotBeEmpty(){

driver.get("http://www.automationpractice.pl/index.php");

Assertions.assertThat(driver.getTitle()).isEqualTo("My Shop");

driver.findElement(By.xpath("//li/a[@title='Women']")).click();


    List<String> productsPriceList =  driver.findElements(By.xpath("//span[@class = 'price product-price' and not(parent::*[@itemprop='offers'])]")).stream()
       .map(el -> el.getAttribute("textContent").trim())
            .collect(Collectors.toList());

    System.out.println(productsPriceList);

var productsWithEmptyPrice = productsPriceList.stream()
        .filter(el -> el.isEmpty())
        .collect(Collectors.toList());

Assertions.assertThat(productsWithEmptyPrice).isEmpty();

var productsWithZeroPrice = productsPriceList.stream()
        .anyMatch(el -> el.equals("0"));


Assertions.assertThat(productsWithZeroPrice).isFalse();

var productsWithDollar = productsPriceList.stream()
        .allMatch(el -> el.contains("$"));

Assertions.assertThat(productsWithDollar).isTrue();

}


}
