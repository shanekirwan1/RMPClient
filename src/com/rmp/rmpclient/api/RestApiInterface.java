package com.rmp.rmpclient.api;

import java.util.List;

import com.rmp.rmpclient.politician.*;
import retrofit.http.GET;
import retrofit.http.Path;

public interface RestApiInterface {
	@GET("/api/politicians")
	List<Politician> allPoliticians();

	@GET("/api/politicians/{id}")
	Politician groupList(@Path("id") String id);

	@GET("/api/politicians/party/{party_name}")
	List<Politician> partyMembers(@Path("party_name") String party);

	@GET("/api/politicians/constituency/{constituency_name}")
	List<Politician> constituencyMembers(@Path("constituency_name") String constituency);

}
