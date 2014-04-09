package com.rmp.rmpclient.controller.politician.profile;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;
import com.rmp.rmpclient.utils.DoubleListIterator;

/**
 * Handles a politician profile.
 */
public class PoliticianProfileHandler {
	
	/** Iterates through a collection of politician*/
	private final Iterator<PoliticianProfile> politicianPIterator;
	private Collection<PoliticianProfile> politicianProfiles = null;
	
	/**
	 * Constructs a PoliticianProfileHandler
	 */
	public PoliticianProfileHandler() {
		politicianProfiles = new LinkedList<PoliticianProfile>();
		try {
			Log.d("EALDUNN", "RUNNING!!");
			Collection<Politician> politicians = PoliticianDAOFactory.getInstance()
				.getPoliticianDAO()
				.getAllPoliticians();
			
			for(Politician p:politicians){
				politicianProfiles.add(new PoliticianProfile(p));
			}
			
			Log.d("EALDUNN", "RAN!!");
		} catch (final Throwable e){
			Log.d("EALDUNN",e.getMessage());
		}
		politicianPIterator = 
				new DoubleListIterator<PoliticianProfile>(politicianProfiles);
	}
	
	public PoliticianProfile getNext() {
		return politicianPIterator.next();
	}
	
	public int getSize() {
		return politicianProfiles.size();
	}

}