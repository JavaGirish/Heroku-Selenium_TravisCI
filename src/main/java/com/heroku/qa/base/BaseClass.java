package com.heroku.qa.base;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;
import com.heroku.qa.browser.Browser;
import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.helpers.LoggerHelper;
import com.heroku.qa.helpers.WaitHelper;
import com.heroku.qa.pages.AddComputerPage;
import com.heroku.qa.pages.LandingPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

/*
 * 
 * @author: Girish Nair
 * 
 */

public class BaseClass {

	public WebDriver driver;
	public LandingPage landingPage;
	public AddComputerPage addCompPage;
	private static final Logger log = LoggerHelper.getLogger(BaseClass.class);

	/* Initializing BaseClass with driver */
	protected BaseClass() {

		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	/* Generic method to Initialize all Page class objects */
	public void initializePages() {
		try {
			landingPage = new LandingPage();
			addCompPage = new AddComputerPage();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Unable to initialize page classes");
		}
	}

	/* Generic Method to click on a element */
	public void clickOnElement(WebElement element) {
		WaitHelper.waitForElementToBeVisible(element);
		try {
			element.click();
			log.info("Clicked on element: " + element.toString());
		}

		catch (Exception e) {
			e.printStackTrace();
			log.debug("Unable to click on element: " + element.toString());
		}

	}

	/* Generic Method to enter text in a text field */
	public void enterText(WebElement element, String value) {
		WaitHelper.waitForElementToBeVisible(element);
		try {
			element.clear();
			element.sendKeys(value);
			log.info("Entered text: " + value.toString() + " in element: " + element.toString());
		}

		catch (Exception e) {
			e.printStackTrace();
			log.debug("Unable to enter: " + value.toString() + " in element: " + element.toString());
		}
	}

	/* Generic Method to get page title */
	public String getPageTitle() {
		return BrowserManager.getDriver().getTitle();
	}

	public String getPageHeader(WebElement element) {
		WaitHelper.waitForElementToBeVisible(element);
		try {
			log.info("Success message is displayed");
			return element.getText();
		}

		catch (Exception e) {
			e.printStackTrace();
			log.info("Success Message is not displayed");
		}
		return null;

	}

	/** Generic Method to select a value from drop down */
	public void selectValueFromDropBox(WebElement element, String value) {
		Select select = new Select(element);
		try {
			select.selectByVisibleText(value);
			log.info(value.toString() + " has been selected from available dropdown options");
		}

		catch (Exception e) {
			e.printStackTrace();
			log.debug("Value is not available in dropdown option: " + value);
		}

	}

	/*
	 * Launching browser. initializing driver, page objects and navigating to page
	 * url
	 */
	@BeforeClass
	@Description("User launches Browser and navigates to website")
	@Step("Launching Browser and navigate to URL")
	public void setUp() {
		try {
			Browser.initialize(); // Initialize browser
			initializePages(); // Initialize all pages

			/**
			 * Custom java library added to generate Allure Environment variables (Refer
			 * https://github.com/AutomatedOwl/allure-environment-writer for more info)
			 * Using this library, environment values can be added in allure reports
			 */
			allureEnvironmentWriter(ImmutableMap.<String, String>builder()
					// Write Browser in Allure environment variable
					.put("Browser",
							((RemoteWebDriver) BrowserManager.getDriver()).getCapabilities().getBrowserName().toString()
									.toUpperCase())
					
					// Write Browser version in Allure environment variable
					.put("Browser Version",
							((RemoteWebDriver) BrowserManager.getDriver()).getCapabilities().getVersion().toString())
					
					// Write URL in Allure environment variable
					.put("URL", Browser.prop.getProperty("url")).build(),
					
					// allure-results path
					System.getProperty("user.dir") + "/allure-results/");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Closing all browser sessions */
	@AfterSuite
	@Description("User closes browser window")
	@Step("Closing all browser sessions")
	public void wrapUp() {
		BrowserManager.getDriver().close();
		log.info("Browser has been closed!!");
	}

	public static void LaunchTestReport() {
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\girishn3\\Desktop\\reports.bat");
		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println(output);
				System.exit(0);
			} else {
				// abnormal...
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
