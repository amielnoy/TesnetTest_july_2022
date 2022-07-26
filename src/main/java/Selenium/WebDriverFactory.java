package Selenium;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import static Utils.ConstsUtil.BROWSER;
import static Utils.ConstsUtil.WEB_SITE_URL;


public class WebDriverFactory extends BrowserFactory {
	public static ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();
	public static String browser;
	public static String url;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws Exception
	{
		String testName;
		System.out.println("Browser: " + BROWSER);
		System.out.println("WebsiteURL: " + WEB_SITE_URL);
		new WebDriverFactory();
		testName = method.getName();
		WebDriver driver = WebDriverFactory.createDriver();
		driver.manage().window().maximize();
		setWebDriver(driver);
	}

	public void setWebDriver(WebDriver driver) {
		wd.set(driver);
	}

	public static WebDriver getWebDriver() {
		return wd.get();
	}

	public static void saveFullPageScreenshot(String name) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getWebDriver());
		ImageIO.write(screenshot.getImage(), "PNG", new File(name));
	}

	public static String getTimeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date).replace("/","_").replace(":","_").replace(" ","_");
	}

	@AfterMethod(alwaysRun = true, enabled = true)
	public void afterMethod(ITestResult result) throws Exception {
		Thread.sleep(2000);
		if (result.getStatus() == ITestResult.FAILURE) {
			//saveFullPageScreenshot("./src/test/resources/Reports/Images/" + result.getTestClass().getName().replace(".","_") + "_"
			//		+ result.getMethod().getMethodName() +getTimeStamp()+ ".png");
		}
		getWebDriver().quit();
	}
}
