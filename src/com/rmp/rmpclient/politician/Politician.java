package com.rmp.rmpclient.politician;

/**
 * Java data object to describe the politician object.
 */
public class Politician {
	
	private String id;
	private String firstName;
	private String lastName;
	private String constituency;
	private String party;
	private String url;

	public Politician(String id, String firstName, String lastName, String constituency, String party, String url){
		this.url = url;
		this.id = id;
		this.firstName = firstName;
		this.lastName=lastName;
		this.constituency = constituency;
		this.party = party;
	}
	
	/**
	 * Gets the politician's url
	 * 
	 * @return the URL
	 */
	public String getUrl(){
		return url;
	}

	/**
	 * Gets the politician's party
	 * 
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * Gets the politician's constituency
	 * 
	 * @return the constituency
	 */
	public String getConstituency() {
		return constituency;
	}

	/**
	 * Gets the politician's last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

}
