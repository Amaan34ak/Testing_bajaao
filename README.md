# Testing_bajaao

package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BajaaoFilterPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public BajaaoFilterPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    // Filter And Sort Button

    @FindBy(css = "a.wizzy-filters-mobile-entry")
    WebElement filterAndSortButton;

    // Apply Button

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    WebElement applyButton;

    // ===========================
    // CLICK FILTER
    // ===========================

    public void clickFilterAndSort() {

        wait.until(ExpectedConditions.elementToBeClickable(filterAndSortButton));

        js.executeScript("arguments[0].click();", filterAndSortButton);
    }

    // ===========================
    // SELECT PRICE RANGE
    // ===========================

    public void selectPriceRange5000To10000() {

        WebElement priceCheckbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[contains(text(),'₹5000')]"
                                + "//ancestor::li"
                                + "//input[@type='checkbox']")));

        js.executeScript("arguments[0].scrollIntoView(true);", priceCheckbox);

        js.executeScript("arguments[0].click();", priceCheckbox);
    }

    // ===========================
    // CLICK APPLY
    // ===========================

    public void clickApply() {

        wait.until(ExpectedConditions.elementToBeClickable(applyButton));

        js.executeScript("arguments[0].click();", applyButton);
    }

    // ===========================
    // COMPLETE FILTER FLOW
    // ===========================

    public void applyPriceFilter() {

        clickFilterAndSort();

        selectPriceRange5000To10000();

        clickApply();
    }
}