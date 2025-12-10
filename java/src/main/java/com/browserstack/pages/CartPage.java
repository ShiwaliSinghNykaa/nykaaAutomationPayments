package com.browserstack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.browserstack.utils.WaitUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import com.browserstack.utils.HelperMethod;

public class CartPage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "com.fsn.nykaa.preprod:id/action_shopping_cart")
    private WebElement cartButton;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/revampLlProceedBtnForPartialCheckout\"]")
    private WebElement payButton;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"com.fsn.nykaa.preprod:id/mainLayout\"]//android.widget.TextView[@text=\"Change\" and not(@resource-id='com.fsn.nykaa.preprod:id/changeAddress')]")
    private WebElement changePaymentMethodButton;

    @FindBy(xpath = "com.fsn.nykaa.preprod:id/cashPaymentWidget")
    private WebElement codOption;

    @FindBy(id = "com.fsn.nykaa.preprod:id/buttonPay")
    private WebElement confirmOrderButton;

    @FindBy(id = "com.fsn.nykaa.preprod:id/lytStickyBtmNewCart")
    private WebElement bagButton;

    @FindBy(id = "com.fsn.nykaa.preprod:id/bag")
    private WebElement bag;

    public CartPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartButton));
            HelperMethod.clickElement(cartButton);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to open cart: " + e.getMessage());
        }
    }

    public void clickPayButton() {
        try {
            WaitUtils.fluentWaitForVisibility(driver, payButton, 15, 2);
            wait.until(ExpectedConditions.elementToBeClickable(payButton));
            WaitUtils.implicitWait(driver, 2);
            HelperMethod.clickElement(payButton);
            WaitUtils.implicitWait(driver, 2);

            // if (!WaitUtils.isElementDisplayed(driver, changePaymentMethodButton)) {
            //     WaitUtils.implicitWait(driver, 2);
            //     HelperMethod.clickElement(payButton);
            // }
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to click Pay button: \n" + e.getMessage());
        }
    }

    public void changePaymentMethod() {
        try {
            WaitUtils.fluentWaitForVisibility(driver, changePaymentMethodButton, 15, 2);
            changePaymentMethodButton.click();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Failed to click Change Payment Method button: " + e.getMessage());
        }
    }

    public void removeCartPopup(AppiumDriver driver) {
        WaitUtils.implicitWait(driver, 5);
        try {
            for (int i = 0; i < 5; i++) {
                HelperMethod.performScroll(driver);
                WaitUtils.implicitWait(driver, 2);
            }
        } catch (TimeoutException e) {
            throw new AssertionError("Failed to click Change Payment Method button: \n" + e.getMessage());
        }
    }

    public String getTextBag() {
        return HelperMethod.getText(bag);
    }
}