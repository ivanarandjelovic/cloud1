package org.aivan.cloud1.testapp;

import java.util.ArrayList;

public class TestApp {

	private static final int ONE_MB = 1024 * 1024;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("TestApp starting ....");

		@SuppressWarnings("rawtypes")
		ArrayList charList = new ArrayList();

		int counter = 0;
		try {
			while (true) {
				charList.add(new byte[ONE_MB]);
				counter++;
				System.out.println(counter + " MBs allocated");
			}
		} catch (OutOfMemoryError oom) {
			System.out.println("No more mem!");
		}

		System.out.println("FINAL: I was able to allocate: "+ counter+" MBs in total");
		
		System.out.println("TestApp ending ...");

		try {
			System.out.println("Sleeping ...");
			Thread.sleep(1000 * 1000);
		} catch (Throwable t) {
			System.out.println(t);
		}
		System.out.println("Closing.");
	}

}
