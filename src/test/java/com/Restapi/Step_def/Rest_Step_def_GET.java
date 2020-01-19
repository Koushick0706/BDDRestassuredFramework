package com.Restapi.Step_def;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.output.WriterOutputStream;
import org.json.JSONObject;
import org.junit.Assert;

import com.Restapi.DataProvider.DataProvider;
import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.Restapi.Utilites.Common_Methods;
import com.Restapi.Utilites.Extent;
import com.codoid.products.exception.FilloException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;


public class Rest_Step_def_GET {
	public static String Status_Line;

	public static String testcase;
	public static String sheetname;
	private static boolean flag= false;
	
	@Before
	public void createExtentreporttest()
	{
          System.out.println("------------------Starting the Execution-----------------");
	}
	
	@Given("^i create the extent report and start a test$")
	public void i_create_the_extent_report_and_start_a_test() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(flag==false)
		{
		flag= true;
		Extent.reportGeneration("Reports/");
		Extent.CreateTest("RESTAPI");
		Extent.setSystemInfo("Windows10Home", "Chrome");
		System.out.println("Creating Test in Extent reports....");
		}
		else
		{
			System.out.println("Already created!");
		}
	}

      
	@Given("^i start my before scenario block with \"([^\"]*)\"and\"([^\"]*)\"$")
	public void i_start_my_before_scenario_block_with_and(String arg1, String arg2) throws Throwable {
		testcase=arg1;
		sheetname=arg2;
		BaseclassInitilizer.getInstance().beforeScenario();
		BaseclassInitilizer.getInstance().beforeScenarioStart(testcase, sheetname);
		
	}
	
	@Then("^I want to execute service for \"([^\"]*)\" which has \"([^\"]*)\" operation$")
	public void i_want_to_execute_service_for_which_has_operation(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String Resttype= BaseclassInitilizer.excelHashMapValues.get(arg2);
		Common_Methods.Get_Instance().hit_service(arg1, Resttype);
	}

	@When("^I submit the GET request as per the data in Excel Worksheet$")
	public void i_submit_the_GET_request_as_per_the_data_in_Excel_Worksheet() throws Throwable {
		//passing the URI
		Common_Methods.Get_Instance().GET_request("serviceBaseURI");	
	}

	@And("^I validate status code$")
	public void i_validate_status_code() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
	   int status = BaseclassInitilizer.response.getStatusCode();
	   BaseclassInitilizer.logger.info("Getting Status Code : " + status);
	
	  
	   Common_Methods.Get_Instance().Validate_Status_Code(status);
	  
	   
	}

	@Then("^I validate mandatory tag in response from Excel Worksheet$")
	public void i_validate_mandatory_tag_in_response_from_Excel_Worksheet() throws Throwable {
      
	    String data = BaseclassInitilizer.excelHashMapValues.get("Mandatory Field");
	    Common_Methods.Get_Instance().Verify_JSON_Data(Common_Methods.getResponse_content(), data);
	 

	}
    

   	@And("^I Store the Response in the Excel$")
	public void i_Store_the_Response_in_the_Excel() throws Throwable {
   	 	
	   DataProvider.GetInstance().writeExcelData(testcase, sheetname, "Response", Common_Methods.getResponse_content());
	}

   	@And("^I validate Content-type code$")
    public void i_validate_Content_type_code() throws Throwable {

   		String Content_type = BaseclassInitilizer.response.getContentType();
   		BaseclassInitilizer.logger.info("The Content_type is : " + Content_type);
   		String Content = BaseclassInitilizer.excelHashMapValues.get("Content_Type");
   		BaseclassInitilizer.logger.info("Getting Excel Data : " + Content);
   		if(Content.contains(Content_type))
   		{
   			System.out.println(BaseclassInitilizer.excelHashMapValues.get("Content_Type"));
   			System.out.println("Valid");
   		}
   		else
   		{
   			System.out.println("Invalid");
   		}
}
	
   	@And("^I validate StatusLine of the Response$")
   	public void i_validate_StatusLine_of_the_Response() throws Throwable {
   		Status_Line = BaseclassInitilizer.response.getStatusLine();
		BaseclassInitilizer.logger.info("Getting Status Line " + Status_Line);
   	}

   	@Then("^i Store the Status Line in Excel$")
   	public void i_Store_the_Status_Line_in_Excel() throws Throwable {

   		Common_Methods.Get_Instance().Validate_Status_Line(Status_Line);
   	}
   	
   	@SuppressWarnings("deprecation")
	@Then("^i Store all my logs into File$")
   	public void i_Store_all_my_logs_into_File() throws Throwable {
   	    // Write code here that turns the phrase above into concrete actions
      String time = Common_Methods.timeStamp();

     File fil = new File("src/test/resources/API Logs/"+time+"log.txt");
     FileWriter writer = null;
   	    
   	    try
   	    {
   	    	if(fil.createNewFile())
   	    	{
   	    	writer= new FileWriter(fil);
   	    	writer.write("-----------------------Getting Request data--------------------------");
   	    	writer.write("\n");
   	    	writer.write(BaseclassInitilizer.requestwriter.toString());
   	  	    writer.write("-----------------------Getting Response data--------------------------");
   	    	writer.write("\n");
   	  	    writer.write(BaseclassInitilizer.responsewriter.toString());
   	   
   	    	}
   	    	else
   	    	{
   	    		System.out.println("File already Exist!");
   	    	}
   	  	writer.flush();
   	    }
   	    catch (FileNotFoundException e) {
			e.printStackTrace();
		}
   	}

   	
    
   	@Then("^i end my After Scenario block$")
   	public void i_end_my_After_Scenario_block() throws Throwable {
   	    // Write code here that turns the phrase above into concrete actions
		BaseclassInitilizer.getInstance().afterScenario();
		BaseclassInitilizer.getInstance().afterScenarioFinish();
   	}
    
   	@After
    public void closereport()
    {
    	Extent.endReport();
    	System.out.println("--------------------Ending the Execution-----------------");
    }
	
}
