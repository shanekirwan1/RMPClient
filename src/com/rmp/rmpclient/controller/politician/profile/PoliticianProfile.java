package com.rmp.rmpclient.controller.politician.profile;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;


public class PoliticianProfile {
	
	private Politician politician;
	
	public PoliticianProfile(Politician politician) {
		super();
		this.politician = politician;
	}

	public Politician getPolitician() {
		return politician;
	}

}
