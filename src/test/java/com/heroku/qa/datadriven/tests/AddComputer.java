package com.heroku.qa.datadriven.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.Browser;
import com.heroku.qa.helpers.ExcelHelper;



public class AddComputer extends BaseClass {
	
	
	
	@DataProvider
	public Object[][] FetchTestData(){
		Object data[][] = ExcelHelper.getExcelData("src/test/java/com/heroku/qa/testdata/DataSheet.xlsx", "Data");;
		return data;
	}
	
	
	
	@Test(dataProvider="FetchTestData")
	public void addComputerTest(String compname,String adddate,String enddate,String company) {
		addCompPage.clickAddComputer();
		addCompPage.setComputerName(compname);
		addCompPage.setIntroducedDate(adddate);
		addCompPage.setDiscontinuedDate(enddate);
		addCompPage.setCompanyDropdown(company);
		addCompPage.clickCreateComputer();
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer " + compname + " has been created");
	}
	

}
