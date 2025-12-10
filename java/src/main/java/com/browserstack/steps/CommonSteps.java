package com.browserstack.steps;

import org.testng.Assert;

import com.browserstack.PageObjectManager;
import com.browserstack.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.PropertySource.Util;
import org.apache.logging.log4j.LogManager;
import com.browserstack.utils.UtilityAPI;

public class CommonSteps {
    private PageObjectManager pageManager;
    private static final Logger logger = LogManager.getLogger(CommonSteps.class);

    @Step("Open the cart")
    public void openCartPage(String stepDescription, AppiumDriver driver) {
        logger.info(stepDescription);
        this.pageManager = new PageObjectManager(driver);
        pageManager.getCartPage().openCart();
        pageManager.getCartPage().removeCartPopup(driver);
        String bagText = pageManager.getCartPage().getTextBag();

        Assert.assertTrue(bagText.contains("Bag"),"Text does not contain expected substring");
    }

    @Step("Click on Proceed to Pay button")
    public void clickOnPayButton(String stepDescription, AppiumDriver driver) {
        logger.info(stepDescription);
        this.pageManager = new PageObjectManager(driver);
        pageManager.getCartPage().clickPayButton();
        pageManager.getCartPage().changePaymentMethod();
        WaitUtils.implicitWait(driver, 5);
        String paymentTitle = pageManager.getPaymentPage().getPaymentTitle();

        Assert.assertTrue(paymentTitle.contains("Payment"), "Payment title does not contain expected text");
    }

    @Step("Select COD payment method and place order")
    public void selectCODPaymentAndPlaceOrder(String stepDescription, AppiumDriver driver) {
        this.pageManager = new PageObjectManager(driver);
        logger.info(stepDescription);
        pageManager.getPaymentPage().paymentPageHandle();
        WaitUtils.implicitWait(driver, 5);
    }

    @Step("Confirm order placement")
    public void confirmOrderPlacement(String stepDescription, AppiumDriver driver) {
        logger.info(stepDescription);
        this.pageManager = new PageObjectManager(driver);
        String orderConfirmText = pageManager.getOrderPage().confirmOrder();
        WaitUtils.implicitWait(driver, 5);

        Assert.assertTrue(orderConfirmText.contains("Order Confirmed!"),
                "Text does not contain expected substring");

    }

    @Step("Change payment gateway")
    public void changePaymentGateway(String stepDescription, AppiumDriver driver, String productType, String deviceType, String domain, String paymentMethod,
            String paymentGateway) {
        logger.info(stepDescription);
        try {
            UtilityAPI.switchPaymentGateway(productType, deviceType, domain, paymentMethod, paymentGateway);
        } catch (Exception e) {
            throw new AssertionError("Failed to switch payment gateway: " + e.getMessage());
        }
    }

    @Step("Switch PCI feature")
        public void switchPciFeature(String stepDescription, AppiumDriver driver,String switchValue, String deviceType, String domain) {
        logger.info(stepDescription);
        try {
            UtilityAPI.switchPci(switchValue, deviceType, domain);
        } catch (Exception e) {
            throw new AssertionError("Failed to switch PCI feature: " + e.getMessage());
        }
    }
}