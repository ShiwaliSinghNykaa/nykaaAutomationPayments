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

public class PaymentPageSaved {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "com.fsn.nykaa.preprod:id/txt_toolbar_title")
    private WebElement savedPaymentTitle;

    @FindBy(id = "com.fsn.nykaa.preprod:id/buttonPay")
    private WebElement paybutton;


    public PaymentPageSaved(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

   


}