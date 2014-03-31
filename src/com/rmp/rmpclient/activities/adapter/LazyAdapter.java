package com.rmp.rmpclient.activities.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmp.rmpclient.R;
import com.rmp.rmpclient.controller.politician.profile.PoliticianProfile;
import com.rmp.rmpclient.politician.Politician;
import com.squareup.picasso.Picasso;

public class LazyAdapter extends BaseAdapter {

    private final Activity activity;
    private final List<PoliticianProfile> politicianProfiles;
    private static LayoutInflater inflater = null;

    public LazyAdapter(final Activity a, final List<PoliticianProfile> politicianProfiles) {
        activity = a;
        this.politicianProfiles = politicianProfiles;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
	public int getCount() {
        return this.politicianProfiles.size();
    }
 
    @Override
	public Object getItem(final int position) {
        return position;
    }
 
    @Override
	public long getItemId(final int position) {
        return position;
    }
 
    @Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
        
    	View vi = convertView;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.list_item, null);
		}

		final TextView	firstName = (TextView) vi.findViewById(R.id.firstName);
        final TextView lastName = (TextView) vi.findViewById(R.id.lastName);
        final TextView party = (TextView) vi.findViewById(R.id.party);
        final TextView id = (TextView) vi.findViewById(R.id.id);
        final ImageView thumb_image = (ImageView) vi.findViewById(R.id.image);
 
        Politician politician = politicianProfiles.get(position).getPolitician();

        firstName.setText(politician.getFirstName());
        lastName.setText(politician.getLastName());
        party.setText(politician.getParty());
        id.setText(politician.getId());
        Picasso.with(activity)
        .load(politicianProfiles.get(position).getImage())
        		.resize(140, 160)
        		.into(thumb_image);
                
        return vi;
    }
    
}