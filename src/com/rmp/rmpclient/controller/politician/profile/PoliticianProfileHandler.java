package com.rmp.rmpclient.controller.politician.profile;

import java.util.Collection;
import java.util.Iterator;

import android.util.Log;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;
import com.rmp.rmpclient.utils.DoubleListIterator;

/**
 * Handles a politician profile.
 */
public class PoliticianProfileHandler {
	
	/** Iterates through a collection of politician*/
	private final Iterator<Politician> politicianIterator;
	private Collection<Politician> politicians = null;
	
	/**
	 * Constructs a PoliticianProfileHandler
	 */
	public PoliticianProfileHandler() {
		try {
			Log.d("EALDUNN", "RUNNING!!");
			politicians = PoliticianDAOFactory.getInstance()
				.getPoliticianDAO()
				.getAllPoliticians();
			Log.d("EALDUNN", "RAN!!");
		} catch (final Throwable e){
			Log.d("EALDUNN",e.getMessage());
		}
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