package com.browserstack;

import io.appium.java_client.AppiumDriver;

import com.browserstack.pages.CartPage;
import com.browserstack.pages.OrderConfrimationPage;
import com.browserstack.pages.PaymentPageOldSinglePage;
import com.browserstack.pages.HomePage;
import com.browserstack.pages.LoginPage;

public class PageObjectManager {
    private AppiumDriver driver;
    private CartPage cartPage;
    private PaymentPageOldSinglePage paymentPage;
    private OrderConfrimationPage orderPage;
    private HomePage homePage;
    private LoginPage loginPage;

    public PageObjectManager(AppiumDriver driver) {
        this.driver = driver;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    public PaymentPageOldSinglePage getPaymentPage() {
        if (paymentPage == null) {
            paymentPage = new PaymentPageOldSinglePage(driver);
        }
        return paymentPage;
    }

    public OrderConfrimationPage getOrderPage() {
        if (orderPage == null) {
            orderPage = new OrderConfrimationPage(driver);
        }
        return orderPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
