package com.heroku.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.Browser;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class AddComputerTest extends BaseClass {
	
	
	@Test(priority=1)
	@Feature("Add a new Computer Name")
	@Description("And I click on the button Add a new Computer")
	public void clickAddComputerButtonTest() {
		addCompPage.clickAddComputer();
	}
	
	@Test(priority=2)
	@Feature("Add a new Computer Name")
	@Description("Then I enter computer name")
	public void enterComputerNameTest() {
		addCompPage.setComputerName(Browser.prop.getProperty("computername"));
	}
	
	@Test(priority=3)
	@Feature("Add a new Computer Name")
	@Description("And I enter introduced date")
	public void enterIntroducedDateTest() {
		addCompPage.setIntroducedDate(Browser.prop.getProperty("adddate"));
	}
	
	@Test(priority=4)
	@Feature("Add a new Computer Name")
	@Description("And I enter discontinued date")
	public void enterDiscontinuedDateTest() {
		addCompPage.setDiscontinuedDate(Browser.prop.getProperty("enddate"));
	}
	
	@Test(priority=5)
	@Feature("Add a new Computer Name")
	@Description("And I select company name from dropdown")
	public void selectCompanyTest() {
		addCompPage.setCompanyDropdown(Browser.prop.getProperty("company"));
	}
	
	@Test(priority=6)
	@Feature("Add a new Computer Name")
	@Description("And I click Create this computer button")
	public void clickCreateComputerTest() {
		addCompPage.clickCreateComputer();
	}
	
	@Test(priority=7)
	@Feature("Add a new Computer Name")
	@Description("Then New Computer Name will be added")
	public void validateSuccessMessageTest() {
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer " + Browser.prop.getProperty("computername") + " has been created");
	}
	

}
