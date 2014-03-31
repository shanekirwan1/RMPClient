package com.rmp.rmpclient.activities.adapter;

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
import com.rmp.rmpclient.controller.politician.profile.PoliticianProfileHandler;
import com.rmp.rmpclient.politician.Politician;
import com.squareup.picasso.Picasso;

public class LazyAdapter extends BaseAdapter {

	private final Activity activity;
	private final PoliticianProfileHandler politicianProfileHandler;
	private static LayoutInflater inflater = null;

	public LazyAdapter(final Activity a,
			final PoliticianProfileHandler politicianProfileHandler) {
		activity = a;
		this.politicianProfileHandler = politicianProfileHandler;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return this.politicianProfileHandler.getSize();
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
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {

		View vi = convertView;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.list_item, null);
		}

		final TextView firstName = (TextView) vi.findViewById(R.id.firstName);
		final TextView lastName = (TextView) vi.findViewById(R.id.lastName);
		final TextView party = (TextView) vi.findViewById(R.id.party);
		final TextView id = (TextView) vi.findViewById(R.id.id);
		final ImageView thumb_image = (ImageView) vi.findViewById(R.id.image);

		final PoliticianProfile politicianProfile = politicianProfileHandler
				.getNext();
		final Politician politician = politicianProfile.getPolitician();

		firstName.setText(politician.getFirstName());
		lastName.setText(politician.getLastName());
		party.setText(politician.getParty());
		id.setText(politician.getId());
		final Picasso instance = Picasso.with(activity);
		instance.setDebugging(true);
		instance.load(politicianProfile.getImage().getImageUrl())
				.resize(140, 160).into(thumb_image);

		return vi;
	}

}