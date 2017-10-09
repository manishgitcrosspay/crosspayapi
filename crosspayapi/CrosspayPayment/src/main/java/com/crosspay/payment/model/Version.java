package com.crosspay.payment.model;

import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "REF_VERSION_INFO")
public class Version {

	/*static {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int REF_VERSION_INFO_ID;

	@NotNull
	private String PREVIOUS_RELEASE_NUMBER;

	@NotNull
	private String RELEASE_NUMBER;

	@NotNull
	private String RELEASE_DETAILS;

	@NotNull
	private LocalDateTime RELEASE_DATE;

	@NotNull
	private LocalDateTime LAST_MODIFIED_TIME;

	@NotNull
	private String LAST_MODIFIED_USER;

	public int getREF_VERSION_INFO_ID() {
		return REF_VERSION_INFO_ID;
	}

	public void setREF_VERSION_INFO_ID(int rEF_VERSION_INFO_ID) {
		REF_VERSION_INFO_ID = rEF_VERSION_INFO_ID;
	}

	public String getPREVIOUS_RELEASE_NUMBER() {
		return PREVIOUS_RELEASE_NUMBER;
	}

	public void setPREVIOUS_RELEASE_NUMBER(String pREVIOUS_RELEASE_NUMBER) {
		PREVIOUS_RELEASE_NUMBER = pREVIOUS_RELEASE_NUMBER;
	}

	public String getRELEASENUMBER() {
		return RELEASE_NUMBER;
	}

	public void setRELEASENUMBER(String rELEASE_NUMBER) {
		RELEASE_NUMBER = rELEASE_NUMBER;
	}

	public String getRELEASE_DETAILS() {
		return RELEASE_DETAILS;
	}

	public void setRELEASE_DETAILS(String rELEASE_DETAILS) {
		RELEASE_DETAILS = rELEASE_DETAILS;
	}

	public LocalDateTime getRELEASE_DATE() {
		return RELEASE_DATE;
	}

	public void setRELEASE_DATE(LocalDateTime rELEASE_DATE) {
		RELEASE_DATE = rELEASE_DATE;
	}

	public LocalDateTime getLAST_MODIFIED_TIME() {
		return LAST_MODIFIED_TIME;
	}

	public void setLAST_MODIFIED_TIME(LocalDateTime lAST_MODIFIED_TIME) {
		LAST_MODIFIED_TIME = lAST_MODIFIED_TIME;
	}

	public String getLAST_MODIFIED_USER() {
		return LAST_MODIFIED_USER;
	}

	public void setLAST_MODIFIED_USER(String lAST_MODIFIED_USER) {
		LAST_MODIFIED_USER = lAST_MODIFIED_USER;
	}

	public Version() {
	}

	public Version(int id) {
		this.REF_VERSION_INFO_ID = id;
	}

	public Version(String currentversion, String newversion, String versionname) {
		this.PREVIOUS_RELEASE_NUMBER = currentversion;
		this.RELEASE_NUMBER = newversion;
		this.RELEASE_DETAILS = versionname;
	}

}