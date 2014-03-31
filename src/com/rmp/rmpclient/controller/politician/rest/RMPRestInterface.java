package com.rmp.rmpclient.controller.politician.rest;

import java.util.Collection;

import retrofit.http.GET;

import com.rmp.rmpclient.politician.Politician;

public interface RMPRestInterface {

	@GET("/api/politicians")
	public Collection<Politician> getAllPoliticians();

}
