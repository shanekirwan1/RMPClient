package com.rmp.rmpclient.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.rmp.rmpclient.controller.politician.profile.PoliticianProfileHandler;
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

	/**/
	PoliticianProfileHandler politicianProfileHandler;
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new GetContacts().execute();
		
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
			politicianProfileHandler = new PoliticianProfileHandler();
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
			final LazyAdapter adapter = new LazyAdapter(MainActivity.this, politicianProfileHandler);			
			lv.setAdapter(adapter);
		
		}

	}

}