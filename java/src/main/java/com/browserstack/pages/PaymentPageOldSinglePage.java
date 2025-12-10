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
import com.browserstack.utils.HelperMethod;

public class PaymentPageOldSinglePage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "com.fsn.nykaa.preprod:id/cashPaymentWidget")
    private WebElement codOption;

    @FindBy(id = "com.fsn.nykaa.preprod:id/buttonPay")
    private WebElement paybutton;

    @FindBy(id = "com.fsn.nykaa.preprod:id/textViewToolbarTitle")
    private WebElement paymentTitle;

    public PaymentPageOldSinglePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void selectCODPayment() {
        try {
            int attempts = 0;
            int maxScrolls = 4;
            while (attempts < maxScrolls) {
                if (codOption.isDisplayed()) {
                    HelperMethod.clickElement(codOption);
                    return;
                } else {
                    HelperMethod.performScroll(driver);
                }
                attempts++;
            }

        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to select COD payment option: " + e.getMessage());
        }
    }

    public void payButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(paybutton));
            HelperMethod.clickElement(paybutton);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to click on pay button: \n" + e.getMessage());
        }
    }

    public String getPaymentTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentTitle));
            return HelperMethod.getText(paymentTitle);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to get payment title text: \n" + e.getMessage());
        }
    }

    public PaymentPageOldSinglePage paymentPageHandle() {
        selectCODPayment();
        payButton();
        return this;
    }
}