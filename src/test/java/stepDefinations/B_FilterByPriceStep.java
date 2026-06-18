package stepDefinations;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;

import base.Base;
import io.cucumber.java.en.*;

import pageObject.PriceFilterPage;

public class B_FilterByPriceStep extends Base {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(B_FilterByPriceStep.class);

	WebElement filterByprice;
	String inputRange;
	List<WebElement>flowerList = new ArrayList<>();
	

@Given("user is on search screen and in stock is checked")
public void user_is_on_search_screen_and_in_stock_is_checked() {
    try {
    		getdriver().get(this.getFrameworkUrl());
    		PriceFilterPage priceFilterPage = new PriceFilterPage(getdriver());
    		priceFilterPage.getInStockCheckbox().click();
    }
    catch(Exception ex) {
    	
    }
    
}

@When("Range {string} is selected")
public void range_is_selected(String range ) {
    // Write code here that turns the phrase above into concrete actions
	try {
		PriceFilterPage priceFilterPage = new PriceFilterPage(getdriver());
		filterByprice = priceFilterPage.getPriceFilter();
		inputRange = range;
		Select selectFilter = new Select(filterByprice);
		selectFilter.selectByContainsVisibleText(inputRange);
		Thread.sleep(3000);;
		flowerList = priceFilterPage.getItemCard();
		
	}
	catch(Exception ex) {
		logger.debug("Exception occured:"+ex.getMessage());
	}
    
}

@Then("price is validated")
public void price_is_validated() {
    // Write code here that turns the phrase above into concrete actions
	try {
		for(int i = 0; i <= flowerList.size();i++) {
			String text = getdriver().findElement(By.xpath("(//p[contains(@data-testid, 'flower-price')])["+i+"]")).getText();
			text = text.replaceAll("[^0-9]","");
			if(inputRange.equals("Below 60") && !text.isEmpty()) {
				int value = Integer.parseInt(text);
				Assert.assertTrue(value<60 ,"value is not less than 60");
			}
			else if(inputRange.equals("₹60 - ₹70") && !text.isEmpty()) {
				int value = Integer.parseInt(text);
				Assert.assertTrue(value>= 60,"value is not less than 60");
				Assert.assertTrue(value<= 70,"value is not grater than 70");
				
			}
			else if(inputRange.equals(" Above ₹70") && !text.isEmpty()) {
				int value = Integer.parseInt(text);
				Assert.assertEquals(value>=70, "value is greater than 70");
			}
		}
	}catch(Exception ex) {
		
	}
    
}

}
