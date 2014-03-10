package com.rmp.rmpclient.politician;

import java.io.Serializable;

/**
 * Java data object to describe the details of a politician
 */
public class Politician implements Serializable {

	/** Serial ID */
	private static final long serialVersionUID = 1L;

	private final String id;
	private final String firstName;
	private final String lastName;
	private final String constituency;
	private final String party;

	public Politician(final String id, final String firstName, final String lastName, final String constituency, final String party) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.constituency = constituency;
		this.party = party;
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
