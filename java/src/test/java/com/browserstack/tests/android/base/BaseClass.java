package com.browserstack.tests.android.base;

import com.browserstack.utils.DriverFactory;
import com.browserstack.utils.ScreenshotUtils;
import com.browserstack.PageObjectManager;
import com.browserstack.config.BrowserStackSetup;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import com.browserstack.utils.AddProductToCart;
import com.browserstack.utils.WaitUtils;

public class BaseClass {

    protected AppiumDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseClass.class);
    public static boolean isTestPassed;

    @BeforeSuite
    public void initializeDriver() throws Exception {
        logger.info("Initializing driver before suite");
        this.driver = DriverFactory.getDriver();
        String appPackage = driver.getCapabilities().getCapability("appPackage").toString();
        Assert.assertNotNull(appPackage, "App package should not be null");
        logger.info("App launched with package: " + appPackage);
    }

    /**
     * Setup method - runs before each test
     */
    @BeforeClass
    @Parameters({ "platform" })
    public void setUp(String platform) throws Exception {
        try {
            PageObjectManager pageManager = new PageObjectManager(driver);
            logger.info("Start login process");
            pageManager.getLoginPage().enterNumber("9800000006");
            pageManager.getLoginPage().clickOtpButton();
            pageManager.getLoginPage().enterOtp("301289");
            WaitUtils.implicitWait(driver, 2);
            logger.info("Start takeovers");
            pageManager.getHomePage().clickCancelButton();
            WaitUtils.implicitWait(driver, 2);
            logger.info("Handling Home Page Takeovers");

            WaitUtils.implicitWait(driver, 2);
            AddProductToCart.inactiveMethod();
            AddProductToCart.addToCart("1017249");

        } catch (Exception e) {
            throw new AssertionError("Android app setup failed\n", e);
        }
    }

    @BeforeTest
    public void setUp() {
        isTestPassed = false; // default to false
    }

    /**
     * Teardown method - runs after each test
     * //
     */

    @AfterTest
    public void updateTestStatus() {
        ScreenshotUtils screenshotUtils = new ScreenshotUtils(driver);
        screenshotUtils.saveScreenshotToAllure();

        String status = isTestPassed ? "passed" : "failed";
        String reason = isTestPassed ? "Test passed successfully" : "Test failed";
        BrowserStackSetup.markTestStatus(driver, status, reason);
        System.out.println("After test method");
    }

    @AfterSuite
    public void tearDown() {
        logger.info("Starting test teardown");
        if (driver != null) {
            DriverFactory.quitDriver();
            driver = null;
        }
    }

    /**
     * Additional setup - runs before each test method
     */
    // public void beforeTestMethod() {
    // // This ensures driver is ready before test methods run
    // if (driver == null) {
    // logger.warn("Driver is null before test method!");
    // }
    // }

    /**
     * Get current platform from driver
     */
    protected String getPlatformName() {
        return driver.getCapabilities().getCapability("platformName").toString();
    }
}
