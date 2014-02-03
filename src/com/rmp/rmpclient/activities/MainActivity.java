package com.rmp.rmpclient.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.rmp.rmpclient.jsonparsing.R;
import com.rmp.rmpclient.parser.ServiceHandler;
import com.rmp.rmpclient.politician.Politician;

public class MainActivity extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static final String SERVER_URL = "http://rmpserver.herokuapp.com/api/politicians";

	// JSON Node names
	private static final String TAG_ID = "id";
	private static final String TAG_FIRST_NAME = "firstname";
	private static final String TAG_LAST_NAME = "lastname";
	private static final String TAG_PARTY = "party";
	private static final String TAG_CONSTITUENCY = "constituency";
	private static final String TAG_URL = "url";

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> politicianDisplayList;
	
	/**
	 * List of Politician objects to be stored TODO refactor
	 */
	private final Map<String, Politician> politicianObjs = new HashMap<String, Politician>(); 

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		politicianDisplayList = new ArrayList<HashMap<String, String>>();
		final ListView lv = getListView();

		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view,
					final int position, final long id) {
				// getting values from selected ListItem
				final String polID = ((TextView) view.findViewById(R.id.id)).getText().toString();
				Log.d(getString(R.string.APP_TAG), "Politician ID is : " + polID);
				
				final Politician pol = politicianObjs.get(polID);

				// Starting single contact activity
				final Intent in = new Intent(getApplicationContext(), SinglePoliticianActivity.class);
				in.putExtra(getString(R.string.intent_single_pol), pol);
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
		protected Void doInBackground(final Void... arg0) {
			final ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			final String jsonStr = sh.makeServiceCall(SERVER_URL, ServiceHandler.GET);
			
			Log.d(getString(R.string.APP_TAG), "jsonStr: " + jsonStr);

			if (jsonStr != null) {
				try {
					final JSONArray politicians = new JSONArray(jsonStr);
					Log.d(getString(R.string.APP_TAG), "Politicians Length: " + politicians.length());
					
					for (int i = 0; i < politicians.length(); i++) {
						final JSONObject c = politicians.getJSONObject(i);

						final String id = c.getString(TAG_ID);
						final String firstName = c.getString(TAG_FIRST_NAME);
						final String lastName = c.getString(TAG_LAST_NAME);
						final String party = c.getString(TAG_PARTY);
						final String constituency = c.getString(TAG_CONSTITUENCY);
						final String url = c.getString(TAG_URL);
						
						final Politician p = new Politician(id, firstName, lastName, constituency, party, url);

						// Hashmap of politician tags to details
						final HashMap<String, String> politician = new HashMap<String, String>();
						
						politician.put(TAG_FIRST_NAME, firstName);
						politician.put(TAG_LAST_NAME, lastName);
						politician.put(TAG_PARTY, party);
						politician.put(TAG_ID, id);

						// adding contact to contact list
						politicianDisplayList.add(politician);
						politicianObjs.put(id, p);

					}
				} catch (final JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}
	
			return null;
		}

		@Override
		protected void onPostExecute(final Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			final ListAdapter adapter = new SimpleAdapter(MainActivity.this,
					politicianDisplayList, 
					R.layout.list_item,
					new String[] { TAG_FIRST_NAME, TAG_LAST_NAME, TAG_PARTY, TAG_ID },
					new int[] { R.id.firstName, R.id.lastName, R.id.party, R.id.id });
			
			setListAdapter(adapter);
		}

	}

}