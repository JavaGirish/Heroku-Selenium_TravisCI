package com.heroku.qa.datadriven.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.browser.Browser;
import com.heroku.qa.helpers.ExcelHelper;

public class DeleteComputer extends BaseClass{

	
	@DataProvider
	public Object[][] FetchTestData(){
		Object data[][] = ExcelHelper.getExcelData("src/test/java/com/heroku/qa/testdata/DataSheet.xlsx", "Delete");
		return data;
	}
	
	
	@Test(dataProvider="FetchTestData")
	public void deleteComputer(String compname) {
		landingPage.setFilterName(compname);
		landingPage.clickFilterName();
		addCompPage.selectComputerName(compname);
		addCompPage.deleteComputer();
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer" +  " has been deleted");

		
	}
	
	
}
