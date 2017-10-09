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
@Table(name = "BENEFICIARYIDS")
public class BeneficiaryIds {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int BENEFICIARYIDS_ID;

	@NotNull
	private String CUSTOMERNO;

	@NotNull
	private int BENEFICIARYNO;

	@NotNull
	private int BENEFICIARYIDTYPE;

	@Null
	private String BENEFICIARYIDOTHERTYPE;

	@Null
	private String BENEFICIARYID;

	@Null
	private String BENEFICIARYIDISSUEDBY;

	@Null
	private String BENEFICIARYIDISSUEDAT;

	@NotNull
	private Date BENEFICIARYIDISSUEDATE;

	@NotNull
	private Date BENEFICIARYIDVALIDTHRU;

	@Null
	private String BENEFICIARYIDTYPEDESC;

	@NotNull
	private Date CREATEDON;

	@NotNull
	private int BENEFICIARYIDSTATUS;

	// Public methods
	// Getter and setter methods

	public int getBENEFICIARYIDS_ID() {
		return BENEFICIARYIDS_ID;
	}

	public void setBENEFICIARYIDS_ID(int bENEFICIARYIDS_ID) {
		BENEFICIARYIDS_ID = bENEFICIARYIDS_ID;
	}

	public String getCUSTOMERNO() {
		return CUSTOMERNO;
	}

	public void setCUSTOMERNO(String cUSTOMERNO) {
		CUSTOMERNO = cUSTOMERNO;
	}

	public int getBENEFICIARYNO() {
		return BENEFICIARYNO;
	}

	public void setBENEFICIARYNO(int bENEFICIARYNO) {
		BENEFICIARYNO = bENEFICIARYNO;
	}

	public int getBENEFICIARYIDTYPE() {
		return BENEFICIARYIDTYPE;
	}

	public void setBENEFICIARYIDTYPE(int bENEFICIARYIDTYPE) {
		BENEFICIARYIDTYPE = bENEFICIARYIDTYPE;
	}

	public String getBENEFICIARYIDOTHERTYPE() {
		return BENEFICIARYIDOTHERTYPE;
	}

	public void setBENEFICIARYIDOTHERTYPE(String bENEFICIARYIDOTHERTYPE) {
		BENEFICIARYIDOTHERTYPE = bENEFICIARYIDOTHERTYPE;
	}

	public String getBENEFICIARYID() {
		return BENEFICIARYID;
	}

	public void setBENEFICIARYID(String bENEFICIARYID) {
		BENEFICIARYID = bENEFICIARYID;
	}

	public String getBENEFICIARYIDISSUEDBY() {
		return BENEFICIARYIDISSUEDBY;
	}

	public void setBENEFICIARYIDISSUEDBY(String bENEFICIARYIDISSUEDBY) {
		BENEFICIARYIDISSUEDBY = bENEFICIARYIDISSUEDBY;
	}

	public String getBENEFICIARYIDISSUEDAT() {
		return BENEFICIARYIDISSUEDAT;
	}

	public void setBENEFICIARYIDISSUEDAT(String bENEFICIARYIDISSUEDAT) {
		BENEFICIARYIDISSUEDAT = bENEFICIARYIDISSUEDAT;
	}

	public Date getBENEFICIARYIDISSUEDATE() {
		return BENEFICIARYIDISSUEDATE;
	}

	public void setBENEFICIARYIDISSUEDATE(Date bENEFICIARYIDISSUEDATE) {
		BENEFICIARYIDISSUEDATE = bENEFICIARYIDISSUEDATE;
	}

	public Date getBENEFICIARYIDVALIDTHRU() {
		return BENEFICIARYIDVALIDTHRU;
	}

	public void setBENEFICIARYIDVALIDTHRU(Date bENEFICIARYIDVALIDTHRU) {
		BENEFICIARYIDVALIDTHRU = bENEFICIARYIDVALIDTHRU;
	}

	public String getBENEFICIARYIDTYPEDESC() {
		return BENEFICIARYIDTYPEDESC;
	}

	public void setBENEFICIARYIDTYPEDESC(String bENEFICIARYIDTYPEDESC) {
		BENEFICIARYIDTYPEDESC = bENEFICIARYIDTYPEDESC;
	}

	public Date getCREATEDON() {
		return CREATEDON;
	}

	public void setCREATEDON(Date cREATEDON) {
		CREATEDON = cREATEDON;
	}

	public int getBENEFICIARYIDSTATUS() {
		return BENEFICIARYIDSTATUS;
	}

	public void setBENEFICIARYIDSTATUS(int bENEFICIARYIDSTATUS) {
		BENEFICIARYIDSTATUS = bENEFICIARYIDSTATUS;
	}

}