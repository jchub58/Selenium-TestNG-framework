package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.saucedemo.pages.*;
import com.saucedemo.utils.ConfigReader;

public class CheckoutTest extends BaseTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );
    }

    @Test(groups = {"smoke", "regression"})
    public void testCompleteCheckout() {
        productsPage.addProductToCart(0);
        productsPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderComplete());
        Assert.assertEquals(checkoutPage.getCompleteMessage(),
                "Thank you for your order!");
    }
}