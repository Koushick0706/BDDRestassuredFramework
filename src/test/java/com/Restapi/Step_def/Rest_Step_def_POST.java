package com.Restapi.Step_def;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.Restapi.Utilites.Common_Methods;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Method;

public class Rest_Step_def_POST {
	


	@When("^i Post My Json Response file \"([^\"]*)\"$")
	public void i_Post_My_Json_Response_file(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
            Common_Methods.Get_Instance().ReadJSONFile(arg1);
	}

	

@Then("^I submit the POST request$")
public void i_submit_the_POST_request() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	//passing the URI
	Common_Methods.Get_Instance().POST_request("serviceBaseURI");
}
	
	


}
