package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    
    public static WebDriver getDriver(String browser, boolean headless) {
        WebDriver driver = null;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser " + browser + " not supported");
        }
        
        return driver;
    }
    
    public static WebDriver getDriver() {
        return getDriver("chrome", false);
    }
}
