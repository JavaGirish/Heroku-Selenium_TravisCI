package com.heroku.qa.base;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.heroku.qa.browser.Browser;
import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.constants.Constants;
import com.heroku.qa.helpers.ExcelHelper;
import com.heroku.qa.helpers.WaitHelper;
import com.heroku.qa.pages.AddComputerPage;
import com.heroku.qa.pages.LandingPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class BaseClass {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public AddComputerPage addCompPage;
	
	
	protected BaseClass(){
		
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public void initializePages() {
		landingPage = new LandingPage();
		addCompPage = new AddComputerPage();
	}
	
	public void clickOnElement(WebElement element) {
		WaitHelper.waitForElementToBeVisible(element);
		element.click();
		
	}
	
	
	public void enterText(WebElement element, String value) {
		WaitHelper.waitForElementToBeVisible(element);
		element.clear();
		element.sendKeys(value);
	}
	
	
	public String getPageTitle() {
		return BrowserManager.getDriver().getTitle();
	}
	
	public String getPageHeader(WebElement element) {
		return element.getText();
	}
	
	
	public void selectValueFromDropBox(WebElement element, String value) {
		Select select = new Select(element);
		try {
		select.selectByVisibleText(value);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Value is not available in dropdown: " + value);
		}
		
	}
	
	
	
	
	@BeforeClass
	@Description("User launches Browser and navigates to website")
	@Step("Launching Browser and navigate to URL")
	public void setUp() {
		try {
			Browser.initialize(); //Initialize browser
			initializePages(); //Initialize all pages
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@AfterSuite
	@Description("User closes browser window")
	@Step("Closing all browser sessions")
	public void wrapUp() {
		BrowserManager.getDriver().close();
	}
	
	
	
	public static void LaunchTestReport() {
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\girishn3\\Desktop\\reports.bat");
		try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(output);
                System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
	
	
	

	}
	


