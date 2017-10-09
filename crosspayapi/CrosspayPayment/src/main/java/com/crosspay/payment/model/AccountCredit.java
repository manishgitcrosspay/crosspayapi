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
@Table(name = "ACCOUNTCREDIT")
public class AccountCredit {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int TransactionId;
		
		@Null
		private String TxnNo;
		
		@Null
		private String PartnerRefNo;
		
		@NotNull
		private String CustomerNo;
		
		@NotNull
		private Date TxnDate;
		
		@Null
		private Date ValueDate;
		
		@NotNull
		private String PayinccyCode;
		
		@Null
		private String AccountCcyCode;
		
		@NotNull
		private String PaymentMode;
		
		@Null
		private double TxnPayTxnPayin2AccountCcyRate;
		
		@Null
		private double IBnPayTxnPayin2AccountCcyRate;
		
		@NotNull
		private float TransferAmount;
		
		@NotNull
		private double PayinAmount;
		
		@Null
		private int Commission;
		
		@Null
		private double TotalPayinAmount;
		
		@Null
		private String DraweeBankCode;
		
		@Null
		private int TxnStatus;
		
		@Null
		private Date CreatedOn;
		
		@Null
		private String CreatedBy;
		
		@Null
		private Date TxnGMTDate;
		
		@Null
		private Date ModifiedOn;
		
		@Null
		private String ModifiedBy;
		
		@Null
		private Date AuthorisedOn;
		
		@Null
		private String AuthorisedBy;
		
		@Null
		private Date CreditedDate;
		
		@Null
		private String SAuthReference;
		
		@Null
		private String SPartnerID;
		
		@NotNull
		private String orderId;
		
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
		public double getTxnPayTxnPayin2AccountCcyRate() {
			return TxnPayTxnPayin2AccountCcyRate;
		}
		public void setTxnPayTxnPayin2AccountCcyRate(double txnPayTxnPayin2AccountCcyRate) {
			TxnPayTxnPayin2AccountCcyRate = txnPayTxnPayin2AccountCcyRate;
		}
		public double getIBnPayTxnPayin2AccountCcyRate() {
			return IBnPayTxnPayin2AccountCcyRate;
		}
		public void setIBnPayTxnPayin2AccountCcyRate(double iBnPayTxnPayin2AccountCcyRate) {
			IBnPayTxnPayin2AccountCcyRate = iBnPayTxnPayin2AccountCcyRate;
		}
		public float getTransferAmount() {
			return TransferAmount;
		}
		public void setTransferAmount(float transferAmount) {
			TransferAmount = transferAmount;
		}
		public double getPayinAmount() {
			return PayinAmount;
		}
		public void setPayinAmount(double payinAmount) {
			PayinAmount = payinAmount;
		}
		public int getCommission() {
			return Commission;
		}
		public void setCommission(int commission) {
			Commission = commission;
		}
		public double getTotalPayinAmount() {
			return TotalPayinAmount;
		}
		public void setTotalPayinAmount(double totalPayinAmount) {
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
		public int getTransactionId() {
			return TransactionId;
		}
		public void setTransactionId(int transactionId) {
			TransactionId = transactionId;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		
	
	
}
