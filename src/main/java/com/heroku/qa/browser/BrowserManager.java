package com.heroku.qa.browser;

import org.openqa.selenium.WebDriver;

public class BrowserManager {
	
public static ThreadLocal<WebDriver> t_driver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {

		return t_driver.get();

	}

	public static void setWebDriver(WebDriver driver) {

		t_driver.set(driver);
	}

}
