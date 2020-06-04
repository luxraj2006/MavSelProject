package MavenExercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class LaunchBrowser {
	

	public String strBrowserType;
	public String strUrl;
	public WebDriver driver;
	
	@BeforeTest
	//member function
	public void Launch() {
		System.out.println("In BeforeTest function");
		strBrowserType = "Chrome";
		strUrl = "http://newtours.demoaut.com/";
		//strUrl = "https://jqueryui.com/";
		if(strBrowserType.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Rajesh\\Java\\DriverExes\\chromedriver.exe");
			//Launch the Browser
			driver = new ChromeDriver();
		}
		else 
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Rajesh\\Java\\DriverExes\\geckodriver.exe");
			//Launch the Browser
			driver = new FirefoxDriver();
		} 
		driver.get(strUrl);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
}
