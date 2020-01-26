package com.Restapi.Utilites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.Restapi.DataProvider.DataProvider;
import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.Restapi.Step_def.Rest_Step_def_GET;
import com.codoid.products.exception.FilloException;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class Common_Methods {

	static final Common_Methods obj = new Common_Methods();
	private static final JSONObject JSONObject = null;
	public static HashMap<String, Object> map = new HashMap<String, Object>();
	static String key;
	static Object value;
	static String keyarray;
	static Object valuearray;
	static String keyobj;
	static Object valueobj;
    private static String response_content;
	
	
	private Common_Methods() {
		// TODO Auto-generated constructor stub
	}
	
	public static Common_Methods Get_Instance()
	{
		return obj;
	}

	public void Verify_JSON_Data(String Response,String data) throws InterruptedException
	{
		
		 JSONObject obj = new JSONObject(Response); 
		   Iterator<String> keyset = obj.keys(); 

			while (keyset.hasNext())

			{

				key = (String) keyset.next();

				value = obj.get(key);

				System.out.print("\n Key is : " + key);
                  map.put(key, value);
			       if(data!=null)
			        {
			        String data1 = data.toString();
			        String[] split_data = data1.split("-");
			        for (String Mandatorydata : split_data) {
					System.out.println(Mandatorydata);
					if(key.equals(Mandatorydata))
					{
						
						System.out.println("Data what we Got is = " + Mandatorydata );
						System.out.println("Data Matched with = " + key );
						Extent.embededTable(key, Mandatorydata);
					}
					else
					{
						System.out.println("Data not Matched!");
					}
					
			        }
			        }
			        else
			        {
			        	BaseclassInitilizer.logger.info("No Mandatory Fields Applicable");
			        }
			       
			       if(value instanceof JSONArray)
			       {
			    	   JSONArray array = obj.getJSONArray(key);
			    	   System.out.println(array);
			    	   for(int i=0;i<array.length();i++)
			    	   {
			    		   Object arrayobj = array.get(i);
			    		   if(arrayobj instanceof String)
			    		   {
			    			   System.out.println(arrayobj);
			    		   }
			    		   
			    		   else
			    		   {
			    			   JSONObject jsonarray = array.getJSONObject(i);
			    			   Iterator<String> keysetarr = jsonarray.keys();

			                    while (keysetarr.hasNext())

			                    {

			                        String keyarr =  (String) keysetarr.next();

			                        Object valuearr = jsonarray.get(keyarr);            

			                       String Keyy = keyarr+i;
			                       
			                       map.put(keyarr, valuearr);

			                       System.out.println("The Key is " + Keyy);
			                       System.out.println("The Value is " + valuearr);
			                       
			                       
			                    if(data!=null)
			   			        {
			   			        String data1 = data.toString();
			   			        String[] split_data = data1.split("-");
			   			        for (String Mandatorydata : split_data) {
			   					System.out.println(Mandatorydata);
			   					if(keyarr.equals(Mandatorydata))
			   					{
									System.out.println("Data what we Got is = " + Mandatorydata );
									System.out.println("Data Matched with = " + keyarr );
									Extent.embededTable(keyarr, Mandatorydata);
			   						
			   					}
			   					else
			   					{
			   						System.out.println("Data not Matched!");
			   					}
			   					
			   					
			   			        }
			   			       
			   			        }
			   			        else
			   			        {
			   			        	BaseclassInitilizer.logger.info("No Mandatory Fields Applicable");
			   			        }
			    		   }
			    	   }
			           
			    	   
			    	   
			       }
				
				
			}
			}
			}
			
			
	
	
	
	
	
	
	public void Validate_Status_Code(int status) throws FilloException, IOException, InterruptedException
	{
		 String Exp = String.valueOf(status);
		 if(status==200||status==201)
		   {
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Status", Exp);
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Result", "Passed");
			   Extent.testStatus("info",getResponse_content());
			   
		   }
		   else
		   {
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Status", Exp);
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Result", "Failed");
			   Extent.testStatus("info", getResponse_content());
		   } 
	}
	
	public void Validate_Status_Line(String Status) throws FilloException, IOException, InterruptedException
	{
		   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Status_Line", Status);
		   if(Status.contentEquals("HTTP/1.1 200 OK")||Status.contentEquals("HTTP/1.1 201 Created"))
		   {
			   Extent.testStatus("pass", "The Status is " + Status);
		   }
		   else
		   {
			   Extent.testStatus("fail", "The Status is " + Status);
		   }
	}
	
	public static String timeStamp()
	{
	Timestamp time =new Timestamp(System.currentTimeMillis());
	String ts= time.toString().replaceAll(":", "_ ").replace(".", "_");
	System.out.println(ts);
	return ts;
	}
	
	public void hit_service(String url,String label) throws InterruptedException
	{
		
		Extent.Childtest(label, "Payments");  
		RestAssured.baseURI=BaseclassInitilizer.excelHashMapValues.get(url);
		BaseclassInitilizer.logger.info("Passing URL : " + BaseclassInitilizer.excelHashMapValues.get(url));
		BaseclassInitilizer.httpreq=RestAssured.given().filter(new RequestLoggingFilter(BaseclassInitilizer.requestcapture)).filter(new ResponseLoggingFilter(BaseclassInitilizer.responsecapture));
		Extent.testStatus("pass", "Passing URL : " + BaseclassInitilizer.excelHashMapValues.get(url));
	}
	
	public void GET_request(String uri) throws InterruptedException
	{
	            Extent.testStatus("pass", "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.header("Authorization", "Bearer ibvjqtYKqkC1HlLfBOzp9mvVv_ftD4p6zhu9").get(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
				BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
	}
	
	public void POST_request(String uri) throws InterruptedException
	{
                Extent.testStatus("pass", "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") ); 
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.post(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
				BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
	}
	
	public void PUT_request(String uri) throws InterruptedException
	{
                Extent.testStatus("pass", "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.put(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
				BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
	}
	
	
	public void ReadJSONFile(String arg1)
	{
		String jsonfile = "src/test/resources/JSON Files/"+arg1;
		 JSONParser jsonParser = new JSONParser();
        
	        try 
	        {
	        	FileReader reader = new FileReader(jsonfile);
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            org.json.simple.JSONObject employeeList = (org.json.simple.JSONObject) obj;
	            System.out.println(employeeList);
	            
	    		BaseclassInitilizer.httpreq.headers("Content-Type","application/json");
	    	    BaseclassInitilizer.httpreq.body(employeeList.toJSONString());
	    
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	}
	
	public void ReadXMLFile(String arg) throws ParserConfigurationException, SAXException, IOException
	{
		String xmlfiles = "src/test/resources/XML Files/"+arg;
		try
		{
		StringBuilder sb = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(xmlfiles))){

	        String sCurrentLine;
			while (( sCurrentLine = br.readLine()) != null) {
	            sb.append(sCurrentLine);
	        }

	    }
	    BaseclassInitilizer.httpreq.headers("Content-Type","text/xml");
	    BaseclassInitilizer.httpreq.body( sb.toString());
		}
		catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } 	
	}

	public static String getResponse_content() {
		return response_content;
	}

	public static void setResponse_content(String response_content) {
		Common_Methods.response_content = response_content;
	}
}
