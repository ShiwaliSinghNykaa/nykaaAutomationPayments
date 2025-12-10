package com.browserstack.config;

import java.util.HashMap;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

/**
 * Configuration class for BrowserStack capabilities and URLs
 */
public class BrowserStackSetup {

    public static AndroidDriver driver;

    public static void launchAppiumDriver() throws Exception {
        // BrowserStack credentials
        String USERNAME = "shiwalisingh_bwNIoJ";
        String ACCESS_KEY = "ybexZ7FsHEFsy8KQhSnn";

        // BrowserStack hub URL
        URL browserStackUrl = new URL("http://" + USERNAME + ":" + ACCESS_KEY + "@hub.browserstack.com/wd/hub");

        // Desired capabilities for your test
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Google Pixel 7"); // example device
        caps.setCapability("os_version", "13.0");
        caps.setCapability("app", "bs://24865d952ff2e16a62d3061f670752bcfdd5d6a7");
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("autoGrantPermissions", true);

        // BrowserStack specific options
        caps.setCapability("bstack:options", new HashMap<String, Object>() {
            {
                put("buildName", "Android Login Test");
                put("projectName", "My Project");
                put("sessionName", "Android App Test");
                put("appiumLogs", "true"); // explicitly enable Appium logs
                put("deviceLogs", "true");
            }
        });

        // Initialize the AndroidDriver
        driver = new AndroidDriver(browserStackUrl, caps);
    }

    public static void markTestStatus(WebDriver driver, String status, String reason) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor
                .executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\""
                        + status + "\", \"reason\": \"" + reason + "\"}}");
    }
}
