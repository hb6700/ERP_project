package com.project.erpsystem.openapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

	public static String checkNationalHoliday(int month, int date) {
		
		Scanner scan = new Scanner(System.in);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		//int date = Calendar.getInstance().get(Calendar.DATE);
		
		
		
		String today = String.format("%d%02d%02d", year, month, date);

		String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?";
		
		url += "serviceKey=VynLffD0VdeINXmH95ahUp9F08iyfMO2QQGvIZ%2Fm5tYINkhjBE4Ctat8DD5W3FQ553GNGZ3c%2Bv06VXU1tsN8lQ%3D%3D";
		url += "&_type=json";
		url += "&numOfRows=69";
		url += "&pageNo=1";
		url += "&solYear=" + year;
		url += "&Month=" + month;

		try {
			
			URL obj_url = new URL(url);
			
			HttpURLConnection conn = (HttpURLConnection)obj_url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			BufferedReader reader = null;
			
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
			} else {
				
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				
			}
			
			JSONParser parser = new JSONParser();
			
			JSONObject root = (JSONObject)parser.parse(reader);	
			
			JSONObject response = (JSONObject)root.get("response");	
			
			JSONObject body = (JSONObject)response.get("body");
			
			JSONObject items = (JSONObject)body.get("items");
			
			HashMap<String, String> map = new HashMap<String, String>();
			
			JSONArray arr = (JSONArray)items.get("item");
				
			for (Object obj : arr) {
					
				JSONObject item = (JSONObject)obj;
					
				String dateName = item.get("dateName").toString();
				String locdate = item.get("locdate").toString();
					
				if (today.equals(locdate)) {
						
					return dateName;
						
				}
						
			}

			reader.close();
			conn.disconnect();
			
			return "";
			
		} catch (Exception e) {
			
			System.out.println("at Ex76.m1");
			e.printStackTrace();
			
			return "";
			
		}

	}

}
