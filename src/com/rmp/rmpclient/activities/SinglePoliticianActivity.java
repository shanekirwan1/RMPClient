package com.rmp.rmpclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmp.rmpclient.R;
import com.rmp.rmpclient.politician.Politician;
import com.squareup.picasso.Picasso;

public class SinglePoliticianActivity extends Activity {
	
	@Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        final Intent in = getIntent();
        
        // Get JSON values from previous intent
        final Politician politician = (Politician) in.getSerializableExtra(getString(R.string.single_pol_act));
        
        // Displaying all values on the screen
		final TextView lblFirstName = (TextView) findViewById(R.id.firstname_label);
        final TextView lblLastName = (TextView) findViewById(R.id.lastname_label);
        final TextView lblParty = (TextView) findViewById(R.id.party_label);
        final ImageView image = (ImageView) findViewById(R.id.image_label);    
        Picasso.with(this).load("http://rmpserver.herokuapp.com/portrait/" + politician.getId()).into(image);

        // Set the labels from the single contact view
        lblFirstName.setText(politician.getFirstName());
        lblLastName.setText(politician.getLastName());
        lblParty.setText(politician.getParty());

	}
}
