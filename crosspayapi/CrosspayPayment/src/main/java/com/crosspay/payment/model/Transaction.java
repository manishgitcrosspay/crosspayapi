/**
 * 
 */
package com.crosspay.payment.model;

import java.util.Date;

public class Transaction {

	private String TxnNo;
	private String PartnerRefNo;
	private String CustomerNo;
	private Date TxnDate;
	private Date ValueDate;
	private String PayinccyCode;
	private String AccountCcyCode;
	private String PaymentMode;
	private int TxnPayTxnPayin2AccountCcyRate;
	private int IBnPayTxnPayin2AccountCcyRate;
	private int TransferAmount;
	private int PayinAmount;
	private int Commission;
	private int TotalPayinAmount;
	
	private String DraweeBankCode;
	private int TxnStatus;
	private Date CreatedOn;
	private String CreatedBy;
	private Date TxnGMTDate;
	private Date ModifiedOn;
	private String ModifiedBy;
	private Date AuthorisedOn;
	private String AuthorisedBy;
	private Date CreditedDate;
	private String SAuthReference;
	private String SPartnerID;
	public String getTxnNo() {
		return TxnNo;
	}
	public void setTxnNo(String txnNo) {
		TxnNo = txnNo;
	}
	public String getPartnerRefNo() {
		return PartnerRefNo;
	}
	public void setPartnerRefNo(String partnerRefNo) {
		PartnerRefNo = partnerRefNo;
	}
	public String getCustomerNo() {
		return CustomerNo;
	}
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}
	public Date getTxnDate() {
		return TxnDate;
	}
	public void setTxnDate(Date txnDate) {
		TxnDate = txnDate;
	}
	public Date getValueDate() {
		return ValueDate;
	}
	public void setValueDate(Date valueDate) {
		ValueDate = valueDate;
	}
	public String getPayinccyCode() {
		return PayinccyCode;
	}
	public void setPayinccyCode(String payinccyCode) {
		PayinccyCode = payinccyCode;
	}
	public String getAccountCcyCode() {
		return AccountCcyCode;
	}
	public void setAccountCcyCode(String accountCcyCode) {
		AccountCcyCode = accountCcyCode;
	}
	public String getPaymentMode() {
		return PaymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}
	public int getTxnPayTxnPayin2AccountCcyRate() {
		return TxnPayTxnPayin2AccountCcyRate;
	}
	public void setTxnPayTxnPayin2AccountCcyRate(int txnPayTxnPayin2AccountCcyRate) {
		TxnPayTxnPayin2AccountCcyRate = txnPayTxnPayin2AccountCcyRate;
	}
	public int getIBnPayTxnPayin2AccountCcyRate() {
		return IBnPayTxnPayin2AccountCcyRate;
	}
	public void setIBnPayTxnPayin2AccountCcyRate(int iBnPayTxnPayin2AccountCcyRate) {
		IBnPayTxnPayin2AccountCcyRate = iBnPayTxnPayin2AccountCcyRate;
	}
	public int getTransferAmount() {
		return TransferAmount;
	}
	public void setTransferAmount(int transferAmount) {
		TransferAmount = transferAmount;
	}
	public int getPayinAmount() {
		return PayinAmount;
	}
	public void setPayinAmount(int payinAmount) {
		PayinAmount = payinAmount;
	}
	public int getCommission() {
		return Commission;
	}
	public void setCommission(int commission) {
		Commission = commission;
	}
	public int getTotalPayinAmount() {
		return TotalPayinAmount;
	}
	public void setTotalPayinAmount(int totalPayinAmount) {
		TotalPayinAmount = totalPayinAmount;
	}
	public String getDraweeBankCode() {
		return DraweeBankCode;
	}
	public void setDraweeBankCode(String draweeBankCode) {
		DraweeBankCode = draweeBankCode;
	}
	public int getTxnStatus() {
		return TxnStatus;
	}
	public void setTxnStatus(int txnStatus) {
		TxnStatus = txnStatus;
	}
	public Date getCreatedOn() {
		return CreatedOn;
	}
	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public Date getTxnGMTDate() {
		return TxnGMTDate;
	}
	public void setTxnGMTDate(Date txnGMTDate) {
		TxnGMTDate = txnGMTDate;
	}
	public Date getModifiedOn() {
		return ModifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		ModifiedOn = modifiedOn;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public Date getAuthorisedOn() {
		return AuthorisedOn;
	}
	public void setAuthorisedOn(Date authorisedOn) {
		AuthorisedOn = authorisedOn;
	}
	public String getAuthorisedBy() {
		return AuthorisedBy;
	}
	public void setAuthorisedBy(String authorisedBy) {
		AuthorisedBy = authorisedBy;
	}
	public Date getCreditedDate() {
		return CreditedDate;
	}
	public void setCreditedDate(Date creditedDate) {
		CreditedDate = creditedDate;
	}
	public String getSAuthReference() {
		return SAuthReference;
	}
	public void setSAuthReference(String sAuthReference) {
		SAuthReference = sAuthReference;
	}
	public String getSPartnerID() {
		return SPartnerID;
	}
	public void setSPartnerID(String sPartnerID) {
		SPartnerID = sPartnerID;
	}
	
	
}
