package Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import static Utils.ConstsUtil.*;


public class BrowserFactory
{
	static WebDriver driver;		
	static DesiredCapabilities capabilities;


	@SuppressWarnings("deprecation")
	protected static WebDriver createDriver() throws Exception
	{

		switch(BROWSER.toLowerCase())
		{
		case "chrome":
			//System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "chrome_headless":
			//System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");  
			chromeOptions.addArguments("--disable-gpu");  
			driver = new ChromeDriver(chromeOptions);
			break;

		case  "firefox":
			//System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
		}
		
		if(IMPLICIT_WAIT > 0)
		{
			implicitlywait(IMPLICIT_WAIT);
		}

		if(MAX_PAGE_LOAD_TIME > 0)
		{
			setMaxPageLoadTime(MAX_PAGE_LOAD_TIME);
		}

		driver.get(WEB_SITE_URL);
		if(!BROWSER.toLowerCase().contains("unit") )
		{
			driver.manage().window().maximize();
		}
		return driver;		
	}

	public static void implicitlywait(int timeInSeconds) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	public static void setMaxPageLoadTime(int timeInSeconds) throws Exception
	{
		driver.manage().timeouts().pageLoadTimeout(timeInSeconds, TimeUnit.SECONDS);
	}
}
