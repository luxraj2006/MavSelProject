package MavenExercises;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSample extends LaunchBrowser{

	@DataProvider(name="LoginCredentials")
    public String[][] LoginData()
   	{
   		String[][] lstLogin = { { "mercury", "mercury" } };
   		return lstLogin;
	}
	
	@DataProvider(name="SelectionParams")
    public String[][] SelectData()
   	{
   		String[][] lstParams = { { "1", "Acapulco", "Frankfurt" },
   								 { "2", "Acapulco", "Frankfurt" },
   								 { "3", "Acapulco", "Frankfurt" },
   								 { "4", "Acapulco", "Frankfurt" },
   								 { "1", "Acapulco", "London" },
  								 { "2", "Acapulco", "London" },
  								 { "3", "Acapulco", "London" },
  								 { "4", "Acapulco", "London" },
								 { "1", "Acapulco", "New York" },
								 { "2", "Acapulco", "New York" },
								 { "3", "Acapulco", "New York" },
								 { "4", "Acapulco", "New York" }
							  };

   		return lstParams;
	}
	
	@BeforeMethod
	public void bm() {
		System.out.println("In Before Method");
	}
	
	@Test (priority = 10, dataProvider = "LoginCredentials")
	public void Login(String strUserName, String strUserPwd) throws InterruptedException {
		WebElement currentElement = null;
		
		currentElement = driver.findElement(By.xpath("//input[@name='userName']"));
		currentElement.click();
		currentElement.sendKeys(strUserName);
		Thread.sleep(3000);
		
		currentElement = driver.findElement(By.xpath("//input[@name='password']"));
		currentElement.click();
		currentElement.sendKeys(strUserPwd);
		Thread.sleep(3000);
		
		currentElement = driver.findElement(By.xpath("//input[@name='login']"));
		currentElement.click();
	}
	
	@Test (priority = 20, dataProvider = "SelectionParams")
	public void FlightFinder(String strPassCount, String strFrom, String strTo) throws InterruptedException {
		Select objSelectItem;
		//Passenger Count drop down
		objSelectItem = new Select(driver.findElement(By.xpath("//select[@name='passCount']")));
		objSelectItem.selectByValue(strPassCount);
		Thread.sleep(1000);
		
		//Departing From drop down
		objSelectItem = new Select(driver.findElement(By.xpath("//select[starts-with(@name,'fromP')]")));
		objSelectItem.selectByValue(strFrom);
		Thread.sleep(1000);

		//Arriving in - name='toPort'
		objSelectItem = new Select(driver.findElement(By.xpath("//select[contains(@name,'oP')]")));
		objSelectItem.selectByValue(strTo);
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void AfterClassDataProviderSample() throws IOException {
		System.out.println("In AfterClassDataProviderSample");
		driver.close();
		//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
	}
	
	@AfterSuite
	public void AfterSuiteFunc() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
	}
}
