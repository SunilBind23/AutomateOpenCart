package com.OpenCart.TestScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
//OpenCartEcomerceProject/OpenCart-TestCases.xlsx
	public Map<String, String> getExcelData(String sheetName, String TestCaseId) {
		// D:\\OpenCartProject\\LoginTestData2.xlsx
		String filePath = "D:\\OpenCartProject\\LoginTestData3.xlsx";
		Workbook workbook = null;
		Map<String, String> mapData = new LinkedHashMap<String, String>();
		InputStream fis;
		try {
			fis = new FileInputStream(filePath);

			if (filePath.contains(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else {
				workbook = new HSSFWorkbook(fis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get the specified sheet
		Sheet sheet = workbook.getSheet(sheetName);

		int rowNumber = 0;
		int rowCount = sheet.getLastRowNum();
		// Assumes data starts in the first row

		// Locate the TestCaseId
		for (int i = 0; i <= rowCount; i++) {
			Row rowObj = sheet.getRow(i);
			if (rowObj == null)
				continue; // Skip empty rows

			Cell cellObj = rowObj.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK); // Assuming TestCaseId is in
																						// column 0
			String cellData = cellObj.toString().trim(); // Handles both string and numeric cells

			if (cellData.equalsIgnoreCase(TestCaseId.trim())) {
				rowNumber = i;
				break;
			}
		}

		// Retrieve the row
		Row rowData = sheet.getRow(rowNumber);
		if (rowData == null) {
			throw new RuntimeException("TestCaseId not found in the sheet: " + TestCaseId);
		}

		// Process row data
		for (int i = 0; i < rowData.getLastCellNum(); i += 2) {
			
			Cell cellKey = rowData.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			Cell cellValueCell = rowData.getCell(i + 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

			if (cellKey != null && cellValueCell != null) {
				String cellName = cellKey.toString().trim();
				//String cellName = cellKey.getStringCellValue().trim();

				String cellValue = cellValueCell.toString().trim();
				//String cellValue = cellValueCell.getStringCellValue().trim();

				mapData.put(cellName, cellValue);
			}
		}
		return mapData;
	}
}
