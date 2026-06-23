package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import pageObject.BajaaoHomePage;
import pageObject.BajaaoSearchPage;
import base.Base;

public class SearchSteps {
    
    WebDriver driver = Base.getdriver(); 
    BajaaoHomePage homePage = new BajaaoHomePage(driver);
    BajaaoSearchPage searchPage = new BajaaoSearchPage(driver);

    // This matches your B.search.feature file perfectly
    @Given("user is on the Bajaao main screen")
    public void user_is_on_the_bajaao_main_screen() {
        homePage.navigateToHomePage();
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String product) {
        searchPage.searchForProduct(product);
    }

    // This matches your B.search.feature file perfectly
    @Then("the search results should be displayed")
    public void the_search_results_should_be_displayed() {
        Assert.assertTrue(searchPage.isSearchSuccessful("electric guitar"), "Search results page did not load.");
    }
}