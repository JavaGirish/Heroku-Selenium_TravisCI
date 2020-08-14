package com.heroku.qa.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public Object[][] getExcelData(String excellocation, String sheetName) {
		try {

			Object dataSets[][] = null;

			FileInputStream File = new FileInputStream(new File(excellocation));
			XSSFWorkbook workbook = new XSSFWorkbook(File);

			// Get Sheet Name
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();

			dataSets = new Object[totalRows][totalColumns];

			// Iterate through each rows
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;

			while (rowIterator.hasNext()) {
				i++;
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				
				while (cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().contains("Computer")) {
						i = 0;
						break;
					}
					

					switch (cell.getCellType()) {

					case STRING:
						dataSets[i-1][j++] = cell.getStringCellValue();
						break;

					case NUMERIC:
						dataSets[i-1][j++] = cell.getNumericCellValue();
						break;

					case BOOLEAN:
						dataSets[i-1][j++] = cell.getBooleanCellValue();
						break;

					case FORMULA:
						dataSets[i-1][j++] = cell.getCellFormula();
					default:
						break;

					}
				}
			}

			return dataSets;
		}
		
		

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	public static void main(String[] args) {
		ExcelHelper eh = new ExcelHelper();
		
		Object[][] data = eh.getExcelData("src/test/java/com/heroku/qa/testdata/DataSheet.xlsx", "Data");
		System.out.println(data);
	}
	
	
	
	
	
	

}
