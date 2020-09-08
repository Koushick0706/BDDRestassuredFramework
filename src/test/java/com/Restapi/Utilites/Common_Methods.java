package com.Restapi.Utilites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import com.Restapi.DataProvider.DataProvider;
import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.Restapi.Step_def.Rest_Step_def_GET;
import com.codoid.products.exception.FilloException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

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
		Object resjson = new JSONTokener(Response).nextValue();
		if(resjson instanceof JSONObject)
		{
			    value = CheckJSON.Get_Instance().getJSONObject(Response, data);
			        	
			       if(value instanceof JSONArray)
			    {
					Object arrayvalue = CheckJSON.Get_Instance().getJSONArray(value.toString(), data);  

					if(arrayvalue instanceof JSONObject)
					{
						CheckJSON.Get_Instance().getJSONObject(arrayvalue.toString(), data);
					}
				}		
		}
		
		else if(resjson instanceof JSONArray)
		{
	        Object arrayvalue = CheckJSON.Get_Instance().getJSONArray(resjson.toString(), data);  

					if(arrayvalue instanceof JSONObject)
					{
						Object myjsonobjdata = CheckJSON.Get_Instance().getJSONObject(arrayvalue.toString(), data);
						
						if(myjsonobjdata instanceof JSONArray)
			           {
					       CheckJSON.Get_Instance().getJSONArray(myjsonobjdata.toString(), data);  
				        }

					}
		}
		else
		{
			String Key = Response;
			if(data!=null)
			{
				String data1 = data.toString();
				String[] split_data = data1.split("-");
				for (String Mandatorydata : split_data) {
				System.out.println(Mandatorydata);
				if(Key.equals(Mandatorydata))
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
		}
	}
			
			

	public void Validate_Status_Code(int status) throws FilloException, IOException, InterruptedException
	{
		 String Exp = String.valueOf(status);
		 if(status==200||status==201)
		   {
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Status", Exp);
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Result", "Passed");
			   Extent.testStatus(Statusdata.info,getResponse_content());
			   
		   }
		   else
		   {
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Status", Exp);
			   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Result", "Failed");
			   Extent.testStatus(Statusdata.info, getResponse_content());
		   } 
	}
	
	public void Validate_Status_Line(String Status) throws FilloException, IOException, InterruptedException
	{
		   DataProvider.GetInstance().writeExcelData(Rest_Step_def_GET.testcase, Rest_Step_def_GET.sheetname, "Status_Line", Status);
		   if(Status.contentEquals("HTTP/1.1 200 OK")||Status.contentEquals("HTTP/1.1 201 Created"))
		   {
			   Extent.testStatus(Statusdata.pass, "The Status is " + Status);
		   }
		   else
		   {
			   Extent.testStatus(Statusdata.fail, "The Status is " + Status);
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
		
		Extent.Childtest(label, label);  
		RestAssured.baseURI=BaseclassInitilizer.excelHashMapValues.get(url);
		BaseclassInitilizer.logger.info("Passing URL : " + BaseclassInitilizer.excelHashMapValues.get(url));
		BaseclassInitilizer.httpreq=RestAssured.given().filter(new RequestLoggingFilter(BaseclassInitilizer.requestcapture)).filter(new ResponseLoggingFilter(BaseclassInitilizer.responsecapture));
		Extent.testStatus(Statusdata.pass, "Passing URL : " + BaseclassInitilizer.excelHashMapValues.get(url));
		
	}
	
	public void GET_request() throws InterruptedException
	{
		String isTemplaterequried = BaseclassInitilizer.excelHashMapValues.get("Template-Requried");
		if(isTemplaterequried.equals("Yes"))
		{
				Extent.testStatus(Statusdata.pass, "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				String URI = BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI");
				String concatdata = BaseclassInitilizer.prop.getProperty("isbn3");
				String finalURI = URI.concat(concatdata);
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.header("", "").get(finalURI);
				BaseclassInitilizer.logger.info("Passing URI : " + finalURI );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
		}
		else
		{
	            Extent.testStatus(Statusdata.pass, "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.header("Authorization", "Bearer ibvjqtYKqkC1HlLfBOzp9mvVv_ftD4p6zhu9").get(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
				BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
		}
	}

	public void DELETE_request() throws InterruptedException
	{
		String isTemplaterequried = BaseclassInitilizer.excelHashMapValues.get("Template-Requried");
		if(isTemplaterequried.equals("Yes"))
		{
				Extent.testStatus(Statusdata.pass, "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				String URI = BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI");
				String concatdata = BaseclassInitilizer.prop.getProperty("userID");
				String finalURI = URI.concat(concatdata);
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.header("", "").delete(finalURI);
				BaseclassInitilizer.logger.info("Passing URI : " + finalURI );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
		}
		else
		{
	            Extent.testStatus(Statusdata.pass, "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.header("Authorization", "Bearer ibvjqtYKqkC1HlLfBOzp9mvVv_ftD4p6zhu9").delete(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
				BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
		}
	}
	
	public void POST_request(String uri) throws InterruptedException
	{
                Extent.testStatus(Statusdata.pass, "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") ); 
				BaseclassInitilizer.response =BaseclassInitilizer.httpreq.post(BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI"));
				BaseclassInitilizer.logger.info("Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
				setResponse_content(BaseclassInitilizer.response.getBody().asString());
				BaseclassInitilizer.logger.info("Getting Response Content " + getResponse_content());
	}
	
	public void PUT_request(String uri) throws InterruptedException
	{
                Extent.testStatus(Statusdata.pass, "Passing URI : " + BaseclassInitilizer.excelHashMapValues.get("serviceBaseURI") );
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
		File fil = new File(xmlfiles);
        BufferedReader reaso = new BufferedReader(new FileReader(fil));
		StringBuilder sb = new StringBuilder();
	        String sCurrentLine;
			while (( sCurrentLine = reaso.readLine()) != null) {
	            sb.append(sCurrentLine);
		    }
		
		BaseclassInitilizer.httpreq.headers("Content-Type","text/xml");
		BaseclassInitilizer.httpreq.body(sb.toString());
		reaso.close();
	    }
		catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } 	
	}
	
	public static long getFileFolderSize(File dir) {
		long size = 0;
		if (dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				if (file.isFile()) {
					size += file.length();
				} else
					size += getFileFolderSize(file);
			}
		} else if (dir.isFile()) {
			size += dir.length();
		}
		return size;
	}

	public void ReadResponseStoretoProperty(String Response,String responsecode) throws IOException
	{
		//  ObjectMapper res = new ObjectMapper();
		//  res.readTree(Response);
		Properties myproperty = new Properties();
		OutputStream  write = new FileOutputStream(new File("PropertyList/Response.properties"),true);
	try
	{	
		if(responsecode.equals("200")|| responsecode.equals("201"))
		{
			// JSONObject testobject = new JSONObject(Response);
			// Object res = testobject;
			Object json = new JSONTokener(Response).nextValue();
		if(json instanceof JSONObject)
		{
			 System.out.println("Entering JSON object..");
			 System.out.println(json);
			 
		  Object getthevalue = getJSONObject(Response, myproperty);
		  if(getthevalue instanceof JSONArray)
		  {
			  Object myarrayvalue = getJSONArray(getthevalue.toString(), myproperty);
			  System.out.println(myarrayvalue);
		  }
			 
		}
		else if(json instanceof JSONArray)
		{
			System.out.println("Entering JSON Array..");
			System.out.println(json);
			Object jsonaary = getJSONArray(Response, myproperty);
			if(jsonaary instanceof JSONObject)
			{
				Object myinnerjsonobjforarray = getJSONObject(jsonaary.toString(), myproperty);
				System.out.println(myinnerjsonobjforarray);
			}
		}
		else if(Response instanceof String)
		{
			System.out.println("Entering String object..");
			System.out.println(json);
			 myproperty.setProperty("Stringvalue",json.toString());
		}
	    }
	else
	{
		System.out.println("Can't Store in Property, Failure Response..!");
	}
    }
catch(Exception e)
{
	e.getLocalizedMessage();
	e.getCause();
	e.getStackTrace();
}
finally
{
	myproperty.store(write, "MyJSON");
   // myproperty.clear();
}
	}

	public Object getJSONArray(String Res,Properties prop)
	{
		JSONArray Myjsonarray = new JSONArray(Res);
			int mylen = Myjsonarray.length();
			Object myvalue = null;
			for(int i=0;i<mylen;i++)
			{
			 JSONObject nnewaarobj = Myjsonarray.getJSONObject(i);
			 Iterator<String> mykeyssetdata = nnewaarobj.keys();
			 while(mykeyssetdata.hasNext())
			 {
			String mykey = mykeyssetdata.next();
			myvalue = nnewaarobj.get(mykey);
			System.out.println("The Key is : " + mykey + " and the value is " + myvalue);
			prop.setProperty(mykey+i, myvalue.toString());
			 }
			}
		return myvalue;
	}

	public Object getJSONObject(String Res,Properties prop)
	{
		JSONObject myjsonobject = new JSONObject(Res);
		Object myvalue = null;
		Iterator<String> mykeyssetdata = myjsonobject.keys();
		int count =0;
		while(mykeyssetdata.hasNext())
		{
	   String mykey = mykeyssetdata.next();
	   myvalue = myjsonobject.get(mykey);
	   System.out.println("The Key is : " + mykey + " and the value is " + myvalue);
	   prop.setProperty(mykey+count, myvalue.toString());
	   count++;
		}
		return myvalue;
	}


	public void RunExe(String ccommand) throws IOException
	{
		try
      {
            Process process = Runtime.getRuntime().exec(ccommand);
	  } 
	  catch (IOException e)
      {
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
