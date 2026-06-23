package stepDefinations;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert; 
import org.openqa.selenium.WebDriver;

import pageObject.BajaaoSearchPage;
import base.Base;

public class SearchSteps {
    
    // Fetching driver from your Base class
    WebDriver driver = Base.getdriver(); 
    
    BajaaoSearchPage searchPage = new BajaaoSearchPage(driver);

    @When("the user searches for {string}")
    public void the_user_searches_for(String product) {
        searchPage.searchForProduct(product);
    }

    @Then("the search results page should display results for {string}")
    public void the_search_results_page_should_display_results_for(String product) {
        // TestNG Assert: Condition first, Message second
        Assert.assertTrue(searchPage.isSearchSuccessful(product), "Search results page did not load for: " + product);
    }
}