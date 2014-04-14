package com.rmp.rmpclient.controller.politician.dao;

import com.rmp.rmpclient.controller.politician.rest.RMPRestInterface;
import com.rmp.rmpclient.controller.politician.rest.RestPoliticianDAO;

public class PoliticianDAOFactory {

	private static final PoliticianDAOFactory INSTANCE = new PoliticianDAOFactory();
	
	public static PoliticianDAOFactory getInstance() {
		return INSTANCE;
	}
	
	public RMPRestInterface getPoliticianDAO() {
		return RestPoliticianDAO.getInstance();
	}


	
}
