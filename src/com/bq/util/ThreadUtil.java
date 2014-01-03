package com.bq.util;

public class ThreadUtil {
	private static ThreadLocal t = new ThreadLocal();
	
	public static Object get(){
		return t.get();
	}
	
	public static void set(Object o){
		t.set(o);
		long i=0x1L;
		
	}
	
}