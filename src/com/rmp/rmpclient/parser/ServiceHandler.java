package com.rmp.rmpclient.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class ServiceHandler {

	static String response = null;

	final static String SHANE_TAG = "SHANE";

	public final static int GET = 1;
	public final static int POST = 2;

	public ServiceHandler() {

	}

	/*
	 * Making service call
	 * 
	 * @url - url to make request
	 * 
	 * @method - http request method
	 */
	public String makeServiceCall(String url, int method) {

		Log.d(SHANE_TAG, "makeServiceCall(url,method,null): " + url+", int: "+method);
		return this.makeServiceCall(url, method, null);
	}

	/*
	 * Making service call
	 * 
	 * @url - url to make request
	 * 
	 * @method - http request method
	 * 
	 * @params - http request params
	 */
	public String makeServiceCall(String url, int method, List<NameValuePair> params) {
		try {
			// http client
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;

			// Checking http request method type
			if (method == POST) {
				Log.d(SHANE_TAG, "SHANE, POST Request: " + url);
				HttpPost httpPost = new HttpPost(url);
				// adding post params
				if (params != null) {
					Log.d(SHANE_TAG, "SHANE, Params are not null...");
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}

				httpResponse = httpClient.execute(httpPost);
			} else if (method == GET) {
				Log.d(SHANE_TAG, "SHANE, GET Request: " + url);

				// appending params to url
				if (params != null) {
					String paramString = URLEncodedUtils.format(params, "utf-8");
					url += "?" + paramString;
					Log.d(SHANE_TAG, "SHANE, NEW URL is: " + url);
				}
				HttpGet httpGet = new HttpGet(url);

				httpResponse = httpClient.execute(httpGet);

			}
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);

			Log.d(SHANE_TAG, "SHANE, WE HAVE A RESPONSE: " + response);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;

	}
}
