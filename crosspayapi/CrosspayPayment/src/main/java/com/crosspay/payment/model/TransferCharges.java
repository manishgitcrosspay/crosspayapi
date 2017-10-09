package com.crosspay.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "Transfercharges")
public class TransferCharges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int TransferChargesId;
	
	@Null
	private String TransferType;
	
	@NotNull
	private String PayinCountryCode;
	
	@NotNull
	private String PayinCcyCode;
	
	@NotNull
	private int SlabSerialNo;
	
	@NotNull
	private double PayinAmountFrom;
	
	@NotNull
	private double PayinAmountTo;
	
	@NotNull
	private double Commission;

	public int getTransferChargesId() {
		return TransferChargesId;
	}

	public void setTransferChargesId(int transferChargesId) {
		TransferChargesId = transferChargesId;
	}

	public String getTransferType() {
		return TransferType;
	}

	public void setTransferType(String transferType) {
		TransferType = transferType;
	}

	public String getPayinCountryCode() {
		return PayinCountryCode;
	}

	public void setPayinCountryCode(String payinCountryCode) {
		PayinCountryCode = payinCountryCode;
	}

	public String getPayinCcyCode() {
		return PayinCcyCode;
	}

	public void setPayinCcyCode(String payinCcyCode) {
		PayinCcyCode = payinCcyCode;
	}

	public int getSlabSerialNo() {
		return SlabSerialNo;
	}

	public void setSlabSerialNo(int slabSerialNo) {
		SlabSerialNo = slabSerialNo;
	}

	public double getPayinAmountFrom() {
		return PayinAmountFrom;
	}

	public void setPayinAmountFrom(double payinAmountFrom) {
		PayinAmountFrom = payinAmountFrom;
	}

	public double getPayinAmountTo() {
		return PayinAmountTo;
	}

	public void setPayinAmountTo(double payinAmountTo) {
		PayinAmountTo = payinAmountTo;
	}

	public double getCommission() {
		return Commission;
	}

	public void setCommission(double commission) {
		Commission = commission;
	}
	
	
}
