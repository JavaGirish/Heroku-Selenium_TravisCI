package com.heroku.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.Browser;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class EditComputerTest extends BaseClass {

	
	@Test(priority=1)
	@Feature("Edit an Computer Name")
	@Description("Enter Added Computer Name and click on filter name button")
	public void enterFilterName() {
		landingPage.setFilterName(Browser.prop.getProperty("computername"));
		landingPage.clickFilterName();
		
	}
	
	
	@Test(priority=2)
	@Feature("Edit an Computer Name")
	@Description("I select the added Company Name")
	public void selectComputerName() {
		addCompPage.selectComputerName(Browser.prop.getProperty("computername"));
	}
	
	
	@Test(priority=3)
	@Feature("Edit an Computer Name")
	@Description("I update the company value")
	public void selectCompanyTest() {
		addCompPage.setCompanyDropdown(Browser.prop.getProperty("newcompany"));
	}
	
	@Test(priority=4)
	@Feature("Edit an Computer Name")
	@Description("I click on Save this computer button")
	public void clickCreateComputerTest() {
		addCompPage.clickCreateComputer();
	}
	
	@Test(priority=5)
	@Feature("Edit an Computer Name")
	@Description("Computer name details has been updated successfully message will be displayed")
	public void validateSuccessMessageTest() {
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer " + Browser.prop.getProperty("computername") + " has been updated");
	}
	
}
