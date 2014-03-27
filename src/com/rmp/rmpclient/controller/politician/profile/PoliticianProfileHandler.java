package com.rmp.rmpclient.controller.politician.profile;

import java.util.*;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;
import com.rmp.rmpclient.utils.DoubleListIterator;


public class PoliticianProfileHandler {
	
	private Iterator<Politician> politicianIterator;
	
	public PoliticianProfileHandler() {
		politicianIterator = new DoubleListIterator<Politician>(PoliticianDAOFactory.getInstance()
				.getPoliticianDAO()
				.getAllPoliticians());
		
	}
	
	public PoliticianProfile getNext() {
		return new PoliticianProfile(politicianIterator.next());
	}

	
}
