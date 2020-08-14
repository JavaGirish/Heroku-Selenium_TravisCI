package com.heroku.qa.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.constants.Constants;

public class WaitHelper {
	
	protected static WebDriverWait wait;
	
	
	
	public static void waitForElementToBeVisible(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),Constants.IMPLICIT_WAIT);
		try {wait.until(ExpectedConditions.visibilityOf(element));}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

}
