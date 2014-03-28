package com.rmp.rmpclient.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rmp.rmpclient.politician.Politician;

public class DoubleListIteratorTest {

	/** testIterator */
	DoubleListIterator<Politician> testIterator;
	
	@Before
	public void setUp() throws Exception {
		// Create an empty iterator to test
		testIterator = new DoubleListIterator<Politician>(null);
	}

	@Test
	public void testNext() throws Exception {
		final List<Politician> politicians = new ArrayList<Politician>();
		politicians.add(new Politician("1", "john", "smith", "dublin", "tories"));
		politicians.add(new Politician("2", "tom", "jones", "meath", "labour"));
		politicians.add(new Politician("3", "jimmy", "kenny", "cork", "lib-dems"));
		
		testIterator = new DoubleListIterator<Politician>(politicians);
		
		final Politician pol = testIterator.next();
		
		boolean verdict = false;
		if (pol.getId().equals("1")){
			assertEquals("john", pol.getFirstName());
			verdict = true;
		} else if (pol.getId().equals("2")){
			assertEquals("tom", pol.getFirstName());
			verdict = true;
		} else if (pol.getId().equals("3")){
			assertEquals("jimmy", pol.getFirstName());
			verdict = true;
		} else {
			throw new Exception("None of the expected politician IDs have been returned.");
		}
		
		assertTrue(verdict);
		
	}
	
	// TODO fix the RANDOM part in DLI.class
	@Test(expected=IllegalArgumentException.class)
	public void testNextWithNullIterator(){
		final Politician pol = testIterator.next();
		final String name = pol.getFirstName();	
		assertEquals("john", name); // failing
	}
	
	@Test
	public void testHasNext(){
		assertTrue(testIterator.hasNext());
	}
	
	@Test
	public void testRemove(){
		testIterator.remove();
	}

}
