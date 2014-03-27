package com.rmp.rmpclient.utils;

import java.util.*;


public class DoubleListIterator<T> implements Iterator<T>{
	
	private static final Random RANDOM = new Random();

	private List<T> cacheList;
	private List<T> mainList;

	public DoubleListIterator(Collection<T> politicians) {
		if(politicians == null) politicians = Collections.emptyList();
		this.mainList = new ArrayList<T>(politicians);
		this.cacheList = new ArrayList<T>(mainList.size());
	}

	@Override
	public boolean hasNext() {
		if(mainList.isEmpty()){
			final List<T> temp = mainList;
			mainList = cacheList;
			cacheList = temp;
		}
		return true;
	}

	@Override
	public T next() {
		hasNext();
		
		final int index = RANDOM.nextInt(mainList.size());
		final T politician = mainList.remove(index);
		cacheList.add(politician);
		return politician;
	}
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
