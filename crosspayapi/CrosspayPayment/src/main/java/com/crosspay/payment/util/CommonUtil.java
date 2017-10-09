/**
 * 
 */
package com.crosspay.payment.util;

/**
 * @author Reddy
 *
 */

import java.util.Random;
import java.util.regex.Pattern;

public class CommonUtil {

	/**
	 * Random Number Generator for MObile OTP return 6 digit random number
	 * 
	 * @return
	 */
	
	public static String generateFiveDigitNumericString() {
		Random rnd = new Random();
		int n = 10000 + rnd.nextInt(90000);
		return "" + n;
	}
	public static String generateSixDigitNumericString() {
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return "" + n;
	}
	
	public static String generateNineDigitNumericString() {
		return "" + (int) (Math.random() * 1000000000);
	}
	
	public static String generateFourDigitNumericString() {
		Random rnd = new Random();
		int n = 1000 + rnd.nextInt(9000);
		return "" + n;
	}
	
	public static String generateSevenDigitNumericString() {
		Random rnd = new Random();
		int n = 1000000 + rnd.nextInt(9000000);
		return "" + n;
	}
	
	

	public static void sleep(long timeInMS) {
		try {
			Thread.sleep(timeInMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static final Pattern PATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	public static boolean validate(final String ip) {
	    return PATTERN.matcher(ip).matches();
	}
	
	
	
	
}

