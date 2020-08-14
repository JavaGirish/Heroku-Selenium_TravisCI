package com.heroku.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.Browser;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class DeleteComputerTest extends BaseClass {
	
	@Test(priority=1)
	@Feature("Delete an Computer Name")
	@Description("Enter Edited Computer Name and click on filter name button")
	public void enterFilterName() {
		landingPage.setFilterName(Browser.prop.getProperty("computername"));
		landingPage.clickFilterName();
		
	}
	
	
	@Test(priority=2)
	@Feature("Delete an Computer Name")
	@Description("I select the edited Computer Name")
	public void selectComputerName() {
		addCompPage.selectComputerName(Browser.prop.getProperty("computername"));
	}
	
	@Test(priority=3)
	@Feature("Delete an Computer Name")
	@Description("Click on Delete this computer button")
	public void deleteComputer() {
		addCompPage.deleteComputer();
	}
	
	@Test(priority=4)
	@Feature("Edit an Computer Name")
	@Description("Computer has been deleted successfully message will be displayed")
	public void validateSuccessMessageTest() {
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer" +  " has been delete");
	}
	

}
