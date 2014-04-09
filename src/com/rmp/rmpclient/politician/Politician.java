package com.rmp.rmpclient.politician;

import java.io.Serializable;

/**
 * Java data object to describe the details of a politician
 */
public class Politician implements Serializable {

	/** Serial ID */
	private static final long serialVersionUID = 1L;

	/** The unique ID */
	private final String id;

	/** The first name */
	private final String firstname;
	
	/** The last name */
	private final String lastname;
	
	/** The constituency*/
	private final String constituency;
	
	/** The party*/
	private final String party;

	/**
	 * Constructs a Politician object with the parameters supplied
	 * 
	 * @param id
	 *            the ID
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param constituency
	 *            the constituency
	 * @param party
	 *            the party
	 */
	public Politician(final String id, final String firstName,
			final String lastName, final String constituency, 
			final String party) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
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
		return lastname;
	}

	/**
	 * Gets the politician's first name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstname;
	}

	/**
	 * Gets the politician's ID
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	

}
