package com.rmp.rmpclient.controller.politician.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class PoliticianDAOFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSingleton() {
		final PoliticianDAOFactory factory1 = PoliticianDAOFactory.getInstance();
		final PoliticianDAOFactory factory2 = PoliticianDAOFactory.getInstance();
		
		assertSame(factory1, factory2);
	}
	
	@Test
	public void test(){
		assertNotNull(PoliticianDAOFactory.getInstance().getPoliticianDAO());
	}

}
