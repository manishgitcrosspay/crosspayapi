package com.crosspay.payment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "BANKS")
public class Banks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int BANK_ID;

	@NotNull
	private String BANKCODE;

	@Null
	private String BANKNAME;

	@Null
	private String BANKCOUNTRYCODE;

	@NotNull
	private String BANKCITY;

	@NotNull
	private String PLACE;

	@Null
	private String PHONE;

	@NotNull
	private String FAX;

	@Null
	private String EMAIL;

	@NotNull
	private String ADDRESS1;

	@Null
	private String ADDRESS2;

	@Null
	private String SECCODE1;

	@NotNull
	private String SECCODE2;

	@Null
	private String LANGCODE;

	@NotNull
	private Date CREATEDON;

	@Null
	private String CREATEDBY;

	@Null
	private String LASTMODBY;

	@NotNull
	private Date LASMODDATE;

	@Null
	private String ACTIVE;

	@Null
	private String BOCCODE;

	@NotNull
	private int ACFORMAT;

	public String getBANKCODE() {
		return BANKCODE;
	}

	public void setBANKCODE(String bANKCODE) {
		BANKCODE = bANKCODE;
	}

	public String getBANKNAME() {
		return BANKNAME;
	}

	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}

	public String getBANKCOUNTRYCODE() {
		return BANKCOUNTRYCODE;
	}

	public void setBANKCOUNTRYCODE(String bANKCOUNTRYCODE) {
		BANKCOUNTRYCODE = bANKCOUNTRYCODE;
	}

	public String getBANKCITY() {
		return BANKCITY;
	}

	public void setBANKCITY(String bANKCITY) {
		BANKCITY = bANKCITY;
	}

	public String getPLACE() {
		return PLACE;
	}

	public void setPLACE(String pLACE) {
		PLACE = pLACE;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getADDRESS1() {
		return ADDRESS1;
	}

	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}

	public String getADDRESS2() {
		return ADDRESS2;
	}

	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}

	public String getSECCODE1() {
		return SECCODE1;
	}

	public void setSECCODE1(String sECCODE1) {
		SECCODE1 = sECCODE1;
	}

	public String getSECCODE2() {
		return SECCODE2;
	}

	public void setSECCODE2(String sECCODE2) {
		SECCODE2 = sECCODE2;
	}

	public String getLANGCODE() {
		return LANGCODE;
	}

	public void setLANGCODE(String lANGCODE) {
		LANGCODE = lANGCODE;
	}

	public Date getCREATEDON() {
		return CREATEDON;
	}

	public void setCREATEDON(Date cREATEDON) {
		CREATEDON = cREATEDON;
	}

	public String getCREATEDBY() {
		return CREATEDBY;
	}

	public void setCREATEDBY(String cREATEDBY) {
		CREATEDBY = cREATEDBY;
	}

	public String getLASTMODBY() {
		return LASTMODBY;
	}

	public void setLASTMODBY(String lASTMODBY) {
		LASTMODBY = lASTMODBY;
	}

	public Date getLASMODDATE() {
		return LASMODDATE;
	}

	public void setLASMODDATE(Date lASMODDATE) {
		LASMODDATE = lASMODDATE;
	}

	public String getACTIVE() {
		return ACTIVE;
	}

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	public String getBOCCODE() {
		return BOCCODE;
	}

	public void setBOCCODE(String bOCCODE) {
		BOCCODE = bOCCODE;
	}

	public int getACFORMAT() {
		return ACFORMAT;
	}

	public void setACFORMAT(int aCFORMAT) {
		ACFORMAT = aCFORMAT;
	}

	public int getBANK_ID() {
		return BANK_ID;
	}

	public void setBANK_ID(int bANK_ID) {
		BANK_ID = bANK_ID;
	}
	
	

}
