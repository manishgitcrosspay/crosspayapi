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
@Table(name = "LOGINCREDENTIALS")
public class Login {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int LOGINCREDENTIALS_ID;

	@NotNull
	private String CUSTOMERNO;

	@NotNull
	private String CUSTOMEREMAIL;

	@NotNull
	private String CUSTOMERMOBILE;

	@Null
	private String PASSWORD;

	@NotNull
	private int USERCATEGORY;

	@NotNull
	private int RECORDSTATUS;

	@Null
	private Timestamp TIMESTAMP;

	// Public methods
	// Getter and setter methods

	public Login() {
	}

	public int getLOGINCREDENTIALS_ID() {
		return LOGINCREDENTIALS_ID;
	}

	public void setLOGINCREDENTIALS_ID(int lOGINCREDENTIALS_ID) {
		LOGINCREDENTIALS_ID = lOGINCREDENTIALS_ID;
	}

	public String getCUSTOMERNO() {
		return CUSTOMERNO;
	}

	public void setCUSTOMERNO(String cUSTOMERNO) {
		CUSTOMERNO = cUSTOMERNO;
	}

	public String getCUSTOMEREMAIL() {
		return CUSTOMEREMAIL;
	}

	public void setCUSTOMEREMAIL(String cUSTOMEREMAIL) {
		CUSTOMEREMAIL = cUSTOMEREMAIL;
	}

	public String getCUSTOMERMOBILE() {
		return CUSTOMERMOBILE;
	}

	public void setCUSTOMERMOBILE(String cUSTOMERMOBILE) {
		CUSTOMERMOBILE = cUSTOMERMOBILE;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public int getUSERCATEGORY() {
		return USERCATEGORY;
	}

	public void setUSERCATEGORY(int uSERCATEGORY) {
		USERCATEGORY = uSERCATEGORY;
	}

	public int getRECORDSTATUS() {
		return RECORDSTATUS;
	}

	public void setRECORDSTATUS(int rECORDSTATUS) {
		RECORDSTATUS = rECORDSTATUS;
	}

	public Timestamp getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Timestamp tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}

	public Login(int id) {
		this.LOGINCREDENTIALS_ID = id;
	}

}