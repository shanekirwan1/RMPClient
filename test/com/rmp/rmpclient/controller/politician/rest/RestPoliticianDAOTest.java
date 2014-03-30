package com.rmp.rmpclient.controller.politician.rest;

import org.junit.Before;
import org.junit.Test;

public class RestPoliticianDAOTest {
	
	RestPoliticianDAO rest;
	
	//@InjectMocks RMPRestInterface rmp;

	@Before
	public void setUp() throws Exception {
		rest = RestPoliticianDAO.getInstance();
	}

	@Test
	public void testGetAllPoliticians() {
		
		rest.getAllPoliticians();
	}

}
