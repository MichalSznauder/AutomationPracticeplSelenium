package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.WomenProductsPriceList;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class WomenSectionTest extends BaseTest {

    private WomenProductsPriceList womenProductsPriceList;


    @BeforeEach
    @Override
    public void setupTest() {

        super.setupTest();
        womenProductsPriceList = new WomenProductsPriceList(driver);

    }

    @Test
    void pricesOnWomenSectionShouldNotBeEmpty() {


        driver.findElement(By.xpath("//li/a[@title='Women']")).click();

        List<String> productsPriceList = womenProductsPriceList.getPriceList();


        System.out.println(productsPriceList);

        var productsWithEmptyPrice = productsPriceList.stream()
                .filter(el -> el.isEmpty())
                .collect(Collectors.toList());

        assertThat(productsWithEmptyPrice).isEmpty();

        var productsWithZeroPrice = productsPriceList.stream()
                .map(el -> el.substring(1))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(productsWithZeroPrice);

        var productsWithZeroPriceInt = productsWithZeroPrice.stream()
                .allMatch(el -> el > 0);

        assertThat(productsWithZeroPriceInt).isTrue();

    }

    @Test
    public void pricesShouldBeInDollars() {


        driver.findElement(By.xpath("//li/a[@title='Women']")).click();


        List<String> productsPriceList = womenProductsPriceList.getPriceList();


        System.out.println(productsPriceList);


        var productsWithDollar = productsPriceList.stream()
                .allMatch(el -> el.contains("$"));

        assertThat(productsWithDollar).isTrue();

    }

    @Test
    public void blouseShouldHaveProperNameAndPrice() {

        String blouse = "Blouse";


        driver.findElement(By.id("search_query_top")).click();

        driver.findElement(By.id("search_query_top")).sendKeys(blouse);


        driver.findElement(By.name("submit_search")).click();

        WebElement blouseProductName = driver.findElement(By.xpath("//h5[@itemprop='name'] /a[@class='product-name' and contains(text(), 'Blouse')] "));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(blouseProductName));


        var blouseProductNameToString = blouseProductName.getText();

        assertThat(blouseProductNameToString.contains("Blouse")).isTrue();


        var blouseProductPrice = driver.findElement(By.xpath("//div[@class='right-block']//div[@class='content_price']/span")).getText().substring(1);

        var blouseProductPriceInt = Integer.parseInt(blouseProductPrice);

        System.out.println("blouse price is " + blouseProductPriceInt);
        assertThat(blouseProductPriceInt > 0).isTrue();

    }
}
