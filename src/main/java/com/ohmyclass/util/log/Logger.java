package com.ohmyclass.util.log;

public class Logger {

	public static void debug(String location, String message) {
		System.out.println(String.format("[DEBUG] - at %s: %s", location, message));
	}


	public static void error(String location, String message) {
		System.out.println(String.format("[DEBUG] - at %s: %s", location, message));
		throw new IllegalArgumentException(message);
	}
}
