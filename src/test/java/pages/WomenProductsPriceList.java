package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;


public class WomenProductsPriceList {

    public WomenProductsPriceList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class = 'price product-price' and not(parent::*[@itemprop='offers'])]")
    List<WebElement> priceList;

    public List<String> getPriceList() {
       return priceList.stream()
               .map(el -> el.getAttribute("textContent").trim())
        .collect(Collectors.toList());

    }
}
