package com.crosspay.payment.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.ACSenderReceiver;
import com.crosspay.payment.model.AccountCredit;
import com.crosspay.payment.model.Beneficiary;
import com.crosspay.payment.model.BeneficiaryBank;
import com.crosspay.payment.model.BeneficiaryIds;
import com.crosspay.payment.model.CPSenderReceiver;
import com.crosspay.payment.model.CashPayment;
import com.crosspay.payment.model.CurrencyRates;
import com.crosspay.payment.model.Customer;
import com.crosspay.payment.model.SBankReceipts;
import com.crosspay.payment.model.SPartnerResponse;
import com.crosspay.payment.service.AmountTransferDao;
import com.crosspay.payment.service.CustomerDao;
import com.crosspay.payment.service.VersionDao;

@Service
public class AmountTransferDaoImpl implements AmountTransferDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	EntityManager em;

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(ACSenderReceiver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends ACSenderReceiver> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<ACSenderReceiver> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ACSenderReceiver> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACSenderReceiver findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ACSenderReceiver> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ACSenderReceiver> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveTransactionData(ACSenderReceiver reg) {
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"insert into ACSENDERRECEIVER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, reg.getTxnNo(), reg.getCustomerNo(), reg.getBeneficiaryNo(),
							reg.getBeneficiaryFirstName(), reg.getBeneficiaryMiddleName(), reg.getBeneficiaryLastName(),
							reg.getBeneficiaryPOBox(), reg.getBeneficiaryAddress1(), reg.getBeneficiaryAddress2(),
							reg.getBeneficiaryAddressCity(), reg.getBeneficiaryAddressState(),
							reg.getBeneficiaryAddressCountryCode(), reg.getBeneficiaryAddressZip(),
							reg.getBeneficiaryPhone(), reg.getBeneficiaryMobile(), reg.getBeneficiaryEmail(),
							reg.getBeneficiaryAccountName(), reg.getBeneficiaryBankCode(), reg.getBeneficiaryBankName(),
							reg.getBeneficiaryBankBranchName(), reg.getBeneficiaryBankAccountNo(), reg.getSwiftIFSC(),
							reg.getBeneficiaryAccountCcyCode(), reg.getOrderId() });
		} catch (Exception e) {
			System.out.println("Error in insertig acsenderreceiver table" + e.toString());
		}
		return i;
	}

	@Override
	public int saveAccountCreditData(AccountCredit acc) {
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"insert into ACCOUNTCREDIT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, acc.getAccountCcyCode(), acc.getAuthorisedBy(), acc.getAuthorisedOn(),
							acc.getCommission(), acc.getCreatedBy(), acc.getCreatedOn(), acc.getCreditedDate(),
							acc.getCustomerNo(), acc.getDraweeBankCode(), acc.getIBnPayTxnPayin2AccountCcyRate(),
							acc.getModifiedBy(), acc.getModifiedOn(), acc.getPartnerRefNo(), acc.getPayinAmount(),
							acc.getPayinccyCode(), acc.getPaymentMode(), acc.getSAuthReference(), acc.getSPartnerID(),
							acc.getTotalPayinAmount(), acc.getTransferAmount(), acc.getTxnDate(), acc.getTxnGMTDate(),
							acc.getTxnNo(), acc.getTxnPayTxnPayin2AccountCcyRate(), acc.getTxnStatus(),
							acc.getValueDate(), acc.getOrderId() });
		} catch (Exception e) {
			System.out.println("Error in inserting ACCOUNTCREDIT table" + e.toString());
		}
		return i;

	}

	@Override
	public int saveCashCreditData(CashPayment acc) {
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"insert into CASHPAYOUT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, acc.getACCOUNTCCYCODE(), acc.getAGENTCODE(), acc.getAUTHORISEDBY(),
							acc.getAUTHORISEDON(), acc.getCOMMISSION(), acc.getCREATEDBY(), acc.getCREATEDON(),
							acc.getCUSTOMERNO(), acc.getIBANPAYTXNPAYIN2ACCOUNTCCYRATE(), acc.getMODIFIEDBY(),
							acc.getMODIFIEDON(), acc.getORDERID(), acc.getPAIDDATE(), acc.getPARTNERREFNO(),
							acc.getPAYINAMOUNT(), acc.getPAYINCCCODE(), acc.getPAYMENTMODE(), acc.getSAUTHREFERENCE(),
							acc.getSPARTNERID(), acc.getTOTALPAYINAMOUNT(), acc.getTRANSFERAMOUNT(), acc.getTXNDATE(),
							acc.getTXNGMTDATE(), acc.getTXNNO(), acc.getTXNPAYTXNPAYIN2ACCOUNTCCYRATE(),
							acc.getTXNSTATUS(), acc.getVALUEDATE() });
		} catch (Exception e) {
			System.out.println("Error in inserting ACCOUNTCREDIT table" + e.toString());
		}
		return i;

	}

	@Override
	public float retrieveCommission(float amount, String transfertype) {
		float i = 0;
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList(
					"select commission from TRANSFERCHARGES where PAYIN_AMOUNT_TO>=? and PAYIN_AMOUNT_FROM<=? and TRANSFER_TYPE = ?",
					new Object[] { amount, amount, transfertype });
			System.out.println("commission val " + result.get(0).get("commission"));
			i = Float.parseFloat(result.get(0).get("commission").toString());
			System.out.println("commission val i " + i);
		} catch (Exception e) {
			System.out.println("Error in retrieving TRANSFERCHARGES table" + e.toString());
		}
		return i;
	}

	@Override
	public float retrieveACCommission(float amount, String ccycode, String ccycodeto) {
		float i = 0;
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList(
					"select commission from ACTRANSFERCHARGES where PAYINAMOUNTTO>=? and PAYINAMOUNTFROM<=? and PAYINCCYCODE = ? AND PAYOUTCCYCODE = ?",
					new Object[] { amount, amount, ccycode, ccycodeto });
			System.out.println("commission val " + result.get(0).get("commission"));
			i = Float.parseFloat(result.get(0).get("commission").toString());
			System.out.println("commission val i " + i);
		} catch (Exception e) {
			System.out.println("Error in retrieving TRANSFERCHARGES table" + e.toString());
		}
		return i;
	}

	@Override
	public float retrieveCPCommission(float amount, String ccycode, String ccycodeto) {
		float i = 0;
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList(
					"select commission from CPTRANSFERCHARGES where PAYINAMOUNTTO>=? and PAYINAMOUNTFROM<=? and PAYINCCYCODE = ? and PAYOUTCCYCODE = ?",
					new Object[] { amount, amount, ccycode, ccycodeto });
			System.out.println("CAHcommission val " + result.get(0).get("commission"));
			i = Float.parseFloat(result.get(0).get("commission").toString());
			System.out.println("CASH I commission val " + i);
		} catch (Exception e) {
			System.out.println("Error in retrieving TRANSFERCHARGES table" + e.toString());
		}
		return i;
	}

	@Override
	public int savePartnerData(SPartnerResponse spr) {
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"insert into SPARTNERRESPONSE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, spr.getACCOUNTTYPEDESC(), spr.getACQUIRERRESPONSECODE(), spr.getAUTHCODE(),
							spr.getAUTHMETHOD(), spr.getBASEAMOUNT(), spr.getCAVV(), spr.getCURRECY(),
							spr.getDCCENABLED(), spr.getECI(), spr.getENROLLED(), spr.getERRORCODE(),
							spr.getERRORMESSAGE(), spr.getISSUER(), spr.getISSUERCOUNTRYCODE(), spr.getLIVESTATUS(),
							spr.getMASKEDPAN(), spr.getMERCHANTCOUNTRYCODE(), spr.getMERCHANTNAME(),
							spr.getMERCHANTNO(), spr.getOPERATORNAME(), spr.getPARENTTXNREF(), spr.getPAYMENTTYPEDESC(),
							spr.getREQUESTTYPEDESC(), spr.getSECURITYRESPONSEADDRESS(),
							spr.getSECURITYRESPONSEPOSTCODE(), spr.getSECURITYRESPONSESECCODE(),
							spr.getSETTLEDDUEDATE(), spr.getSETTLESTATUS(), spr.getSPARTERID(), spr.getSPLITFINALNO(),
							spr.getSTATUS(), spr.getTID(), spr.getTXNREF(), spr.getTXNSTARTTIMESTAMP(), spr.getXID(),
							spr.getORDERID() });
		} catch (Exception e) {
			System.out.println("Error in inserting SPARTNERRESPONSE table" + e.toString());
		}
		return i;

	}

	@Override
	public int acsendertxno(String txno, String orderid) {
		int i = 0;
		try {
			i = jdbcTemplate.update("update ACSENDERRECEIVER set TxnNo = ? where OrderId = ?",
					new Object[] { txno, orderid });
		} catch (Exception e) {
			System.out.println("Error in updating txno in  ACSENDERRECEIVER table" + e.toString());
		}
		return i;

	}

	@Override
	public int cpsendertxno(String txno, String orderid) {
		int i = 0;
		try {
			i = jdbcTemplate.update("update CPSENDERRECEIVER set TXNNO = ? where ORDERID = ?",
					new Object[] { txno, orderid });
		} catch (Exception e) {
			System.out.println("Error in updating txno in  CPSENDERRECEIVER table" + e.toString());
		}
		return i;

	}

	@Override
	public int updateaccountcredit(AccountCredit acc) {
		try {
			System.out.println("adsadasd " + acc.getOrderId());
		} catch (Exception e) {
			System.out.println("adsadasd err" + e.toString());
		}
		try {
			System.out.println("9999 " + acc.getSAuthReference());
		} catch (Exception e) {
			System.out.println("99999 err" + e.toString());
		}
		try {
			System.out.println("888888 " + acc.getSPartnerID());
		} catch (Exception e) {
			System.out.println("8888 err" + e.toString());
		}
		try {
			System.out.println("66666 " + acc.getTxnStatus());
		} catch (Exception e) {
			System.out.println("6666 err" + e.toString());
		}

		try {
			System.out.println("333333 " + acc.getTxnDate());
		} catch (Exception e) {
			System.out.println("6666 err" + e.toString());
		}
		System.out.println("af " + acc.getTxnDate() + "==" + acc.getSAuthReference() + "----" + acc.getSPartnerID()
				+ "===" + acc.getTxnStatus() + "///" + acc.getOrderId());
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"update ACCOUNTCREDIT set txn_date = ?,sauth_reference=?,spartnerid =?,txn_status=? where order_id=?",
					new Object[] { acc.getTxnDate(), acc.getSAuthReference(), acc.getSPartnerID(), acc.getTxnStatus(),
							acc.getOrderId() });
		} catch (Exception e) {
			System.out.println("Error in updating ACCOUNTCREDIT table" + e.toString());
		}
		return i;

	}

	@Override
	public int updatecashpay(CashPayment acc) {
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"update CASHPAYOUT set 	txndate = ?,sauthreference=?,spartnerid =?,txnstatus=? where orderid=?",
					new Object[] { acc.getTXNDATE(), acc.getSAUTHREFERENCE(), acc.getSPARTNERID(), acc.getTXNSTATUS(),
							acc.getORDERID() });
		} catch (Exception e) {
			System.out.println("Error in updating ACCOUNTCREDIT table" + e.toString());
		}
		return i;

	}

	@Override
	public int updateaccountcredits(AccountCredit acc) {
		int i = 0;
		try {
			i = jdbcTemplate.update("update ACCOUNTCREDIT set txn_status=? where order_id=?",
					new Object[] { acc.getTxnStatus(), acc.getOrderId() });
		} catch (Exception e) {
			System.out.println("Error in updating ACCOUNTCREDIT table" + e.toString());
		}
		return i;

	}

	@Override
	public int updatecashpays(CashPayment acc) {
		int i = 0;
		try {
			i = jdbcTemplate.update("update CASHPAYOUT set txnstatus=? where orderid=?",
					new Object[] { acc.getTXNSTATUS(), acc.getORDERID() });
		} catch (Exception e) {
			System.out.println("Error in updating ACCOUNTCREDIT table" + e.toString());
		}
		return i;

	}

	@Override
	public int retrievespartnerid() {
		int i = 0;
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from SPARTNERRESPONSE");
			i = Integer.parseInt(result.get(result.size() - 1).get("SPARTNERRESPONSEID").toString());
		} catch (Exception e) {
			System.out.println("Error in retrieving data SPARTNERRESPONSE table" + e.toString());
		}
		return i;

	}

	@Override
	public List<Map<String, Object>> retrieveTransaction(String customerno) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				" select ben.ACSenderReceiverId,ben.BeneficiaryFirstName,ben.BeneficiaryMiddleName,ben.BeneficiaryLastName,ben.BeneficiaryBankAccountNo,ben.SwiftIFSC, "
						+ " ben.OrderId,acc.transfer_amount,acc.total_payin_amount,acc.txn_no,acc.payinccy_code,acc.account_ccy_code,acc.txn_date,acc.txn_status,acc.commission,acc.txn_pay_txn_payin2account_ccy_rate,ben.beneficiaryaddresscountrycode,ben.beneficiaryaddresscity  "
						+ " from ACSENDERRECEIVER ben,ACCOUNTCREDIT acc where acc.order_id=ben.OrderId and acc.customer_no  = ? and acc.txn_status!='4'"
						+ " UNION "
						+ " select ben.CPSenderReceiverId,ben.BeneficiaryFirstName,ben.BeneficiaryMiddleName,ben.BeneficiaryLastName,null,null, "
						+ " ben.OrderId,acc.transferamount,acc.totalpayinamount,acc.txnno,acc.payincccode,acc.accountccycode,acc.txndate,acc.txnstatus,acc.commission,acc.txnpaytxnpayin2accountccyrate,ben.beneficiaryaddresscountrycode,ben.beneficiaryaddresscity  "
						+ " from CPSENDERRECEIVER ben,CASHPAYOUT acc where acc.orderid=ben.OrderId and acc.customerno  = ? and acc.txnstatus!='4'",
				new Object[] { customerno, customerno });
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {
			System.out.println("result no " + result.size());
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("name",
							result.get(i).get("BeneficiaryFirstName").toString() + " "
									+ result.get(i).get("BeneficiaryMiddleName").toString() + " "
									+ result.get(i).get("BeneficiaryLastName").toString());
				} catch (Exception e) {
					obj.put("name", result.get(i).get("BeneficiaryFirstName").toString() + " "
							+ result.get(i).get("BeneficiaryLastName").toString());
				}
				try {
					obj.put("accout_num", result.get(i).get("BeneficiaryBankAccountNo").toString());
				} catch (Exception e) {
					obj.put("accout_num", "Cash Transaction");
				}

				try {
					System.out.println("orderid " + result.get(i).get("OrderId").toString().startsWith("bankt"));
					if (result.get(i).get("OrderId").toString().contains("bankt")) {
						obj.put("accout_num", "Bank Transfer");
					}

				} catch (Exception e) {
				}

				try {
					obj.put("ifsc", result.get(i).get("SwiftIFSC").toString());
				} catch (Exception e) {
					obj.put("ifsc", null);
				}

				// obj.put("total_amount", result.get(i).get("transfer_amount").toString());
				// Float total =
				// Float.parseFloat(result.get(i).get("total_payin_amount").toString())+Float.parseFloat(result.get(i).get("commission").toString());
				obj.put("total_amount", result.get(i).get("total_payin_amount").toString());

				try {
					obj.put("txn_no", result.get(i).get("txn_no").toString());
				} catch (Exception e) {
					obj.put("txn_no", "");
				}

				try {
					obj.put("order_id", result.get(i).get("OrderId").toString());
				} catch (Exception e) {
					obj.put("order_id", "");
				}
				String dateSample = result.get(i).get("txn_date").toString();
				String oldFormat = "dd-MM-yyyy HH:mm";
				String newFormat = "yyyy-MM-dd HH:mm:ss";

				SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
				SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

				try {
					obj.put("txn_date", sdf1.format(sdf2.parse(dateSample)));
				} catch (Exception e) {
					obj.put("txn_date", result.get(i).get("txn_date"));
					e.printStackTrace();
				}

				obj.put("txn_status", result.get(i).get("txn_status"));
				obj.put("payin_ccycode", result.get(i).get("payinccy_code"));
				obj.put("account_ccycode", result.get(i).get("account_ccy_code"));
				obj.put("rate", result.get(i).get("txn_pay_txn_payin2account_ccy_rate"));
				obj.put("country", result.get(i).get("beneficiaryaddresscountrycode"));
				obj.put("city", result.get(i).get("beneficiaryaddresscity"));
				// obj.put("payin_amount", result.get(i).get("total_payin_amount").toString());
				// obj.put("commission", result.get(i).get("commission").toString());
				CurrencyRates currencyrates = new CurrencyRates();
				CurrencyRates currencyrateoutput = new CurrencyRates();
				currencyrates.setCCYCODEFROM(result.get(i).get("payinccy_code").toString());
				currencyrates.setCCYCODETO(result.get(i).get("account_ccy_code").toString());
				currencyrateoutput = versionDao.retieveData(currencyrates);

				Double out = (Double.parseDouble(result.get(i).get("txn_pay_txn_payin2account_ccy_rate").toString()))
						* (Double.parseDouble(result.get(i).get("total_payin_amount").toString()));
				System.out.println("inside " + out);
				obj.put("payee_amount", Float.parseFloat(result.get(i).get("transfer_amount").toString()));

				System.out.println("obj" + obj);
				output.add(obj);

			}
			System.out.println("output" + output);
		} catch (Exception e) {
			System.out.println("Error in CUSTOMER retrieval" + e.toString());
		}
		return output;

	}

	@Override
	public List<Map<String, Object>> retrieveTransactionview(String customerno, String orderid) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				" select ben.ACSenderReceiverId,ben.BeneficiaryFirstName,ben.BeneficiaryMiddleName,ben.BeneficiaryLastName,ben.BeneficiaryBankAccountNo,ben.SwiftIFSC, "
						+ " ben.OrderId,acc.transfer_amount,acc.total_payin_amount,acc.txn_no,acc.payinccy_code,acc.account_ccy_code,acc.txn_date,acc.txn_status,acc.commission,acc.txn_pay_txn_payin2account_ccy_rate,ben.beneficiaryaddresscountrycode,ben.beneficiaryaddresscity,acc.payin_amount  "
						+ " from ACSENDERRECEIVER ben,ACCOUNTCREDIT acc where acc.order_id= ? and ben.OrderId=? and acc.customer_no  = ? "
						+ " UNION "
						+ " select ben.CPSenderReceiverId,ben.BeneficiaryFirstName,ben.BeneficiaryMiddleName,ben.BeneficiaryLastName,null,null, "
						+ " ben.OrderId,acc.transferamount,acc.totalpayinamount,acc.txnno,acc.payincccode,acc.accountccycode,acc.txndate,acc.txnstatus,acc.commission,acc.txnpaytxnpayin2accountccyrate,ben.beneficiaryaddresscountrycode,ben.beneficiaryaddresscity,acc.payinamount  "
						+ " from CPSENDERRECEIVER ben,CASHPAYOUT acc where acc.orderid= ? and ben.OrderId=? and acc.customerno  = ?",
				new Object[] { orderid, orderid, customerno, orderid, orderid, customerno });
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {
			System.out.println("result no " + result.size());
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("name",
							result.get(i).get("BeneficiaryFirstName").toString() + " "
									+ result.get(i).get("BeneficiaryMiddleName").toString() + " "
									+ result.get(i).get("BeneficiaryLastName").toString());
				} catch (Exception e) {
					obj.put("name", result.get(i).get("BeneficiaryFirstName").toString() + " "
							+ result.get(i).get("BeneficiaryLastName").toString());
				}
				try {
					obj.put("accout_num", result.get(i).get("BeneficiaryBankAccountNo").toString());
					obj.put("accout_type", "Account Credit");
				} catch (Exception e) {
					obj.put("accout_num", "Cash Transaction");
					obj.put("accout_type", "Cash Credit");
				}

				try {
					System.out.println("orderid " + result.get(i).get("OrderId").toString());
					if (result.get(i).get("OrderId").toString().startsWith("bankt")) {
						obj.put("accout_num", "Bank Transfer");
						obj.put("accout_type", "Bank Transfer");
					}

				} catch (Exception e) {
				}

				try {
					obj.put("ifsc", result.get(i).get("SwiftIFSC").toString());
				} catch (Exception e) {
					obj.put("ifsc", null);
				}

				// obj.put("total_amount", result.get(i).get("transfer_amount").toString());
				// Float total =
				// Float.parseFloat(result.get(i).get("total_payin_amount").toString())+Float.parseFloat(result.get(i).get("commission").toString());
				obj.put("total_amount", result.get(i).get("total_payin_amount").toString());

				try {
					obj.put("txn_no", result.get(i).get("txn_no").toString());
				} catch (Exception e) {
					obj.put("txn_no", "");
				}
				String dateSample = result.get(i).get("txn_date").toString();
				String oldFormat = "dd-MM-yyyy HH:mm";
				String newFormat = "yyyy-MM-dd HH:mm:ss";

				SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
				SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

				try {
					obj.put("txn_date", sdf1.format(sdf2.parse(dateSample)));
				} catch (Exception e) {
					obj.put("txn_date", result.get(i).get("txn_date"));
					e.printStackTrace();
				}
				obj.put("txn_status", result.get(i).get("txn_status"));
				obj.put("payin_ccycode", result.get(i).get("payinccy_code"));
				obj.put("account_ccycode", result.get(i).get("account_ccy_code"));
				obj.put("payin_amount", result.get(i).get("payin_amount").toString());
				obj.put("commission", result.get(i).get("commission"));
				obj.put("rate", result.get(i).get("txn_pay_txn_payin2account_ccy_rate"));
				obj.put("country", result.get(i).get("beneficiaryaddresscountrycode"));
				obj.put("city", result.get(i).get("beneficiaryaddresscity"));
				CurrencyRates currencyrates = new CurrencyRates();
				CurrencyRates currencyrateoutput = new CurrencyRates();
				currencyrates.setCCYCODEFROM(result.get(i).get("payinccy_code").toString());
				currencyrates.setCCYCODETO(result.get(i).get("account_ccy_code").toString());
				currencyrateoutput = versionDao.retieveData(currencyrates);
				System.out.println("totalpa " + result.get(i).get("total_payin_amount").toString());
				System.out.println("currencyrateoutput " + currencyrateoutput.getXCHGRATE());
				Double out = (Double.parseDouble(result.get(i).get("txn_pay_txn_payin2account_ccy_rate").toString()))
						* (Double.parseDouble(result.get(i).get("total_payin_amount").toString()));
				System.out.println("inside " + out);
				int r = (int) Math.round(out * 100);
				out = r / 100.0;
				obj.put("payee_amount", Float.parseFloat(result.get(i).get("transfer_amount").toString()));

				System.out.println("obj" + obj);
				output.add(obj);

			}
			System.out.println("output" + output);
		} catch (Exception e) {
			System.out.println("Error in CUSTOMER retrieval" + e.toString());
		}
		return output;

	}

	@Override
	public List<Map<String, Object>> retrieveAgent() {
		JSONArray finallist = new JSONArray();

		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();

		System.out.println("output ");

		try {
			List<Map<String, Object>> val = jdbcTemplate.queryForList("select * from CPAGENTS where RECORDSTATUS = 1");

			System.out.println("output " + val.size());

			for (int i = 0; i < val.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("agent_code", val.get(i).get("AGENTCODE").toString());
					obj.put("agent_name", val.get(i).get("AGENTNAME").toString());
					obj.put("agent_country_code", val.get(i).get("AGENTCOUNTRYCODE").toString());
					obj.put("country_name", val.get(i).get("COUNTRYNAME").toString());
					obj.put("payout_currencycode", val.get(i).get("PAYOUTCURRENCYCODE").toString());
					obj.put("payout_maxlimit", val.get(i).get("PAYOUTMAXLIMIT"));
					System.out.println("iter " + obj);
					output.add(obj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			System.out.println("Exception " + e.toString());
		}
		System.out.println("output " + output);
		// finallist.put(output);
		return output;
	}

	@Override
	public int saveSbankreceipts(SBankReceipts sbr) {
		int i = 0;
		try {
			i = jdbcTemplate.update("insert into SBANKRECEIPTS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, sbr.getAMOUNTCCYCODE(), sbr.getAMOUNTRECEIVED(), sbr.getAMOUNTRECEIVEDON(),
							sbr.getBANKREFERENCENO(), sbr.getCREDITVERIFIEDBYUSER(), sbr.getCREDITVERIFIEDON(),
							sbr.getCUSTOMERNO(), sbr.getRECORDSTATUS(), sbr.getSPARTNERID(), sbr.getTXNDATE(),
							sbr.getTXNNO(), sbr.getTXNTYPE() });
		} catch (Exception e) {
			System.out.println("Error in inserting SBANKRECEIPTS table" + e.toString());
		}
		return i;
	}

	@Override
	public List<Map<String, Object>> retrieveaccountpdfviewdetails(String customerno, String orderid) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				" select ben.ACSenderReceiverId,ben.BeneficiaryFirstName,ben.BeneficiaryMiddleName,ben.BeneficiaryLastName,ben.BeneficiaryMobile,ben.BeneficiaryAccountName,ben.BeneficiaryBankAccountNo,ben.SwiftIFSC, ben.BeneficiaryBankName,"
						+ " ben.OrderId,acc.payment_mode,acc.transfer_amount,acc.total_payin_amount,acc.txn_no,acc.payinccy_code,acc.account_ccy_code,acc.txn_date,acc.txn_status,acc.commission,acc.txn_pay_txn_payin2account_ccy_rate,ben.beneficiaryaddresscountrycode,ben.beneficiaryaddresscity,ben.beneficiaryaddressstate,ben.beneficiaryaddress1,ben.beneficiaryaddress2,acc.payin_amount"
						+ " from ACSENDERRECEIVER ben,ACCOUNTCREDIT acc where acc.order_id= ? and ben.OrderId=? and acc.customer_no  = ? and acc.txn_status = '3'",
				new Object[] { orderid, orderid, customerno });
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {
			System.out.println("result no " + result.size());
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("name",
							result.get(i).get("BeneficiaryFirstName").toString() + " "
									+ result.get(i).get("BeneficiaryMiddleName").toString() + " "
									+ result.get(i).get("BeneficiaryLastName").toString());
				} catch (Exception e) {
					obj.put("name", result.get(i).get("BeneficiaryFirstName").toString() + " "
							+ result.get(i).get("BeneficiaryLastName").toString());
				}

				try {
					obj.put("accout_num", result.get(i).get("BeneficiaryBankAccountNo").toString());
					obj.put("accout_type", "Account Credit");
					obj.put("bank_name", result.get(i).get("BeneficiaryBankName").toString());
					try {
						obj.put("account_name", result.get(i).get("BeneficiaryAccountName").toString());
					} catch (Exception e) {
						obj.put("account_name", null);
					}
				} catch (Exception e) {
					obj.put("accout_num", "Cash Transaction");
					obj.put("accout_type", "Cash Credit");
					obj.put("bank_name", null);
				}

				try {
					obj.put("ifsc", result.get(i).get("SwiftIFSC").toString());
				} catch (Exception e) {
					obj.put("ifsc", null);
				}

				// obj.put("total_amount", result.get(i).get("transfer_amount").toString());
				// Float total =
				// Float.parseFloat(result.get(i).get("total_payin_amount").toString())+Float.parseFloat(result.get(i).get("commission").toString());
				obj.put("total_amount", result.get(i).get("total_payin_amount").toString());

				try {
					obj.put("txn_no", result.get(i).get("txn_no").toString());
				} catch (Exception e) {
					obj.put("txn_no", "");
				}
				String dateSample = result.get(i).get("txn_date").toString();
				String oldFormat = "dd-MM-yyyy HH:mm";
				String newFormat = "yyyy-MM-dd HH:mm:ss";

				SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
				SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
				// SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy hh:mm");

				try {
					obj.put("txn_date", sdf1.format(sdf2.parse(dateSample)));
				} catch (Exception e) {
					obj.put("txn_date", result.get(i).get("txn_date"));
					e.printStackTrace();
				}
				obj.put("txn_status", result.get(i).get("txn_status"));
				obj.put("payin_ccycode", result.get(i).get("payinccy_code"));
				obj.put("account_ccycode", result.get(i).get("account_ccy_code"));
				obj.put("payin_amount", result.get(i).get("payin_amount"));
				obj.put("commission", result.get(i).get("commission"));
				obj.put("rate", result.get(i).get("txn_pay_txn_payin2account_ccy_rate"));
				obj.put("country", result.get(i).get("beneficiaryaddresscountrycode"));
				obj.put("city", result.get(i).get("beneficiaryaddresscity"));
				obj.put("mobile", result.get(i).get("beneficiarymobile"));
				obj.put("payment_mode", result.get(i).get("payment_mode"));
				CurrencyRates currencyrates = new CurrencyRates();
				CurrencyRates currencyrateoutput = new CurrencyRates();
				currencyrates.setCCYCODEFROM(result.get(i).get("payinccy_code").toString());
				currencyrates.setCCYCODETO(result.get(i).get("account_ccy_code").toString());
				currencyrateoutput = versionDao.retieveData(currencyrates);

				Double out = (currencyrateoutput.getXCHGRATE())
						* (Float.parseFloat(result.get(i).get("total_payin_amount").toString()));
				System.out.println("inside " + out);
				obj.put("payee_amount", Float.parseFloat(result.get(i).get("transfer_amount").toString()));

				System.out.println("obj" + obj);
				output.add(obj);

			}
			System.out.println("output" + output);
		} catch (Exception e) {
			System.out.println("Error in CUSTOMER retrieval" + e.toString());
		}
		return output;
	}

	@Override
	public List<Map<String, Object>> retrievecashpdfviewdetails(String customerno, String orderid) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"  select ben.CPSenderReceiverId,ben.BeneficiaryFirstName,ben.BeneficiaryMiddleName,ben.BeneficiaryLastName,ben.BeneficiaryMobile, "
						+ " ben.OrderId,acc.transferamount,acc.totalpayinamount,acc.txnno,acc.payincccode,acc.accountccycode,acc.txndate,acc.txnstatus,acc.commission,acc.txnpaytxnpayin2accountccyrate,ben.beneficiaryaddresscountrycode,ben.beneficiaryaddresscity,acc.agentcode,acc.payinamount  "
						+ " from CPSENDERRECEIVER ben,CASHPAYOUT acc where acc.orderid= ? and ben.OrderId=? and acc.customerno  = ?",
				new Object[] { orderid, orderid, customerno });
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {

			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("name",
							result.get(i).get("BeneficiaryFirstName").toString() + " "
									+ result.get(i).get("BeneficiaryMiddleName").toString() + " "
									+ result.get(i).get("BeneficiaryLastName").toString());
				} catch (Exception e) {
					obj.put("name", result.get(i).get("BeneficiaryFirstName").toString() + " "
							+ result.get(i).get("BeneficiaryLastName").toString());
				}

				/*
				 * try { obj.put("accout_num", "Cash Transaction"); obj.put("accout_type",
				 * "Cash Credit"); obj.put("bank_name", null); obj.put("account_name", null);
				 * }catch(Exception e) { obj.put("accout_num", "Cash Transaction");
				 * obj.put("accout_type", "Cash Credit"); obj.put("bank_name", null); }
				 */

				/*
				 * try { obj.put("ifsc", result.get(i).get("SwiftIFSC").toString());
				 * }catch(Exception e) { obj.put("ifsc", null); }
				 */

				// obj.put("total_amount", result.get(i).get("transferamount").toString());
				System.out.println("total pay " + result.get(i).get("totalpayinamount").toString());
				System.out.println("total paycsddsf " + result.get(i).get("commission").toString());
				// Float total =
				// Float.parseFloat(result.get(i).get("totalpayinamount").toString())+Float.parseFloat(result.get(i).get("commission").toString());
				obj.put("total_amount", result.get(i).get("totalpayinamount").toString());

				try {
					obj.put("txn_no", result.get(i).get("txnno").toString());
				} catch (Exception e) {
					obj.put("txn_no", "");
				}
				String dateSample = result.get(i).get("txndate").toString();
				String oldFormat = "dd-MM-yyyy HH:mm";
				String newFormat = "yyyy-MM-dd HH:mm:ss";

				SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
				SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
				// SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy hh:mm");

				try {
					obj.put("txn_date", sdf1.format(sdf2.parse(dateSample)));
				} catch (Exception e) {
					obj.put("txn_date", result.get(i).get("txndate"));
					e.printStackTrace();
				}
				obj.put("txn_status", result.get(i).get("txnstatus"));

				obj.put("payin_ccycode", result.get(i).get("payincccode"));
				obj.put("account_ccycode", result.get(i).get("accountccycode"));
				obj.put("payin_amount", result.get(i).get("payinamount"));
				obj.put("commission", result.get(i).get("commission"));
				obj.put("rate", result.get(i).get("txnpaytxnpayin2accountccyrate"));
				obj.put("country", result.get(i).get("beneficiaryaddresscountrycode"));

				obj.put("city", result.get(i).get("beneficiaryaddresscity"));
				obj.put("mobile", result.get(i).get("beneficiarymobile"));
				// obj.put("payment_mode", result.get(i).get("paymentmode"));
				CurrencyRates currencyrates = new CurrencyRates();
				CurrencyRates currencyrateoutput = new CurrencyRates();
				currencyrates.setCCYCODEFROM(result.get(i).get("payincccode").toString());
				currencyrates.setCCYCODETO(result.get(i).get("accountccycode").toString());

				currencyrateoutput = versionDao.retieveData(currencyrates);

				Double out = (currencyrateoutput.getXCHGRATE())
						* (Float.parseFloat(result.get(i).get("totalpayinamount").toString()));

				obj.put("payee_amount", Float.parseFloat(result.get(i).get("transferamount").toString()));
				obj.put("agent_code", result.get(i).get("agentcode"));

				output.add(obj);
				System.out.println("cash oup " + output);

			}
		} catch (Exception e) {
			System.out.println("Error in CUSTOMER retrieval" + e.toString());

		}
		return output;
	}

	/**
	 * This method will find an User instance in the database by its email. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */

	@Autowired
	private VersionDao versionDao;

	@Override
	public double retrieveaccounttxnno() {
		System.out.println("retrieveCustomerNo in ");
		double id = 0;
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from ACCOUNTCREDIT");
			System.out.println("result in ");

			try {
				System.out.println("TXN_NO in " + result.size());
				System.out.println("TXN_NO in SADASD" + result.get(result.size() - 1).get("TXN_NO"));
				String TXNNO = result.get(result.size() - 1).get("TXN_NO").toString();
				System.out.println("TXN_NO in " + TXNNO);

				String sub = TXNNO.substring(4, 16).toString();
				System.out.println("TXN_NO in substring" + sub);
				// sub = "2017000000001";
				id = Double.valueOf(sub);
				System.out.println(String.format("%12f", id));

				System.out.println("TXN_NO id before" + String.valueOf(id));
				id++;
				System.out.println("TXN_NO id " + id);
			} catch (Exception e) {
				System.out.println("Error in TXN_NO retrieval" + e.toString());
			}
		} catch (Exception e) {
			System.out.println("Error in data retrieval" + e.toString());
		}
		return id;

	}

	@Override
	public double retrievecashtxnno() {
		System.out.println("retrieveCustomerNo in ");
		double id = 0;
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from CASHPAYOUT");
			System.out.println("result in ");

			try {
				System.out.println("TXNNO in " + result.size());
				System.out.println("TXNNO in SADASD" + result.get(result.size() - 1).get("TXNNO"));
				String TXNNO = result.get(result.size() - 1).get("TXNNO").toString();
				System.out.println("TXNNO in " + TXNNO);

				String sub = TXNNO.substring(4, 16).toString();
				System.out.println("TXNNO in substring" + sub);
				// sub = "2017000000001";
				id = Double.valueOf(sub);
				System.out.println(String.format("%12f", id));

				System.out.println("cusTXNNOtomer id before" + String.valueOf(id));
				id++;
				System.out.println("TXNNO id " + id);
			} catch (Exception e) {
				System.out.println("Error in TXNNO retrieval" + e.toString());
			}
		} catch (Exception e) {
			System.out.println("Error in data retrieval" + e.toString());
		}
		return id;

	}

	@Override
	public int saveCPTransactionData(CPSenderReceiver reg) {
		int i = 0;
		try {
			i = jdbcTemplate.update("insert into CPSENDERRECEIVER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, reg.getTXNNO(), reg.getBENEFICIARYNO(), reg.getBENEFICIARYFIRSTNAME(),
							reg.getBENEFICIARYMIDDLENAME(), reg.getBENEFICIARYLASTNAME(), reg.getBENEFICIARYPOBOX(),
							reg.getBENEFICIARYADDRESS1(), reg.getBENEFICIARYADDRESS2(), reg.getBENEFICIARYADDRESSCITY(),
							reg.getBENEFICIARYADDRESSSTATE(), reg.getBENEFICIARYADDRESSCOUNTRYCODE(),
							reg.getBENEFICIARYADDRESSZIP(), reg.getBENEFICIARYPHONE(), reg.getBENEFICIARYMOBILE(),
							reg.getBENEFICIARYEMAIL(), reg.getORDERID() });
		} catch (Exception e) {
			System.out.println("Error in inserting CPSENDERRECEIVER table" + e.toString());
		}
		return i;
	}

	@Override
	public Map<String, Object> retrieveAccountACData(String currdate) {
		System.out.println("result asdasdasd");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			result = jdbcTemplate.queryForList("select * from ACCOUNTCREDIT where created_on like  ?  ",
					new Object[] { "%" + currdate + "%" });
		} catch (Exception e) {
			System.out.println("result exce" + e.toString());
		}
		System.out.println("result " + result.size());
		List<Map<String, Object>> acoutput = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> accoutput = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> customer = new ArrayList<Map<String, Object>>();
		String cus = null;
		Map<String, Object> output = new HashMap<String, Object>();
		String from = null;
		String to = null;
		try {
			from = result.get(0).get("txn_no").toString();
			to = result.get(result.size() - 1).get("txn_no").toString();
			System.out.println("from " + from);
			System.out.println("to " + to);
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("transcation_id", result.get(i).get("transcation_id"));
				obj.put("account_ccy_code", result.get(i).get("account_ccy_code"));
				obj.put("authorised_by", result.get(i).get("authorised_by"));
				obj.put("authorised_on", result.get(i).get("authorised_on"));
				obj.put("commission", result.get(i).get("commission"));
				obj.put("created_by", result.get(i).get("created_by"));
				obj.put("created_on", result.get(i).get("created_on"));
				obj.put("credited_date", result.get(i).get("credited_date"));
				obj.put("customer_no", result.get(i).get("customer_no"));
				obj.put("drawee_bank_code", result.get(i).get("drawee_bank_code"));
				obj.put("ibn_pay_txn_payin2account_ccy_rate", result.get(i).get("ibn_pay_txn_payin2account_ccy_rate"));
				obj.put("modified_by", result.get(i).get("modified_by"));
				obj.put("modified_on", result.get(i).get("modified_on"));
				obj.put("partner_ref_no", result.get(i).get("partner_ref_no"));
				obj.put("payin_amount", result.get(i).get("payin_amount"));
				obj.put("payinccy_code", result.get(i).get("payinccy_code"));
				obj.put("payment_mode", result.get(i).get("payment_mode"));
				obj.put("sauth_reference", result.get(i).get("sauth_reference"));
				obj.put("spartnerid", result.get(i).get("spartnerid"));
				obj.put("total_payin_amount", result.get(i).get("total_payin_amount"));
				obj.put("transfer_amount", result.get(i).get("transfer_amount"));
				obj.put("txn_date", result.get(i).get("txn_date"));
				obj.put("txngmtdate", result.get(i).get("txngmtdate"));
				obj.put("txn_no", result.get(i).get("txn_no"));
				obj.put("txn_pay_txn_payin2account_ccy_rate", result.get(i).get("txn_pay_txn_payin2account_ccy_rate"));
				obj.put("txn_status", result.get(i).get("txn_status"));
				obj.put("value_date", result.get(i).get("value_date"));
				obj.put("order_id", result.get(i).get("order_id"));
				obj.put("total_amount", result.get(i).get("total_payin_amount"));// Float.parseFloat(result.get(i).get("txn_pay_txn_payin2account_ccy_rate").toString())*Float.parseFloat(result.get(i).get("total_payin_amount").toString())
				if (i == 0) {
					cus = "'" + result.get(i).get("customer_no").toString() + "'";
				} else {
					cus = "'" + result.get(i).get("customer_no").toString() + "'," + cus;
				}

				System.out.println("obj " + i + "==" + obj);
				accoutput.add(obj);

			}
			System.out.println("customer " + cus);

			List<Map<String, Object>> result1 = jdbcTemplate.queryForList(
					"select * from ACSENDERRECEIVER where TxnNo between ? and ? ", new Object[] { from, to });
			System.out.println("result1 " + result1.size());
			for (int i = 0; i < result1.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("ACSenderReceiverId", result1.get(i).get("ACSenderReceiverId"));
				obj.put("TxnNo", result1.get(i).get("TxnNo"));
				obj.put("CustomerNo", result1.get(i).get("CustomerNo"));
				obj.put("BeneficiaryNo", result1.get(i).get("BeneficiaryNo"));
				obj.put("BeneficiaryFirstName", result1.get(i).get("BeneficiaryFirstName"));
				obj.put("BeneficiaryMiddleName", result1.get(i).get("BeneficiaryMiddleName"));
				obj.put("BeneficiaryLastName", result1.get(i).get("BeneficiaryLastName"));
				obj.put("BeneficiaryPOBox", result1.get(i).get("BeneficiaryPOBox"));
				obj.put("BeneficiaryAddress1", result1.get(i).get("BeneficiaryAddress1"));
				obj.put("BeneficiaryAddress2", result1.get(i).get("BeneficiaryAddress2"));
				obj.put("BeneficiaryAddressCity", result1.get(i).get("BeneficiaryAddressCity"));
				obj.put("BeneficiaryAddressState", result1.get(i).get("BeneficiaryAddressState"));
				obj.put("BeneficiaryAddressCountryCode", result1.get(i).get("BeneficiaryAddressCountryCode"));
				obj.put("BeneficiaryAddressZip", result1.get(i).get("BeneficiaryAddressZip"));
				obj.put("BeneficiaryPhone", result1.get(i).get("BeneficiaryPhone"));
				obj.put("BeneficiaryMobile", result1.get(i).get("BeneficiaryMobile"));
				obj.put("BeneficiaryEmail", result1.get(i).get("BeneficiaryEmail"));
				obj.put("BeneficiaryAccountName", result1.get(i).get("BeneficiaryAccountName"));
				obj.put("BeneficiaryBankCode", result1.get(i).get("BeneficiaryBankCode"));
				obj.put("BeneficiaryBankName", result1.get(i).get("BeneficiaryBankName"));
				obj.put("BeneficiaryBankBranchName", result1.get(i).get("BeneficiaryBankBranchName"));
				obj.put("BeneficiaryBankAccountNo", result1.get(i).get("BeneficiaryBankAccountNo"));
				obj.put("SwiftIFSC", result1.get(i).get("SwiftIFSC"));
				obj.put("BeneficiaryAccountCcyCode", result1.get(i).get("BeneficiaryAccountCcyCode"));
				obj.put("OrderId", result1.get(i).get("OrderId"));
				System.out.println("obj k " + i + "--" + obj);
				acoutput.add(obj);
			}
			List<Map<String, Object>> result2 = jdbcTemplate
					.queryForList("select * from CUSTOMER where CUSTOMERNO in (?)", new Object[] { cus });
			for (int i = 0; i < result1.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("name", result1.get(i).get("customerfirstname") + " " + result1.get(i).get("customermiddlename")
						+ " " + result1.get(i).get("customerlastname"));
				customer.add(obj);
			}
			System.out.println("account " + accoutput);
			System.out.println("acsender " + acoutput);
			output.put("account", accoutput);
			output.put("acsender", acoutput);
			// output.put("customer", customer);

		} catch (Exception e) {
			System.out.println("excep acdghb " + e.toString());
		}
		return output;
	}

	@Override
	public Map<String, Object> retrieveCashCPData(String currdate) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"select * from CASHPAYOUT where createdon like ?  ", new Object[] { "%" + currdate + "%" });
		List<Map<String, Object>> acoutput = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> accoutput = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> customer = new ArrayList<Map<String, Object>>();
		String cus = null;
		Map<String, Object> output = new HashMap<String, Object>();
		String from = null;
		String to = null;
		try {
			from = result.get(0).get("txnno").toString();
			to = result.get(result.size() - 1).get("txnno").toString();
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("cashpayoutid", result.get(i).get("cashpayoutid"));
				obj.put("cashpayoutid", result.get(i).get("cashpayoutid"));
				obj.put("agent_code", result.get(i).get("agentcode"));
				obj.put("authorisedby", result.get(i).get("authorisedby"));
				obj.put("authorisedon", result.get(i).get("authorisedon"));
				obj.put("commission", result.get(i).get("commission"));
				obj.put("createdby", result.get(i).get("createdby"));
				obj.put("createdon", result.get(i).get("createdon"));
				obj.put("customerno", result.get(i).get("customerno"));
				obj.put("ibanpaytxnpayin2accountccyrate", result.get(i).get("ibanpaytxnpayin2accountccyrate"));
				obj.put("modifiedby", result.get(i).get("modifiedby"));
				obj.put("modifiedon", result.get(i).get("modifiedon"));
				obj.put("partnerrefno", result.get(i).get("partnerrefno"));
				obj.put("payinamount", result.get(i).get("payinamount"));
				obj.put("payincccode", result.get(i).get("payincccode"));
				obj.put("paymentmode", result.get(i).get("paymentmode"));
				obj.put("sauthreference", result.get(i).get("sauthreference"));
				obj.put("spartnerid", result.get(i).get("spartnerid"));
				obj.put("totalpayinamount", result.get(i).get("totalpayinamount"));
				obj.put("transferamount", result.get(i).get("transferamount"));
				obj.put("txndate", result.get(i).get("txndate"));
				obj.put("txngmtdate", result.get(i).get("txngmtdate"));
				obj.put("txnno", result.get(i).get("txnno"));
				obj.put("txnpaytxnpayin2accountccyrate", result.get(i).get("txnpaytxnpayin2accountccyrate"));
				obj.put("txnstatus", result.get(i).get("txnstatus"));
				obj.put("paiddate", result.get(i).get("paiddate"));
				obj.put("valuedate", result.get(i).get("valuedate"));
				obj.put("orderid", result.get(i).get("orderid"));
				obj.put("total_amount", result.get(i).get("totalpayinamount"));// Float.parseFloat(result.get(i).get("txnpaytxnpayin2accountccyrate").toString())*Float.parseFloat(result.get(i).get("totalpayinamount").toString()));
				accoutput.add(obj);
			}
			List<Map<String, Object>> result1 = jdbcTemplate.queryForList(
					"select * from CPSENDERRECEIVER where TXNNO between ? and ? ", new Object[] { from, to });
			for (int i = 0; i < result1.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("CPSENDERRECEIVERID", result1.get(i).get("CPSENDERRECEIVERID"));
				obj.put("TXNNO", result1.get(i).get("TXNNO"));
				obj.put("BENEFICIARYNO", result1.get(i).get("BENEFICIARYNO"));
				obj.put("BENEFICIARYFIRSTNAME", result1.get(i).get("BENEFICIARYFIRSTNAME"));
				obj.put("BENEFICIARYMIDDLENAME", result1.get(i).get("BENEFICIARYMIDDLENAME"));
				obj.put("BENEFICIARYLASTNAME", result1.get(i).get("BENEFICIARYLASTNAME"));
				obj.put("BENEFICIARYPOBOX", result1.get(i).get("BENEFICIARYPOBOX"));
				obj.put("BENEFICIARYADDRESS1", result1.get(i).get("BENEFICIARYADDRESS1"));
				obj.put("BENEFICIARYADDRESS2", result1.get(i).get("BENEFICIARYADDRESS2"));
				obj.put("BENEFICIARYADDRESSCITY", result1.get(i).get("BENEFICIARYADDRESSCITY"));
				obj.put("BENEFICIARYADDRESSSTATE", result1.get(i).get("BENEFICIARYADDRESSSTATE"));
				obj.put("BENEFICIARYADDRESSCOUNTRYCODE", result1.get(i).get("BENEFICIARYADDRESSCOUNTRYCODE"));
				obj.put("BENEFICIARYADDRESSZIP", result1.get(i).get("BENEFICIARYADDRESSZIP"));
				obj.put("BENEFICIARYPHONE", result1.get(i).get("BENEFICIARYPHONE"));
				obj.put("BENEFICIARYMOBILE", result1.get(i).get("BENEFICIARYMOBILE"));
				obj.put("BENEFICIARYEMAIL", result1.get(i).get("BENEFICIARYEMAIL"));
				obj.put("ORDERID", result1.get(i).get("ORDERID"));
				acoutput.add(obj);
			}
			output.put("cash", accoutput);
			output.put("cpsender", acoutput);
		} catch (Exception e) {

		}
		return output;
	}

	@Override
	public List<Map<String, Object>> retrieveaccountTransaction() {
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT b.BeneficiaryAddressCountryCode,"
					+ "  b.BeneficiaryAccountCcyCode,  b.BeneficiaryAddressCity,  a.txn_date,"
					+ "  a.transfer_amount,  a.txn_pay_txn_payin2account_ccy_rate,  a.txn_no,"
					+ "  a.payinccy_code,"
					+ "  concat_ws(' ', e.customerfirstname, e.customermiddlename, e.customerlastname)as name,"
					+ "  a.total_payin_amount,  b.SwiftIFSC,  b.BeneficiaryBankAccountNo,  b.OrderId,"
					+ "  ROUND(a.payin_amount,2)as payin,  a.commission FROM ACCOUNTCREDIT a"
					+ ", ACSENDERRECEIVER b ,SBANKRECEIPTS c ,BENEFICIARY d"
					+ ", CUSTOMER e WHERE a.txn_no = b.TxnNo AND a.txn_no = c.txnno"
					+ " AND a.txn_date >= curdate() AND a.customer_no = d.customerno"
					+ " AND b.BeneficiaryNo = d.beneficiaryno AND a.customer_no = e.customerno"
					+ " ORDER BY a.txn_date ,b.BeneficiaryAccountCcyCode ", new Object[] {});
			System.out.println("result size "+result.size());
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> obj = new HashMap<String, Object>();
					obj.put("country", result.get(i).get("BeneficiaryAddressCountryCode"));
					obj.put("accountccy_code", result.get(i).get("BeneficiaryAccountCcyCode"));
					obj.put("city", result.get(i).get("BeneficiaryAddressCity"));
					obj.put("txn_date", result.get(i).get("txn_date"));
					obj.put("transfer_amount", result.get(i).get("transfer_amount"));
					obj.put("rate", result.get(i).get("txn_pay_txn_payin2account_ccy_rate"));
					obj.put("txn_no", result.get(i).get("txn_no"));
					obj.put("payinccy_code", result.get(i).get("payinccy_code"));
					obj.put("name", result.get(i).get("name"));
					obj.put("total_amount", result.get(i).get("total_payin_amount"));
					obj.put("ifsc", result.get(i).get("SwiftIFSC"));
					obj.put("account_name", result.get(i).get("BeneficiaryBankAccountNo"));
					obj.put("orderId", result.get(i).get("OrderId"));
					obj.put("payin_amount", result.get(i).get("payin"));
					obj.put("commission", result.get(i).get("commission"));
					output.add(obj);
				}
				System.out.println("output size "+output);
			}
		} catch (Exception e) {
			System.out.println("Error in data retrieval" + e.toString());
		}
		return output;
	}

	@Override
	public List<Map<String, Object>> retrievecashTransaction() {
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {
			List<Map<String, Object>> result = jdbcTemplate
					.queryForList("SELECT  b.BENEFICIARYADDRESSCOUNTRYCODE,   a.accountccycode,"
							+ "  b.BENEFICIARYADDRESSCITY,   a.txndate,  a.transferamount,"
							+ "  a.txnpaytxnpayin2accountccyrate,  a.txnno,  a.payincccode,"
							+ "  concat_ws(' ', e.customerfirstname, e.customermiddlename, e.customerlastname)as name,"
							+ "  a.totalpayinamount,  a.orderid,  ROUND(a.payinamount,2)as payin,"
							+ "  a.commission FROM  CASHPAYOUT a ,CPSENDERRECEIVER b"
							+ ", SBANKRECEIPTS c ,CUSTOMER e WHERE a.txnno = b.TxnNo"
							+ " AND a.txnno = c.txnno AND a.txndate >= curdate() AND a.txnno = c.txnno"
							+ " AND a.customerno = e.customerno ORDER BY a.txndate", new Object[] {});
			System.out.println("result cashsize "+result.size());
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> obj = new HashMap<String, Object>();
					obj.put("country", result.get(i).get("BENEFICIARYADDRESSCOUNTRYCODE"));
					obj.put("accountccy_code", result.get(i).get("accountccycode"));
					obj.put("city", result.get(i).get("BENEFICIARYADDRESSCITY"));
					obj.put("txn_date", result.get(i).get("txndate"));
					obj.put("transfer_amount", result.get(i).get("transferamount"));
					obj.put("rate", result.get(i).get("txnpaytxnpayin2accountccyrate"));
					obj.put("txn_no", result.get(i).get("txnno"));
					obj.put("payinccy_code", result.get(i).get("payincccode"));
					obj.put("name", result.get(i).get("name"));
					obj.put("total_amount", result.get(i).get("totalpayinamount"));
					obj.put("orderId", result.get(i).get("orderid"));
					obj.put("payin_amount", result.get(i).get("payin"));
					obj.put("commission", result.get(i).get("commission"));
					output.add(obj);
				}
				System.out.println("cash size "+output);
			}
		} catch (Exception e) {
			System.out.println("Error in data retrieval" + e.toString());
		}
		return output;
	}

	@Override
	public List<Map<String, Object>> retrieveACSenderData(String from, String to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> retrieveCPSenderData(String from, String to) {
		// TODO Auto-generated method stub
		return null;
	}

}