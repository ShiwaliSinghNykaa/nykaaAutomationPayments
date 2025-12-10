package com.browserstack.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private static final int DEFAULT_TIMEOUT = 10;

    // public static WebElement waitForElementToBeVisible(AppiumDriver driver,
    // WebElement element) {
    // return waitForElementToBeVisible(driver, element, DEFAULT_TIMEOUT);
    // }

    public static WebElement waitForElementToBeVisible(AppiumDriver driver, WebElement element, int timeoutInSeconds) {
        logger.info("Waiting for element: " + element + " to be visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isElementDisplayed(AppiumDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static WebElement waitForElementToBeClickable(AppiumDriver driver, WebElement element) {
        return waitForElementToBeClickable(driver, element, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeClickable(AppiumDriver driver, WebElement element, int timeoutInSeconds) {
        logger.info("Waiting for element: " + element + " to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // public static WebElement waitForElementToBePresent(AppiumDriver driver, WebElement element) {
    //     return waitForElementToBePresent(driver, element, DEFAULT_TIMEOUT);
    // }

    // public static WebElement waitForElementToBePresent(AppiumDriver driver, WebElement element, int timeoutInSeconds) {
    //     logger.info("Waiting for element: " + element + " to be present");
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    //     return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    // }

    public static void implicitWait(AppiumDriver driver, int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
    }

    public static void fluentWaitForVisibility(AppiumDriver driver, WebElement element, int timeoutInSeconds,
            int pollingInMillis) {
        Wait<AppiumDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
