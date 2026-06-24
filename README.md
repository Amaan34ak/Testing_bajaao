# Testing_bajaao

package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BajaaoSearchPage {
    
    WebDriver driver;
    WebDriverWait wait;

    public BajaaoSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Shopify's standard desktop search bar locator
    @FindBy(xpath = "//input[@name='q' and (@type='text' or @type='search')]")
    WebElement searchBox;

    public void searchForProduct(String product) {
        try {
            // Wait for the search box to be clickable, then type and press ENTER
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            searchInput.clear();
            searchInput.sendKeys(product);
            searchInput.sendKeys(Keys.ENTER);
            
            // Adding a small pause to let the redirect begin
            Thread.sleep(2000); 
        } catch (Exception e) {
            System.out.println("Could not interact with the search bar: " + e.getMessage());
        }
    }

    public boolean isSearchSuccessful(String expectedProduct) {
        try {
            // We use 'wait.until' to check for multiple possible URL patterns.
            // Bajaao either redirects to a "/collections/" page or a "/search?q=" page.
            // If ANY of these appear in the URL within 15 seconds, the test passes (returns true).
            return wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("collections"),
                ExpectedConditions.urlContains("q="),
                ExpectedConditions.urlContains(expectedProduct.split(" ")[0].toLowerCase()) // Looks for "electric"
            ));
        } catch (Exception e) {
            // If 15 seconds pass and the URL still hasn't changed, it fails gracefully
            System.out.println("URL validation timed out.");
            return false;
        }
    }
}

