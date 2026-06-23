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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locator: Bajaao search box
    @FindBy(css = "input[name='q']")
    WebElement searchBox;

    // Action: Type product and press Enter
    public void searchForProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchBox.sendKeys(Keys.ENTER);
    }

    // Validation: Check if the search successfully redirected to results
    public boolean isSearchSuccessful(String expectedProduct) {
        wait.until(ExpectedConditions.urlContains("q="));
        return driver.getCurrentUrl().contains(expectedProduct.replace(" ", "+"));
    }
}