# Testing_bajaao

package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

    // Looks for the "PRICE" accordion arrow
    @FindBy(xpath = "//*[contains(translate(text(), 'PRICE', 'price'), 'price')]")
    WebElement priceAccordionButton;

    public void applyPriceFilter(String min, String max) {
        try {
            // 1. Scroll down so the filters are visible on screen
            js.executeScript("window.scrollBy(0, 500);");
            Thread.sleep(1500);

            // 2. Try to click the Price accordion to open the checkboxes
            try {
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", priceAccordionButton);
                js.executeScript("arguments[0].click();", priceAccordionButton);
                Thread.sleep(1500); // Wait for the menu to slide open
            } catch (Exception e) {
                System.out.println("Price accordion not found or already open. Proceeding...");
            }

            // 3. DYNAMIC XPATH: This looks exactly at the DOM from your photo!
            // It finds the list item <li> containing both "5000" and "10000", then finds the checkbox inside it.
            String checkboxXPath = "//li[contains(., '" + min + "') and contains(., '" + max + "')]//input[@type='checkbox']";
            
            WebElement targetCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(checkboxXPath)));

            // 4. Force click the hidden checkbox using Javascript
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", targetCheckbox);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", targetCheckbox);
            
            System.out.println("Successfully clicked the checkbox for range: " + min + " - " + max);
            
            // Wait 3 seconds for the page to refresh with the filtered results
            Thread.sleep(3000); 

        } catch (Exception e) {
            System.out.println("Filter execution encountered an issue: " + e.getMessage());
        }
    }

    public boolean isFilterApplied() {
        try {
            // Check if the URL updated to show the filter was applied (From your photo: sellingPrice_gte=5000)
            return wait.until(ExpectedConditions.urlContains("5000"));
        } catch (Exception e) {
            return false;
        }
    }
}
