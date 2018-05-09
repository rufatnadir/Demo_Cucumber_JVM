package core;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    public static WebDriver driver;

    public BaseClass(WebDriver driver) {
        BaseClass.driver = driver;
    }

    public static void waitForElementToBeVisible(WebElement element, long timeout) {
        new WebDriverWait(driver, timeout, 5000).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void clickElementWhenClickable(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public static boolean checkTitleEquals(String title) {
        return new WebDriverWait(driver, 20).until(ExpectedConditions
                .titleIs(title));
    }

    public static void insertText(WebElement element, String text) {
        waitForElementToBeVisible(element, 30);
        element.sendKeys(text);
    }

    public static void waitForAttributeEquals(WebElement element, String attribute, String value){
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .attributeToBe(element, attribute, value));
    }
}
