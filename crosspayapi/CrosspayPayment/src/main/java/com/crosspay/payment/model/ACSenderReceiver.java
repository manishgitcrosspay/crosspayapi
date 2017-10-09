package com.crosspay.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "ACSenderReceiver")
public class ACSenderReceiver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ACSenderReceiverId;
	
	@Null
	private String TxnNo;
	
	@NotNull
	private String CustomerNo;
	
	@NotNull
	private int BeneficiaryNo;
	
	@NotNull
	private String BeneficiaryFirstName;	
	
	@Null
	private String BeneficiaryMiddleName;
	
	@NotNull
	private String BeneficiaryLastName;
	
	@Null
	private String BeneficiaryPOBox;
	
	@Null
	private String BeneficiaryAddress1;
	
	@Null
	private String BeneficiaryAddress2;
	
	@Null
	private String BeneficiaryAddressCity;
	
	@Null
	private String BeneficiaryAddressState;
	
	@Null
	private String BeneficiaryAddressCountryCode;
	
	@Null
	private String BeneficiaryAddressZip;
	
	@Null
	private String BeneficiaryPhone;
	
	@Null
	private String BeneficiaryMobile;
	
	@NotNull
	private String BeneficiaryEmail;
	
	@Null
	private String BeneficiaryAccountName;
	
	@Null
	private String BeneficiaryBankCode;
	
	@Null
	private String BeneficiaryBankName;
	
	@Null
	private String BeneficiaryBankBranchName;
	
	@NotNull
	private String BeneficiaryBankAccountNo;
	
	@Null
	private String SwiftIFSC;
	
	@Null
	private String BeneficiaryAccountCcyCode;
	
	@NotNull
	private String orderId;
	 
	public int getACSenderReceiverId() {
		return ACSenderReceiverId;
	}
	public void setACSenderReceiverId(int aCSenderReceiverId) {
		ACSenderReceiverId = aCSenderReceiverId;
	}
	public String getTxnNo() {
		return TxnNo;
	}
	public void setTxnNo(String txnNo) {
		TxnNo = txnNo;
	}
	public String getCustomerNo() {
		return CustomerNo;
	}
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}
	public int getBeneficiaryNo() {
		return BeneficiaryNo;
	}
	public void setBeneficiaryNo(int beneficiaryNo) {
		BeneficiaryNo = beneficiaryNo;
	}
	public String getBeneficiaryFirstName() {
		return BeneficiaryFirstName;
	}
	public void setBeneficiaryFirstName(String beneficiaryFirstName) {
		BeneficiaryFirstName = beneficiaryFirstName;
	}
	public String getBeneficiaryMiddleName() {
		return BeneficiaryMiddleName;
	}
	public void setBeneficiaryMiddleName(String beneficiaryMiddleName) {
		BeneficiaryMiddleName = beneficiaryMiddleName;
	}
	public String getBeneficiaryLastName() {
		return BeneficiaryLastName;
	}
	public void setBeneficiaryLastName(String beneficiaryLastName) {
		BeneficiaryLastName = beneficiaryLastName;
	}
	public String getBeneficiaryPOBox() {
		return BeneficiaryPOBox;
	}
	public void setBeneficiaryPOBox(String beneficiaryPOBox) {
		BeneficiaryPOBox = beneficiaryPOBox;
	}
	public String getBeneficiaryAddress1() {
		return BeneficiaryAddress1;
	}
	public void setBeneficiaryAddress1(String beneficiaryAddress1) {
		BeneficiaryAddress1 = beneficiaryAddress1;
	}
	public String getBeneficiaryAddress2() {
		return BeneficiaryAddress2;
	}
	public void setBeneficiaryAddress2(String beneficiaryAddress2) {
		BeneficiaryAddress2 = beneficiaryAddress2;
	}
	public String getBeneficiaryAddressCity() {
		return BeneficiaryAddressCity;
	}
	public void setBeneficiaryAddressCity(String beneficiaryAddressCity) {
		BeneficiaryAddressCity = beneficiaryAddressCity;
	}
	public String getBeneficiaryAddressState() {
		return BeneficiaryAddressState;
	}
	public void setBeneficiaryAddressState(String beneficiaryAddressState) {
		BeneficiaryAddressState = beneficiaryAddressState;
	}
	public String getBeneficiaryAddressCountryCode() {
		return BeneficiaryAddressCountryCode;
	}
	public void setBeneficiaryAddressCountryCode(String beneficiaryAddressCountryCode) {
		BeneficiaryAddressCountryCode = beneficiaryAddressCountryCode;
	}
	public String getBeneficiaryAddressZip() {
		return BeneficiaryAddressZip;
	}
	public void setBeneficiaryAddressZip(String beneficiaryAddressZip) {
		BeneficiaryAddressZip = beneficiaryAddressZip;
	}
	public String getBeneficiaryPhone() {
		return BeneficiaryPhone;
	}
	public void setBeneficiaryPhone(String beneficiaryPhone) {
		BeneficiaryPhone = beneficiaryPhone;
	}
	public String getBeneficiaryMobile() {
		return BeneficiaryMobile;
	}
	public void setBeneficiaryMobile(String beneficiaryMobile) {
		BeneficiaryMobile = beneficiaryMobile;
	}
	public String getBeneficiaryEmail() {
		return BeneficiaryEmail;
	}
	public void setBeneficiaryEmail(String beneficiaryEmail) {
		BeneficiaryEmail = beneficiaryEmail;
	}
	public String getBeneficiaryAccountName() {
		return BeneficiaryAccountName;
	}
	public void setBeneficiaryAccountName(String beneficiaryAccountName) {
		BeneficiaryAccountName = beneficiaryAccountName;
	}
	public String getBeneficiaryBankCode() {
		return BeneficiaryBankCode;
	}
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		BeneficiaryBankCode = beneficiaryBankCode;
	}
	public String getBeneficiaryBankName() {
		return BeneficiaryBankName;
	}
	public void setBeneficiaryBankName(String beneficiaryBankName) {
		BeneficiaryBankName = beneficiaryBankName;
	}
	public String getBeneficiaryBankBranchName() {
		return BeneficiaryBankBranchName;
	}
	public void setBeneficiaryBankBranchName(String beneficiaryBankBranchName) {
		BeneficiaryBankBranchName = beneficiaryBankBranchName;
	}
	public String getBeneficiaryBankAccountNo() {
		return BeneficiaryBankAccountNo;
	}
	public void setBeneficiaryBankAccountNo(String beneficiaryBankAccountNo) {
		BeneficiaryBankAccountNo = beneficiaryBankAccountNo;
	}
	public String getSwiftIFSC() {
		return SwiftIFSC;
	}
	public void setSwiftIFSC(String swiftIFSC) {
		SwiftIFSC = swiftIFSC;
	}
	public String getBeneficiaryAccountCcyCode() {
		return BeneficiaryAccountCcyCode;
	}
	public void setBeneficiaryAccountCcyCode(String beneficiaryAccountCcyCode) {
		BeneficiaryAccountCcyCode = beneficiaryAccountCcyCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	
	
}
