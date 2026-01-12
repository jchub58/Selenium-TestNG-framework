package com.saucedemo.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import com.saucedemo.utils.ConfigReader;

public class DriverFactory {
    
    // ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // commented for single-run (no parallel)
    private static WebDriver driver;
    
    public static WebDriver initDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
                
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                driver = new EdgeDriver();
                break;
                
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }
        
        return driver;
    }
    
    public static WebDriver getDriver() {
        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}