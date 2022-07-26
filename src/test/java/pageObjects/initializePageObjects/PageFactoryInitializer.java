/**
 * 
 */
package pageObjects.initializePageObjects;

import Selenium.BaseMethod;
import org.openqa.selenium.support.PageFactory;

import pageObjects.pages.*;

import static Selenium.WebDriverFactory.getWebDriver;


public class PageFactoryInitializer extends BaseMethod
{

	public TablePage tablePage()
	{
		getWebDriver().navigate().to("http://www.w3schools.com/html/html_tables.asp");
		//ApplicationConfig.setWebsiteUrl("http://www.w3schools.com/html/html_tables.asp");
		return PageFactory.initElements(getWebDriver(), TablePage.class);
	}
}
