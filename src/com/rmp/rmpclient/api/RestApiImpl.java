package com.rmp.rmpclient.api;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

import com.rmp.rmpclient.politician.Politician;

public class RestApiImpl implements RestApiInterface {

	private static final String API_URL = System.getProperty("api_url");
	private RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
	RestApiInterface rmp = restAdapter.create(RestApiInterface.class);

	@Override
	@GET("/api/politicians")
	public List<Politician> allPoliticians() {
		// TODO Auto-generated method stub
		List<Politician> politicians = rmp.allPoliticians();
		for (Politician p : politicians) {
			System.out.println(p.getFirstName() + " " +
					p.getLastName() + " " + p.getParty() + " " +
					p.getConstituency());
		}
		return politicians;
	}

	@Override
	@GET("/api/politicians/{id}")
	public Politician singlePolitician(@Path("id") String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET("/api/politicians/party/{party_name}")
	public List<Politician> partyMembers(@Path("party_name") String party) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET("/api/politicians/constituency/{constituency_name}")
	public List<Politician> constituencyMembers(@Path("constituency_name") String constituency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
