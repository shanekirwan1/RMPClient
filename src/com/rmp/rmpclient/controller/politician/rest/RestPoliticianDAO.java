package com.rmp.rmpclient.controller.politician.rest;

import java.util.Collection;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.rmp.rmpclient.politician.Politician;

public class RestPoliticianDAO implements RMPRestInterface {
	
	private static final RestPoliticianDAO INSTANCE = new RestPoliticianDAO();

	private static final String API_URL = "http://rmpserver.herokuapp.com";
	private static RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
	private static RMPRestInterface rest = restAdapter.create(RMPRestInterface.class);
	
	/**
	 * Private constructor for Singleton.
	 */
	private RestPoliticianDAO() {
	}
	
	/**
	 * Gets the Singleton RestPoliticianDAO instance.
	 * 
	 * @return the Singleton instance
	 */
	public static RestPoliticianDAO getInstance() {
		return INSTANCE;
	}

	@Override
	public Collection<Politician> getAllPoliticians() {
		return rest.getAllPoliticians();
	}
	
	@Override
	public void rate(@Path("id") int politicianId,  @Path("rating") int rating, Callback<String> cb){
		rest.rate(politicianId, rating, cb);
	}

	@Override
	@POST("/api/politician/rate/")
	public void rate(Map<String, Object> politicianRating, Callback<String> cb) {
		rest.rate(politicianRating, cb);
		
	}

}
