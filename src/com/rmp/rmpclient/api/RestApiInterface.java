package com.rmp.rmpclient.api;

import java.util.List;

import com.rmp.rmpclient.politician.*;
import retrofit.http.GET;
import retrofit.http.Path;

public interface RestApiInterface {
	
	@GET("/api/politicians")
	public List<Politician> allPoliticians();

	@GET("/api/politicians/{id}")
	public Politician singlePolitician(@Path("id") String id);

	@GET("/api/politicians/party/{party_name}")
	public List<Politician> partyMembers(@Path("party_name") String party);

	@GET("/api/politicians/constituency/{constituency_name}")
	public List<Politician> constituencyMembers(@Path("constituency_name") String constituency);

}
