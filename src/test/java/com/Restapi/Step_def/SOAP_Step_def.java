package com.Restapi.Step_def;

import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.Restapi.Utilites.Common_Methods;
import com.Restapi.Utilites.Extent;
import com.Restapi.Utilites.Statusdata;
import com.calculatorservice.CalculatorLocator;
import com.calculatorservice.CalculatorSoap;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;

public class SOAP_Step_def {
	
	CalculatorLocator loc;
	CalculatorSoap data;

	@And("^i Submit my SOAP request for \"([^\"]*)\"$")
	public void i_Submit_my_SOAP_request_for(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		Common_Methods.Get_Instance().ReadXMLFile(arg1);
	}


	@And("^i hit the webservice$")
	public void i_hit_the_webservice() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 loc = new CalculatorLocator();
		 data = loc.getCalculatorSoap();
		
	}

	@Then("^i check the response and status of the service$")
	public void i_check_the_response_and_status_of_the_service() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Extent.Childtest("POST", "Webservices");
	    int add = data.add(10, 20);
	    int sub = data.subtract(50, 20);
	    int mul = data.multiply(10, 4);
	    int div = data.divide(20, 5);
	    
	    String adddata = String.valueOf(add);
	    String subdata = String.valueOf(sub);
	    String muldata = String.valueOf(mul);
	    String divdata = String.valueOf(div);
	   // System.out.println("The Result is : " + result);
	    
	   BaseclassInitilizer.logger.info("The add Result is : " + add);
	   BaseclassInitilizer.logger.info("The sub Result is : " + sub);
	   BaseclassInitilizer.logger.info("The mul Result is : " + mul);
	   BaseclassInitilizer.logger.info("The div Result is : " + div);
	   
	   
	   Extent.testStatus(Statusdata.pass, "The add Result is : " + adddata);
	   Extent.testStatus(Statusdata.pass, "The sub Result is : " + subdata);
	   Extent.testStatus(Statusdata.pass, "The mul Result is : " + muldata);
	   Extent.testStatus(Statusdata.pass, "The div Result is : " + divdata);
	   
	   
	} 


}
