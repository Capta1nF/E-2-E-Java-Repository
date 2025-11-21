package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    
    // Locators
    private final By PRODUCT_TITLE = By.id("productTitle");
    private final By ADD_TO_CART_BUTTON = By.id("add-to-cart-button");
    private final By BRAND = By.xpath("//tr[contains(.,'Brand')]//td[2]");
    private final By MODEL = By.xpath("//tr[contains(.,'Model')]//td[2]");
    private final By MANUFACTURER = By.xpath("//tr[contains(.,'Manufacturer')]//td[2]");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public String getProductTitle() {
        return getText(PRODUCT_TITLE);
    }
    
    public String getBrand() {
        try {
            return getText(BRAND);
        } catch (Exception e) {
            return "Brand not available";
        }
    }
    
    public String getModel() {
        try {
            return getText(MODEL);
        } catch (Exception e) {
            return "Model not available";
        }
    }
    
    public String getManufacturer() {
        try {
            return getText(MANUFACTURER);
        } catch (Exception e) {
            return "Manufacturer not available";
        }
    }
    
    public void addToCart() {
        clickElement(ADD_TO_CART_BUTTON);
    }
}
