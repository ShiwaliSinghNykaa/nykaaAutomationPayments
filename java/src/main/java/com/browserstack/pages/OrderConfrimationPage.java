package com.browserstack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

public class OrderConfrimationPage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "com.fsn.nykaa.preprod:id/order_confirm_label")
    private WebElement orderConfirmText;

    @FindBy(id = "com.fsn.nykaa.preprod:id/iv_order_status")
    private WebElement orderStatusIcon;

    public OrderConfrimationPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String confirmOrder() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderConfirmText));
            wait.until(ExpectedConditions.visibilityOf(orderStatusIcon));
            return orderConfirmText.getText();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to confirm the order: " + e.getMessage());
        }
    }
}