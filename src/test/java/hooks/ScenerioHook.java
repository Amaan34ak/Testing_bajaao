package hooks;

import java.net.URL;
import java.io.*;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v143.page.model.Screenshot;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
//import act.app.SourceInfo.Base;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

public class ScenerioHook extends Base {
	RemoteWebDriver remoteDriver;
	//WebDriver driver;
	@Before
	public void setup() {
		try {
			// System.out.println("java -jar src/main/resources/selenium-server-4.44.0.jar standalone");
			String browser = this.getBrowser();
			
			  
			if(browser.equals("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				options.setCapability("platformName", "Windows");
				  
				remoteDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
				 
			}
			else if(browser.equals("edge")) {
//				driver = new EdgeDriver();
				EdgeOptions options = new EdgeOptions();
				options.setCapability("platformName", "Windows");
				
				remoteDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
			}
			setDriver(remoteDriver);
			getdriver().manage().timeouts().
		implicitlyWait(Duration.ofSeconds(10));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@After 
	public void tearDown(Scenario scenario)
	{
		if(!scenario.isFailed()) {
			byte[] screenshot = 
					((TakesScreenshot) getdriver()).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment(
					"Screenshot",
					new ByteArrayInputStream(screenshot)
					);
		}
		getdriver().quit();
	}
	
}

	