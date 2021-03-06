package com.crosspay.payment.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
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
@Table(name = "LOGINCREDENTIALSHISTORY")
public class LoginCredentialHistory {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int LOGINCREDENTIALSHISTORY_ID;

	@NotNull
	private String CUSTOMERNO;

	@Null
	private int PASSWORDNO;

	@NotNull
	private String PASSWORD;

	@NotNull
	private Date PASSWORDCREATEDON;

	@Null
	private Date PASSWORDEXPIREDON;

	@Null
	private String PASSWORDCHANGEDBY;

	@Null
	private String PASSWORDCHANGEREASON;

	@Null
	private String PASSWORDCHANGECLIENT;

	@NotNull
	private int USERCATEGORY;

	@Null
	private Timestamp TIMESTAMP;

	@Null
	private String OTPUSEDFORCHANGE;

	// Public methods
	// Getter and setter methods

	public int getLOGINCREDENTIALSHISTORY_ID() {
		return LOGINCREDENTIALSHISTORY_ID;
	}

	public void setLOGINCREDENTIALSHISTORY_ID(int lOGINCREDENTIALSHISTORY_ID) {
		LOGINCREDENTIALSHISTORY_ID = lOGINCREDENTIALSHISTORY_ID;
	}

	public String getCUSTOMERNO() {
		return CUSTOMERNO;
	}

	public void setCUSTOMERNO(String cUSTOMERNO) {
		CUSTOMERNO = cUSTOMERNO;
	}

	public int getPASSWORDNO() {
		return PASSWORDNO;
	}

	public void setPASSWORDNO(int pASSWORDNO) {
		PASSWORDNO = pASSWORDNO;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public Date getPASSWORDCREATEDON() {
		return PASSWORDCREATEDON;
	}

	public void setPASSWORDCREATEDON(Date pASSWORDCREATEDON) {
		PASSWORDCREATEDON = pASSWORDCREATEDON;
	}

	public Date getPASSWORDEXPIREDON() {
		return PASSWORDEXPIREDON;
	}

	public void setPASSWORDEXPIREDON(Date pASSWORDEXPIREDON) {
		PASSWORDEXPIREDON = pASSWORDEXPIREDON;
	}

	public String getPASSWORDCHANGEDBY() {
		return PASSWORDCHANGEDBY;
	}

	public void setPASSWORDCHANGEDBY(String pASSWORDCHANGEDBY) {
		PASSWORDCHANGEDBY = pASSWORDCHANGEDBY;
	}

	public String getPASSWORDCHANGEREASON() {
		return PASSWORDCHANGEREASON;
	}

	public void setPASSWORDCHANGEREASON(String pASSWORDCHANGEREASON) {
		PASSWORDCHANGEREASON = pASSWORDCHANGEREASON;
	}

	public String getPASSWORDCHANGECLIENT() {
		return PASSWORDCHANGECLIENT;
	}

	public void setPASSWORDCHANGECLIENT(String pASSWORDCHANGECLIENT) {
		PASSWORDCHANGECLIENT = pASSWORDCHANGECLIENT;
	}

	public int getUSERCATEGORY() {
		return USERCATEGORY;
	}

	public void setUSERCATEGORY(int uSERCATEGORY) {
		USERCATEGORY = uSERCATEGORY;
	}

	public Timestamp getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Timestamp tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}

	public String getOTPUSEDFORCHANGE() {
		return OTPUSEDFORCHANGE;
	}

	public void setOTPUSEDFORCHANGE(String oTPUSEDFORCHANGE) {
		OTPUSEDFORCHANGE = oTPUSEDFORCHANGE;
	}

}