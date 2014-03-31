package com.rmp.rmpclient.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoubleListIteratorTest {

	/** testIterator */
	DoubleListIterator<MockObjectToTest> testIterator;
	
	@Before
	public void setUp() throws Exception {
		// Create an empty iterator to test
		testIterator = new DoubleListIterator<MockObjectToTest>(null);
	}
	
	@After
	public void teardown(){
		testIterator = null;
	}

	@Test
	public void testNext() throws Exception {
		
		fillIterator();	
		final MockObjectToTest mock = testIterator.next();
		
		boolean verdict = false;
		if (mock.getId().equals("1")){
			assertEquals("john", mock.name());
			verdict = true;
		} else if (mock.getId().equals("2")){
			assertEquals("tom", mock.name());
			verdict = true;
		} else if (mock.getId().equals("3")){
			assertEquals("jimmy", mock.name());
			verdict = true;
		} else {
			throw new Exception("None of the expected politician IDs have been returned.");
		}
		
		assertTrue(verdict);		
	}
	
	@Test
	public void testNextIsRandom() throws Exception {		
		fillIterator();
		verifyRandomness(testIterator);
		// should switch the cache list back to the main list the second time and repeat...
		verifyRandomness(testIterator);
	}
	
	@Test
	public void testRemove(){
		try {
			testIterator.remove();
			fail("UnsupportedOperationException should have been thrown.");
		} catch (final UnsupportedOperationException uoe) {
			assertEquals("The remove() method should never be called.", uoe.getMessage());
		}
	}
	
	@Test
	public void testHasNextReturnsFalseWhenMainListEmpty() {
		assertFalse(testIterator.hasNext());
	}
	
	@Test
	public void testHasNextReturnsTrueWhenMainListNotEmpty() {
		fillIterator();
		assertTrue(testIterator.hasNext());
	}
	
	@Test
	public void testNextWithEmtpyIteratorThrowsException() {
		try {
			assertNull(testIterator.next());
			fail("NoSuchElementException should have been thrown.");
		} catch (final NoSuchElementException nsee) {
			assertEquals("Iterator has no next element.", nsee.getMessage());
		}
	}
	
	// ================= private methods ==========================================
	
	/**
	 * Verify that the iterator is exhausted with calls to next()
	 * 
	 * @param testIterator2
	 * @throws Exception
	 */
	private void verifyRandomness(final DoubleListIterator<MockObjectToTest> testIterator2) throws Exception {

		final MockObjectToTest mock = testIterator2.next();
		String id = mock.getId();
		
		assertTrue(id == "1" || id == "2" || id == "3");
		if (id == "1"){
			id = getId(testIterator2);
			assertTrue(id == "2" || id == "3");
			if (id == "2"){
				id = getId(testIterator2);
				assertTrue(id == "3");
			} else if (id == "3"){
				id = getId(testIterator2);
				assertTrue(id == "2");
			}
		} else if (id == "2") {
			id = getId(testIterator2);
			assertTrue(id == "1" || id == "3");
			if (id == "1"){
				id = getId(testIterator2);
				assertTrue(id == "3");
			} else if (id == "3"){
				id = getId(testIterator2);
				assertTrue(id == "1");
			}
		} else if (id == "3"){
			id = getId(testIterator2);
			assertTrue(id == "1" || id == "2");
			if (id == "1"){
				id = getId(testIterator2);
				assertTrue(id == "2");
			} else if (id == "2"){
				id = getId(testIterator2);
				assertTrue(id == "1");
			}
		} else {
			throw new Exception("Politician is out of range!");
		}
		
	}

	private String getId(final DoubleListIterator<MockObjectToTest> i) {
		return i.next().getId();
	}
	
	private void fillIterator() {
		final List<MockObjectToTest> mockedObjs = new ArrayList<MockObjectToTest>();
		mockedObjs.add(new MockObjectToTest("1", "john"));
		mockedObjs.add(new MockObjectToTest("2", "tom"));
		mockedObjs.add(new MockObjectToTest("3", "jimmy"));
		
		testIterator = new DoubleListIterator<MockObjectToTest>(mockedObjs);
	}
	
	private class MockObjectToTest{
		
		private final String id;
		private final String name;

		MockObjectToTest(final String id, final String name){
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String name() {
			return name;
		}
	}

}
