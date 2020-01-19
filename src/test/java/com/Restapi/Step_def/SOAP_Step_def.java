package com.Restapi.Step_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;

public class SOAP_Step_def {
	
	
	@Given("^i Execute the SOAP service$")
	public void i_Execute_the_SOAP_service() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//http://www.dneonline.com/calculator.asmx
		RestAssured.baseURI = "http://www.dneonline.com";
	}

	@Then("^i Submit my SOAP request for \"([^\"]*)\"$")
	public void i_Submit_my_SOAP_request_for(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		
		
		
	}

	@Then("^i check my SOAP status$")
	public void i_check_my_SOAP_status() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}

}
