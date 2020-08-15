package com.heroku.qa.pages;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.BrowserManager;
import com.heroku.qa.helpers.WaitHelper;

import io.qameta.allure.Step;

/**
 * 
 * @author Girish Nair
 *
 */

public class AddComputerPage extends BaseClass {

	public AddComputerPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	
	@FindBy(id = "add")
	WebElement addComputerButton;

	/** Add Computer Page: Header */
	@FindBy(css = "#main h1")
	WebElement addComputerPageHeader;

	/** Add Computer Page: Computer Name text field */
	@FindBy(id = "name")
	WebElement computerName;
	
	/** Add Computer Page: Introduced date text field */
	@FindBy(id = "introduced")
	WebElement introducedDate;

	/** Add Computer Page: Discontinued date text field */
	@FindBy(id = "discontinued")
	WebElement discontinuedDate;

	/** Add Computer Page: Computer drop down */
	@FindBy(id = "company")
	WebElement companyDropdown;

	/** Add Computer Page: Create this Computer button */
	@FindBy(css = "input[type='submit']")
	WebElement saveComputerBtn;
	
	
	/** Add Computer Page: Success alert message */
	@FindBy(css = ".alert-message")
	WebElement successAlert;

	/** Add Computer Page: Delete this computer button */
	@FindBy(css = ".btn.danger")
	WebElement deleteComputerBtn;

	/** Add Computer Page: Computer Name search Results link
	 * This is a list of all web elements which will be displayed 
	   based on searching for a Computer Name entered
	 *  */
	@FindBy(css = "table.zebra-striped tbody a")
	List<WebElement> searchResultsLink;

	
	/** Page Method to click on Add a new computer button */
	@Step("User clicks Add a new Computer button")
	public void clickAddComputer() {
		clickOnElement(addComputerButton);
	}

	/** Page Method to enter text in Computer Name text field*/
	@Step("User enters Computer Name: {0}")
	public void setComputerName(String compName) {
		enterText(computerName, compName);
	}

	/** Page Method to enter text in Introduced Date text field*/
	@Step("User enters Introduced date: {0}")
	public void setIntroducedDate(String startDate) {
		enterText(introducedDate, startDate);
	}

	/** Page Method to enter text in Discontinued Date text field*/
	@Step("User enters Discontinued date: {0}")
	public void setDiscontinuedDate(String endDate) {
		enterText(discontinuedDate, endDate);
	}
	
	
	/** Page Method to select Company from drop down*/
	@Step("User selects company name from dropdown: {0}")
	public void setCompanyDropdown(String dropValue) {
		selectValueFromDropBox(companyDropdown, dropValue);
	}

	/** Page Method to click Create this computer button */
	@Step("User clicks Save computer button")
	public void clickCreateComputer() {
		clickOnElement(saveComputerBtn);
	}

	/** Page Method to return text of success message bar displayed */
	@Step("Success Message will be displayed")
	public String getSuccessMessage() {
		WaitHelper.waitForElementToBeVisible(successAlert);
		return getPageHeader(successAlert);
	}

	/** Page Method to select Computer name displayed after user searches 
	 * for a specific computer name*/
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
	
	/** Page Method to click Delete this computer button */
	@Step("User clicks on delete this computer button")
	public void deleteComputer() {
		clickOnElement(deleteComputerBtn);
	}

}
