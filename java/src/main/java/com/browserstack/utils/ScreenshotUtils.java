package com.browserstack.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.AppiumDriver;

public class ScreenshotUtils {

    private AppiumDriver driver;

    public ScreenshotUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void saveScreenshotToAllure() {
        captureScreenshot();
    }
}
