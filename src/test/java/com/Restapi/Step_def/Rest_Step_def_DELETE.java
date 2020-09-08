package com.Restapi.Step_def;

import com.Restapi.Utilites.Common_Methods;

import cucumber.api.java.en.When;

public class Rest_Step_def_DELETE {

    @When("^I submit the DELETE request as per the data in Excel Worksheet$")
     public void i_submit_the_DELETE_request_as_per_the_data_in_Excel_Worksheet() throws Throwable 
     {
        Common_Methods.Get_Instance().DELETE_request();
     }
    
}
