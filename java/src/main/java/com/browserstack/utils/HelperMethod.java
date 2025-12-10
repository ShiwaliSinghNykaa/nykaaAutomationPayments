package com.browserstack.utils;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class HelperMethod {

    public static void tapByCoordinates(AppiumDriver driver, int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));
    }

    public static void performScroll(AppiumDriver driver) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        int startX = width / 2;
        int startY = height / 2;

        int endY = startY - (height / 3);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(
                finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(scroll));
    }

    public static void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            System.err.println("Failed to click the element: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getText(WebElement element) {
        if (element != null) {
            return element.getText();
        } else {
            return "";
        }
    }
}