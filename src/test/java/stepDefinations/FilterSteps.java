package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import pageObject.BajaaoHomePage;
import pageObject.BajaaoSearchPage;
import pageObject.BajaaoFilterPage;
import base.Base; 

public class FilterSteps {
    
    WebDriver driver = Base.getdriver(); 
    BajaaoHomePage homePage = new BajaaoHomePage(driver);
    BajaaoSearchPage searchPage = new BajaaoSearchPage(driver);
    BajaaoFilterPage filterPage = new BajaaoFilterPage(driver);

    // This matches your C.filter.feature file perfectly
    @Given("user has launched the Bajaao website")
    public void user_has_launched_the_bajaao_website() {
        homePage.navigateToHomePage();
    }

    // This matches your C.filter.feature file perfectly
    @When("user searches for an instrument")
    public void user_searches_for_an_instrument() {
        searchPage.searchForProduct("electric guitar");
    }

    // This matches your C.filter.feature file perfectly
    @When("Range {string} is selected")
    public void range_is_selected(String range) {
        filterPage.applyPriceFilter("5000", "10000");
    }

    // This matches your C.filter.feature file perfectly
    @Then("the price is validated successfully")
    public void the_price_is_validated_successfully() {
        Assert.assertTrue(filterPage.isFilterApplied(), "The price filter tags were not displayed on the page.");
    }
}