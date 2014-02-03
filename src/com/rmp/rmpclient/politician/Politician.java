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

	public Politician(String id, String firstName, String lastName, String constituency, String party){
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setConstituency(constituency);
		this.setParty(party);
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
	 * Sets the politician's party
	 * 
	 * @param party
	 */
	public void setParty(String party) {
		this.party = party;
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
	 * Sets the politician's constituency
	 * 
	 * @param constituency
	 */
	public void setConstituency(String constituency) {
		this.constituency = constituency;
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
	 * Sets the politician's last name
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
