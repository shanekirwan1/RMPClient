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
import android.widget.ListView;
import android.widget.TextView;

import com.rmp.rmpclient.R;
import com.rmp.rmpclient.activities.adapter.LazyAdapter;
import com.rmp.rmpclient.parser.ServiceHandler;
import com.rmp.rmpclient.politician.Politician;

/**
 * The RMP MainActivity.
 * 
 * This will contain the entry screen to the app.
 * 
 */
public class MainActivity extends ListActivity {

	/** progress dialog for startup */
	private ProgressDialog pDialog;

	/** list to populate the view */
	private ArrayList<HashMap<String, String>> politicianDisplayList;
	
	/** List of Politician objects to be stored TODO refactor this to seperate class/cache */
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
				Log.d(getString(R.string.APP_DEBUG), "Politician ID is : " + polID);
				
				final Politician pol = politicianObjs.get(polID);

				// Starting single contact activity
				final Intent in = new Intent(getApplicationContext(), SinglePoliticianActivity.class);
				in.putExtra(getString(R.string.single_pol_act), pol);
				startActivity(in);
				
			}
		});

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
			pDialog.setMessage(getString(R.string.please_wait));
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(final Void... arg0) {
			final ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			final String jsonStr = sh.makeServiceCall(getString(R.string.server_url), ServiceHandler.GET);
			
			Log.d(getString(R.string.APP_DEBUG), "jsonStr: " + jsonStr);

			if (jsonStr != null) {
				try {
					final JSONArray politicians = new JSONArray(jsonStr);
					Log.d(getString(R.string.APP_DEBUG), "Politicians Length: " + politicians.length());
					
					for (int i = 0; i < politicians.length(); i++) {
						final JSONObject c = politicians.getJSONObject(i);

						// Get the tags from the JSON object
						final String id = c.getString(getString(R.string.id));
						final String firstName = c.getString(getString(R.string.firstname));
						final String lastName = c.getString(getString(R.string.lastname));
						final String party = c.getString(getString(R.string.party));
						final String constituency = c.getString(getString(R.string.constituency));
						
						final Politician p = new Politician(id, firstName, lastName, constituency, party);

						// Hashmap of politician tags to details
						final HashMap<String, String> mapToListView = new HashMap<String, String>();	
						mapToListView.put(getString(R.string.firstname), firstName);
						mapToListView.put(getString(R.string.lastname), lastName);
						mapToListView.put(getString(R.string.party), party);
						mapToListView.put(getString(R.string.id), id);
						mapToListView.put(getString(R.string.image_url), "http://rmpserver.herokuapp.com/portrait/" + id);

						// adding contact to contact list
						politicianDisplayList.add(mapToListView);					
						// map the politician id to the Politician object to store
						politicianObjs.put(id, p);

					}
				} catch (final JSONException e) {
					Log.e("something", e.getMessage());
				}
			} else {
				Log.e(getString(R.string.APP_ERROR), "Could not get any data from the url");
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
//			final ListAdapter adapter = new SimpleAdapter(MainActivity.this,
//					politicianDisplayList, 
//					R.layout.list_item,
//					new String[] { getString(R.string.firstname), getString(R.string.lastname), 
//										getString(R.string.party), getString(R.string.id) },
//					new int[] { R.id.firstName, R.id.lastName, R.id.party, R.id.id });
			
			final ListView lv = getListView();
			final LazyAdapter adapter = new LazyAdapter(MainActivity.this, politicianDisplayList);			
			lv.setAdapter(adapter);
		}

	}

}