package com.rmp.rmpclient.jsonparsing;

import com.rmp.rmpclient.jsonparsing.R;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://rmpserver.herokuapp.com/api/politicians";

	// JSON Node names
	private static final String TAG_POLITICIANS = "politicians";
	private static final String TAG_ID = "id";
	private static final String TAG_FIRST_NAME = "firstname";
	private static final String TAG_LAST_NAME = "lastname";
	private static final String TAG_PARTY = "party";
//	private static final String TAG_CONSTITUENCY = "constituency";
//	private static final String TAG_URL = "url";
	// private static final String TAG_PHONE_MOBILE = "mobile";
	// private static final String TAG_PHONE_HOME = "home";
	// private static final String TAG_PHONE_OFFICE = "office";

	protected static final String SHANE_TAG = "SHANE";

	// contacts JSONArray
//	JSONArray politicians = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> politicianList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		politicianList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();

		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// getting values from selected ListItem
				String firstName = ((TextView) view.findViewById(R.id.firstName)).getText().toString();
				Log.d(SHANE_TAG, "firstName is : " + firstName);
				String lastName = ((TextView) view.findViewById(R.id.lastName)).getText().toString();
				String party = ((TextView) view.findViewById(R.id.party)).getText().toString();

				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(), SinglePoliticianActivity.class);
				in.putExtra(TAG_FIRST_NAME, firstName);
				in.putExtra(TAG_LAST_NAME, lastName);
				in.putExtra(TAG_PARTY, party);
				startActivity(in);

			}
		});

		// Calling async task to get json
		new GetContacts().execute();
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			Log.d(SHANE_TAG,"jsonStr: "+jsonStr);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					//JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					JSONArray politicians = new JSONArray(jsonStr);// = jsonObj.getJSONArray("");
					Log.d(SHANE_TAG, "SHANE: Polititcians Length: "+politicians.length());
					// looping through All Contacts
					for (int i = 0; i < politicians.length(); i++) {
						JSONObject c = politicians.getJSONObject(i);

//						String id = c.getString(TAG_ID);
						String firstName = c.getString(TAG_FIRST_NAME);
						Log.d(SHANE_TAG,"YAAAAAY firstName: "+firstName);
						String lastName = c.getString(TAG_LAST_NAME);
						String party = c.getString(TAG_PARTY);
//						String constituency = c.getString(TAG_CONSTITUENCY);
//						String url = c.getString(TAG_URL);

						// Phone node is JSON Object
//						JSONObject url = c.getJSONObject(TAG_URL);
						// String mobile = phone.getString(TAG_PHONE_MOBILE);
						// String home = phone.getString(TAG_PHONE_HOME);
						// String office = phone.getString(TAG_PHONE_OFFICE);

						// tmp hashmap for single contact
						HashMap<String, String> politician = new HashMap<String, String>();

						// adding each child node to HashMap key => value
//						politician.put(TAG_ID, id);
						politician.put(TAG_FIRST_NAME, firstName);
						politician.put(TAG_LAST_NAME, lastName);
						politician.put(TAG_PARTY, party);

						// adding contact to contact list
						politicianList.add(politician);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(MainActivity.this, politicianList, R.layout.list_item, new String[] { TAG_FIRST_NAME, TAG_LAST_NAME, TAG_PARTY }, new int[] {
					R.id.firstName, R.id.lastName, R.id.party });

			setListAdapter(adapter);
		}

	}

}