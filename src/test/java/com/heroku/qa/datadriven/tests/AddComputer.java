package com.heroku.qa.datadriven.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.constants.Constants;
import com.heroku.qa.helpers.ExcelHelper;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AddComputer extends BaseClass {

	
	/** Data Provider to fetch data from excel sheet
	 * Excel sheet path and Sheet name mentioned in Constants class.
	 * */
	@DataProvider
	public Object[][] FetchTestData() {
		Object data[][] = ExcelHelper.getExcelData(Constants.EXCEL_PATH, Constants.ADD_DATA_WORKBOOK);
		return data;
	}

	
	/** Test to Add a new Computer Name in Heroku */
	@Test(dataProvider = "FetchTestData")
	@Feature("Feature: Add a new Computer Name")
	@Description("Add a new Computer Name")
	@Severity(SeverityLevel.CRITICAL)
	public void addComputerTest(String compname, String adddate, String enddate, String company) {
		addCompPage.clickAddComputer(); 					//Click Create Computer button
		addCompPage.setComputerName(compname);    			//Enter Computer Name
		addCompPage.setIntroducedDate(adddate);  			//Enter introduced date
		addCompPage.setDiscontinuedDate(enddate); 			//Enter discontinued date
		addCompPage.setCompanyDropdown(company);			//Select Company
		addCompPage.clickCreateComputer();					//Click Create this computer button
		
		/** Validating creation success message text displayed*/
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer " + compname + " has been created"); 
	}

}
