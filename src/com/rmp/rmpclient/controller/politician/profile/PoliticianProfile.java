package com.rmp.rmpclient.controller.politician.profile;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Image;
import com.rmp.rmpclient.politician.Politician;

public class PoliticianProfile {

	private final Politician politician;

	/**
	 * The politician image to be displayed
	 */
	private final Image image;

	public PoliticianProfile(final Politician politician) {
		super();
		this.politician = politician;
		this.image = new Image(politician.getId());
	}

	public Politician getPolitician() {
		return politician;
	}

	/**
	 * Get's the politician's image.
	 * 
	 * @return the politician's image
	 */
	public Image getImage() {
		return image;
	}

}
