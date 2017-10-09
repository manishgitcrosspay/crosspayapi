package com.crosspay.payment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "CASHPAYOUT")
public class CashPayment {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int CASHPAYOUTID;
		
		@Null
		@Column(length = 16)
		private String TXNNO;
		
		@Null
		@Column(length = 16)
		private String PARTNERREFNO;
		
		@NotNull
		@Column(length = 16)
		private String CUSTOMERNO;
		
		@NotNull
		private Date TXNDATE;
		
		@Null
		private Date VALUEDATE;
		
		@NotNull
		@Column(length = 3)
		private String PAYINCCCODE;
		
		@NotNull
		@Column(length = 3	)
		private String ACCOUNTCCYCODE;
		
		@NotNull
		@Column(length = 1)
		private String PAYMENTMODE;
		
		@Null
		@Column(precision=15, scale=8)
		private double TXNPAYTXNPAYIN2ACCOUNTCCYRATE;
		
		@Null
		@Column(precision=15, scale=8)
		private double IBANPAYTXNPAYIN2ACCOUNTCCYRATE;
		
		@NotNull
		@Column(precision=15, scale=8)
		private double TRANSFERAMOUNT;
		
		@NotNull
		@Column(precision=15, scale=8)
		private double PAYINAMOUNT;
		
		@Null
		@Column(precision=15, scale=8)
		private double COMMISSION;
		
		@Null
		@Column(precision=15, scale=8)
		private double TOTALPAYINAMOUNT;
		
		@Null
		@Column(length = 12)
		private String AGENTCODE;
		
		@Null
		private int TXNSTATUS;
		
		@Null
		private Date CREATEDON;
		
		@Null
		@Column(length = 16)
		private String CREATEDBY;
		
		@Null
		private Date TXNGMTDATE;
		
		@Null
		private Date MODIFIEDON;
		
		@Null
		@Column(length = 16)
		private String MODIFIEDBY;
		
		@Null
		private Date AUTHORISEDON;
		
		@Null
		@Column(length = 16)
		private String AUTHORISEDBY;
		
		@Null
		private Date PAIDDATE;
		
		@Null
		@Column(length = 16)
		private String SAUTHREFERENCE;
		
		@Null
		@Column(length = 10)
		private String SPARTNERID;
		
		@NotNull
		private String ORDERID;
		

		public int getCASHPAYOUTID() {
			return CASHPAYOUTID;
		}

		public void setCASHPAYOUTID(int cASHPAYOUTID) {
			CASHPAYOUTID = cASHPAYOUTID;
		}

		public String getTXNNO() {
			return TXNNO;
		}

		public void setTXNNO(String tXNNO) {
			TXNNO = tXNNO;
		}

		public String getPARTNERREFNO() {
			return PARTNERREFNO;
		}

		public void setPARTNERREFNO(String pARTNERREFNO) {
			PARTNERREFNO = pARTNERREFNO;
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

		public Date getVALUEDATE() {
			return VALUEDATE;
		}

		public void setVALUEDATE(Date vALUEDATE) {
			VALUEDATE = vALUEDATE;
		}

		public String getPAYINCCCODE() {
			return PAYINCCCODE;
		}

		public void setPAYINCCCODE(String pAYINCCCODE) {
			PAYINCCCODE = pAYINCCCODE;
		}

		public String getACCOUNTCCYCODE() {
			return ACCOUNTCCYCODE;
		}

		public void setACCOUNTCCYCODE(String aCCOUNTCCYCODE) {
			ACCOUNTCCYCODE = aCCOUNTCCYCODE;
		}

		public String getPAYMENTMODE() {
			return PAYMENTMODE;
		}

		public void setPAYMENTMODE(String pAYMENTMODE) {
			PAYMENTMODE = pAYMENTMODE;
		}

		public double getTXNPAYTXNPAYIN2ACCOUNTCCYRATE() {
			return TXNPAYTXNPAYIN2ACCOUNTCCYRATE;
		}

		public void setTXNPAYTXNPAYIN2ACCOUNTCCYRATE(double tXNPAYTXNPAYIN2ACCOUNTCCYRATE) {
			TXNPAYTXNPAYIN2ACCOUNTCCYRATE = tXNPAYTXNPAYIN2ACCOUNTCCYRATE;
		}

		public double getIBANPAYTXNPAYIN2ACCOUNTCCYRATE() {
			return IBANPAYTXNPAYIN2ACCOUNTCCYRATE;
		}

		public void setIBANPAYTXNPAYIN2ACCOUNTCCYRATE(double iBANPAYTXNPAYIN2ACCOUNTCCYRATE) {
			IBANPAYTXNPAYIN2ACCOUNTCCYRATE = iBANPAYTXNPAYIN2ACCOUNTCCYRATE;
		}

		public double getTRANSFERAMOUNT() {
			return TRANSFERAMOUNT;
		}

		public void setTRANSFERAMOUNT(double tRANSFERAMOUNT) {
			TRANSFERAMOUNT = tRANSFERAMOUNT;
		}

		public double getPAYINAMOUNT() {
			return PAYINAMOUNT;
		}

		public void setPAYINAMOUNT(double pAYINAMOUNT) {
			PAYINAMOUNT = pAYINAMOUNT;
		}

		public double getCOMMISSION() {
			return COMMISSION;
		}

		public void setCOMMISSION(double cOMMISSION) {
			COMMISSION = cOMMISSION;
		}

		public double getTOTALPAYINAMOUNT() {
			return TOTALPAYINAMOUNT;
		}

		public void setTOTALPAYINAMOUNT(double tOTALPAYINAMOUNT) {
			TOTALPAYINAMOUNT = tOTALPAYINAMOUNT;
		}

		public String getAGENTCODE() {
			return AGENTCODE;
		}

		public void setAGENTCODE(String aGENTCODE) {
			AGENTCODE = aGENTCODE;
		}

		public int getTXNSTATUS() {
			return TXNSTATUS;
		}

		public void setTXNSTATUS(int tXNSTATUS) {
			TXNSTATUS = tXNSTATUS;
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

		public Date getTXNGMTDATE() {
			return TXNGMTDATE;
		}

		public void setTXNGMTDATE(Date tXNGMTDATE) {
			TXNGMTDATE = tXNGMTDATE;
		}

		public Date getMODIFIEDON() {
			return MODIFIEDON;
		}

		public void setMODIFIEDON(Date mODIFIEDON) {
			MODIFIEDON = mODIFIEDON;
		}

		public String getMODIFIEDBY() {
			return MODIFIEDBY;
		}

		public void setMODIFIEDBY(String mODIFIEDBY) {
			MODIFIEDBY = mODIFIEDBY;
		}

		public Date getAUTHORISEDON() {
			return AUTHORISEDON;
		}

		public void setAUTHORISEDON(Date aUTHORISEDON) {
			AUTHORISEDON = aUTHORISEDON;
		}

		public String getAUTHORISEDBY() {
			return AUTHORISEDBY;
		}

		public void setAUTHORISEDBY(String aUTHORISEDBY) {
			AUTHORISEDBY = aUTHORISEDBY;
		}

		public Date getPAIDDATE() {
			return PAIDDATE;
		}

		public void setPAIDDATE(Date pAIDDATE) {
			PAIDDATE = pAIDDATE;
		}

		public String getSAUTHREFERENCE() {
			return SAUTHREFERENCE;
		}

		public void setSAUTHREFERENCE(String sAUTHREFERENCE) {
			SAUTHREFERENCE = sAUTHREFERENCE;
		}

		public String getSPARTNERID() {
			return SPARTNERID;
		}

		public void setSPARTNERID(String sPARTNERID) {
			SPARTNERID = sPARTNERID;
		}

		public String getORDERID() {
			return ORDERID;
		}

		public void setORDERID(String oRDERID) {
			ORDERID = oRDERID;
		}
		
	
	
}
