package com.browserstack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import com.browserstack.utils.HelperMethod;

public class HomePage {

    private AppiumDriver driver;

    @FindBy(id = "com.fsn.nykaa.preprod:id/cancel")
    private List<WebElement> cancelButtons;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            System.out.println("Total elements found: " + cancelButtons.size());
            for (WebElement element : cancelButtons) {
                try {
                    // Check if element is displayed and enabled (interactable)
                    if (element.isDisplayed() && element.isEnabled()) {
                        // Wait until element is clickable before clicking
                        wait.until(ExpectedConditions.elementToBeClickable(element));
                        HelperMethod.clickElement(element);
                        System.out.println("Clicked element: " + element.getTagName() + " Text: " + element.getText());
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    System.out.println("Could not click element: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to find or interact with elements: " + e.getMessage());
        }
    }

    public HomePage handleHomePage() {
        clickCancelButton();
        return this;
    }
}