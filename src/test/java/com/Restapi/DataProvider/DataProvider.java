package com.Restapi.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;


public class DataProvider {
	
	private static final DataProvider obj = new DataProvider();
	
	private DataProvider() {
		
	}

	public static DataProvider GetInstance()
	{
		return obj;
	}
	
	Fillo fillo;
	Connection connection;

	// Write the value into Excel

	public void writeExcelData(String testCaseID, String sheetName, String Columnname, String columnvalue)
			throws FilloException, IOException {
		fillo = new Fillo();
		connection = fillo.getConnection("src/test/resources/Datatable/TestData.xlsx");
		String strQuery = "Update " + sheetName + " Set " + Columnname + "='" + columnvalue + "' where ServiceName='"+ testCaseID + "' ";
		connection.executeUpdate(strQuery);
		connection.close();

	}

	// Read the values form Excel

	public HashMap<String, String> extractExcelData(String testCaseID, String sheetName)
			throws FilloException, IOException {
		HashMap<String, String> excelHashMapValues = new HashMap<String, String>();
		fillo = new Fillo();
		connection = fillo.getConnection("src/test/resources/Datatable/TestData.xlsx");
		String strQuery =BaseclassInitilizer.prop.getProperty("Query")  + sheetName + " " + BaseclassInitilizer.prop.getProperty("Query1") + " ='" + testCaseID + "'"; 
		Recordset recordset = connection.executeQuery(strQuery);
		while (recordset.next()) {
			ArrayList<String> ColCollection = recordset.getFieldNames();
			int Iter;
			int size = ColCollection.size();
			for (Iter = 0; Iter <= (size - 1); Iter++) {
				String ColName = ColCollection.get(Iter);
				String ColValue = recordset.getField(ColName);
				excelHashMapValues.put(ColName, ColValue);
			}
		}

		recordset.close();
		connection.close();
		return excelHashMapValues;
	}

	

}
