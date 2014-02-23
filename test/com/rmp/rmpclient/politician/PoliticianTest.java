package com.rmp.rmpclient.politician;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PoliticianTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		final Politician p = new Politician("1", "Seanie", "O'Leary", "Dublin", "Fianna Fáil", "http://politician.com");
		
		assertEquals(p.getId(), "1");
		assertEquals(p.getFirstName(), "Seanie");
		assertEquals(p.getLastName(), "O'Leary");
		assertEquals(p.getConstituency(), "Dublin");
		assertEquals(p.getParty(), "Fianna Fáil");
		assertEquals(p.getUrl(), "http://politician.com");
	}
	

}
