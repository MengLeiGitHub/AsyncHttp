package com.async.http.proxy;

public enum MIO {
	
	MainThread(2),
	
	IOThread(12);

	private int val=0;
	
	MIO(int i){
		val=i;
	}
	protected int val() {
		return val;
	}
	
	
}
