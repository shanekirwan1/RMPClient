package com.rmp.rmpclient.politician;

/**
 * This class is a holder for the politician image to be displayed.
 * 
 * At the moment this class is simply a wrapper for a String.
 * 
 */
public class Image {

	/**
	 * The image URL - may change to a complete object later (TODO)
	 */
	private final String imageUrl;
	
	private static final String STATIC_URL = "http://rmpserver.herokuapp.com/portrait/";

	/**
	 * Constructs an Image object.
	 * 
	 * @param URL
	 */
	public Image(final String id) {
		// just append on the id to the static url
		this.imageUrl = STATIC_URL + id;
	}
	
	/**
	 * Gets the image URL.
	 * 
	 * @return the image URL
	 */
	public String getImageUrl(){
		return imageUrl;
	}

}
