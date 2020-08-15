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

public class DeleteComputer extends BaseClass{

	/** Data Provider to fetch data from excel sheet 
	 * Excel sheet path and Sheet name mentioned in Constants class.
	 * */	
	@DataProvider
	public Object[][] FetchTestData(){
		Object data[][] = ExcelHelper.getExcelData(Constants.EXCEL_PATH, Constants.DELETE_DATA_WORKBOOK);
		return data;
	}
	
	/** Test to Delete a Computer Name already added in Heroku */
	@Test(dataProvider="FetchTestData")
	@Feature("Feature: Delete an added Computer Name")
	@Description("Delete a computer")
	@Severity(SeverityLevel.NORMAL)
	public void deleteComputer(String compname) {
		landingPage.setFilterName(compname);		//Enter Computer name
		landingPage.clickFilterName();				//Click Filter by name button
		addCompPage.selectComputerName(compname);	//Select Computer Name from search results
		addCompPage.deleteComputer();				//Click Delete this computer button
		
		/** Validating deletion success message text displayed*/
		String successMsg = addCompPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Done! Computer" +  " has been deleted");

		
	}
	
	
}
