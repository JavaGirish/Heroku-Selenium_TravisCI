package com.heroku.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.helpers.WaitHelper;

import io.qameta.allure.Step;

public class AddComputerPage extends BaseClass {

	public AddComputerPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	@FindBy(id = "add")
	WebElement addComputerButton;

	@FindBy(css = "#main h1")
	WebElement addComputerPageHeader;

	@FindBy(id = "name")
	WebElement computerName;

	@FindBy(id = "introduced")
	WebElement introducedDate;

	@FindBy(id = "discontinued")
	WebElement discontinuedDate;

	@FindBy(id = "company")
	WebElement companyDropdown;

	@FindBy(css = "input[type='submit']")
	WebElement saveComputerBtn;

	@FindBy(css = ".alert-message")
	WebElement successAlert;

	@FindBy(css = ".btn.danger")
	WebElement deleteComputerBtn;

	@FindBy(css = "table.zebra-striped tbody a")
	List<WebElement> searchResultsLink;

	@Step("User clicks Add a new Computer button")
	public void clickAddComputer() {
		clickOnElement(addComputerButton);
	}

	@Step("User enters Computer Name: {0}")
	public void setComputerName(String compName) {
		enterText(computerName, compName);
	}

	@Step("User enters Introduced date: {0}")
	public void setIntroducedDate(String startDate) {
		enterText(introducedDate, startDate);
	}

	@Step("User enters Discontinued date: {0}")
	public void setDiscontinuedDate(String endDate) {
		enterText(discontinuedDate, endDate);
	}

	@Step("User selects company name from dropdown: {0}")
	public void setCompanyDropdown(String dropValue) {
		selectValueFromDropBox(companyDropdown, dropValue);
	}

	@Step("User clicks Save computer button")
	public void clickCreateComputer() {
		clickOnElement(saveComputerBtn);
	}

	@Step("Success Message will be displayed")
	public String getSuccessMessage() {
		WaitHelper.waitForElementToBeVisible(successAlert);
		return getPageHeader(successAlert);
	}

	@Step("User selects the added company name: {0}")
	public void selectComputerName(String name) {

		for (WebElement ele : searchResultsLink) {

			if (ele.getText().equalsIgnoreCase(name)) {
				ele.click();
				break;
			}

			else {
				System.out.println("Computer Name does not exist!!");
			}
		}

	}
	
	
	@Step("User clicks on delete this computer button")
	public void deleteComputer() {
		clickOnElement(deleteComputerBtn);
	}

}
