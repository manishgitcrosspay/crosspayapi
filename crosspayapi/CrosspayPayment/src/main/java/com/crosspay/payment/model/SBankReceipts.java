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
@Table(name = "SBANKRECEIPTS")
public class SBankReceipts {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int SBANKRECEIPTSID;

	@Null
	@Column(length = 10)
	private String SPARTNERID;

	@Null
	@Column(length = 2)
	private String TXNTYPE;

	@Null
	@Column(length = 16)
	private String TXNNO;

	@Null
	@Column(length = 16)
	private String CUSTOMERNO;

	@Null
	private Date TXNDATE;

	@Null
	@Column(precision = 15, scale = 3)
	private double AMOUNTRECEIVED;

	@Null
	@Column(length = 3)
	private String AMOUNTCCYCODE;

	@Null
	private Date AMOUNTRECEIVEDON;

	@Null
	@Column(length = 16)
	private String BANKREFERENCENO;

	@Null
	@Column(length = 30)
	private String CREDITVERIFIEDBYUSER;

	@Null
	private Date CREDITVERIFIEDON;

	@NotNull
	private int RECORDSTATUS;

	// Public methods
	// Getter and setter methods

	public int getSBANKRECEIPTSID() {
		return SBANKRECEIPTSID;
	}

	public void setSBANKRECEIPTSID(int sBANKRECEIPTSID) {
		SBANKRECEIPTSID = sBANKRECEIPTSID;
	}

	public String getSPARTNERID() {
		return SPARTNERID;
	}

	public void setSPARTNERID(String sPARTNERID) {
		SPARTNERID = sPARTNERID;
	}

	public String getTXNTYPE() {
		return TXNTYPE;
	}

	public void setTXNTYPE(String tXNTYPE) {
		TXNTYPE = tXNTYPE;
	}

	public String getTXNNO() {
		return TXNNO;
	}

	public void setTXNNO(String tXNNO) {
		TXNNO = tXNNO;
	}

	public String getCUSTOMERNO() {
		return CUSTOMERNO;
	}

	public void setCUSTOMERNO(String cUSTOMERNO) {
		CUSTOMERNO = cUSTOMERNO;
	}

	public Date getTXNDATE() {
		return TXNDATE;
	}

	public void setTXNDATE(Date tXNDATE) {
		TXNDATE = tXNDATE;
	}

	public double getAMOUNTRECEIVED() {
		return AMOUNTRECEIVED;
	}

	public void setAMOUNTRECEIVED(double aMOUNTRECEIVED) {
		AMOUNTRECEIVED = aMOUNTRECEIVED;
	}

	public String getAMOUNTCCYCODE() {
		return AMOUNTCCYCODE;
	}

	public void setAMOUNTCCYCODE(String aMOUNTCCYCODE) {
		AMOUNTCCYCODE = aMOUNTCCYCODE;
	}

	public Date getAMOUNTRECEIVEDON() {
		return AMOUNTRECEIVEDON;
	}

	public void setAMOUNTRECEIVEDON(Date aMOUNTRECEIVEDON) {
		AMOUNTRECEIVEDON = aMOUNTRECEIVEDON;
	}

	public String getBANKREFERENCENO() {
		return BANKREFERENCENO;
	}

	public void setBANKREFERENCENO(String bANKREFERENCENO) {
		BANKREFERENCENO = bANKREFERENCENO;
	}

	public String getCREDITVERIFIEDBYUSER() {
		return CREDITVERIFIEDBYUSER;
	}

	public void setCREDITVERIFIEDBYUSER(String cREDITVERIFIEDBYUSER) {
		CREDITVERIFIEDBYUSER = cREDITVERIFIEDBYUSER;
	}

	public Date getCREDITVERIFIEDON() {
		return CREDITVERIFIEDON;
	}

	public void setCREDITVERIFIEDON(Date cREDITVERIFIEDON) {
		CREDITVERIFIEDON = cREDITVERIFIEDON;
	}

	public int getRECORDSTATUS() {
		return RECORDSTATUS;
	}

	public void setRECORDSTATUS(int rECORDSTATUS) {
		RECORDSTATUS = rECORDSTATUS;
	}

}