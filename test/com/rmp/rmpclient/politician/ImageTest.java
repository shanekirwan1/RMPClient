package com.rmp.rmpclient.politician;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ImageTest {

	private Image testImage;
	
	@Before
	public void setUp() throws Exception {
		testImage = new Image(1);
	}

	@Test
	public void testGetImage() {
		assertEquals("http://rmpserver.herokuapp.com/portrait/1", testImage.getImageUrl());
	}

}