package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BajaaoHomePage {
    WebDriver driver;

    public BajaaoHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action: Navigate to the site
    public void navigateToHomePage() {
        driver.get("https://www.bajaao.com");
    }

    // Validation: Check if homepage loaded successfully
    public boolean isHomePageLoaded() {
        return driver.getTitle().toLowerCase().contains("bajaao");
    }
}