package com.rmp.rmpclient.controller.politician.rest;

import java.util.Collection;

import retrofit.RestAdapter;
import retrofit.http.GET;

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
	@GET("/api/politicians")
	public Collection<Politician> getAllPoliticians() {
		return rest.getAllPoliticians();
	}

}
