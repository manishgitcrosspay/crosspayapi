/**
 * 
 */
package com.crosspay.payment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crosspay.payment.model.Customer;
import com.crosspay.payment.model.Login;
import com.crosspay.payment.model.Register;

@RequestMapping("/transaction")
public class TransactionController {

	public static void main(String args[]) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("cardnumber", "4111111111111111");
		json.put("expirymonth", "12");
		json.put("expiryyear", "2030");
		json.put("securitycode", "123");
		json.put("amount", "200");
		json.put("ccycode", "INR");
		try {
			checkthreed(json);
		} catch (JSONException e) {
			System.out.println("error in calling api"+e.toString());
		}
	}
	@RequestMapping(value = "/check3Dquery", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public static String checkthreed(Map<String, Object> json) throws JSONException {//@RequestBody 
		JSONObject resultjson = new JSONObject();
		String cardnumber = null;
		String expirymonth = null;
		String expiryyear = null;
		String securitycode = null;
		String amount = null;
		String ccycode = null;
		String res = null;
		 try {
			
			 cardnumber = json.get("cardnumber").toString();
			 expirymonth = json.get("expirymonth").toString();
			 expiryyear = json.get("expiryyear").toString();
			 securitycode = json.get("securitycode").toString();
			 amount = json.get("amount").toString();
			 ccycode = json.get("ccycode").toString();
			 
			 if(!cardnumber.equalsIgnoreCase("") || !cardnumber.isEmpty() || !(cardnumber == "null")) {
				 if(!expirymonth.equalsIgnoreCase("") || !expirymonth.isEmpty() || !(expirymonth == "null")) {
					 if(!expiryyear.equalsIgnoreCase("") || !expiryyear.isEmpty() || !(expiryyear == "null")) {
						 if(!securitycode.equalsIgnoreCase("") || !securitycode.isEmpty() || !(securitycode == "null")) {
							 if(!amount.equalsIgnoreCase("") || !amount.isEmpty() || !(amount == "null")) {
								 if(!ccycode.equalsIgnoreCase("") || !ccycode.isEmpty() || !(ccycode == "null")) {
									 try {

									        URL url = new URL("https://www.example.com");
									        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
									        conn.setDoOutput(true);
									        conn.setUseCaches(true);
									        conn.setDoInput(true);
									        conn.setRequestMethod("POST");
									       // conn.setContentHandlerFactory(fac);
									        conn.setRequestProperty("accept", "application/json");

									        String input = "{ \"sitereference\":\"test_crosspay68993\",\"locale\":\"en_gb\",\"pan\":\""+cardnumber+"\",\"expirymonth\":\""+expirymonth+"\",\"expiryyear\":\""+expiryyear+"\",\"securitycode\":\""+securitycode+"\"}";
									        System.out.println("input "+input);
									        OutputStream os = conn.getOutputStream();
									        os.write(input.getBytes());
									        os.flush();

									        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
									        	System.out.println("getResponseCode "+conn.getHeaderFields());
									        	Map<String, List<String>> map = conn.getHeaderFields();
									        	for (Map.Entry<String, List<String>> entry : map.entrySet()) {
									        		System.out.println("Key : " + entry.getKey()
									                                   + " ,Value : " + entry.getValue());
									        	}

									        	System.out.println("\nGet Response Header By Key ...\n");
									        	Map<String, String> query_pairs = new LinkedHashMap<String, String>();
									            String query = url.getQuery();
									            String[] pairs = query.split("&");
									            for (String pair : pairs) {
									            	 System.out.println("pairs "+pairs);
									                int idx = pair.indexOf("=");
									                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
									                System.out.println("query "+query_pairs);
									            }
									            throw new RuntimeException("Failed : HTTP error code : "
									                + conn.getResponseCode());
									        }

									        BufferedReader br = new BufferedReader(new InputStreamReader(
									                (conn.getInputStream())));

									        String output;
									        System.out.println("Output from Server .... \n");
									        while ((output = br.readLine()) != null) {
									            System.out.println(output);
									        }
									        res = output;
									        conn.disconnect();

									      } catch (MalformedURLException e) {
									    	  System.out.println("error in malformed"+e.toString());
									        e.printStackTrace();

									      } catch (IOException e) {
									    	  System.out.println("error in IOException"+e.toString());
									        e.printStackTrace();

									     }
									 
									 /*URL url;
									 try {
									     url = new URL("https://webservices.securetrading.net/js/st.js");
									     HttpURLConnection conn = (HttpURLConnection) url.openConnection();

									     // Set Headers
									     conn.setRequestProperty("CustomHeader", "someValue");
									     conn.setRequestProperty("accept", "application/json");

									     // Output is null here <--------
									     System.out.println(conn.getHeaderField("CustomHeader"));

									     // Request not successful
									     if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
									         throw new RuntimeException("Request Failed. HTTP Error Code: " + conn.getResponseCode());
									     }

									     // Read response
									     BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
									     StringBuffer jsonString = new StringBuffer();
									     String line;
									     while ((line = br.readLine()) != null) {
									         jsonString.append(line);
									     }
									     br.close();
									     conn.disconnect();
									 } catch (Exception e) {
									     e.printStackTrace();
									 }*/
									 
									 
								 }else {
									 resultjson.put("code", "406");
									 resultjson.put("message", "ccycode should not be empty");
								 } 
							 }else {
								 resultjson.put("code", "406");
								 resultjson.put("message", "amount should not be empty");
							 }
						 }else {
							 resultjson.put("code", "406");
							 resultjson.put("message", "securitycode should not be empty");
						 } 
					 }else {
						 resultjson.put("code", "406");
						 resultjson.put("message", "expiryyear should not be empty");
					 }
				 }else {
					 resultjson.put("code", "406");
					 resultjson.put("message", "expirymonth should not be empty");
				 }
				 
			 }else {
				 resultjson.put("code", "406");
				 resultjson.put("message", "CardNumber should not be empty");
			 }
		 }catch(Exception e) {
			 resultjson.put("code", "406");
			 resultjson.put("message", "Some input fields missing");
		 }
		
		return res;
	}
}
