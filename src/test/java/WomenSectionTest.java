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
 void pricesOnWomenSectionShouldNotBeEmpty() {

    driver.get("http://www.automationpractice.pl/index.php");

    Assertions.assertThat(driver.getTitle()).isEqualTo("My Shop");

    driver.findElement(By.xpath("//li/a[@title='Women']")).click();


    List<String> productsPriceList = driver.findElements(By.xpath("//span[@class = 'price product-price' and not(parent::*[@itemprop='offers'])]")).stream()
            .map(el -> el.getAttribute("textContent").trim().substring(1))
            .collect(Collectors.toList());

    System.out.println(productsPriceList);

    var productsWithEmptyPrice = productsPriceList.stream()
            .filter(el -> el.isEmpty())
            .collect(Collectors.toList());

    Assertions.assertThat(productsWithEmptyPrice).isEmpty();

    var productsWithZeroPrice = productsPriceList.stream()
            .anyMatch(el -> el.equals("0"));


    Assertions.assertThat(productsWithZeroPrice).isFalse();

}

@Test
public void pricesShouldBeInDollars() {

    driver.get("http://www.automationpractice.pl/index.php");

    Assertions.assertThat(driver.getTitle()).isEqualTo("My Shop");

    driver.findElement(By.xpath("//li/a[@title='Women']")).click();


    List<String> productsPriceList = driver.findElements(By.xpath("//span[@class = 'price product-price' and not(parent::*[@itemprop='offers'])]")).stream()
            .map(el -> el.getAttribute("textContent").trim())
            .collect(Collectors.toList());

    System.out.println(productsPriceList);


    var productsWithDollar = productsPriceList.stream()
                .allMatch(el -> el.contains("$"));

        Assertions.assertThat(productsWithDollar).isTrue();

    }

@Test
    public void blouseShouldHaveProperNameAndPrice(){

     String blouse = "Blouse";

    driver.get("http://www.automationpractice.pl/index.php");

    Assertions.assertThat(driver.getTitle()).isEqualTo("My Shop");

    driver.findElement(By.id("search_query_top")).click();

    driver.findElement(By.id("search_query_top")).sendKeys(blouse);

    driver.findElement(By.name("submit_search")).click();

var blouseProductName = driver.findElement(By.xpath("//h5[@itemprop='name'] /a[@class='product-name' and contains(text(), 'Blouse')] ")).getText();

    System.out.println(blouseProductName);

    Assertions.assertThat(blouseProductName.contains("Blouse")).isTrue();


    var blouseProductPrice = driver.findElement(By.xpath("//div[@class='right-block']//div[@class='content_price']/span")).getText().substring(1);

    var blouseProductPriceInt = Integer.parseInt(blouseProductPrice);

    System.out.println(blouseProductPriceInt);
    Assertions.assertThat(blouseProductPriceInt > 0).isTrue();


}

}