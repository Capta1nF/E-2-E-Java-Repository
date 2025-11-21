package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    
    // Locators
    private final By CART_SUCCESS_MESSAGE = By.xpath("//div[contains(@class, 'a-alert-success')]");
    private final By GO_TO_CART = By.id("sw-atc-goto-cart-button");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isItemAddedToCart() {
        try {
            return findElement(CART_SUCCESS_MESSAGE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void goToCart() {
        clickElement(GO_TO_CART);
    }
}
