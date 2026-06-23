package stepDefinations;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert; 
import org.openqa.selenium.WebDriver;

import pageObject.BajaaoFilterPage;
import base.Base; 

public class FilterSteps {
    
    WebDriver driver = Base.getdriver(); 
    
    BajaaoFilterPage filterPage = new BajaaoFilterPage(driver);

    @When("the user applies a price filter between {int} and {int}")
    public void the_user_applies_a_price_filter_between_and(Integer min, Integer max) {
        filterPage.applyPriceFilter(min.toString(), max.toString());
    }

    @Then("the search results should display products within the selected price range")
    public void the_search_results_should_display_products_within_the_selected_price_range() {
        
        Assert.assertTrue(filterPage.isFilterApplied(), "The price filter tags were not displayed on the page.");
    }
}