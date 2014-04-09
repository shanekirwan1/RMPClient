package com.rmp.rmpclient.utils;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("NewApi")
public class CursorList<T> {
	private final Map<Integer,T> data = new HashMap<Integer,T>();
	
	public boolean hasPosition(int position) {
		return data.containsKey(position);
	}
	
	public T get(int position){
		if(!hasPosition(position)){
			throw new java.util.NoSuchElementException("No element at position: " + position);
		}
		
		return data.get(position);
	}
	
	
	public T offer(int position, T element){
		data.put(position, element);
		return element;
	}

	@Override
	public String toString() {
		return data.toString();
	}
	
	
}
