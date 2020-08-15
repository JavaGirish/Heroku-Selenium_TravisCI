package com.heroku.qa.helpers;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.constants.Constants;

public class WaitHelper {
	
	protected static WebDriverWait wait;
	private final static Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	
	public static void waitForElementToBeVisible(WebElement element) {
		
		WebDriverWait wait = getWait(Constants.timeOutInSeconds, Constants.pollingEveryInMillisec);
		try {
			log.info("Waiting for element to be visible on page: "+ element.toString());
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Found Element on page: " + element.toString());
		}
			
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Unable to locate element on page: " + element.toString() );
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
