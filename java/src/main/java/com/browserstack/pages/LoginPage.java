package com.browserstack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.browserstack.utils.HelperMethod;

public class LoginPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "com.fsn.nykaa.preprod:id/mobileNumber")
    private WebElement numberField;

    @FindBy(id = "com.fsn.nykaa.preprod:id/btnGetOtp")
    private WebElement otpButton;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/llPin\"]/android.widget.EditText[1]")
    private WebElement otp1;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/llPin\"]/android.widget.EditText[2]")
    private WebElement otp2;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/llPin\"]/android.widget.EditText[3]")
    private WebElement otp3;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/llPin\"]/android.widget.EditText[4]")
    private WebElement otp4;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/llPin\"]/android.widget.EditText[5]")
    private WebElement otp5;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.fsn.nykaa.preprod:id/llPin\"]/android.widget.EditText[6]")
    private WebElement otp6;

    public LoginPage(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver must not be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public LoginPage enterNumber(String number) {
        try {
            WebElement numberFieldVisible = wait.until(ExpectedConditions.visibilityOf(numberField));
            numberFieldVisible.clear();
            numberFieldVisible.sendKeys(number);
        } catch (Exception e) {
            System.out.println("Failed to enter number: " + e.getMessage());
        }
        return this;
    }

    public LoginPage clickOtpButton() {
        try {
            WebElement otpBtnClickable = wait.until(ExpectedConditions.elementToBeClickable(otpButton));
            HelperMethod.clickElement(otpBtnClickable);
        } catch (Exception e) {
            System.out.println("Failed to click OTP button: " + e.getMessage());
        }
        return this;
    }

    public LoginPage enterOtp(String otp) {
        try {
            wait.until(ExpectedConditions.visibilityOf(otp1)).sendKeys(String.valueOf(otp.charAt(0)));
            wait.until(ExpectedConditions.visibilityOf(otp2)).sendKeys(String.valueOf(otp.charAt(1)));
            wait.until(ExpectedConditions.visibilityOf(otp3)).sendKeys(String.valueOf(otp.charAt(2)));
            wait.until(ExpectedConditions.visibilityOf(otp4)).sendKeys(String.valueOf(otp.charAt(3)));
            wait.until(ExpectedConditions.visibilityOf(otp5)).sendKeys(String.valueOf(otp.charAt(4)));
            wait.until(ExpectedConditions.visibilityOf(otp6)).sendKeys(String.valueOf(otp.charAt(5)));
        } catch (Exception e) {
            System.out.println("Failed to enter OTP: " + e.getMessage());
        }
        return this;
    }

    public LoginPage loginAs(AppiumDriver driver, String number, String otp) {
        enterNumber(number);
        clickOtpButton();
        enterOtp(otp);
        return this;
    }
}
