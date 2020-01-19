package com.Restapi.Step_def;

import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.Restapi.Utilites.Common_Methods;

import cucumber.api.java.en.Then;
import io.restassured.http.Method;

public class Rest_Step_def_PUT {
	

	@Then("^I submit the PUT request$")
	public void i_submit_the_PUT_request() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//passing the URI
//		BaseclassInitilizer.response =BaseclassInitilizer.httpreq.put(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
//		BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
//		Rest_Step_def_GET.response_content = BaseclassInitilizer.response.getBody().asString();
//		BaseclassInitilizer.logger.info("Getting Response Content " + Rest_Step_def_GET.response_content);
		
		Common_Methods.Get_Instance().PUT_request("serviceBaseURI");
	}

}
