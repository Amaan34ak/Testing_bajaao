package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src//test//resources//features"}, glue= {"stepDefinations", "hooks"},
	tags= "@Homepage or @Search or @Filter", plugin= {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
		)
public class TestRunner extends AbstractTestNGCucumberTests{
	
	
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
}
