package com.heroku.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.BrowserManager;
import io.qameta.allure.Step;

/*
 * @author: Girish Nair
 */

public class LandingPage extends BaseClass {

	/** Landing Page: Add a New Computer button */
	@FindBy(id = "add")
	WebElement addComputerBtn;

	@FindBy(css = "h1.fill a")
	WebElement pageHeader;

	/** Landing Page: Filter by computer name input field */
	@FindBy(id = "searchbox")
	WebElement filterSearchBox;

	/** Landing Page: Filter by name button */
	@FindBy(css = "#searchsubmit")
	WebElement filterByNameBtn;

	public LandingPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public String getHeader() {
		return getPageHeader(pageHeader);
	}

	/** Get page Title */
	public String getTitle() {
		return getPageTitle();
	}

	/**
	 * Method to enter computer name in Filter by computer name input field
	 */
	@Step("User enters computer name: {0}")
	public void setFilterName(String value) {
		enterText(filterSearchBox, value);
	}

	/** Method to click on Filter by name button */

	@Step("User clicks on Filter by name button")
	public void clickFilterName() {
		clickOnElement(filterByNameBtn);
	}

}
