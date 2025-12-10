package com.browserstack.tests.android;

import org.testng.annotations.Test;
import com.browserstack.steps.CommonSteps;
import com.browserstack.tests.android.base.BaseClass;
import io.qameta.allure.Description;
/**
 * Simple Android test case
 */
public class COD_Sanity extends BaseClass {
    CommonSteps steps = new CommonSteps();

    @Test
    @Description("Verify user is able to place order with COD payment method")
    public void verifyCODFlow() {
        logger.info("Verifying COD payment test case");

        try {
            steps.openCartPage("Opening the cart",driver);
            steps.clickOnPayButton("Clicking on Proceed to Pay button",driver);
            steps.selectCODPaymentAndPlaceOrder("Selecting COD payment method and placing order",driver);
            steps.confirmOrderPlacement("Confirming order placement",driver);

            logger.info("Order placed successfully with COD payment method");
            isTestPassed = true;

        } catch (Exception e) {
            isTestPassed = false;
            logger.error("Test failed with error: " + e.getMessage());
            throw new AssertionError("Verify user is able to place order with COD payment method test failed", e);
        }
    }


}