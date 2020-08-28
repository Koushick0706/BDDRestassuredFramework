package com.Restapi.InitilizeBaseConfiguration;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.Restapi.DataProvider.DataProvider;
import com.Restapi.Utilites.Common_Methods;
import com.codoid.products.exception.FilloException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;


public class BaseclassInitilizer {
	
	private final static BaseclassInitilizer obj = new BaseclassInitilizer();
	
	private BaseclassInitilizer() {
		// TODO Auto-generated constructor stub
	}
	
	public static BaseclassInitilizer getInstance()
	{
		return obj;
	}
	public static Logger logger;
	public static Properties prop;
	public static RequestSpecification httpreq;
	public static RequestSpecification httplog;
	public static Response response;
	public static ValidatableResponse response1;
	public static StringWriter requestwriter;
	public static PrintStream requestcapture;
	
	public static StringWriter responsewriter;
	public static PrintStream responsecapture;
	public static HashMap<String, String> excelHashMapValues = new HashMap<String, String>();
	
	
    public void beforeScenario(){
        System.out.println("Start the API URI and EndPoint");
        logger=Logger.getLogger("API Testing");
        PropertyConfigurator.configure("PropertyList/log4j.properties");
		logger.setLevel(Level.DEBUG);
    } 
 
    @SuppressWarnings("deprecation")
	public void beforeScenarioStart(String testCaseID, String sheetName) throws IOException, FilloException{
          logger.info("-----------------Start of Scenario-----------------");
          prop = new Properties();
 		 BufferedReader reader = new BufferedReader(new
 		 FileReader("PropertyList/config.properties"));
 		 prop.load(reader);
 		 BufferedReader reader_1 = new BufferedReader(new FileReader(prop.getProperty("roles")));
 		 prop.load(reader_1);
 		 BufferedReader reader_2 = new BufferedReader(new FileReader(prop.getProperty("DB_Data")));
 		 prop.load(reader_2);
 		 BufferedReader reader_3 = new BufferedReader(new FileReader(prop.getProperty("Directory")));
 		 prop.load(reader_3);
 		 System.out.println(prop);
 		excelHashMapValues = DataProvider.GetInstance().extractExcelData(testCaseID, sheetName);
 		
 		
 		requestwriter = new StringWriter();
		requestcapture = new PrintStream(new WriterOutputStream(requestwriter),true);
 		
		responsewriter = new StringWriter();
		responsecapture = new PrintStream(new WriterOutputStream(responsewriter),true);
          
    } 
 
    public void i_validate_the_size_of_the_logs_folder(String filedata) throws Throwable {
   	    // Write code here that turns the phrase above into concrete actions
   		
   		File file = new File("src/test/resources/API Logs/"+filedata);
		long size = 0;
		size = Common_Methods.getFileFolderSize(file);
 
		double sizeMB = (double) size / 1024 / 1024;
		String s = " MB";
		if (sizeMB < 1) {
			sizeMB = (double) size / 1024;
			s = " KB";
		}
		System.out.println(file.getName() + " : " + sizeMB + s);
		
		if(sizeMB>25.00)
		{
			System.out.println("The Folder size is bigger , please move the files to another backup folder");
		}
		else
		{
			System.out.println("The Size is " + sizeMB + " and it is not bigger , you can contunie in this folder!");
		}
   	   
   	}
  
    public void afterScenarioFinish(){
        logger.info("-----------------End of Scenario-----------------");

    } 

    public void afterScenario(){
        System.out.println("Stored the Response in DataSheet");
    } 
 

}
