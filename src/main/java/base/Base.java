package base;
import java.util.Properties;
import java.io.*;
import org.openqa.selenium.*;
public class Base {
	
	public static void main(String[] args) throws Exception {
		Base base = new Base();
		System.out.println(base.getUrl());
		System.out.println(base.getFrameworkUrl());
		System.out.println(base.getBrowser());
		
	}
	private static ThreadLocal<WebDriver>driver = new ThreadLocal<>();
	public static WebDriver getdriver() {
	return driver.get();
	}
	public static void setDriver(WebDriver driver1) {
		driver.set(driver1);
	}
	public String getUrl() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\Himank.Patidar\\eclipse-workspace\\Framework_Project\\src\\main\\java\\data.properties"));
		return prop.getProperty("url");
		
	}
	public String getFrameworkUrl() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\Himank.Patidar\\eclipse-workspace\\Framework_Project\\src\\main\\java\\data.properties"));
		return prop.getProperty("frameworkUrl");
		
	}
	public String getBrowser() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\Himank.Patidar\\eclipse-workspace\\Framework_Project\\src\\main\\java\\data.properties"));
		return prop.getProperty("browser");
	}
}
