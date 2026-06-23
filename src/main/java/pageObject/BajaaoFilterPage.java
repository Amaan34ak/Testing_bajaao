package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class BajaaoFilterPage {
    
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public BajaaoFilterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver; // Heavy artillery initialized
        PageFactory.initElements(driver, this);
    }

    // Shopify standard "Price" accordion button
    @FindBy(xpath = "//summary[contains(., 'Price') or contains(., 'price')] | //div[contains(@class, 'filter') and contains(text(), 'Price')]")
    WebElement priceAccordionButton;

    // Shopify standard price inputs
    @FindBy(xpath = "//input[contains(@name, 'price.gte') or contains(@id, 'Price-min') or @id='Filter-Price-GTE']")
    WebElement minPriceInput;

    @FindBy(xpath = "//input[contains(@name, 'price.lte') or contains(@id, 'Price-max') or @id='Filter-Price-LTE']")
    WebElement maxPriceInput;

    @FindBy(xpath = "//*[contains(@class, 'active-facets') or contains(@class, 'filter-clear') or contains(text(), 'Clear')]")
    WebElement activeFilterTag;


    public void applyPriceFilter(String min, String max) {
        try {
            // 1. Scroll down a bit so the filters are in view
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(1000);

            // 2. Try to click the "Price" dropdown to open it
            try {
                WebElement priceBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    org.openqa.selenium.By.xpath("//summary[contains(., 'Price') or contains(., 'price')]")
                ));
                js.executeScript("arguments[0].click();", priceBtn); // Force click with JS
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("Price accordion open/not found, trying to enter prices directly...");
            }

            // 3. Wait for inputs to be present in the HTML
            WebElement minInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//input[contains(@name, 'price.gte') or contains(@id, 'Price-min')]")
            ));
            WebElement maxInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//input[contains(@name, 'price.lte') or contains(@id, 'Price-max')]")
            ));

            // 4. Use JS to force them to be visible (just in case) and type the numbers
            js.executeScript("arguments[0].scrollIntoView(true);", minInput);
            minInput.clear();
            minInput.sendKeys(min);

            js.executeScript("arguments[0].scrollIntoView(true);", maxInput);
            maxInput.clear();
            maxInput.sendKeys(max);
            
            // Press ENTER on the max box to submit
            maxInput.sendKeys(Keys.ENTER);
            Thread.sleep(3000); // Wait for the page to refresh with the new prices

        } catch (Exception e) {
            System.out.println("Filter execution encountered an issue: " + e.getMessage());
        }
    }

    public boolean isFilterApplied() {
        try {
            // Since active tags can change constantly, let's check if the URL updated to contain the price parameter
            return wait.until(ExpectedConditions.urlContains("price="));
        } catch (Exception e) {
            return false;
        }
    }
}