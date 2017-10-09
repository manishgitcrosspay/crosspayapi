package com.crosspay.payment.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.ACSenderReceiver;
import com.crosspay.payment.model.AccountCredit;
import com.crosspay.payment.model.Beneficiary;
import com.crosspay.payment.model.BeneficiaryBank;
import com.crosspay.payment.model.BeneficiaryIds;
import com.crosspay.payment.model.CPSenderReceiver;
import com.crosspay.payment.model.CashPayment;
import com.crosspay.payment.model.Customer;
import com.crosspay.payment.model.SBankReceipts;
import com.crosspay.payment.model.SPartnerResponse;

@Service
public interface AmountTransferDao extends CrudRepository<ACSenderReceiver, Long> {

	public int saveTransactionData(ACSenderReceiver reg);

	public int saveAccountCreditData(AccountCredit reg);

	public float retrieveCommission(float amount, String transfertype);

	public int saveCashCreditData(CashPayment acc);

	public int savePartnerData(SPartnerResponse spr);

	public int acsendertxno(String txno, String orderid);

	public int updateaccountcredit(AccountCredit acc);
	
	public int retrievespartnerid();
	
	public List<Map<String, Object>> retrieveTransaction(String customerno);
	
	public List<Map<String, Object>> retrieveTransactionview(String customerno,String orderid);
	
	public int updatecashpay(CashPayment acc);
	
	public List<Map<String, Object>> retrieveAgent();
	
	public int saveSbankreceipts(SBankReceipts sbr);

	public int updatecashpays(CashPayment acc);
	
	public int updateaccountcredits(AccountCredit acc);
	
	public float retrieveACCommission(float amount,String ccycode,String ccycodeto);
	
	public float retrieveCPCommission(float amount,String ccycode,String ccycodeto);
	
	public List<Map<String, Object>> retrieveaccountpdfviewdetails(String customerno,String orderid);
	
	public List<Map<String, Object>> retrievecashpdfviewdetails(String customerno,String orderid);
	
	public double retrieveaccounttxnno();
	
	public double retrievecashtxnno();
	
	public int saveCPTransactionData(CPSenderReceiver reg);
	
	public int cpsendertxno(String txno, String orderid);
	
	public Map<String, Object> retrieveAccountACData(String currdate);
	
	public Map<String, Object> retrieveCashCPData(String currdate);
	
	public List<Map<String, Object>> retrieveACSenderData(String from,String to);
	
	public List<Map<String, Object>> retrieveCPSenderData(String from,String to);
	
	public List<Map<String, Object>> retrieveaccountTransaction();
	
	public List<Map<String, Object>> retrievecashTransaction();
}