package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    
    // Locators
    private final By FIRST_PRODUCT = By.xpath("//div[@data-component-type='s-search-result'][1]//h2/a");
    private final By PRODUCT_TITLE = By.xpath("//div[@data-component-type='s-search-result'][1]//h2/a/span");
    private final By PRODUCT_PRICE = By.xpath("//div[@data-component-type='s-search-result'][1]//span[@class='a-price-whole']");
    
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    
    public String getFirstProductName() {
        return getText(PRODUCT_TITLE);
    }
    
    public String getFirstProductPrice() {
        try {
            return getText(PRODUCT_PRICE);
        } catch (Exception e) {
            return "Price not available";
        }
    }
    
    public void clickFirstProduct() {
        clickElement(FIRST_PRODUCT);
    }
}
