package com.rmp.rmpclient.activities.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmp.rmpclient.R;
import com.squareup.picasso.Picasso;

public class LazyAdapter extends BaseAdapter {

    private final Activity activity;
    private final ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    public LazyAdapter(final Activity a, final ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
	public int getCount() {
        return data.size();
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
 
        HashMap<String, String> politician = new HashMap<String, String>();
        politician = data.get(position);

        firstName.setText(politician.get("firstname"));
        lastName.setText(politician.get("lastname"));
        party.setText(politician.get("party"));
        id.setText(politician.get("id"));
        Picasso.with(activity).load(politician.get("image_url")).resize(140, -1).into(thumb_image);
                
        return vi;
    }
}