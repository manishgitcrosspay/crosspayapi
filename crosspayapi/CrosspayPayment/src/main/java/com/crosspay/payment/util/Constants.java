/**
 * 
 */
package com.crosspay.payment.util;

/**
 * @author Techuva
 *
 */
public class Constants {
	
	private String saltkey = "crosspaymentwithencryption";
	
	private String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public int global = 000000000;
	
	static final String SMSAPI = "http://api.msg91.com/api/sendhttp.php?";
    static final String smsauthKey = "170446AX9bPUsyQ59969aff";

	public String getSaltkey() {
		return saltkey;
	}

	public void setSaltkey(String saltkey) {
		this.saltkey = saltkey;
	}

	public String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}

	public void setEMAIL_REGEX(String eMAIL_REGEX) {
		EMAIL_REGEX = eMAIL_REGEX;
	}

	public  int getGlobal() {
		return global;
	}

	public  void setGlobal(int global) {
		this.global = global;
	}
	
	
	
	

}
