package com.saucedemo.tests;

import com.saucedemo.dataprovider.LoginDataProvider;
import com.saucedemo.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.listener.AllureListener;

@Listeners({AllureListener.class})
@Epic("SauceDemo Application")
@Feature("Login Functionality")
public class LoginTests extends BaseTest {
    private LoginPage loginPage;


    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "validCredentials",
            dataProviderClass = LoginDataProvider.class,
            groups = {"regression"})
    public void testMultipleValidLogins(String username, String password) {
        loginPage.login(username, password);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Test(dataProvider = "invalidCredentials",
            dataProviderClass = LoginDataProvider.class,
            groups = {"regression"})
    public void testInvalidLoginMessages(String username, String password,
                                         String expectedError) {
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError));
    }
}