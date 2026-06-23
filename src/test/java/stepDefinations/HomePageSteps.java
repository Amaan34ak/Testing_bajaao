package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert; 
import org.openqa.selenium.WebDriver;

import pageObject.BajaaoHomePage;
import base.Base;

public class HomePageSteps {
    
    WebDriver driver = Base.getdriver(); 
    BajaaoHomePage homePage = new BajaaoHomePage(driver);

    // Removed the word "the" so it matches your feature file!
    @Given("user navigates to the Bajaao homepage")
    public void user_navigates_to_the_bajaao_homepage() {
        homePage.navigateToHomePage();
    }

    @Then("the homepage should load successfully")
    public void the_homepage_should_load_successfully() {
        Assert.assertTrue(homePage.isHomePageLoaded(), "Bajaao Homepage did not load correctly.");
    }
}