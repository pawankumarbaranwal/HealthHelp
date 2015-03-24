package com.healthhelp;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ServiceHandler {

	//static String response = null;
	public final static int GET = 1;
	public final static int POST = 2;
	
	public ServiceHandler() {

	}

	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String urlStr) throws MalformedURLException {
		URL url = new URL(urlStr);
		URI uri=null;
		try {
			uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		url = uri.toURL();
		Log.d("sds", url.toString());
		
		//String url = "http://truemd.in/api/medicine_alternatives?" +  (id=Plavix ( 75mg )&key=af494a80bc65dd77f88ac4efa99742&limit=1";
		AsyncHttpClient asyncHttpClient = AsyncHttpClientUtils.getCommonAsyncHTTPClient();
		asyncHttpClient.get(url.toString(), new AsyncHttpResponseHandler(){
			//private final ProgressDialog dialog =new ProgressDialog(ServiceHandler.this);
			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				super.onSuccess(response);
				System.out.println(response);
				TrueMDAPI.responseValue = response;
			}

			@Override
			public void onStart() {
				super.onStart();
				System.out.println("hi");
			}
		});
		//while(responseValue.isEmpty()){continue;}
			//return responseValue;
		return "true";
	}
}