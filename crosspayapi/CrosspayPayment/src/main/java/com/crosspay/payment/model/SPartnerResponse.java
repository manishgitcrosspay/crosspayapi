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
@Table(name = "SPARTNERRESPONSE")
public class SPartnerResponse {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int SPARTERRESPONSEID;
		
		@Null
		@Column(length = 10)
		private String SPARTERID;
		
		@Null
		@Column(length = 120)
		private String ERRORMESSAGE;
		
		@Null
		private Date TXNSTARTTIMESTAMP;
		
		@NotNull
		@Column(length = 15)
		private String PARENTTXNREF;
		
		@Null
		private int LIVESTATUS;
		
		@Null
		@Column(length = 60)
		private String ISSUER;
		
		@NotNull
		private int SPLITFINALNO;
		
		@Null
		@Column(length = 60)
		private String XID;
		
		@Null
		private int DCCENABLED;
		
		@Null
		private Date SETTLEDDUEDATE;
		
		@NotNull
		private int ERRORCODE;
		
		@Null
		@Column(precision=10)
		private double TID;
		
		@Null
		@Column(length = 16)
		private String MERCHANTNO;
		
		@Null
		@Column(length = 2)
		private String MERCHANTCOUNTRYCODE;
		
		@Null
		@Column(length = 1)
		private String STATUS;
		
		@Null
		@Column(length = 15)
		private String TXNREF;
		
		@Null
		@Column(length = 60)
		private String MERCHANTNAME;
		
		@Null
		@Column(length = 30)
		private String PAYMENTTYPEDESC;
		
		@Null
		@Column(precision=15, scale=3)
		private double BASEAMOUNT;
		
		@Null
		@Column(length = 1)
		private String ENROLLED;
		
		@Null
		@Column(length = 2)
		private String ECI;
		
		@Null
		@Column(length = 10)
		private String ACCOUNTTYPEDESC;
		
		@Null
		@Column(length = 60)
		private String CAVV;
		
		@Null
		@Column(length = 2)
		private String ACQUIRERRESPONSECODE;
		
		@Null
		@Column(length = 10)
		private String REQUESTTYPEDESC;
		
		@Null
		private int SECURITYRESPONSESECCODE;
		
		@Null
		@Column(length = 3)
		private String CURRECY;
		
		@Null
		@Column(length = 15)
		private String AUTHCODE;
		
		@Null
		@Column(length = 15)
		private String AUTHMETHOD;
		
		@Null
		@Column(length = 60)
		private String OPERATORNAME;
		
		@Null
		private int SECURITYRESPONSEPOSTCODE;
		
		@Null
		@Column(length = 60)
		private String MASKEDPAN;
		
		@Null
		private int SECURITYRESPONSEADDRESS;
		
		@Null
		@Column(length = 2)
		private String ISSUERCOUNTRYCODE;
		
		@Null
		private int SETTLESTATUS;
		
		@Null
		@Column(length = 60)
		private String ORDERID;

		public int getSPARTERRESPONSEID() {
			return SPARTERRESPONSEID;
		}

		public void setSPARTERRESPONSEID(int sPARTERRESPONSEID) {
			SPARTERRESPONSEID = sPARTERRESPONSEID;
		}

		public String getSPARTERID() {
			return SPARTERID;
		}

		public void setSPARTERID(String sPARTERID) {
			SPARTERID = sPARTERID;
		}

		public String getERRORMESSAGE() {
			return ERRORMESSAGE;
		}

		public void setERRORMESSAGE(String eRRORMESSAGE) {
			ERRORMESSAGE = eRRORMESSAGE;
		}

		public Date getTXNSTARTTIMESTAMP() {
			return TXNSTARTTIMESTAMP;
		}

		public void setTXNSTARTTIMESTAMP(Date tXNSTARTTIMESTAMP) {
			TXNSTARTTIMESTAMP = tXNSTARTTIMESTAMP;
		}

		public String getPARENTTXNREF() {
			return PARENTTXNREF;
		}

		public void setPARENTTXNREF(String pARENTTXNREF) {
			PARENTTXNREF = pARENTTXNREF;
		}

		public int getLIVESTATUS() {
			return LIVESTATUS;
		}

		public void setLIVESTATUS(int lIVESTATUS) {
			LIVESTATUS = lIVESTATUS;
		}

		public String getISSUER() {
			return ISSUER;
		}

		public void setISSUER(String iSSUER) {
			ISSUER = iSSUER;
		}

		public int getSPLITFINALNO() {
			return SPLITFINALNO;
		}

		public void setSPLITFINALNO(int sPLITFINALNO) {
			SPLITFINALNO = sPLITFINALNO;
		}

		public String getXID() {
			return XID;
		}

		public void setXID(String xID) {
			XID = xID;
		}

		public int getDCCENABLED() {
			return DCCENABLED;
		}

		public void setDCCENABLED(int dCCENABLED) {
			DCCENABLED = dCCENABLED;
		}

		public Date getSETTLEDDUEDATE() {
			return SETTLEDDUEDATE;
		}

		public void setSETTLEDDUEDATE(Date sETTLEDDUEDATE) {
			SETTLEDDUEDATE = sETTLEDDUEDATE;
		}

		public int getERRORCODE() {
			return ERRORCODE;
		}

		public void setERRORCODE(int eRRORCODE) {
			ERRORCODE = eRRORCODE;
		}

		public double getTID() {
			return TID;
		}

		public void setTID(double tID) {
			TID = tID;
		}

		public String getMERCHANTNO() {
			return MERCHANTNO;
		}

		public void setMERCHANTNO(String mERCHANTNO) {
			MERCHANTNO = mERCHANTNO;
		}

		public String getMERCHANTCOUNTRYCODE() {
			return MERCHANTCOUNTRYCODE;
		}

		public void setMERCHANTCOUNTRYCODE(String mERCHANTCOUNTRYCODE) {
			MERCHANTCOUNTRYCODE = mERCHANTCOUNTRYCODE;
		}

		public String getSTATUS() {
			return STATUS;
		}

		public void setSTATUS(String sTATUS) {
			STATUS = sTATUS;
		}

		public String getTXNREF() {
			return TXNREF;
		}

		public void setTXNREF(String tXNREF) {
			TXNREF = tXNREF;
		}

		public String getMERCHANTNAME() {
			return MERCHANTNAME;
		}

		public void setMERCHANTNAME(String mERCHANTNAME) {
			MERCHANTNAME = mERCHANTNAME;
		}

		public String getPAYMENTTYPEDESC() {
			return PAYMENTTYPEDESC;
		}

		public void setPAYMENTTYPEDESC(String pAYMENTTYPEDESC) {
			PAYMENTTYPEDESC = pAYMENTTYPEDESC;
		}

		public double getBASEAMOUNT() {
			return BASEAMOUNT;
		}

		public void setBASEAMOUNT(double bASEAMOUNT) {
			BASEAMOUNT = bASEAMOUNT;
		}

		public String getENROLLED() {
			return ENROLLED;
		}

		public void setENROLLED(String eNROLLED) {
			ENROLLED = eNROLLED;
		}

		public String getECI() {
			return ECI;
		}

		public void setECI(String eCI) {
			ECI = eCI;
		}

		public String getACCOUNTTYPEDESC() {
			return ACCOUNTTYPEDESC;
		}

		public void setACCOUNTTYPEDESC(String aCCOUNTTYPEDESC) {
			ACCOUNTTYPEDESC = aCCOUNTTYPEDESC;
		}

		public String getCAVV() {
			return CAVV;
		}

		public void setCAVV(String cAVV) {
			CAVV = cAVV;
		}

		public String getACQUIRERRESPONSECODE() {
			return ACQUIRERRESPONSECODE;
		}

		public void setACQUIRERRESPONSECODE(String aCQUIRERRESPONSECODE) {
			ACQUIRERRESPONSECODE = aCQUIRERRESPONSECODE;
		}

		public String getREQUESTTYPEDESC() {
			return REQUESTTYPEDESC;
		}

		public void setREQUESTTYPEDESC(String rEQUESTTYPEDESC) {
			REQUESTTYPEDESC = rEQUESTTYPEDESC;
		}

		public int getSECURITYRESPONSESECCODE() {
			return SECURITYRESPONSESECCODE;
		}

		public void setSECURITYRESPONSESECCODE(int sECURITYRESPONSESECCODE) {
			SECURITYRESPONSESECCODE = sECURITYRESPONSESECCODE;
		}

		public String getCURRECY() {
			return CURRECY;
		}

		public void setCURRECY(String cURRECY) {
			CURRECY = cURRECY;
		}

		public String getAUTHCODE() {
			return AUTHCODE;
		}

		public void setAUTHCODE(String aUTHCODE) {
			AUTHCODE = aUTHCODE;
		}

		public String getAUTHMETHOD() {
			return AUTHMETHOD;
		}

		public void setAUTHMETHOD(String aUTHMETHOD) {
			AUTHMETHOD = aUTHMETHOD;
		}

		public String getOPERATORNAME() {
			return OPERATORNAME;
		}

		public void setOPERATORNAME(String oPERATORNAME) {
			OPERATORNAME = oPERATORNAME;
		}

		public int getSECURITYRESPONSEPOSTCODE() {
			return SECURITYRESPONSEPOSTCODE;
		}

		public void setSECURITYRESPONSEPOSTCODE(int sECURITYRESPONSEPOSTCODE) {
			SECURITYRESPONSEPOSTCODE = sECURITYRESPONSEPOSTCODE;
		}

		public String getMASKEDPAN() {
			return MASKEDPAN;
		}

		public void setMASKEDPAN(String mASKEDPAN) {
			MASKEDPAN = mASKEDPAN;
		}

		public int getSECURITYRESPONSEADDRESS() {
			return SECURITYRESPONSEADDRESS;
		}

		public void setSECURITYRESPONSEADDRESS(int sECURITYRESPONSEADDRESS) {
			SECURITYRESPONSEADDRESS = sECURITYRESPONSEADDRESS;
		}

		public String getISSUERCOUNTRYCODE() {
			return ISSUERCOUNTRYCODE;
		}

		public void setISSUERCOUNTRYCODE(String iSSUERCOUNTRYCODE) {
			ISSUERCOUNTRYCODE = iSSUERCOUNTRYCODE;
		}

		public int getSETTLESTATUS() {
			return SETTLESTATUS;
		}

		public void setSETTLESTATUS(int sETTLESTATUS) {
			SETTLESTATUS = sETTLESTATUS;
		}

		public String getORDERID() {
			return ORDERID;
		}

		public void setORDERID(String oRDERID) {
			ORDERID = oRDERID;
		}
		
		
	
	
}
