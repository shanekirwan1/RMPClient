package com.rmp.rmpclient.controller.politician.profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.rmp.rmpclient.politician.Image;
import com.rmp.rmpclient.politician.Politician;

public class PoliticianProfileTest {
	
	private PoliticianProfile profile;

	@Before
	public void setUp() throws Exception {
		final Politician politician = new Politician(100, "alan", "shearer", "newcastle", "boring-party");
		profile = new PoliticianProfile(politician);
	}

	@Test
	public void testGetPoltician() {
		final Politician politician = profile.getPolitician();
		
		assertNotNull(politician);
		// No need to test extensively, will be tested in its own class
		assertEquals("alan", politician.getFirstName());
	}
	
	@Test
	public void testGetImage(){
		final Image image = profile.getImage();
		
		assertNotNull(image);
	}

}
