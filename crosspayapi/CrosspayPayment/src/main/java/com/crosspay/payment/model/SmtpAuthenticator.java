package com.crosspay.payment.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.crosspay.payment.util.CommonUtil;
import com.crosspay.payment.util.Constants;
import com.crosspay.payment.util.CryptoUtil;

public class SmtpAuthenticator  {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	//public static void main(String args[]) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
	
	public void changepswd() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from SIGNUPS ");
		
		for (int i = 0; i < result.size(); i++) {
			
			String email = result.get(i).get("CUSTOMEREMAIL").toString();
			
			String password = new CommonUtil().generateFourDigitNumericString();
			String encryptedpswd = new CryptoUtil().encrypt(new Constants().getSaltkey(),password);
			
			System.out.println("name pswd "+i+"=="+email);
			
			int j = jdbcTemplate.update("update SIGNUPS set PASSWORD = ? where CUSTOMEREMAIL = ?",new Object[]{encryptedpswd,email});
			int k = jdbcTemplate.update("update LOGINCREDENTIALS set PASSWORD = ? where CUSTOMEREMAIL = ?",new Object[]{encryptedpswd,email});
			
			System.out.println("j  "+j);
			System.out.println("k  "+k);
			
		}
	}
	
	

}
