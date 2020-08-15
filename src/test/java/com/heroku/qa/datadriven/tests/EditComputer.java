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

public class EditComputer extends BaseClass {
	
	/** Data Provider to fetch data from excel sheet 
	 * Excel sheet path and Sheet name mentioned in Constants class.
	 * */	
	@DataProvider
	public Object[][] FetchTestData(){
		Object data[][] = ExcelHelper.getExcelData(Constants.EXCEL_PATH, Constants.EDIT_DATA_WORKBOOK);
		return data;
	}
	
	
	/** Test to Edit a Computer Name already present in Heroku */
	@Test(dataProvider="FetchTestData")
	@Feature("Feature: Edit an added Computer Name")
	@Description("Edit a Computer name")
	@Severity(SeverityLevel.NORMAL)
	public void editComputer(String compname,String newcompany) {
		landingPage.setFilterName(compname);			//Enter computer name
		landingPage.clickFilterName();					//Click on filter by name button
		addCompPage.selectComputerName(compname);		//Select computer name from results displayed
		addCompPage.setCompanyDropdown(newcompany);		//Update company drop down value
		addCompPage.clickCreateComputer();				//Click Save details
		
		/** Validating updated success message text displayed*/
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer " + compname + " has been updated");
		
		
	}

}
