package com.heroku.qa.helpers;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.constants.Constants;

public class WaitHelper {
	
	protected static WebDriverWait wait;
	
	
	
	public static void waitForElementToBeVisible(WebElement element) {
		
		WebDriverWait wait = getWait(Constants.timeOutInSeconds, Constants.pollingEveryInMillisec);
		try {wait.until(ExpectedConditions.visibilityOf(element));}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMillisec)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMillisec, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		return wait;
	}
	
	
	
	

}
