package com.rmp.rmpclient.activities;

import com.rmp.rmpclient.jsonparsing.R;
import com.rmp.rmpclient.jsonparsing.R.id;
import com.rmp.rmpclient.jsonparsing.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SinglePoliticianActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_FIRST_NAME = "firstname";
	private static final String TAG_LAST_NAME = "lastname";
	private static final String TAG_PARTY = "party";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String firstName = in.getStringExtra(TAG_FIRST_NAME);
        String lastName = in.getStringExtra(TAG_LAST_NAME);
        String party = in.getStringExtra(TAG_PARTY);
        
        // Displaying all values on the screen
		TextView lblFirstName = (TextView) findViewById(R.id.firstname_label);
        TextView lblLastName = (TextView) findViewById(R.id.lastname_label);
        TextView lblParty = (TextView) findViewById(R.id.party_label);
        
        lblFirstName.setText(firstName);
        lblLastName.setText(lastName);
        lblParty.setText(party);
    }
}
