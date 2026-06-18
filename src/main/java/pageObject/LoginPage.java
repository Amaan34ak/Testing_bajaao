package pageObject;

import org.openqa.selenium.*;

public class LoginPage {
	WebDriver driver;
	By signUpAndsignInBtn = By.xpath("//*[text()='Sign In / Sign Up']");
	By signInBtn = By.xpath("//*[text()='Login']");;
	By email = By.id("input-Email");
	By password = By.id("input-Password");
	By submitBtn = By.xpath("//*[@type='submit']");
	By loginText = By.xpath("//div[@class='relative'])[3]");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement getSignUpandInBtn() {
		return driver.findElement(signUpAndsignInBtn);
	}
	public WebElement getSignInBtn() {
		return driver.findElement(this.signInBtn);
		
	}
	public WebElement getEmail() {
		return driver.findElement(this.email);
	}
	public WebElement getPassword() {
		// TODO Auto-generated method stub
		return driver.findElement(this.password);
	}
	public WebElement getSubmitBtn() {
		// TODO Auto-generated method stub
		return driver.findElement(this.submitBtn);
	}
	public WebElement getLoginText() {
		// TODO Auto-generated method stub
		return driver.findElement(this.loginText);
	}
	

}
