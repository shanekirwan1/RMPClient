/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rmp.rmpclient.activities.fragment;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmp.rmpclient.R;
import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.controller.politician.profile.PoliticianProfile;
import com.rmp.rmpclient.politician.Politician;
import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy
 * title indicating the page number, along with some dummy text.
 * 
 * <p>
 * This class is used by the {@link CardFlipActivity} and
 * {@link ScreenSlideActivity} samples.
 * </p>
 */
public class ScreenSlidePageFragment extends Fragment implements OnClickListener{

	private Activity activity;
	private PoliticianProfile politicianProfile;

	public ScreenSlidePageFragment() {

	}

	public ScreenSlidePageFragment setActivity(Activity activity) {
		this.activity = activity;
		return this;
	}

	public ScreenSlidePageFragment setPoliticianProfile(
			PoliticianProfile politicianProfile) {
		this.politicianProfile = politicianProfile;
		return this;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vi = (ViewGroup) inflater.inflate(R.layout.list_item,
				container, false);

		final TextView firstName = (TextView) vi.findViewById(R.id.firstName);
		final TextView lastName = (TextView) vi.findViewById(R.id.lastName);
		final TextView party = (TextView) vi.findViewById(R.id.party);
		final TextView id = (TextView) vi.findViewById(R.id.id);
		final ImageView thumb_image = (ImageView) vi.findViewById(R.id.image);
		
		 Button shake = (Button) vi.findViewById(R.id.shake);
	     shake.setOnClickListener(this);
	     
	     Button tap = (Button) vi.findViewById(R.id.tap);
	     tap.setOnClickListener(this);

		final Politician politician = politicianProfile.getPolitician();

		firstName.setText(politician.getFirstName());
		lastName.setText(politician.getLastName());
		party.setText(politician.getParty());
		id.setText(""+politician.getId());
		final Picasso instance = Picasso.with(activity);
		instance.setDebugging(true);
		instance.load(politicianProfile.getImage().getImageUrl())
				.resize(140, 160).into(thumb_image);

		return vi;
	}

	@Override
	public void onClick(View v) {
		 Map<String, Object> politicianRating = new HashMap<String, Object>();
		 politicianRating.put("politicianId",politicianProfile.getPolitician().getId());
		 politicianRating.put("timestamp", System.currentTimeMillis());
		 switch (v.getId()) {
		 	case R.id.shake: 
		 		politicianRating.put("rating", -1);
		 		PoliticianDAOFactory
		 					.getInstance()
		 					.getPoliticianDAO()
		 					.rate(politicianRating, new Callback<String>() {
								
								@Override
								public void success(String arg0, Response arg1) {
									System.out.println(arg0 + " : " + arg1);
								}
								
								@Override
								public void failure(RetrofitError arg0) {
									System.err.println(arg0);
								}
							});
		 		break;
		 	case R.id.tap: 
		 		politicianRating.put("rating", 1);
		 		PoliticianDAOFactory
					.getInstance()
					.getPoliticianDAO()
					.rate(politicianRating, new Callback<String>() {
						
						@Override
						public void success(String arg0, Response arg1) {
							System.out.println(arg0 + " : " + arg1);
						}
						
						@Override
						public void failure(RetrofitError arg0) {
							System.err.println(arg0);
						}
					});
		 		break;
		 	default: break;
		 }
	}
}