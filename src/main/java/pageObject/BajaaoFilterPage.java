package pageObject;

import org.openqa.selenium.Keys;
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

    public BajaaoFilterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Locators: Price range inputs
    @FindBy(css = "input[name='filter.v.price.gte']")
    WebElement minPriceInput;

    @FindBy(css = "input[name='filter.v.price.lte']")
    WebElement maxPriceInput;

    // Locator: The "Active Filter" badge that appears after filtering
    @FindBy(css = ".active-facets__button")
    WebElement activeFilterTag;

    // Action: Input minimum and maximum price, then press Enter
    public void applyPriceFilter(String minPrice, String maxPrice) {
        wait.until(ExpectedConditions.visibilityOf(minPriceInput));
        
        minPriceInput.clear();
        minPriceInput.sendKeys(minPrice);
        
        maxPriceInput.clear();
        maxPriceInput.sendKeys(maxPrice);
        maxPriceInput.sendKeys(Keys.ENTER); // Submits the filter on Shopify sites
    }

    // Validation: Checks if the filter badge appears on the page
    public boolean isFilterApplied() {
        try {
            wait.until(ExpectedConditions.visibilityOf(activeFilterTag));
            return activeFilterTag.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}