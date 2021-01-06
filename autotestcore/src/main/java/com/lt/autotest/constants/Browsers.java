package com.lt.autotest.constants;

public enum Browsers {

	CHROME, IE, FIREFOX, EDGE, SAFARI, REMOTE;

	public static Browsers getBrowser(String browser) throws IllegalArgumentException {
		for (Browsers b : values()) {
			if (b.toString().equalsIgnoreCase(browser)) {
				return b;
			}                  
		}
		throw browserNotFound(browser);
	}

	private static IllegalArgumentException browserNotFound(String browserName) {
		return new IllegalArgumentException(("Browsers.browserNotFound - Invalid browser [" + browserName + "]"));
	}
}
