package com.browserstack.tests.android;

import org.testng.annotations.Test;
import com.browserstack.steps.CommonSteps;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.browserstack.tests.android.base.BaseClass;
public class CC_Payu_Sanity extends BaseClass {
    
    CommonSteps steps = new CommonSteps();
    private static final Logger logger = LogManager.getLogger(CC_Payu_Sanity.class);

    @Test(dependsOnMethods = "switchPciFeature")
    @Description("Verify user is able to place order with CC PayU payment method")
    public void verifyCreditCardWithPayu() {
        logger.info("Verifying CC PayU payment test case");
        steps.switchPciFeature("Switching PCI to off mode", driver, "off", "ios", "nykaa");
    }

}
