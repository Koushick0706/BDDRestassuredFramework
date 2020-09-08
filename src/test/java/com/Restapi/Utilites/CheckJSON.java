package com.Restapi.Utilites;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.Restapi.InitilizeBaseConfiguration.BaseclassInitilizer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

public class CheckJSON {


    static final CheckJSON obj = new CheckJSON();
    static final Map<String,String> PUTJSON_HASH_MAP = new HashMap<String,String>();

    private CheckJSON() {
		// TODO Auto-generated constructor stub
	}
	
	public static CheckJSON Get_Instance()
	{
		return obj;
    }
    

    public Object getJSONArray(String Res,String data)
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
            if(data!=null)
            {
                String data1 = data.toString();
                String[] split_data = data1.split("-");
                for (String Mandatorydata : split_data) {
                System.out.println(Mandatorydata);
                if(mykey.equals(Mandatorydata))
                {
                    
                    System.out.println("Data what we Got is = " + Mandatorydata );
                    System.out.println("Data Matched with = " + mykey );
                    Extent.embededTable(mykey, Mandatorydata);
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
		return myvalue;
	}

	public Object getJSONObject(String Res,String data)
	{
		JSONObject myjsonobject = new JSONObject(Res);
		Object myvalue = null;
		Iterator<String> mykeyssetdata = myjsonobject.keys();
		while(mykeyssetdata.hasNext())
		{
	   String mykey = mykeyssetdata.next();
	   myvalue = myjsonobject.get(mykey);
       System.out.println("The Key is : " + mykey + " and the value is " + myvalue);
       if(data!=null)
            {
                String data1 = data.toString();
                String[] split_data = data1.split("-");
                for (String Mandatorydata : split_data) {
                System.out.println(Mandatorydata);
                if(mykey.equals(Mandatorydata))
                {
                    
                    System.out.println("Data what we Got is = " + Mandatorydata );
                    System.out.println("Data Matched with = " + mykey );
                    Extent.embededTable(mykey, Mandatorydata);
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
		return myvalue;
	}

    public void VerifyJSONobjandArray(String response,String data)
    {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        
        for(Map.Entry getobj : jsonObject.entrySet())
        {
            String key = getobj.getKey().toString();
            String value = getobj.getValue().toString();

            PUTJSON_HASH_MAP.put(key, value);

            Checkmandatorydata(data, key);

            Object  Checkvalue = getobj.getValue();

            if(Checkvalue == null)
            {
                break;
            }
            else
            {

            if(Checkvalue instanceof JsonArray)
            {
                JsonArray jsonObjectarray = JsonParser.parseString(Checkvalue.toString()).getAsJsonArray();

                int size = jsonObjectarray.size();

                for(int i=0;i<size;i++)
                {
                     JsonObject myinnerjson = jsonObjectarray.get(i).getAsJsonObject();

                     for(Map.Entry getobjarr : myinnerjson.entrySet())
                     {
                        String keyarr = getobjarr.getKey().toString();
                        String valuearr = getobjarr.getValue().toString();
            
                        PUTJSON_HASH_MAP.put(keyarr, valuearr);

                        Checkmandatorydata(data, key);
                     }
                }

                
            }
            }
        }
    }

    public void Checkmandatorydata(String Mandatorydata,String key)
    {
        if(Mandatorydata!=null)
            {
                String data1 = Mandatorydata.toString();
                String[] split_data = data1.split("-");
                for (String Mandatory : split_data) {
                System.out.println(Mandatory);
                if(key.equals(Mandatory))
                {
                    
                    System.out.println("Data what we Got is = " + Mandatory );
                    System.out.println("Data Matched with = " + key );
                    Extent.embededTable(key, Mandatory);
                }
                else
                {
                    System.out.println("Data not Matched!");
                }
                
                }
           }
    }
}
