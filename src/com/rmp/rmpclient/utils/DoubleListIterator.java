package com.rmp.rmpclient.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This class provides an Iterator using two Lists - one main list
 * and another to cache.
 */
public class DoubleListIterator<T> implements Iterator<T>{
	
	/** Random object to pick object from a Collection */
	private static final Random RANDOM = new Random();

	/** The List in which to cache objects */
	private List<T> cacheList;
	
	/** The main list to store */
	private List<T> mainList;

	/**
	 * Constructs a DoubleListIterator object.
	 * 
	 * @param objects
	 *            a Collection of objects. This Collection can be null.
	 */
	public DoubleListIterator(Collection<T> objects) {
		if (objects == null) {
			objects = Collections.emptyList();
		}
		this.mainList = new ArrayList<T>(objects);
		this.cacheList = new ArrayList<T>(mainList.size());
	}

	@Override
	public boolean hasNext() {
		if (mainList.isEmpty()) {
			// if empty then switch collections
			final List<T> temp = mainList;
			mainList = cacheList;
			cacheList = temp;
		} 
		// TODO question: should we return false if there is still nothing in mainList after? 
		return !mainList.isEmpty();
	}

	@Override
	public T next() {
		// TODO changed this up a bit - discuss with lads
		if (!hasNext()){
			throw new java.util.NoSuchElementException("Iterator has no next element.");
		}
		
		final int index = RANDOM.nextInt(mainList.size());
		final T object = mainList.remove(index);
		cacheList.add(object);
		return object;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException("The remove() method should never be called.");
	}

}
