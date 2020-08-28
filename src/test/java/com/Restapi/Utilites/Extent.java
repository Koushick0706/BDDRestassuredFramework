package com.Restapi.Utilites;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import gherkin.formatter.JSONFormatter;



public class Extent 
{
//public static ExtentHtmlReporter reporter;
	public static ExtentHtmlReporter reporter;
	public static ExtentSparkReporter reporter1;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest child;
	public static ExtentTest Insidechild;
	public static Properties pro;
		
		
		public static String timeStamp()
		{
		Timestamp time =new Timestamp(System.currentTimeMillis());
		String ts= time.toString().replaceAll(":", "_ ").replace(".", "_");
		System.out.println(ts);
		return ts;
		}
		public static void reportGeneration(String reportpath)
		{
			 System.out.println("loging in to browser");
			 reporter= new ExtentHtmlReporter(reportpath+"ExtentReport.html");
			 //formatter = new JsonFormatter(reportpath+"ExtentReport.html");
			 //reporter.setAppendExisting(true);
			 reporter.loadXMLConfig("Extent-Config.xml");
			 extent = new ExtentReports();
			 extent.attachReporter(reporter);
			 System.out.println("Report Created");
			
		}
		
		
		public static void CreateTest(String testcasename)
		{
			test=extent.createTest(testcasename);
		}
		
		
		public static void testStatus(Statusdata datatype,String data) throws InterruptedException
		{
		switch (datatype) {
		case pass :
			Assert.assertTrue(true);
			Thread.sleep(2000);
			Insidechild.pass(MarkupHelper.createCodeBlock(data));
			break;
		case fail :

			Assert.assertTrue(true);
			Thread.sleep(2000);
			Insidechild.fail(MarkupHelper.createCodeBlock(data));
			break;
		case skip :
			Assert.assertTrue(true);
			Thread.sleep(2000);
			Insidechild.skip(MarkupHelper.createCodeBlock(data));
			break;
			
		case info:
			Assert.assertTrue(true);
			Thread.sleep(2000);
			if(data.startsWith("{"))
			{
			Insidechild.log(Status.INFO, MarkupHelper.createCodeBlock(data, CodeLanguage.JSON));
			}
			else
			{
			Insidechild.log(Status.INFO, MarkupHelper.createCodeBlock(data, CodeLanguage.XML));
			}
			
		}
		}
		void configfile() throws IOException
		{
			//File f=new File();
			FileInputStream sc = new FileInputStream("C:\\Users\\Training\\workspace\\CucumberReportDemo\\configaration\\config.properties");
		    pro=new Properties();
			pro.load(sc);
			//return null;
			
		}
		
		public static String createLable(String text)
		{
			
			 String testResult = text ;
			 test.info(MarkupHelper.createLabel(testResult, ExtentColor.GREEN));
			 return text;   
		}
		
		public static void embededTable(String exp,String data)
		    {
			String key = null;
			String status = null;

		       status="";
		       if(exp.equals(data))
		       {
		       status="Pass";
		       }
		       else
		       {
		       status="Fail";  
		       }
//		                System.out.println(entry.getKey() + " = " + entry.getValue());
//		                stringMapTable.append("<tr><td>" + key + "</td><td>" +data + "</td><td width=\"40%\">" +status + "</td></tr>");
//		 		        stringMapTable.append("</table></body></html>");
		        
		       
		       String[][] table = {
		    		    { "Excepted", "Actual", "status" },
		    		    { exp, data, status }};
		       
		       Markup m = MarkupHelper.createTable(table);

		     // String mapTable = stringMapTable.toString();

		    
		       Insidechild.info(m);
		    }
			
		public static void embededLink(String link)
		    {
		        test.log(Status.INFO, "<a href="+link+">Click Here</a>");
		    }
		public static void codeBlock(String content)
		{
			String code = "\n\t\n\t\t"+content+"\n\t\n";
			Markup m = MarkupHelper.createCodeBlock(code);

			test.pass(m);
			
		}
		
		public static void setSystemInfo( String os, String browser)
		{
			extent.setSystemInfo(os, browser);
		}
		
		public static void attachScreenshot(String screenushotpath) throws IOException
		{
			// log with snapshot
	        //test.fail("fail because of some issue", MediaEntityBuilder.createScreenCaptureFromPath(screenushotpath).build());
	        test.log(Status.INFO, "<a href=\"#s\">back to summary</a>");
		}
		public static void assignAuthorName(String name)
		{
			test.assignAuthor(name);
			
		}
		public static void addCategories(String testing_type)
		{
			
			Insidechild = child.assignCategory(testing_type);
			
		}
		
		public static void Childtest(String name,String testdata)
		{
			child = test.createNode(name);
			Insidechild = child.assignCategory(testdata);
		}
		
		public static void endReport()
		{
			
			extent.flush();
			
		}
}
