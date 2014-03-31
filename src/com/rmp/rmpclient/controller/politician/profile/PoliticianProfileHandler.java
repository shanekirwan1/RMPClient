package com.rmp.rmpclient.controller.politician.profile;

import java.util.*;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;
import com.rmp.rmpclient.utils.DoubleListIterator;

/**
 * Handles a politician profile.
 */
public class PoliticianProfileHandler {
	
	/** Iterates through a collection of politician*/
	private final Iterator<Politician> politicianIterator;
	private final Collection<Politician> politicians;
	
	/**
	 * Constructs a PoliticianProfileHandler
	 */
	public PoliticianProfileHandler() {
		politicians = PoliticianDAOFactory.getInstance()
				.getPoliticianDAO()
				.getAllPoliticians();
		politicianIterator = 
				new DoubleListIterator<Politician>(politicians);
	}
	
	public PoliticianProfile getNext() {
		return new PoliticianProfile(politicianIterator.next());
	}
	
	public int getSize() {
		return politicians.size();
	}

}