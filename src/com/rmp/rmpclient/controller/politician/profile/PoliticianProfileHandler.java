package com.rmp.rmpclient.controller.politician.profile;

import java.util.Iterator;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;
import com.rmp.rmpclient.utils.DoubleListIterator;

/**
 * Handles a politician profile.
 */
public class PoliticianProfileHandler {
	
	/** Iterates through a collection of politician*/
	private final Iterator<Politician> politicianIterator;
	
	/**
	 * Constructs a PoliticianProfileHandler
	 */
	public PoliticianProfileHandler() {
		politicianIterator = 
				new DoubleListIterator<Politician>(PoliticianDAOFactory.getInstance()
				.getPoliticianDAO().getAllPoliticians());
	}
	
	public PoliticianProfile getNext() {
		return new PoliticianProfile(politicianIterator.next());
	}

}