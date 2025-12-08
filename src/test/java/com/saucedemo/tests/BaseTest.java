package com.saucedemo.tests;

import com.saucedemo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.saucedemo.utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }
    
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}