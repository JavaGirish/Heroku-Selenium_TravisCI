package com.heroku.qa.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.LaunchSuite;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.Browser;
import com.heroku.qa.browser.BrowserManager;

import io.qameta.allure.Attachment;

public class AllureTestListener implements ITestListener {
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	public void onStart(ITestContext iTestContext) {
		System.out.println("Starting with execution of Test Suite: " + iTestContext.getCurrentXmlTest().getSuite().getName() + "\n");
		iTestContext.setAttribute("WebDriver", BrowserManager.getDriver());
	
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("Finished with execution of Test Suite: " + iTestContext.getCurrentXmlTest().getSuite().getName());
		
		//BaseClass.LaunchTestReport();
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Starting with execution of test: " + getTestMethodName(iTestResult) + " >> STARTED");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test has been Executed successfully: " + getTestMethodName(iTestResult) + " >> SUCCESS" + "\n");
		
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BrowserManager.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult) + "\n" );
			saveScreenshotPNG(driver);
		}
		
		saveTextLog(getTestMethodName(iTestResult) + " has passed and screenshot is taken!");	
		
		
	}

	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("Test Failed: " + getTestMethodName(iTestResult) + " >> FAIL");
		
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BrowserManager.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult) + "\n" );
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " has failed and screenshot is taken!");		
	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("Skipped Test Method:  " + getTestMethodName(iTestResult) + " SKIPPED");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
