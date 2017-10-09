package com.crosspay.payment.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.AccountCredit;
import com.crosspay.payment.model.Beneficiary;
import com.crosspay.payment.model.BeneficiaryBank;
import com.crosspay.payment.model.BeneficiaryIds;
import com.crosspay.payment.model.Customer;

@Service
public interface CustomerDao extends CrudRepository<Customer, Long> {

	/**
	 * This method will find an User instance in the database by its email. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */

	public int saveBeneficiary(Beneficiary reg);

	public int saveBeneficiaryBank(BeneficiaryBank reg);
	
	public int saveBeneficiaryIds(BeneficiaryIds ids);
	
	public int updateBeneficiary(Beneficiary reg);

	public int updateBeneficiaryBank(BeneficiaryBank reg);
	
	public int retrieveBeneficiaryNo();
	
	public List<Map<String, Object>> retrieveBeneficiaryDetails(String customerno);
	
	public BeneficiaryBank retrieveBeneficiaryBankDetails(String customerno);
	
	public int delBeneficiaryIds(int beneficiary_no);
	
	public int retrieveBeneficiaryAccountNo(String accntnum,String userId);
	
	public int retrieveBeneficiaryIdNo();
	
	public List<Map<String, Object>> retrieveBeneficiaryData(String beneficiaryno);
	
	public BeneficiaryBank retrieveBeneficiaryBankData(String beneficiaryno);
	
	public int checkBeneficiaryNo(String customerno,String mobile,String accnum);
	
	public List<Map<String, Object>> retrieveBeneficiaryDatas(String beneficiaryno);
	
	

}