package com.crosspay.payment.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Blob;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "COUNTRY")
public class Country {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int COUNTRY_ID;

	@NotNull
	private String COUNTRYCODE;

	@Null
	private String COUNTRYIMAGE;

	@Null
	private String COUNTRYNAME;

	@NotNull
	private int GMTOFFSET;

	@NotNull
	private int COUNTRYSTATUS;

	@NotNull
	private Date RECORDDATE;

	// Public methods
	// Getter and setter methods

	public int getCOUNTRY_ID() {
		return COUNTRY_ID;
	}

	public void setCOUNTRY_ID(int cOUNTRY_ID) {
		COUNTRY_ID = cOUNTRY_ID;
	}

	public String getCOUNTRYCODE() {
		return COUNTRYCODE;
	}

	public void setCOUNTRYCODE(String cOUNTRYCODE) {
		COUNTRYCODE = cOUNTRYCODE;
	}

	public String getCOUNTRYIMAGE() {
		return COUNTRYIMAGE;
	}

	public void setCOUNTRYIMAGE(String cOUNTRYIMAGE) {
		COUNTRYIMAGE = cOUNTRYIMAGE;
	}

	public String getCOUNTRYNAME() {
		return COUNTRYNAME;
	}

	public void setCOUNTRYNAME(String cOUNTRYNAME) {
		COUNTRYNAME = cOUNTRYNAME;
	}

	public int getGMTOFFSET() {
		return GMTOFFSET;
	}

	public void setGMTOFFSET(int gMTOFFSET) {
		GMTOFFSET = gMTOFFSET;
	}

	public int getCOUNTRYSTATUS() {
		return COUNTRYSTATUS;
	}

	public void setCOUNTRYSTATUS(int cOUNTRYSTATUS) {
		COUNTRYSTATUS = cOUNTRYSTATUS;
	}

	public Date getRECORDDATE() {
		return RECORDDATE;
	}

	public void setRECORDDATE(Date rECORDDATE) {
		RECORDDATE = rECORDDATE;
	}

}