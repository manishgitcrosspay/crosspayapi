package com.crosspay.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "ACTRANSFERCHARGES")
public class ACTransferCharges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int TRANSFERCHARGESID;
	
	@Null
	@Column(length = 2)
	private String TRANSFERTYPE;
	
	@NotNull
	@Column(length = 2)
	private String PAYINCOUNTRYCODE;
	
	@NotNull
	@Column(length = 3)
	private String PAYINCCYCODE;
	
	@NotNull
	private int SLABSERIALNO;
	
	@NotNull
	@Column(precision = 15, scale = 3)
	private double PAYINAMOUNTFROM;
	
	@NotNull
	@Column(precision = 15, scale = 3)
	private double PAYINAMOUNTTO;
	
	@NotNull
	@Column(precision = 15, scale = 3)
	private double COMMISSION;

	@NotNull
	@Column(length = 3)
	private String COMMISSIONCCYCODE;

	public int getTRANSFERCHARGESID() {
		return TRANSFERCHARGESID;
	}

	public void setTRANSFERCHARGESID(int tRANSFERCHARGESID) {
		TRANSFERCHARGESID = tRANSFERCHARGESID;
	}

	public String getTRANSFERTYPE() {
		return TRANSFERTYPE;
	}

	public void setTRANSFERTYPE(String tRANSFERTYPE) {
		TRANSFERTYPE = tRANSFERTYPE;
	}

	public String getPAYINCOUNTRYCODE() {
		return PAYINCOUNTRYCODE;
	}

	public void setPAYINCOUNTRYCODE(String pAYINCOUNTRYCODE) {
		PAYINCOUNTRYCODE = pAYINCOUNTRYCODE;
	}

	public String getPAYINCCYCODE() {
		return PAYINCCYCODE;
	}

	public void setPAYINCCYCODE(String pAYINCCYCODE) {
		PAYINCCYCODE = pAYINCCYCODE;
	}

	public int getSLABSERIALNO() {
		return SLABSERIALNO;
	}

	public void setSLABSERIALNO(int sLABSERIALNO) {
		SLABSERIALNO = sLABSERIALNO;
	}

	public double getPAYINAMOUNTFROM() {
		return PAYINAMOUNTFROM;
	}

	public void setPAYINAMOUNTFROM(double pAYINAMOUNTFROM) {
		PAYINAMOUNTFROM = pAYINAMOUNTFROM;
	}

	public double getPAYINAMOUNTTO() {
		return PAYINAMOUNTTO;
	}

	public void setPAYINAMOUNTTO(double pAYINAMOUNTTO) {
		PAYINAMOUNTTO = pAYINAMOUNTTO;
	}

	public double getCOMMISSION() {
		return COMMISSION;
	}

	public void setCOMMISSION(double cOMMISSION) {
		COMMISSION = cOMMISSION;
	}

	public String getCOMMISSIONCCYCODE() {
		return COMMISSIONCCYCODE;
	}

	public void setCOMMISSIONCCYCODE(String cOMMISSIONCCYCODE) {
		COMMISSIONCCYCODE = cOMMISSIONCCYCODE;
	}
	
	
	
}
