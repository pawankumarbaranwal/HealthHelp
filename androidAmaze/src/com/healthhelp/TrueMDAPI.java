package com.healthhelp;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TrueMDAPI {

	static String url;
	static JSONArray medicineSuggestions = null;
	static JSONArray medicineDetails = null;
	static JSONArray medicineAlternatives = null;
//	/public static String responeValue = "";
	protected static String responseValue = "";

	public static ArrayList<String> getMedicineSuggestions(String find,String key) throws MalformedURLException
	{
		ArrayList<String> listOfSuggestions= new ArrayList<String> ();
		 try {
	        	
				find= URLEncoder.encode(find, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        url = " http://www.truemd.in/api/medicine_suggestions/"+find+"&key="+key+"&limit=100";
		       
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String success = sh.makeServiceCall(url);
		while(!responseValue.isEmpty()){
			break;
		}
	
		Log.d("Response: ", "> " + responseValue);
		
		if (responseValue != null) {
			try {
				JSONObject jsonObj = new JSONObject(responseValue);
				
				// Getting JSON Array node
				medicineSuggestions = jsonObj.getJSONArray("suggestions");

				// looping through All Contacts
				for (int i = 0; i < medicineSuggestions.length(); i++) {
					JSONObject c = medicineSuggestions.getJSONObject(i);
					
					
					String medicineName = c.getString("suggestion");
					listOfSuggestions.add(medicineName);
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any medicine suggestions from the url");
		}
		
		return listOfSuggestions;
	}

	public static Medicine getMedicineDetails(String find, String key)
			throws MalformedURLException {
		Medicine medicineDetailsObject = new Medicine();
		try {

			find = URLEncoder.encode(find, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = "http://oaayush-aayush.rhcloud.com/old_api/medicine.json?id="
				+ find + "&key=" + key + "&limit=100";

		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = "";// sh.makeServiceCall(url, ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);

		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				// Getting JSON Array node
				medicineDetails = jsonObj.getJSONArray("medicine");

				// looping through All Contacts
				for (int i = 0; i < medicineDetails.length(); i++) {
					JSONObject c = medicineDetails.getJSONObject(i);

					// String medicineName = c.getString("suggestion");

					medicineDetailsObject.setManufacturer(c
							.getString("manufacturer"));
					medicineDetailsObject.setBrand(c.getString("brand"));
					medicineDetailsObject.setCategory(c.getString("category"));
					medicineDetailsObject.setDClass(c.getString("d_class"));
					medicineDetailsObject.setUnitQty(c.getString("unit_qty"));
					medicineDetailsObject.setUnitType(c.getString("unit_type"));
					medicineDetailsObject.setPackageQty(c
							.getString("package_qty"));
					medicineDetailsObject.setPackageType(c
							.getString("package_type"));
					medicineDetailsObject.setPackagePrice(c
							.getString("package_price"));
					medicineDetailsObject.setUnitPrice(c
							.getString("unit_price"));
					medicineDetailsObject.setGenericId(c
							.getString("generic_id"));

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler",
					"Couldn't get any medicine suggestions from the url");
		}

		return medicineDetailsObject;
	}

	public static ArrayList<Medicine> getMedicineAlternatives(String find,
			String key) throws MalformedURLException {
		ArrayList<Medicine> medicineAlternativesList = new ArrayList<Medicine>();
		try {

			find = URLEncoder.encode(find, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ServiceHandler sh = new ServiceHandler();
		String urlStr = "http://truemd.in/api/medicine_alternatives?id=" + find
				+ "&key=" + key + "&limit=10";
		/*URL url = new URL(urlStr);
		Log.d("URL alter:", url.toString());
		URI uri = null;
		try {
			uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
					url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		//url = urlStr.toURL();
		// Making a request to url and getting response
		String success = sh.makeServiceCall(urlStr);
		while(responseValue.isEmpty()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			continue;
		}
		String jsonStr = "";
		Log.d("Response: ", "> " + jsonStr);

		if (responseValue != null) {
			try {
				JSONObject jsonObj = new JSONObject(responseValue);

				// Getting JSON Array node
				medicineAlternatives = jsonObj.getJSONArray("drugs");

				// looping through All Contacts
				for (int i = 0; i < medicineAlternatives.length(); i++) {
					JSONObject c = medicineAlternatives.getJSONObject(i);

					// String medicineName = c.getString("suggestion");

					Medicine medicineDetailsObject = new Medicine();
					medicineDetailsObject.setManufacturer(c
							.getString("manufacturer"));
					medicineDetailsObject.setBrand(c.getString("brand"));
					medicineDetailsObject.setCategory(c.getString("category"));
					medicineDetailsObject.setDClass(c.getString("d_class"));
					medicineDetailsObject.setUnitQty(c.getString("unit_qty"));
					medicineDetailsObject.setUnitType(c.getString("unit_type"));
					medicineDetailsObject.setPackageQty(c
							.getString("package_qty"));
					medicineDetailsObject.setPackageType(c
							.getString("package_type"));
					medicineDetailsObject.setPackagePrice(c
							.getString("package_price"));
					medicineDetailsObject.setUnitPrice(c
							.getString("unit_price"));
					medicineDetailsObject.setGenericId(c
							.getString("generic_id"));

					medicineAlternativesList.add(medicineDetailsObject);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler",
					"Couldn't get any medicine suggestions from the url");
		}
		return medicineAlternativesList;
	}

}
