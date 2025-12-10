package com.browserstack.utils;

import com.browserstack.config.BrowserStackSetup;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                logger.info("Initializing Appium driver on BrowserStack...");
                BrowserStackSetup.launchAppiumDriver();
                driver = BrowserStackSetup.driver;
                logger.info("Appium driver initialized successfully.");
            } catch (Exception e) {
                logger.error("Failed to initialize Appium driver: ", e);
                throw new RuntimeException("Driver initialization failed", e);
            }
        } else {
            logger.info("Returning existing Appium driver instance.");
        }
        return driver;
    }


    public static void quitDriver() {
        if (driver != null) {
            logger.info("Quitting Appium driver.");
            driver.quit();
            driver = null;
            logger.info("Appium driver quit successfully.");
        } else {
            logger.warn("Attempted to quit driver but driver was null.");
        }
    }
}
