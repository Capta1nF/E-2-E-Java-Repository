package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    
    // Locators
    private final By SEARCH_BOX = By.id("twotabsearchtextbox");
    private final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void searchItem(String itemName) {
        sendKeys(SEARCH_BOX, itemName);
        clickElement(SEARCH_BUTTON);
    }
}
