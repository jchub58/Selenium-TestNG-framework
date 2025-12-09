package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.ConfigReader;

public class ProductsTest extends BaseTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );
    }

    @Test(groups = {"smoke", "regression"})
    public void testProductsDisplayed() {
        Assert.assertTrue(productsPage.getProductCount() > 0);
    }

    @Test(groups = {"regression"})
    public void testAddToCart() {
        productsPage.addProductToCart(0);
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1");
    }
}
