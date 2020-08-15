package com.heroku.qa.browser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.heroku.qa.constants.Constants;
import com.heroku.qa.helpers.LoggerHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	public WebDriver driver = null;
	public static DesiredCapabilities cap;
	public static Properties prop;
	public static ChromeOptions chromeOptions;
	public FileInputStream inputStream;
	private final Logger log = LoggerHelper.getLogger(Browser.class);
	

	private Browser() {

		prop = new Properties();
		try {
			inputStream = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/heroku/qa/config/config.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			prop.load(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("CHROME")) {

			chromeOptions = new ChromeOptions();
			cap = new DesiredCapabilities();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);

		}

		else if (browserName.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		else {
			System.out.println("Path of Driver Executable is not Set for any Browser");
		}
		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		BrowserManager.setWebDriver(driver);

		driver.get(prop.getProperty("url"));
		log.info("URL has been opened: " + prop.getProperty("url"));

	}

	public static void initialize() {
		if (BrowserManager.getDriver() == null)
			try {
				new Browser();
			} catch (Exception e) {

			}

	}

	public static void quit() {
		if (BrowserManager.getDriver() != null) {
			BrowserManager.getDriver().quit();
		}
	}

	public static String getBrowser() {

		String browser = ((RemoteWebDriver) BrowserManager.getDriver()).getCapabilities().getBrowserName().toString();
		return browser;
	}

	public static String getVersion() {

		String v = ((RemoteWebDriver) BrowserManager.getDriver()).getCapabilities().getVersion().toString();
		return v;
	}
	
	
	public static String getPlatform() {
		String platform= ((RemoteWebDriver) BrowserManager.getDriver()).getCapabilities().getPlatform().toString();
		return platform;
	}

}
