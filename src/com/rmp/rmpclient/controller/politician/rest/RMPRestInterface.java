package com.rmp.rmpclient.controller.politician.rest;

import java.util.Collection;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.rmp.rmpclient.politician.Politician;

public interface RMPRestInterface {

	@GET("/api/politicians")
	public Collection<Politician> getAllPoliticians();

	@POST("/api/politician/rate")
	public void rate(@Body Map<String, Object> politicianRating, Callback<String> cb);

}
