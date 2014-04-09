package com.rmp.rmpclient.controller.politician.profile;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

import com.rmp.rmpclient.controller.politician.dao.PoliticianDAOFactory;
import com.rmp.rmpclient.politician.Politician;
import com.rmp.rmpclient.utils.CursorList;
import com.rmp.rmpclient.utils.DoubleListIterator;

/**
 * Handles a politician profile.
 */
public class PoliticianProfileHandler {
	
	/** Iterates through a collection of politician*/
	private final Iterator<PoliticianProfile> politicianPIterator;
	private Collection<PoliticianProfile> politicianProfiles = null;
	
	private CursorList<PoliticianProfile> timeline = new CursorList<PoliticianProfile>();
	private int lastPosition = -1;
	
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
			Log.e("EALDUNN",e.getMessage());
		}
		politicianPIterator = 
				new DoubleListIterator<PoliticianProfile>(politicianProfiles);
	}
	
	
	public int getSize() {
		return politicianProfiles.size();
	}

	public PoliticianProfile get(int position) {
		if(timeline.hasPosition(position)){
			return timeline.get(position);
		}
		return timeline.offer(position, politicianPIterator.next());
	}

}