package com.heroku.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.BrowserManager;

import io.qameta.allure.Step;

public class LandingPage extends BaseClass {
	
	
	@FindBy(id= "add")
	WebElement addComputerBtn;
	
	
	@FindBy(css="h1.fill a")
	WebElement pageHeader;
	
	@FindBy(id="searchbox")
	WebElement filterSearchBox;
	
	@FindBy(css="#searchsubmit")
	WebElement filterByNameBtn;
	
	
	public LandingPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	
	public String getHeader() {
		return getPageHeader(pageHeader);
	}
	
	
	public String getTitle() {
		return getPageTitle();
	}
	
	@Step("User enters added computer name: {0}")
	public void setFilterName(String value) {
		enterText(filterSearchBox, value);
	}
	
	@Step("User clicks on FIlter by name button")
	public void clickFilterName() {
		clickOnElement(filterByNameBtn);
	}
	
	
}
