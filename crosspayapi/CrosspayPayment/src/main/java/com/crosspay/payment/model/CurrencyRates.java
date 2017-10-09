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
@Table(name = "currencyrates")
public class CurrencyRates {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int CURRENCYRATE_ID;

	@NotNull
	private String INSTRUMENT_TYPE;

	@Null
	private String CCYCODEFROM;

	@Null
	private String CCYCODETO;

	@NotNull
	private double XCHGRATE;

	@NotNull
	private double XCHGRATEHIGH;

	@NotNull
	private double XCHGRATELOW;

	@Null
	private String RATESETBYUSERID;

	@NotNull
	private Date RECORDDATE;

	// Public methods
	// Getter and setter methods

	public int getCURRENCYRATE_ID() {
		return CURRENCYRATE_ID;
	}

	public void setCURRENCYRATE_ID(int cURRENCYRATE_ID) {
		CURRENCYRATE_ID = cURRENCYRATE_ID;
	}

	public String getINSTRUMENT_TYPE() {
		return INSTRUMENT_TYPE;
	}

	public void setINSTRUMENT_TYPE(String iNSTRUMENT_TYPE) {
		INSTRUMENT_TYPE = iNSTRUMENT_TYPE;
	}

	public String getCCYCODEFROM() {
		return CCYCODEFROM;
	}

	public void setCCYCODEFROM(String cCYCODEFROM) {
		CCYCODEFROM = cCYCODEFROM;
	}

	public String getCCYCODETO() {
		return CCYCODETO;
	}

	public void setCCYCODETO(String cCYCODETO) {
		CCYCODETO = cCYCODETO;
	}

	public double getXCHGRATE() {
		return XCHGRATE;
	}

	public void setXCHGRATE(double xCHGRATE) {
		XCHGRATE = xCHGRATE;
	}

	public double getXCHGRATEHIGH() {
		return XCHGRATEHIGH;
	}

	public void setXCHGRATEHIGH(double xCHGRATEHIGH) {
		XCHGRATEHIGH = xCHGRATEHIGH;
	}

	public double getXCHGRATELOW() {
		return XCHGRATELOW;
	}

	public void setXCHGRATELOW(double xCHGRATELOW) {
		XCHGRATELOW = xCHGRATELOW;
	}

	public String getRATESETBYUSERID() {
		return RATESETBYUSERID;
	}

	public void setRATESETBYUSERID(String rATESETBYUSERID) {
		RATESETBYUSERID = rATESETBYUSERID;
	}

	public Date getRECORDDATE() {
		return RECORDDATE;
	}

	public void setRECORDDATE(Date rECORDDATE) {
		RECORDDATE = rECORDDATE;
	}

}