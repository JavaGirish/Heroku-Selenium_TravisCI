package com.heroku.qa.datadriven.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.constants.Constants;
import com.heroku.qa.helpers.ExcelHelper;

public class EditComputer extends BaseClass {
	
	
	@DataProvider
	public Object[][] FetchTestData(){
		Object data[][] = ExcelHelper.getExcelData(Constants.EXCEL_PATH, Constants.EDIT_DATA_WORKBOOK);
		return data;
	}
	
	
	@Test(dataProvider="FetchTestData")
	public void editComputer(String compname,String newcompany) {
		landingPage.setFilterName(compname);
		landingPage.clickFilterName();
		addCompPage.selectComputerName(compname);
		addCompPage.setCompanyDropdown(newcompany);
		addCompPage.clickCreateComputer();
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer " + compname + " has been updated");
		
		
	}

}
