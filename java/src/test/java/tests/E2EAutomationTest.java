package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverManager;
import utils.ExcelUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class E2EAutomationTest {
    
    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;
    
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://www.amazon.com");
        
        // Initialize page objects
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    @Description("E2E test: Search item, extract details, add to cart, capture screenshot, save to Excel")
    public void testSearchAndAddToCart() throws IOException {
        String searchItem = "iPhone 15";
        
        // Step 1: Search for item
        searchForItem(searchItem);
        
        // Step 2: Get product details from search results
        String productName = searchPage.getFirstProductName();
        String productPrice = searchPage.getFirstProductPrice();
        
        // Step 3: Click on first product
        clickFirstProduct();
        
        // Step 4: Extract detailed product information
        String detailedName = productPage.getProductTitle();
        String brand = productPage.getBrand();
        String model = productPage.getModel();
        String manufacturer = productPage.getManufacturer();
        
        // Step 5: Add to cart
        addItemToCart();
        
        // Step 6: Verify item added to cart
        Assert.assertTrue(cartPage.isItemAddedToCart(), "Item was not added to cart successfully");
        
        // Step 7: Take screenshot
        String screenshotPath = takeScreenshot("item_added_to_cart");
        
        // Step 8: Save to Excel
        String[] headers = {"Item_Name", "Model", "Manufacturer", "Brand", "Price", "Test_Date", "Screenshot_Path", "Test_Status"};
        String[] data = {
            detailedName,
            model,
            manufacturer,
            brand,
            productPrice,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            screenshotPath,
            "PASSED"
        };
        
        ExcelUtils.writeToCSV(headers, data, "test_results.xlsx");
        
        // Assertions
        Assert.assertFalse(detailedName.isEmpty(), "Product name should not be empty");
        Assert.assertNotNull(screenshotPath, "Screenshot should be captured");
        
        System.out.println("Test data saved to: test-data/test_results.xlsx");
        System.out.println("Screenshot saved to: " + screenshotPath);
    }
    
    @Test
    @Description("Test searching for multiple items and saving results")
    public void testMultipleItemsSearch() throws IOException {
        String[] searchItems = {"MacBook Pro", "Samsung Galaxy", "Dell Laptop"};
        List<String[]> allResults = new ArrayList<>();
        String[] headers = {"Search_Term", "Item_Name", "Price", "Test_Date", "Test_Status"};
        
        for (String item : searchItems) {
            searchForItem(item);
            
            String productName = searchPage.getFirstProductName();
            String productPrice = searchPage.getFirstProductPrice();
            
            String[] data = {
                item,
                productName,
                productPrice,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "PASSED"
            };
            
            allResults.add(data);
            ExcelUtils.writeToCSV(headers, data, "multiple_search_results.xlsx");
        }
        
        // Create comprehensive report
        String reportPath = ExcelUtils.createTestReport(allResults, headers);
        System.out.println("Comprehensive report saved to: " + reportPath);
        
        Assert.assertEquals(allResults.size(), searchItems.length, "All search items should be processed");
    }
    
    @Step("Search for item: {item}")
    private void searchForItem(String item) {
        homePage.searchItem(item);
    }
    
    @Step("Click on first product")
    private void clickFirstProduct() {
        searchPage.clickFirstProduct();
    }
    
    @Step("Add item to cart")
    private void addItemToCart() {
        productPage.addToCart();
    }
    
    @Step("Take screenshot: {name}")
    private String takeScreenshot(String name) throws IOException {
        return cartPage.takeScreenshot(name);
    }
}
