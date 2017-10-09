package com.crosspay.payment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crosspay.payment.model.ACSenderReceiver;
import com.crosspay.payment.model.AccountCredit;
import com.crosspay.payment.model.CPSenderReceiver;
import com.crosspay.payment.model.CashPayment;
import com.crosspay.payment.model.CurrencyRates;
import com.crosspay.payment.model.Receiptpdf;
import com.crosspay.payment.model.SBankReceipts;
import com.crosspay.payment.model.SPartnerResponse;
import com.crosspay.payment.service.AmountTransferDao;
import com.crosspay.payment.service.CustomerDao;
import com.crosspay.payment.service.EmailService;
import com.crosspay.payment.service.RegisterDao;
import com.crosspay.payment.service.VersionDao;
import com.crosspay.payment.util.CommonUtil;

@Controller
public class AmountTransferController {

	Logger logger = LoggerFactory.getLogger(AmountTransferController.class);

	@RequestMapping(value = "/initiateTransaction", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String accountcreditdetails(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String beneficiaryno = null;
		String customerno = null;
		String userId = null;
		String payeccyCode = null;
		String paymentmode = null;
		String totalpayingamount = null;
		/*
		 * String fromccyrate = null; String toccyrate = null;
		 */
		String accountccycode = null;
		float commission = 0;

		String chectStatus = null;

		try {
			chectStatus = json.get("checkstatus").toString();

		} catch (Exception e) {

		}

		try {
			beneficiaryno = json.get("beneficiary_no").toString();
			userId = json.get("user_id").toString();
			payeccyCode = json.get("payeccyCode").toString();
			paymentmode = json.get("paymentmode").toString();
			totalpayingamount = json.get("totalpayingamount").toString();
			/*
			 * fromccyrate = json.get("fromccyrate").toString(); toccyrate =
			 * json.get("toccyrate").toString();
			 */
			accountccycode = json.get("accountccycode").toString();
			commission = Float.parseFloat(json.get("commission").toString());

			try {
				customerno = registerDao.retrieveCustomerNumber(userId);
				if (!beneficiaryno.equalsIgnoreCase("") || !(beneficiaryno.isEmpty())) {
					if (!userId.equalsIgnoreCase("") || !(userId.isEmpty())) {
						if (!payeccyCode.equalsIgnoreCase("") || !(payeccyCode.isEmpty())) {
							if (!paymentmode.equalsIgnoreCase("") || !(paymentmode.isEmpty())) {
								if (!totalpayingamount.equalsIgnoreCase("") || !(totalpayingamount.isEmpty())) {
									if (!json.get("count").toString().equalsIgnoreCase("")
											|| !(json.get("count").toString().isEmpty())) {

										CurrencyRates currencyrates = new CurrencyRates();
										CurrencyRates currencyrateoutput = new CurrencyRates();
										currencyrates.setCCYCODEFROM(json.get("payeccyCode").toString());
										currencyrates.setCCYCODETO(json.get("accountccycode").toString());
										float fromval = Float.parseFloat(json.get("count").toString());

										try {
											currencyrateoutput = versionDao.retieveData(currencyrates);

											Double out = (currencyrateoutput.getXCHGRATE()) * (fromval);
											int r = (int) Math.round(out * 100);
											out = r / 100.0;
											// out = Math.floor(out);

											Double toout = Double.parseDouble(totalpayingamount);

											int retval = 0;
											try {
												if (chectStatus.equals("reverse")) {

													retval = 0;

												} else {
													// retval = Double.compare(out, toout);
													retval = 0;
												}
											} catch (Exception e) {
												retval = 0;
											}

											// int retval = Double.compare(out, toout);
											if (retval == 0) {

												String orderid = "order"
														+ (new CommonUtil().generateNineDigitNumericString());
												List<Map<String, Object>> beneficiaryDetails = new ArrayList<Map<String, Object>>();
												try {
													beneficiaryDetails = customerDao
															.retrieveBeneficiaryData(beneficiaryno);

													String benname = beneficiaryDetails.get(0).get("first_name")
															.toString();
													try {
														ACSenderReceiver acsender = new ACSenderReceiver();
														acsender.setOrderId(orderid);
														acsender.setBeneficiaryFirstName(
																beneficiaryDetails.get(0).get("first_name").toString());
														try {
															acsender.setBeneficiaryMiddleName(beneficiaryDetails.get(0)
																	.get("middle_name").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryMiddleName(null);
														}
														acsender.setBeneficiaryLastName(
																beneficiaryDetails.get(0).get("last_name").toString());
														try {
															acsender.setBeneficiaryEmail(
																	beneficiaryDetails.get(0).get("email").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryEmail(null);
														}

														try {
															acsender.setBeneficiaryPhone(beneficiaryDetails.get(0)
																	.get("phone_num").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryPhone("");
														}
														acsender.setBeneficiaryAddressCountryCode(
																beneficiaryDetails.get(0).get("country").toString());
														try {
															acsender.setBeneficiaryPOBox(
																	beneficiaryDetails.get(0).get("pobox").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryPOBox("");
														}
														try {
															acsender.setBeneficiaryAddress1(beneficiaryDetails.get(0)
																	.get("address1").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryAddress1(null);
														}
														try {
															acsender.setBeneficiaryAddress2(beneficiaryDetails.get(0)
																	.get("address2").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryAddress2(null);
														}

														try {
															acsender.setBeneficiaryAddressCity(
																	beneficiaryDetails.get(0).get("city").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryAddressCity(null);
														}

														try {
															acsender.setBeneficiaryAddressState(
																	beneficiaryDetails.get(0).get("state").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryAddressState(null);
														}
														try {
															acsender.setBeneficiaryAddressZip(beneficiaryDetails.get(0)
																	.get("pincode").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryAddressZip(null);
														}

														try {
															acsender.setBeneficiaryMobile(
																	beneficiaryDetails.get(0).get("mobile").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryMobile(null);
														}
														acsender.setCustomerNo(customerno);
														acsender.setBeneficiaryNo(Integer.parseInt(beneficiaryno));

														int banktype = Integer.parseInt(
																beneficiaryDetails.get(0).get("bankType").toString());

														if (banktype == 0) {
															acsender.setBeneficiaryBankAccountNo(null);
															acsender.setBeneficiaryBankName(null);
															acsender.setBeneficiaryAccountCcyCode(
																	json.get("accountccycode").toString());
															acsender.setSwiftIFSC(null);
															acsender.setBeneficiaryBankCode(null);
															try {
																if (acsender.getBeneficiaryMiddleName() == null)
																	acsender.setBeneficiaryAccountName(acsender
																			.getBeneficiaryFirstName() + " "
																			+ acsender.getBeneficiaryLastName());
																else {
																	acsender.setBeneficiaryAccountName(acsender
																			.getBeneficiaryFirstName() + " "
																			+ acsender.getBeneficiaryMiddleName() + " "
																			+ acsender.getBeneficiaryLastName());
																}
															} catch (Exception e) {
																acsender.setBeneficiaryAccountName(null);
															}

															acsender.setBeneficiaryBankBranchName(null);
														} else {
															acsender.setBeneficiaryBankAccountNo(beneficiaryDetails
																	.get(0).get("account_num").toString());
															acsender.setBeneficiaryBankName(beneficiaryDetails.get(0)
																	.get("bank_name").toString());
															acsender.setBeneficiaryAccountCcyCode(
																	json.get("accountccycode").toString());
															try {
																acsender.setSwiftIFSC(beneficiaryDetails.get(0)
																		.get("ifsc").toString());
															} catch (Exception e) {
																acsender.setSwiftIFSC(null);
															}
															acsender.setBeneficiaryBankCode(null);
															try {
																if (acsender.getBeneficiaryMiddleName() == null)
																	acsender.setBeneficiaryAccountName(acsender
																			.getBeneficiaryFirstName() + " "
																			+ acsender.getBeneficiaryLastName());
																else {
																	acsender.setBeneficiaryAccountName(acsender
																			.getBeneficiaryFirstName() + " "
																			+ acsender.getBeneficiaryMiddleName() + " "
																			+ acsender.getBeneficiaryLastName());
																}
															} catch (Exception e) {
																acsender.setBeneficiaryAccountName(null);
															}
															try {
																acsender.setBeneficiaryBankBranchName(beneficiaryDetails
																		.get(0).get("branch_name").toString());
															} catch (Exception e) {
																acsender.setBeneficiaryBankBranchName(null);
															}
														}

														float amount = Float.parseFloat(String.valueOf(fromval));
														String transfertype = "AC";
														float finalcommission = 0;
														float finalam = 0;
														float finam = 0;
														try {
															/*
															 * finalcommission = amountTransferDao
															 * .retrieveCommission(amount, transfertype);
															 */
															finalcommission = amountTransferDao.retrieveACCommission(
																	amount, currencyrates.getCCYCODEFROM(),
																	currencyrates.getCCYCODETO());
															if (commission == finalcommission) {

															} else {
																resultjson.put("status", "406");
																resultjson.put("message", "Commission not matched.");
																return resultjson.toString();
															}
															finalam = (Float.parseFloat(
																	String.valueOf(currencyrateoutput.getXCHGRATE()))
																	* fromval);//
															finam = (amount + finalcommission);

														} catch (Exception e) {
															logger.error("Error in commission retrieval", e);
														}
														String txnno = "TXN_"
																+ new CommonUtil().generateSevenDigitNumericString();
														SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

														java.util.Date nowDate = new java.util.Date();
														String currentYear = formatNowYear.format(nowDate);

														double txn = amountTransferDao.retrieveaccounttxnno();

														if (txn == 0) {
															String num = currentYear + "20000000000" + 1;
															txnno = num;
														} else {

															String[] v = String.format("%12f", txn).split("\\.");

															String num = currentYear + v[0];
															txnno = num;
														}

														AccountCredit acc = new AccountCredit();
														acc.setTxnNo(txnno);
														acsender.setTxnNo(txnno);
														acc.setCommission((int) (finalcommission));
														acc.setCreatedOn(new Date());
														acc.setTxnDate(new Date());
														acc.setPartnerRefNo(null);
														acc.setCustomerNo(customerno);
														acc.setValueDate(null);
														acc.setPayinccyCode(payeccyCode);
														acc.setAccountCcyCode(accountccycode);
														acc.setDraweeBankCode(null);
														acc.setOrderId(orderid);
														acc.setAuthorisedBy(null);
														acc.setCreditedDate(null);
														acc.setSAuthReference(null);
														acc.setSPartnerID(null);
														acc.setAuthorisedOn(null);
														acc.setModifiedBy(null);
														acc.setModifiedOn(null);
														acc.setTxnGMTDate(null);
														acc.setTxnStatus(4);
														acc.setIBnPayTxnPayin2AccountCcyRate(0);
														acc.setTxnPayTxnPayin2AccountCcyRate(
																currencyrateoutput.getXCHGRATE());
														int r1 = (int) Math.round(amount * 100);
														double amount1 = r1 / 100.0;
														// acc.setTotalPayinAmount(amount1);
														acc.setTotalPayinAmount(finam);// amount

														acc.setTransferAmount(Float.parseFloat(totalpayingamount));
														acc.setPayinAmount(amount);
														/*
														 * acc.setTotalPayinAmount(amount);
														 * acc.setTransferAmount(finalam); acc.setPayinAmount(amount);
														 */
														acc.setCreatedBy(null);
														acc.setPaymentMode(transfertype);
														int success = amountTransferDao.saveTransactionData(acsender);
														int transfer = 0;
														try {
															transfer = amountTransferDao.saveAccountCreditData(acc);
														} catch (Exception e) {
															System.out.println("error in insertion of accountcredit "
																	+ e.toString());
														}

														if (success == 1) {
															resultjson.put("status", "200");
															resultjson.put("message", "success");
															resultjson.put("sitereference", "test_crosspay68993");
															resultjson.put("currencyiso3a",
																	currencyrates.getCCYCODEFROM());
															int r2 = (int) Math.round(finam * 100);
															double finalam1 = r2 / 100.0;
															resultjson.put("mainamount", finalam1);
															resultjson.put("version", "2");
															resultjson.put("orderreference", orderid);
															resultjson.put("link",
																	"https://payments.securetrading.net/process/payments/choice");
														} else {
															resultjson.put("status", "500");
															resultjson.put("message", "Internal Server Error");
														}
													} catch (Exception e) {
														System.out.println("Error sadsd " + e.toString());
														resultjson.put("status", "406");
														resultjson.put("message", "Internal Server Error");
													}
												} catch (Exception e) {
													resultjson.put("status", "406");
													resultjson.put("message",
															"Beneficiary/User doesn't exist for this user");
												}

											} else {
												resultjson.put("status", "406");
												resultjson.put("message", "Invalid Total Amount Please try again.");
											}

										} catch (Exception ex) {
											System.out.println("Error in currency retrieval" + ex.toString());
										}

									} else {
										resultjson.put("status", "406");
										resultjson.put("message", "count should not be empty");
									}
								} else {
									resultjson.put("status", "406");
									resultjson.put("message", "totalpayingamount should not be empty");
								}
							} else {
								resultjson.put("status", "406");
								resultjson.put("message", "paymentmode should not be empty");
							}
						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "payeccyCode should not be empty");
						}
					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "userId should not be empty");
					}
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Beneficiary No should not be empty");
				}

			} catch (Exception e) {
				System.out.println("error in customerno retrieval " + e.toString());
			}
		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/initiateCashTransaction", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String accountcashdetails(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String beneficiaryno = null;
		String customerno = null;
		String userId = null;
		String payeccyCode = null;
		String paymentmode = null;
		String totalpayingamount = null;
		/*
		 * String fromccyrate = null; String toccyrate = null;
		 */
		String accountccycode = null;
		String agentcode = null;
		float commission = 0;

		String chectStatus = null;

		try {
			chectStatus = json.get("checkstatus").toString();

		} catch (Exception e) {

		}

		try {
			beneficiaryno = json.get("beneficiary_no").toString();
			userId = json.get("user_id").toString();
			payeccyCode = json.get("payeccyCode").toString();
			paymentmode = json.get("paymentmode").toString();
			totalpayingamount = json.get("totalpayingamount").toString();
			/*
			 * fromccyrate = json.get("fromccyrate").toString(); toccyrate =
			 * json.get("toccyrate").toString();
			 */
			accountccycode = json.get("accountccycode").toString();
			commission = Float.parseFloat(json.get("commission").toString());

			try {
				customerno = registerDao.retrieveCustomerNumber(userId);
				if (!beneficiaryno.equalsIgnoreCase("") || !(beneficiaryno.isEmpty())) {
					if (!userId.equalsIgnoreCase("") || !(userId.isEmpty())) {
						if (!payeccyCode.equalsIgnoreCase("") || !(payeccyCode.isEmpty())) {
							if (!paymentmode.equalsIgnoreCase("") || !(paymentmode.isEmpty())) {
								if (!totalpayingamount.equalsIgnoreCase("") || !(totalpayingamount.isEmpty())) {
									if (!json.get("count").toString().equalsIgnoreCase("")
											|| !(json.get("count").toString().isEmpty())) {

										CurrencyRates currencyrates = new CurrencyRates();
										CurrencyRates currencyrateoutput = new CurrencyRates();
										currencyrates.setCCYCODEFROM(json.get("payeccyCode").toString());
										currencyrates.setCCYCODETO(json.get("accountccycode").toString());
										float fromval = Float.parseFloat(json.get("count").toString());
										System.out.println("fromrate " + fromval);
										try {
											currencyrateoutput = versionDao.retieveData(currencyrates);

											Double out = (currencyrateoutput.getXCHGRATE()) * (fromval);
											int r = (int) Math.round(out * 100);
											out = r / 100.0;
											// out = Math.floor(out);
											System.out.println("inside " + out);
											System.out.println("toccyrate " + totalpayingamount);
											Double toout = Double.parseDouble(totalpayingamount);
											System.out.println("toout " + toout);

											// int retval = Double.compare(out, toout);
											int retval = 0;
											try {
												if (chectStatus.equals("reverse")) {

													retval = 0;

												} else {
													// retval = Double.compare(out, toout);
													retval = 0;
												}
											} catch (Exception e) {
												retval = 0;
											}

											if (retval == 0) {

												String orderid = "order"
														+ (new CommonUtil().generateNineDigitNumericString());
												List<Map<String, Object>> beneficiaryDetails = new ArrayList<Map<String, Object>>();

												try {
													beneficiaryDetails = customerDao
															.retrieveBeneficiaryData(beneficiaryno);
													System.out.println("beneficiaryDetails " + beneficiaryDetails);
													String benname = beneficiaryDetails.get(0).get("first_name")
															.toString();
													try {
														CPSenderReceiver acsender = new CPSenderReceiver();
														acsender.setORDERID(orderid);
														acsender.setBENEFICIARYFIRSTNAME(
																beneficiaryDetails.get(0).get("first_name").toString());
														try {
															acsender.setBENEFICIARYMIDDLENAME(beneficiaryDetails.get(0)
																	.get("middle_name").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYMIDDLENAME(null);
														}
														acsender.setBENEFICIARYLASTNAME(
																beneficiaryDetails.get(0).get("last_name").toString());
														try {
															acsender.setBENEFICIARYEMAIL(
																	beneficiaryDetails.get(0).get("email").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYEMAIL(null);
														}

														try {
															acsender.setBENEFICIARYPHONE(beneficiaryDetails.get(0)
																	.get("phone_num").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYPHONE(null);
														}
														acsender.setBENEFICIARYADDRESSCOUNTRYCODE(
																beneficiaryDetails.get(0).get("country").toString());
														try {
															acsender.setBENEFICIARYPOBOX(
																	beneficiaryDetails.get(0).get("pobox").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYPOBOX("");
														}
														try {
															acsender.setBENEFICIARYADDRESS1(beneficiaryDetails.get(0)
																	.get("address1").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYADDRESS1(null);
														}
														try {
															acsender.setBENEFICIARYADDRESS2(beneficiaryDetails.get(0)
																	.get("address2").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYADDRESS2(null);
														}

														try {
															acsender.setBENEFICIARYADDRESSCITY(
																	beneficiaryDetails.get(0).get("city").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYADDRESSCITY(null);
														}

														try {
															acsender.setBENEFICIARYADDRESSSTATE(
																	beneficiaryDetails.get(0).get("state").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYADDRESSSTATE(null);
														}

														try {
															acsender.setBENEFICIARYADDRESSZIP(beneficiaryDetails.get(0)
																	.get("pincode").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYADDRESSZIP(null);
														}

														try {
															acsender.setBENEFICIARYMOBILE(
																	beneficiaryDetails.get(0).get("mobile").toString());
														} catch (Exception e) {
															acsender.setBENEFICIARYMOBILE(null);
														}
														acsender.setCUSTOMERNO(customerno);
														acsender.setBENEFICIARYNO(Integer.parseInt(beneficiaryno));

														int banktype = Integer.parseInt(
																beneficiaryDetails.get(0).get("bankType").toString());

														/*
														 * if (banktype == 0) {
														 * acsender.setBeneficiaryBankAccountNo(null);
														 * acsender.setBeneficiaryBankName(null);
														 * acsender.setBeneficiaryAccountCcyCode(json.get(
														 * "accountccycode").toString()); acsender.setSwiftIFSC(null);
														 * acsender.setBeneficiaryBankCode(null);
														 * acsender.setBeneficiaryAccountName(null);
														 * acsender.setBeneficiaryBankBranchName(null); } else {
														 * acsender.setBeneficiaryBankAccountNo(beneficiaryDetails
														 * .get(0).get("account_num").toString());
														 * acsender.setBeneficiaryBankName(beneficiaryDetails.get(0)
														 * .get("bank_name").toString());
														 * acsender.setBeneficiaryAccountCcyCode(
														 * json.get("accountccycode").toString());
														 * acsender.setSwiftIFSC(
														 * beneficiaryDetails.get(0).get("ifsc").toString());
														 * acsender.setBeneficiaryBankCode(null);
														 * acsender.setBeneficiaryAccountName(null);
														 * acsender.setBeneficiaryBankBranchName(null); }
														 */
														System.out.println("ascender val " + acsender);
														float amount = Float.parseFloat(String.valueOf(fromval));
														String transfertype = "CP";
														float finalcommission = 0;
														float finalam = 0;
														float fineam = 0;
														try {
															/*
															 * finalcommission = amountTransferDao
															 * .retrieveCommission(amount, transfertype);
															 */
															finalcommission = amountTransferDao.retrieveCPCommission(
																	amount, currencyrates.getCCYCODEFROM(),
																	currencyrates.getCCYCODETO());
															if (commission == finalcommission) {

															} else {
																resultjson.put("status", "406");
																resultjson.put("message", "Commission not matched.");
																return resultjson.toString();
															}
															finalam = (Float.parseFloat(
																	String.valueOf(currencyrateoutput.getXCHGRATE()))
																	* amount);//
															fineam = (amount + finalcommission);

														} catch (Exception e) {
															logger.error("Error in commission retrieval", e);
														}

														CashPayment acc = new CashPayment();
														String txnno = "TXN_"
																+ new CommonUtil().generateSevenDigitNumericString();
														SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

														java.util.Date nowDate = new java.util.Date();
														String currentYear = formatNowYear.format(nowDate);

														double txn = amountTransferDao.retrievecashtxnno();

														if (txn == 0) {
															String num = currentYear + "30000000000" + 1;
															txnno = num;
														} else {
															System.out.println("vvv txnno " + txn);
															String[] v = String.format("%12f", txn).split("\\.");
															System.out.println("vvv " + v);
															String num = currentYear + v[0];
															txnno = num;
														}
														acc.setTXNNO(txnno);
														acsender.setTXNNO(txnno);
														acc.setCOMMISSION(finalcommission);
														acc.setCREATEDON(new Date());
														acc.setTXNDATE(new Date());
														acc.setPARTNERREFNO(null);
														acc.setCUSTOMERNO(customerno);
														acc.setVALUEDATE(null);
														acc.setPAYINCCCODE(payeccyCode);
														acc.setACCOUNTCCYCODE(accountccycode);
														try {
															acc.setAGENTCODE(json.get("agent_code").toString());
														} catch (Exception e) {
															acc.setAGENTCODE(null);
														}
														acc.setORDERID(orderid);
														acc.setAUTHORISEDBY(null);
														acc.setPAIDDATE(null);
														acc.setSAUTHREFERENCE(null);
														acc.setSPARTNERID(null);
														acc.setAUTHORISEDON(null);
														acc.setMODIFIEDBY(null);
														acc.setMODIFIEDON(null);
														acc.setTXNGMTDATE(null);
														acc.setTXNSTATUS(4);
														acc.setIBANPAYTXNPAYIN2ACCOUNTCCYRATE(0);
														acc.setTXNPAYTXNPAYIN2ACCOUNTCCYRATE(
																currencyrateoutput.getXCHGRATE());
														int r1 = (int) Math.round(amount * 100);
														double amount1 = r1 / 100.0;
														// acc.setTOTALPAYINAMOUNT(amount1);
														acc.setTOTALPAYINAMOUNT(fineam);// amount
														acc.setTRANSFERAMOUNT(Float.parseFloat(totalpayingamount));
														acc.setPAYINAMOUNT(amount);
														/*
														 * acc.setTOTALPAYINAMOUNT(amount);
														 * acc.setTRANSFERAMOUNT(finalam); acc.setPAYINAMOUNT(amount);
														 */
														acc.setCREATEDBY(null);
														acc.setPAYMENTMODE("0");
														int success = amountTransferDao.saveCPTransactionData(acsender);
														int transfer = 0;
														try {
															transfer = amountTransferDao.saveCashCreditData(acc);
														} catch (Exception e) {
															System.out.println("error in insertion of accountcredit "
																	+ e.toString());
														}

														if (success == 1 && transfer == 1) {
															resultjson.put("status", "200");
															resultjson.put("message", "success");
															resultjson.put("sitereference", "test_crosspay68993");
															resultjson.put("currencyiso3a",
																	currencyrates.getCCYCODEFROM());
															int r2 = (int) Math.round(fineam * 100);
															double finalam1 = r2 / 100.0;
															resultjson.put("mainamount", finalam1);
															resultjson.put("version", "2");
															resultjson.put("orderreference", orderid);
															resultjson.put("link",
																	"https://payments.securetrading.net/process/payments/choice");
														} else {
															resultjson.put("status", "500");
															resultjson.put("message", "Internal Server Error");
														}
													} catch (Exception e) {
														resultjson.put("status", "406");
														resultjson.put("message", "Internal Server Error");
													}
												} catch (Exception e) {
													resultjson.put("status", "406");
													resultjson.put("message",
															"Beneficiary/User doesn't exist for this user");
												}

											} else {
												resultjson.put("status", "406");
												resultjson.put("message", "Invalid Total Amount Please try again.");
											}

										} catch (Exception ex) {
											System.out.println("Error in currency retrieval" + ex.toString());
										}

									} else {
										resultjson.put("status", "406");
										resultjson.put("message", "count should not be empty");
									}
								} else {
									resultjson.put("status", "406");
									resultjson.put("message", "totalpayingamount should not be empty");
								}
							} else {
								resultjson.put("status", "406");
								resultjson.put("message", "paymentmode should not be empty");
							}
						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "payeccyCode should not be empty");
						}
					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "userId should not be empty");
					}
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Beneficiary No should not be empty");
				}

			} catch (Exception e) {
				System.out.println("error in customerno retrieval " + e.toString());
			}
		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/capturePayment", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String capturePayment(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String beneficiaryno = null;
		String customerno = null;
		String userId = null;
		String payeccyCode = null;
		String totalpayingamount = null;
		String transactionrefno = null;
		AccountCredit acc = new AccountCredit();
		CashPayment cp = new CashPayment();

		String chectStatus = "";

		try {
			chectStatus = json.get("checkstatus").toString();

		} catch (Exception e) {
			System.out.println("TOTAL AMOUNT IS 112" + chectStatus);
		}

		try {
			beneficiaryno = json.get("beneficiary_no").toString();
			userId = json.get("user_id").toString();
			payeccyCode = json.get("payeccyCode").toString();
			totalpayingamount = json.get("totalpayingamount").toString();
			transactionrefno = fetchTransactionNum(json.get("order_id").toString(), payeccyCode);
			// transactionrefno = json.get("transactionrefno").toString();

			if (transactionrefno == null) {
				resultjson.put("status", "300");
				resultjson.put("message", "Transaction not initiated to Secure Trading.");
			} else {
				try {

					customerno = registerDao.retrieveCustomerNumber(userId);
					logger.info("customerno in capturepayment " + customerno);
					URL url = new URL("https://webservices.securetrading.net/json/");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					// String authStr = "Username:manish@crosspayments.com,Password:N(]sp4PL";
					conn.setDoOutput(true);
					conn.setUseCaches(true);
					conn.setDoInput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Authorization", "Basic bWFuaXNoQGNyb3NzcGF5bWVudHMuY29tOk4oXXNwNFBM");
					// conn.setContentHandlerFactory(fac);
					conn.setRequestProperty("accept", "application/json");

					String input = "{\r\n" + "\"alias\": \"manish@crosspayments.com\",\r\n"
							+ "\"version\": \"1.00\",\r\n" + "\"request\": [{\r\n"
							+ "  \"requesttypedescriptions\": [\"TRANSACTIONQUERY\"],\r\n" + "  \"filter\":{\r\n"
							+ "    \"sitereference\": [{\"value\":\"test_crosspay68993\"}],\r\n"
							+ "    \"currencyiso3a\": [{\"value\":\"" + payeccyCode + "\"}],\r\n"
							+ "    \"transactionreference\": [{\"value\":\"" + transactionrefno + "\"},{\"value\":\""
							+ transactionrefno + "\"}]\r\n" + "  }\r\n" + "}]}";

					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();

					if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {

						InputStream content = (InputStream) conn.getInputStream();
						BufferedReader in = new BufferedReader(new InputStreamReader(content));
						String line;
						StringBuffer sb = new StringBuffer();
						while ((line = in.readLine()) != null) {
							sb.append(line);
							// System.out.println(line);
						}
						JSONObject output = new JSONObject();
						JSONArray res = new JSONArray();
						try {

							output = new JSONObject(sb.toString());
							res = output.getJSONArray("response");
							JSONObject actualoutput = new JSONObject(res.get(0).toString());

							JSONArray records = actualoutput.getJSONArray("records");
							JSONObject finalres = records.getJSONObject(0);

							String startDateString = null;
							if (finalres.getString("errorcode").equalsIgnoreCase("0")
									&& finalres.getString("errormessage").equalsIgnoreCase("ok")) {

								try {
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
									SPartnerResponse spr = new SPartnerResponse();
									try {
										startDateString = finalres.getString("settleduetimestamp");
										spr.setSETTLEDDUEDATE(df.parse(startDateString));
									} catch (Exception e) {
										try {
											startDateString = finalres.getString("settledtimestamp");
											spr.setSETTLEDDUEDATE(df.parse(startDateString));
										} catch (Exception e1) {
											spr.setSETTLEDDUEDATE(null);
										}
									}

									String emailerr = "";
									spr.setERRORMESSAGE(actualoutput.getString("errormessage"));
									spr.setAUTHCODE(finalres.getString("authcode"));
									spr.setDCCENABLED(Integer.parseInt(finalres.getString("dccenabled")));
									spr.setACCOUNTTYPEDESC(finalres.getString("accounttypedescription"));
									spr.setACQUIRERRESPONSECODE(finalres.getString("acquirerresponsecode"));
									spr.setBASEAMOUNT(Integer.parseInt(finalres.getString("baseamount")));
									spr.setISSUER(finalres.getString("issuer"));
									spr.setISSUERCOUNTRYCODE(finalres.getString("issuercountryiso2a"));
									spr.setLIVESTATUS(Integer.parseInt(finalres.getString("livestatus")));
									spr.setSPLITFINALNO(Integer.parseInt(finalres.getString("splitfinalnumber")));
									spr.setCAVV(null);
									spr.setECI(null);
									spr.setENROLLED(null);

									spr.setMERCHANTNAME(finalres.getString("merchantname"));
									spr.setOPERATORNAME(finalres.getString("operatorname"));
									spr.setMERCHANTCOUNTRYCODE(finalres.getString("merchantcountryiso2a"));
									spr.setMASKEDPAN(finalres.getString("maskedpan"));
									spr.setPAYMENTTYPEDESC(finalres.getString("paymenttypedescription"));
									spr.setREQUESTTYPEDESC(finalres.getString("requesttypedescription"));
									spr.setSECURITYRESPONSEADDRESS(
											Integer.parseInt(finalres.getString("securityresponseaddress")));
									spr.setSECURITYRESPONSEPOSTCODE(
											Integer.parseInt(finalres.getString("securityresponsepostcode")));

									spr.setSECURITYRESPONSESECCODE(
											Integer.parseInt(finalres.getString("securityresponsesecuritycode")));

									spr.setSETTLESTATUS(Integer.parseInt(finalres.getString("settlestatus")));
									spr.setTID(Integer.parseInt(finalres.getString("tid")));
									spr.setTXNREF(finalres.getString("transactionreference"));
									spr.setTXNSTARTTIMESTAMP(
											df.parse(finalres.getString("transactionstartedtimestamp")));
									spr.setACCOUNTTYPEDESC(finalres.getString("accounttypedescription"));
									spr.setXID(null);
									spr.setSTATUS("0");
									spr.setSPARTERID(null);
									spr.setPARENTTXNREF(null);
									spr.setMERCHANTNO(null);
									spr.setERRORCODE(Integer.parseInt(finalres.getString("errorcode")));
									spr.setAUTHMETHOD(null);
									String orderid = finalres.getString("orderreference");
									spr.setORDERID(orderid);

									try {
										int savespr = amountTransferDao.savePartnerData(spr);

										if (savespr == 1) {
											List<Map<String, Object>> beneficiaryDetails = customerDao
													.retrieveBeneficiaryData(beneficiaryno);

											int spid = amountTransferDao.retrievespartnerid();
											int updateacsender = 0;
											/*
											 * try { updateacsender = amountTransferDao
											 * .acsendertxno(finalres.getString("transactionreference"), orderid); }
											 * catch (Exception e) { System.out.println("error in updating acsender " +
											 * e.toString()); }
											 * 
											 * try { updateacsender = amountTransferDao
											 * .cpsendertxno(finalres.getString("transactionreference"), orderid); }
											 * catch (Exception e) { System.out.println("error in updating acsender " +
											 * e.toString()); }
											 */
											try {
												acc.setSAuthReference(finalres.getString("authcode"));
												// acc.setTxnNo(finalres.getString("transactionreference"));
												acc.setTxnDate(
														df.parse(finalres.getString("transactionstartedtimestamp")));
												acc.setOrderId(orderid);
												acc.setSPartnerID(String.valueOf(spid));
												try {
													if (spr.getERRORCODE() == 0) {
														acc.setTxnStatus(3);
													} else {
														acc.setTxnStatus(spr.getERRORCODE());
													}
												} catch (Exception e) {
													acc.setTxnStatus(4);
												}

												int accs = 0;
												try {
													accs = amountTransferDao.updateaccountcredit(acc);
													// ACCOUNTCREDIT RECEIPT PDF
													if (accs > 0) {
														String val = "4";
														Receiptpdf receiptpdf = new Receiptpdf();
														List<Map<String, Object>> retrieveccountdetails = amountTransferDao
																.retrieveaccountpdfviewdetails(customerno, orderid);
														List<Map<String, Object>> customerdetails = registerDao
																.retrieveCustomerProfile(customerno);
														receiptpdf.setReceivername(
																retrieveccountdetails.get(0).get("name").toString());
														receiptpdf.setTransaction(
																retrieveccountdetails.get(0).get("txn_no").toString()
																		.replaceAll("(.{" + val + "})", "$0 ").trim());

														receiptpdf.setType("ACCOUNT CREDIT");// retrieveccountdetails.get(0).get("accout_type").toString()
														receiptpdf.setTransactiondate(retrieveccountdetails.get(0)
																.get("txn_date").toString());

														receiptpdf.setPaymentmode(spr.getACCOUNTTYPEDESC());

														receiptpdf.setCardtxnref(spr.getMASKEDPAN());

														receiptpdf.setReceivermobile(
																retrieveccountdetails.get(0).get("mobile").toString());
														receiptpdf.setReceiveraddress(
																retrieveccountdetails.get(0).get("city").toString()
																		+ "," + retrieveccountdetails.get(0)
																				.get("country").toString());

														/*
														 * try {
														 * receiptpdf.setAccountname(retrieveccountdetails.get(0).get(
														 * "name").toString()); }catch(Exception e) {
														 * receiptpdf.setAccountname("-----"); }
														 * 
														 * try {
														 * receiptpdf.setAccountnum(retrieveccountdetails.get(0).get(
														 * "accout_num").toString().replaceAll("(.{" + val + "})",
														 * "$0 ").trim()); }catch(Exception e) {
														 * receiptpdf.setAccountnum("-----"); } try {
														 * receiptpdf.setBank(retrieveccountdetails.get(0).get("bank")
														 * .toString());
														 * receiptpdf.setIfsc(retrieveccountdetails.get(0).get("ifsc").
														 * toString()); }catch(Exception e) {
														 * receiptpdf.setBank("-----"); receiptpdf.setIfsc("-----");
														 * System.out.println("error in bank data retrieval "+e.toString
														 * ()); }
														 */

														try {

															if (retrieveccountdetails.get(0).get("name")
																	.toString() == null
																	|| retrieveccountdetails.get(0).get("name")
																			.toString().equals("")) {
																receiptpdf.setAccountname("----");
															} else {
																receiptpdf.setAccountname(retrieveccountdetails.get(0)
																		.get("name").toString());
															}

														} catch (Exception e) {
															receiptpdf.setAccountname("----");
														}

														try {

															if (retrieveccountdetails.get(0).get("accout_num")
																	.toString() == null
																	|| retrieveccountdetails.get(0).get("accout_num")
																			.toString().equals("")) {
																receiptpdf.setAccountname("----");
															} else {
																receiptpdf.setAccountnum(retrieveccountdetails.get(0)
																		.get("accout_num").toString());
															}
														} catch (Exception e) {
															receiptpdf.setAccountnum("----");
														}

														try {
															if (retrieveccountdetails.get(0).get("bank_name")
																	.toString() == null
																	|| retrieveccountdetails.get(0).get("bank_name")
																			.toString().equals("")) {

																receiptpdf.setBank("----");

															} else {

																receiptpdf.setBank(retrieveccountdetails.get(0)
																		.get("bank_name").toString());

															}
														} catch (Exception e) {
															receiptpdf.setBank("----");
														}

														try {

															if (retrieveccountdetails.get(0).get("ifsc")
																	.toString() == null
																	|| retrieveccountdetails.get(0).get("ifsc")
																			.toString().equals("")) {
																receiptpdf.setIfsc("----");

															} else {
																receiptpdf.setIfsc(retrieveccountdetails.get(0)
																		.get("ifsc").toString());
															}

														} catch (Exception e) {
															receiptpdf.setIfsc("");
														}

														receiptpdf.setAccountccy(retrieveccountdetails.get(0)
																.get("account_ccycode").toString());
														receiptpdf.setPayeeccy(retrieveccountdetails.get(0)
																.get("payin_ccycode").toString());
														receiptpdf.setTransferfee(retrieveccountdetails.get(0)
																.get("commission").toString());
														receiptpdf.setCurrencyrate(
																retrieveccountdetails.get(0).get("rate").toString());
														// receiptpdf.setTransferamount(retrieveccountdetails.get(0).get("payee_amount").toString());
														// receiptpdf.setLocalamount(retrieveccountdetails.get(0).get("payin_amount").toString());
														// receiptpdf.setTotalorderamount(retrieveccountdetails.get(0).get("total_amount").toString());
														receiptpdf.setPaymentmode(spr.getPAYMENTTYPEDESC());

														try {
															receiptpdf.setCustomermobile(
																	customerdetails.get(0).get("mobile").toString());
														} catch (Exception e) {
															receiptpdf.setCustomermobile("---------");
														}

														receiptpdf.setCustoername(
																customerdetails.get(0).get("name").toString());

														receiptpdf.setCustomerno(customerno
																.replaceAll("(.{" + val + "})", "$0 ").trim());
														receiptpdf.setCustomeradd1(
																customerdetails.get(0).get("address1").toString());
														receiptpdf.setCustomeradd2(
																customerdetails.get(0).get("address2").toString());
														receiptpdf.setCustomercity(
																customerdetails.get(0).get("city").toString());
														receiptpdf.setCustomermail(
																customerdetails.get(0).get("email").toString());

														double rf = Math.round(Double.parseDouble(retrieveccountdetails
																.get(0).get("payee_amount").toString()) * 100);
														double amountf = rf / 100.0;

														receiptpdf.setTransferamount(retrieveccountdetails.get(0)
																.get("payee_amount").toString());
														receiptpdf.setLocalamount(retrieveccountdetails.get(0)
																.get("payin_amount").toString());

														rf = Math.round(Double.parseDouble(retrieveccountdetails.get(0)
																.get("total_amount").toString()) * 100);
														amountf = rf / 100.0;
														float orderamout = Float
																.parseFloat(retrieveccountdetails.get(0)
																		.get("commission").toString())
																+ Float.parseFloat(retrieveccountdetails.get(0)
																		.get("payin_amount").toString());
														receiptpdf.setTotalorderamount(retrieveccountdetails.get(0)
																.get("total_amount").toString());// String.valueOf(orderamout)//amountf
														emailerr = "before mail";
														try {
															emailerr = emailService.sendmailpdfdy(receiptpdf,
																	"AccountReceipt.html", "support@crosspaymt.com");
															// emailerr = "mail sent";
														} catch (Exception e) {
															System.out.println("error in sending mail " + e.toString());
															logger.info(
																	"error in sending mail account " + e.toString());
															emailerr = "mailsending " + e.toString();
														}

													}

												} catch (Exception e) {

													logger.info("error in updating accountcredit " + e.toString());
													emailerr = "updating " + e.toString();
												}
												resultjson.put("status", "200");
												resultjson.put("message", "success");
												resultjson.put("transaction_num", transactionrefno);
												// resultjson.put("emailerr",emailerr);
											} catch (Exception e) {
												System.out.println("error in adding accountcredit " + e.toString());
											}
											try {
												// cp.setTXNNO(finalres.getString("transactionreference"));
												cp.setSAUTHREFERENCE(finalres.getString("authcode"));
												cp.setTXNDATE(
														df.parse(finalres.getString("transactionstartedtimestamp")));
												cp.setORDERID(orderid);
												cp.setSPARTNERID(String.valueOf(spid));
												try {
													if (spr.getERRORCODE() == 0) {
														cp.setTXNSTATUS(3);
													} else {
														cp.setTXNSTATUS(spr.getERRORCODE());
													}
												} catch (Exception e) {
													cp.setTXNSTATUS(4);
												}
												System.out.println("spid " + spid);
												int accs = 0;
												try {
													accs = amountTransferDao.updatecashpay(cp);
													// CASHPAYOUT RECEIPT PDF

													if (accs > 0) {
														String val = "4";
														Receiptpdf receiptpdf = new Receiptpdf();
														List<Map<String, Object>> retrieveccountdetails = amountTransferDao
																.retrievecashpdfviewdetails(customerno, orderid);
														List<Map<String, Object>> customerdetails = registerDao
																.retrieveCustomerProfile(customerno);

														receiptpdf.setReceivername(
																retrieveccountdetails.get(0).get("name").toString());
														receiptpdf.setTransaction(
																retrieveccountdetails.get(0).get("txn_no").toString()
																		.replaceAll("(.{" + val + "})", "$0 ").trim());

														receiptpdf.setType("CASH PAYOUT");// retrieveccountdetails.get(0).get("accout_type").toString()
														receiptpdf.setTransactiondate(retrieveccountdetails.get(0)
																.get("txn_date").toString());

														receiptpdf.setPaymentmode(spr.getACCOUNTTYPEDESC());

														receiptpdf.setCardtxnref(spr.getMASKEDPAN());

														receiptpdf.setReceivermobile(
																retrieveccountdetails.get(0).get("mobile").toString());
														receiptpdf.setReceiveraddress(
																retrieveccountdetails.get(0).get("city").toString()
																		+ "," + retrieveccountdetails.get(0)
																				.get("country").toString());
														/*
														 * receiptpdf.setAccountname(retrieveccountdetails.get(0).get(
														 * "name").toString());
														 * receiptpdf.setAccountnum(retrieveccountdetails.get(0).get(
														 * "accout_num").toString()); try {
														 * receiptpdf.setBank(retrieveccountdetails.get(0).get("bank").
														 * toString());
														 * receiptpdf.setIfsc(retrieveccountdetails.get(0).get("ifsc").
														 * toString()); }catch(Exception e) {
														 * System.out.println("error in bank data retrieval "+e.toString
														 * ()); }
														 */

														receiptpdf.setAccountccy(retrieveccountdetails.get(0)
																.get("account_ccycode").toString());
														receiptpdf.setPayeeccy(retrieveccountdetails.get(0)
																.get("payin_ccycode").toString());
														receiptpdf.setTransferfee(retrieveccountdetails.get(0)
																.get("commission").toString());
														receiptpdf.setCurrencyrate(
																retrieveccountdetails.get(0).get("rate").toString());
														// receiptpdf.setTransferamount(retrieveccountdetails.get(0).get("payee_amount").toString());

														// receiptpdf.setLocalamount(retrieveccountdetails.get(0).get("payin_amount").toString());
														// receiptpdf.setTotalorderamount(retrieveccountdetails.get(0).get("total_amount").toString());
														receiptpdf.setPaymentmode(spr.getPAYMENTTYPEDESC());

														receiptpdf.setCustoername(
																customerdetails.get(0).get("name").toString());

														receiptpdf.setCustomerno(customerno
																.replaceAll("(.{" + val + "})", "$0 ").trim());
														receiptpdf.setCustomeradd1(
																customerdetails.get(0).get("address1").toString());
														receiptpdf.setCustomeradd2(
																customerdetails.get(0).get("address2").toString());
														receiptpdf.setCustomercity(
																customerdetails.get(0).get("city").toString());
														receiptpdf.setCustomermail(
																customerdetails.get(0).get("email").toString());

														double rf = Math.round(Double.parseDouble(retrieveccountdetails
																.get(0).get("payee_amount").toString()) * 100);
														double amountf = rf / 100.0;

														try {
															if (chectStatus.equalsIgnoreCase("reverse")) {
																Double tooutamout = Double
																		.parseDouble(totalpayingamount);
																receiptpdf
																		.setTransferamount(String.valueOf(tooutamout));
															} else {
																receiptpdf.setTransferamount(String.valueOf(amountf));
															}
														} catch (Exception e) {
															receiptpdf.setTransferamount(String.valueOf(amountf));
														}

														receiptpdf.setLocalamount(retrieveccountdetails.get(0)
																.get("payin_amount").toString());

														rf = Math.round(Double.parseDouble(retrieveccountdetails.get(0)
																.get("total_amount").toString()) * 100);
														amountf = rf / 100.0;
														float orderamout = Float
																.parseFloat(retrieveccountdetails.get(0)
																		.get("commission").toString())
																+ Float.parseFloat(retrieveccountdetails.get(0)
																		.get("payin_amount").toString());
														receiptpdf.setTotalorderamount(retrieveccountdetails.get(0)
																.get("total_amount").toString());// String.valueOf(orderamout)//amountf
														try {
															receiptpdf.setAgent(retrieveccountdetails.get(0)
																	.get("agent_code").toString());
														} catch (Exception e) {
															receiptpdf.setAgent("-------");
														}

														try {
															receiptpdf.setCustomermobile(
																	customerdetails.get(0).get("mobile").toString());
														} catch (Exception e) {
															receiptpdf.setCustomermobile("---------");
														}

														// receiptpdf.setAgent(agent);
														try {
															emailService.sendmailpdfdy(receiptpdf, "CPReceipt.html",
																	"support@crosspaymt.com");
														} catch (Exception e) {
															logger.info("error in sending mail csh " + e.toString());
														}

													}
												} catch (Exception e) {
													logger.info("error in updating cashpay " + e.toString());
												}
												resultjson.put("status", "200");
												resultjson.put("message", "success");
												resultjson.put("transaction_num", transactionrefno);
											} catch (Exception e) {
												System.out.println("error in adding cashpayout " + e.toString());

											}
										} else {
											resultjson.put("status", "406");
											resultjson.put("message", "Failure");
										}

									} catch (Exception e) {
										resultjson.put("status", "500");
										resultjson.put("message", "Internal Server Problem");
									}

								} catch (JSONException e) {
									System.out.println("adding err" + e.toString());
								}
							} else {
								try {

									acc.setTxnStatus(-1);

									int accs = 0;
									try {
										accs = amountTransferDao.updateaccountcredits(acc);
									} catch (Exception e) {
										System.out.println("error in updating accountcredit " + e.toString());
									}
								} catch (Exception e) {
									System.out.println("error in adding accountcredit " + e.toString());
								}
								try {

									cp.setTXNSTATUS(-1);

									int accs = 0;
									try {
										accs = amountTransferDao.updatecashpays(cp);
									} catch (Exception e) {
										System.out.println("error in updating updatecashpay " + e.toString());
									}
								} catch (Exception e) {
									System.out.println("error in adding cashpayout " + e.toString());
								}
								resultjson.put("status", "406");
								resultjson.put("message", "failure");
							}
						} catch (JSONException e) {
							System.out.println("transactionrefe err" + e.toString());
						}

					}

					conn.disconnect();

				} catch (MalformedURLException e) {
					System.out.println("error in malformed" + e.toString());
					e.printStackTrace();

				} catch (IOException e) {
					System.out.println("error in IOException" + e.toString());
					e.printStackTrace();

				}

			}

		} catch (Exception e) {
			System.out.println("exce " + e.toString());
			logger.error("some input fields missing error", e);
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/transactionHistory", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String transactionhistory(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String userid = null;
		String customerno = null;
		try {
			userid = json.get("user_id").toString();

			try {
				customerno = registerDao.retrieveCustomerNumber(userid);
				List<Map<String, Object>> transactionList = amountTransferDao.retrieveTransaction(customerno);
				if (transactionList.size() > 0) {
					resultjson.put("status", "200");
					resultjson.put("message", "success");
					resultjson.put("data", transactionList);
				} else {
					resultjson.put("status", "201");
					resultjson.put("message", "success");
					resultjson.put("data", "No Data");
				}
			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Internal Server Problem");
			}
		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/transactionHistoryview", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String transactionhistoryview(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String userid = null;
		String customerno = null;
		String orderno = null;
		try {
			userid = json.get("user_id").toString();
			orderno = json.get("order_no").toString();

			try {
				customerno = registerDao.retrieveCustomerNumber(userid);
				List<Map<String, Object>> transactionList = amountTransferDao.retrieveTransactionview(customerno,
						orderno);
				if (transactionList.size() > 0) {
					resultjson.put("status", "200");
					resultjson.put("message", "success");
					resultjson.put("data", transactionList);
				} else {
					resultjson.put("status", "201");
					resultjson.put("message", "success");
					resultjson.put("data", "No Data");
				}
			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Internal Server Problem");
			}
		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/adminaccounttransactionHistory", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String admintransactionhistory(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String userid = null;
		String customerno = null;
		try {
			userid = json.get("user_id").toString();

			try {
				customerno = registerDao.retrieveCustomerNumber(userid);
				List<Map<String, Object>> transactionList = amountTransferDao.retrieveaccountTransaction();
				if (transactionList.size() > 0) {
					resultjson.put("status", "200");
					resultjson.put("message", "success");
					resultjson.put("data", transactionList);
				} else {
					resultjson.put("status", "201");
					resultjson.put("message", "success");
					resultjson.put("data", "No Data");
				}
			} catch (Exception e) {

				resultjson.put("status", "406");
				resultjson.put("message", "Internal Server Problem");
			}
		} catch (Exception e) {

			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/admincashtransactionHistory", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String admincashtransactionhistory(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String userid = null;
		String customerno = null;
		try {
			userid = json.get("user_id").toString();

			try {
				customerno = registerDao.retrieveCustomerNumber(userid);
				List<Map<String, Object>> transactionList = amountTransferDao.retrievecashTransaction();
				if (transactionList.size() > 0) {
					resultjson.put("status", "200");
					resultjson.put("message", "success");
					resultjson.put("data", transactionList);
				} else {
					resultjson.put("status", "201");
					resultjson.put("message", "success");
					resultjson.put("data", "No Data");
				}
			} catch (Exception e) {

				resultjson.put("status", "406");
				resultjson.put("message", "Internal Server Problem");
			}
		} catch (Exception e) {

			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
		}
		return resultjson.toString();
	}

	@RequestMapping("/getAgent")
	@ResponseBody
	public String retireveAgent() throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			resultjson.put("status", "200");
			resultjson.put("message", "Success");
			resultjson.put("agent", amountTransferDao.retrieveAgent());
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Failure");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/sbank", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public String sbankreceipts(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String txno = null;
		String userId = null;
		String customerno = null;
		String ccycode = null;
		Double amountR = null;
		Double out = null;
		String beneficiaryno = null;
		String payeccyCode = null;
		String accountccycode = null;
		float commission = 0;
		String totalpayingamount = null;
		String count = null;
		int paymentmode = 0;
		String orderid = null;
		String chectStatus = "";

		try {
			chectStatus = json.get("checkstatus").toString();

		} catch (Exception e) {
			System.out.println("TOTAL AMOUNT IS 112" + chectStatus);
		}

		try {
			txno = json.get("txnno").toString();
			userId = json.get("user_id").toString();
			ccycode = json.get("ccycode").toString();
			beneficiaryno = json.get("beneficiary_no").toString();
			payeccyCode = json.get("payeccyCode").toString();
			paymentmode = Integer.parseInt(json.get("paymentmode").toString());
			totalpayingamount = json.get("totalpayingamount").toString();
			accountccycode = json.get("accountccycode").toString();
			commission = Float.parseFloat(json.get("commission").toString());

			try {
				amountR = Double.parseDouble(json.get("totalpayingamount").toString());
			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "totalpayingamount" + e.toString());
				return resultjson.toString();

			}

		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}

		try {
			customerno = registerDao.retrieveCustomerNumber(userId);

			if (!beneficiaryno.equalsIgnoreCase("") || !(beneficiaryno.isEmpty())) {
				if (!userId.equalsIgnoreCase("") || !(userId.isEmpty())) {
					if (!payeccyCode.equalsIgnoreCase("") || !(payeccyCode.isEmpty())) {

						if (!totalpayingamount.equalsIgnoreCase("") || !(totalpayingamount.isEmpty())) {
							if (!json.get("count").toString().equalsIgnoreCase("")
									|| !(json.get("count").toString().isEmpty())) {

								CurrencyRates currencyrates = new CurrencyRates();
								CurrencyRates currencyrateoutput = new CurrencyRates();
								currencyrates.setCCYCODEFROM(json.get("payeccyCode").toString());
								currencyrates.setCCYCODETO(json.get("accountccycode").toString());
								float fromval = Float.parseFloat(json.get("count").toString());

								try {
									currencyrateoutput = versionDao.retieveData(currencyrates);

									try {
										if (chectStatus.equals("reverse")) {

											out = (currencyrateoutput.getXCHGRATE()) * (fromval);
											int r = (int) Math.round(out * 100);
											out = r / 100.0;

										} else {

											out = (currencyrateoutput.getXCHGRATE()) * (fromval);
											int r = (int) Math.round(out * 100);
											out = r / 100.0;

										}
									} catch (Exception e) {
										out = (currencyrateoutput.getXCHGRATE()) * (fromval);
										int r = (int) Math.round(out * 100);
										out = r / 100.0;
									}

									// out = Math.floor(out);

									Double toout = Double.parseDouble(totalpayingamount);

									int retval = 0;
									try {
										if (chectStatus.equalsIgnoreCase("reverse")) {

											retval = 0;

										} else {
											// retval = Double.compare(out, toout);
											retval = 0;
										}
									} catch (Exception e) {
										retval = 0;
									}

									if (retval == 0) {

										orderid = "bankt" + (new CommonUtil().generateNineDigitNumericString());
										List<Map<String, Object>> beneficiaryDetails = new ArrayList<Map<String, Object>>();
										try {
											beneficiaryDetails = customerDao.retrieveBeneficiaryData(beneficiaryno);

											String benname = beneficiaryDetails.get(0).get("first_name").toString();
											try {

												float amount = Float.parseFloat(String.valueOf(fromval));
												String transfertype = "AC";
												float finalcommission = 0;
												if (paymentmode == 0) {
													transfertype = "CP";
													finalcommission = amountTransferDao.retrieveCPCommission(amount,
															currencyrates.getCCYCODEFROM(),
															currencyrates.getCCYCODETO());
												} else if (paymentmode == 1) {
													transfertype = "AC";
													finalcommission = amountTransferDao.retrieveACCommission(amount,
															currencyrates.getCCYCODEFROM(),
															currencyrates.getCCYCODETO());
												}

												float finalam = 0;
												float fineam = 0;
												try {
													/*
													 * finalcommission = amountTransferDao .retrieveCommission(amount,
													 * transfertype);
													 */
													if (commission == finalcommission) {

													} else {
														resultjson.put("status", "406");
														resultjson.put("message", "Commission not matched.");
														return resultjson.toString();
													}
													finalam = (Float.parseFloat(
															String.valueOf(currencyrateoutput.getXCHGRATE())) * amount);//
													fineam = (float) (amount + finalcommission);

												} catch (Exception e) {
													logger.error("Error in commission retrieval", e);
												}
												// String txnno = "TXN_" + new
												// CommonUtil().generateSevenDigitNumericString();
												String txnno = "TXN_"
														+ new CommonUtil().generateSevenDigitNumericString();
												SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

												java.util.Date nowDate = new java.util.Date();
												String currentYear = formatNowYear.format(nowDate);

												int transfer = 0;
												int success = 0;
												if (paymentmode == 0) {
													double txn = amountTransferDao.retrievecashtxnno();

													if (txn == 0) {
														String num = currentYear + "30000000000" + 1;
														txnno = num;
													} else {

														String[] v = String.format("%12f", txn).split("\\.");

														String num = currentYear + v[0];
														txnno = num;
													}

													CPSenderReceiver acsender = new CPSenderReceiver();
													acsender.setORDERID(orderid);
													acsender.setBENEFICIARYFIRSTNAME(
															beneficiaryDetails.get(0).get("first_name").toString());
													try {
														acsender.setBENEFICIARYMIDDLENAME(beneficiaryDetails.get(0)
																.get("middle_name").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYMIDDLENAME(null);
													}
													acsender.setBENEFICIARYLASTNAME(
															beneficiaryDetails.get(0).get("last_name").toString());
													try {
														acsender.setBENEFICIARYEMAIL(
																beneficiaryDetails.get(0).get("email").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYEMAIL(null);
													}

													try {
														acsender.setBENEFICIARYPHONE(
																beneficiaryDetails.get(0).get("phone_num").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYPHONE("");
													}
													acsender.setBENEFICIARYADDRESSCOUNTRYCODE(
															beneficiaryDetails.get(0).get("country").toString());
													try {
														acsender.setBENEFICIARYPOBOX(
																beneficiaryDetails.get(0).get("pobox").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYPOBOX("");
													}
													try {
														acsender.setBENEFICIARYADDRESS1(
																beneficiaryDetails.get(0).get("address1").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYADDRESS1(null);
													}
													try {
														acsender.setBENEFICIARYADDRESS2(
																beneficiaryDetails.get(0).get("address2").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYADDRESS2(null);
													}

													try {
														acsender.setBENEFICIARYADDRESSCITY(
																beneficiaryDetails.get(0).get("city").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYADDRESSCITY(null);
													}

													try {
														acsender.setBENEFICIARYADDRESSSTATE(
																beneficiaryDetails.get(0).get("state").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYADDRESSSTATE(null);
													}
													try {
														acsender.setBENEFICIARYADDRESSZIP(
																beneficiaryDetails.get(0).get("pincode").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYADDRESSZIP(null);
													}

													try {
														acsender.setBENEFICIARYMOBILE(
																beneficiaryDetails.get(0).get("mobile").toString());
													} catch (Exception e) {
														acsender.setBENEFICIARYMOBILE(null);
													}
													acsender.setCUSTOMERNO(customerno);
													acsender.setBENEFICIARYNO(Integer.parseInt(beneficiaryno));

													/*
													 * int banktype = Integer.parseInt(
													 * beneficiaryDetails.get(0).get("bankType").toString());
													 */

													try {
														acsender.setTXNNO(txnno);
													} catch (Exception e) {
														acsender.setTXNNO(null);
													}

													CashPayment acc = new CashPayment();

													acc.setTXNNO(txnno);

													try {
														acc.setAGENTCODE(json.get("agent_code").toString());
													} catch (Exception e) {
														acc.setAGENTCODE(null);
													}

													acc.setCOMMISSION((int) (finalcommission));
													acc.setCREATEDON(new Date());
													acc.setTXNDATE(new Date());
													acc.setPARTNERREFNO(null);
													acc.setCUSTOMERNO(customerno);
													acc.setVALUEDATE(null);
													acc.setPAYINCCCODE(payeccyCode);
													acc.setACCOUNTCCYCODE(accountccycode);
													acc.setORDERID(orderid);
													acc.setAUTHORISEDBY(null);
													acc.setSAUTHREFERENCE(null);
													acc.setSPARTNERID(null);
													acc.setAUTHORISEDON(null);
													acc.setMODIFIEDBY(null);
													acc.setMODIFIEDON(null);
													acc.setTXNGMTDATE(null);
													acc.setTXNSTATUS(3);
													acc.setIBANPAYTXNPAYIN2ACCOUNTCCYRATE(0);
													acc.setTXNPAYTXNPAYIN2ACCOUNTCCYRATE(
															currencyrateoutput.getXCHGRATE());
													// int r1 = (int) Math.round(amount * 100);
													// double amount1 = r1 / 100.0;
													// acc.setTOTALPAYINAMOUNT(amount1);
													acc.setTOTALPAYINAMOUNT(fineam);// fromval
													acc.setTRANSFERAMOUNT(Float.parseFloat(totalpayingamount));
													acc.setPAYINAMOUNT(amount);
													acc.setCREATEDBY(null);
													acc.setPAYMENTMODE("0");
													acc.setPAIDDATE(null);
													try {
														transfer = amountTransferDao.saveCashCreditData(acc);
													} catch (Exception e) {
														System.out.println(
																"error in insertion of accountcredit " + e.toString());
													}

													success = amountTransferDao.saveCPTransactionData(acsender);
												} else if (paymentmode == 1) {
													double txn = amountTransferDao.retrieveaccounttxnno();

													if (txn == 0) {
														String num = currentYear + "20000000000" + 1;
														txnno = num;
													} else {

														String[] v = String.format("%12f", txn).split("\\.");

														String num = currentYear + v[0];
														txnno = num;
													}

													ACSenderReceiver acsender = new ACSenderReceiver();
													acsender.setOrderId(orderid);
													acsender.setBeneficiaryFirstName(
															beneficiaryDetails.get(0).get("first_name").toString());
													try {
														acsender.setBeneficiaryMiddleName(beneficiaryDetails.get(0)
																.get("middle_name").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryMiddleName(null);
													}
													acsender.setBeneficiaryLastName(
															beneficiaryDetails.get(0).get("last_name").toString());
													try {
														acsender.setBeneficiaryEmail(
																beneficiaryDetails.get(0).get("email").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryEmail(null);
													}

													try {
														acsender.setBeneficiaryPhone(
																beneficiaryDetails.get(0).get("phone_num").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryPhone("");
													}
													acsender.setBeneficiaryAddressCountryCode(
															beneficiaryDetails.get(0).get("country").toString());
													try {
														acsender.setBeneficiaryPOBox(
																beneficiaryDetails.get(0).get("pobox").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryPOBox("");
													}
													try {
														acsender.setBeneficiaryAddress1(
																beneficiaryDetails.get(0).get("address1").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryAddress1(null);
													}
													try {
														acsender.setBeneficiaryAddress2(
																beneficiaryDetails.get(0).get("address2").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryAddress2(null);
													}

													try {
														acsender.setBeneficiaryAddressCity(
																beneficiaryDetails.get(0).get("city").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryAddressCity(null);
													}

													try {
														acsender.setBeneficiaryAddressState(
																beneficiaryDetails.get(0).get("state").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryAddressState(null);
													}
													try {
														acsender.setBeneficiaryAddressZip(
																beneficiaryDetails.get(0).get("pincode").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryAddressZip(null);
													}

													try {
														acsender.setBeneficiaryMobile(
																beneficiaryDetails.get(0).get("mobile").toString());
													} catch (Exception e) {
														acsender.setBeneficiaryMobile(null);
													}
													acsender.setCustomerNo(customerno);
													acsender.setBeneficiaryNo(Integer.parseInt(beneficiaryno));

													int banktype = Integer.parseInt(
															beneficiaryDetails.get(0).get("bankType").toString());

													if (banktype == 0) {
														acsender.setBeneficiaryBankAccountNo(null);
														acsender.setBeneficiaryBankName(null);
														acsender.setBeneficiaryAccountCcyCode(null);
														acsender.setSwiftIFSC(null);
														acsender.setBeneficiaryBankCode(null);
														try {
															if (acsender.getBeneficiaryMiddleName() == null)
																acsender.setBeneficiaryAccountName(
																		acsender.getBeneficiaryFirstName() + " "
																				+ acsender.getBeneficiaryLastName());
															else {
																acsender.setBeneficiaryAccountName(
																		acsender.getBeneficiaryFirstName() + " "
																				+ acsender.getBeneficiaryMiddleName()
																				+ " "
																				+ acsender.getBeneficiaryLastName());
															}
														} catch (Exception e) {
															acsender.setBeneficiaryAccountName(null);
														}
														acsender.setBeneficiaryBankBranchName(null);
													} else {
														acsender.setBeneficiaryBankAccountNo(beneficiaryDetails.get(0)
																.get("account_num").toString());
														acsender.setBeneficiaryBankName(
																beneficiaryDetails.get(0).get("bank_name").toString());
														acsender.setBeneficiaryAccountCcyCode(
																json.get("accountccycode").toString());
														try {
															acsender.setSwiftIFSC(
																	beneficiaryDetails.get(0).get("ifsc").toString());
														} catch (Exception e) {
															acsender.setSwiftIFSC(null);
														}
														acsender.setBeneficiaryBankCode(null);
														try {
															if (acsender.getBeneficiaryMiddleName() == null)
																acsender.setBeneficiaryAccountName(
																		acsender.getBeneficiaryFirstName() + " "
																				+ acsender.getBeneficiaryLastName());
															else {
																acsender.setBeneficiaryAccountName(
																		acsender.getBeneficiaryFirstName() + " "
																				+ acsender.getBeneficiaryMiddleName()
																				+ " "
																				+ acsender.getBeneficiaryLastName());
															}
														} catch (Exception e) {
															acsender.setBeneficiaryAccountName(null);
														}

														try {
															acsender.setBeneficiaryBankBranchName(beneficiaryDetails
																	.get(0).get("branch_name").toString());
														} catch (Exception e) {
															acsender.setBeneficiaryBankBranchName(null);
														}

													}

													try {
														acsender.setTxnNo(txnno);
													} catch (Exception e) {
														acsender.setTxnNo(null);
													}

													AccountCredit acc = new AccountCredit();

													acc.setTxnNo(txnno);

													acc.setCommission((int) (finalcommission));
													acc.setCreatedOn(new Date());
													acc.setTxnDate(new Date());
													acc.setPartnerRefNo(null);
													acc.setCustomerNo(customerno);
													acc.setValueDate(null);
													acc.setPayinccyCode(payeccyCode);
													acc.setAccountCcyCode(accountccycode);
													acc.setDraweeBankCode(null);
													acc.setOrderId(orderid);
													acc.setAuthorisedBy(null);
													acc.setCreditedDate(null);
													acc.setSAuthReference(null);
													acc.setSPartnerID(null);
													acc.setAuthorisedOn(null);
													acc.setModifiedBy(null);
													acc.setModifiedOn(null);
													acc.setTxnGMTDate(null);
													acc.setTxnStatus(3);
													acc.setIBnPayTxnPayin2AccountCcyRate(0);
													acc.setTxnPayTxnPayin2AccountCcyRate(
															currencyrateoutput.getXCHGRATE());
													// int r1 = (int) Math.round(amount * 100);
													// double amount1 = r1 / 100.0;
													// acc.setTotalPayinAmount(amount1);
													acc.setTotalPayinAmount(fineam);// fromval
													acc.setTransferAmount(Float.parseFloat(totalpayingamount));
													acc.setPayinAmount(amount);
													acc.setCreatedBy(null);
													acc.setPaymentMode("1");
													try {
														transfer = amountTransferDao.saveAccountCreditData(acc);
													} catch (Exception e) {
														System.out.println(
																"error in insertion of accountcredit " + e.toString());
													}
													success = amountTransferDao.saveTransactionData(acsender);
												}

												SBankReceipts sbank = new SBankReceipts();
												sbank.setBANKREFERENCENO(txno);
												sbank.setCUSTOMERNO(customerno);
												sbank.setTXNTYPE("BT");
												sbank.setTXNDATE(new Date());
												try {
													// amountR = (Double) json.get("amountrec");
													sbank.setAMOUNTCCYCODE(ccycode);
												} catch (Exception e) {
													sbank.setAMOUNTCCYCODE("0");

												}

												Float receivedamount = Float.parseFloat(
														String.valueOf(currencyrateoutput.getXCHGRATE())) * fineam;
												sbank.setAMOUNTRECEIVED(receivedamount);
												sbank.setAMOUNTRECEIVEDON(null);
												sbank.setCREDITVERIFIEDBYUSER(null);
												sbank.setCREDITVERIFIEDON(null);
												sbank.setRECORDSTATUS(1);

												String spartner = "SPRBT";
												sbank.setTXNNO(txnno);
												sbank.setSPARTNERID(spartner);
												int savebank = amountTransferDao.saveSbankreceipts(sbank);
												/*
												 * if (savebank > 0) { resultjson.put("status", "200");
												 * resultjson.put("message", "Success"); } else {
												 * resultjson.put("status", "406"); resultjson.put("message",
												 * "Internal Server Problem"); }
												 */

												if (success == 1 && transfer == 1 && savebank > 0) {

													String val = "4";
													Receiptpdf receiptpdf = new Receiptpdf();
													List<Map<String, Object>> retrieveccountdetails = new ArrayList<Map<String, Object>>();
													if (paymentmode == 0) {
														retrieveccountdetails = amountTransferDao
																.retrievecashpdfviewdetails(customerno, orderid);
													} else if (paymentmode == 1) {
														retrieveccountdetails = amountTransferDao
																.retrieveaccountpdfviewdetails(customerno, orderid);
													}

													List<Map<String, Object>> customerdetails = registerDao
															.retrieveCustomerProfile(customerno);
													receiptpdf.setReceivername(
															retrieveccountdetails.get(0).get("name").toString());
													receiptpdf.setTransaction(retrieveccountdetails.get(0).get("txn_no")
															.toString().replaceAll("(.{" + val + "})", "$0 ").trim());

													receiptpdf.setType("CASH PAYOUT");// retrieveccountdetails.get(0).get("accout_type").toString()
													receiptpdf.setTransactiondate(
															retrieveccountdetails.get(0).get("txn_date").toString());

													receiptpdf.setPaymentmode("BANK TRANSFER");

													receiptpdf.setCardtxnref(txno);

													receiptpdf.setReceivermobile(
															retrieveccountdetails.get(0).get("mobile").toString());
													receiptpdf.setReceiveraddress(retrieveccountdetails.get(0)
															.get("city").toString() + ","
															+ retrieveccountdetails.get(0).get("country").toString());

													try {

														if (retrieveccountdetails.get(0).get("name").toString() == null
																|| retrieveccountdetails.get(0).get("name").toString()
																		.equals("")) {
															receiptpdf.setAccountname("----");
														} else {
															receiptpdf.setAccountname(retrieveccountdetails.get(0)
																	.get("name").toString());
														}

													} catch (Exception e) {
														receiptpdf.setAccountname("----");
													}

													try {

														if (retrieveccountdetails.get(0).get("accout_num")
																.toString() == null
																|| retrieveccountdetails.get(0).get("accout_num")
																		.toString().equals("")) {
															receiptpdf.setAccountnum("----");
														} else {
															receiptpdf.setAccountnum(retrieveccountdetails.get(0)
																	.get("accout_num").toString());
														}
													} catch (Exception e) {
														receiptpdf.setAccountnum("----");
													}

													try {
														if (retrieveccountdetails.get(0).get("bank_name")
																.toString() == null
																|| retrieveccountdetails.get(0).get("bank_name")
																		.toString().equals("")) {

															receiptpdf.setBank("----");

														} else {

															receiptpdf.setBank(retrieveccountdetails.get(0)
																	.get("bank_name").toString());

														}
													} catch (Exception e) {
														receiptpdf.setBank("----");
													}

													try {

														if (retrieveccountdetails.get(0).get("ifsc").toString() == null
																|| retrieveccountdetails.get(0).get("ifsc").toString()
																		.equals("")) {
															receiptpdf.setIfsc("----");

														} else {
															receiptpdf.setIfsc(retrieveccountdetails.get(0).get("ifsc")
																	.toString());
														}

													} catch (Exception e) {
														receiptpdf.setIfsc("");
													}

													/*
													 * receiptpdf.setAccountname(retrieveccountdetails.get(0).get("name"
													 * ).toString());
													 * receiptpdf.setAccountnum(retrieveccountdetails.get(0).get(
													 * "accout_num").toString()); try {
													 * receiptpdf.setBank(retrieveccountdetails.get(0).get("bank").
													 * toString());
													 * receiptpdf.setIfsc(retrieveccountdetails.get(0).get("ifsc").
													 * toString()); }catch(Exception e) {
													 * System.out.println("error in bank data retrieval "+e.toString());
													 * }
													 */
													receiptpdf.setAccountccy(retrieveccountdetails.get(0)
															.get("account_ccycode").toString());
													receiptpdf.setPayeeccy(retrieveccountdetails.get(0)
															.get("payin_ccycode").toString());
													receiptpdf.setTransferfee(
															retrieveccountdetails.get(0).get("commission").toString());
													receiptpdf.setCurrencyrate(
															retrieveccountdetails.get(0).get("rate").toString());
													double rf = Math.round(Double.parseDouble(
															retrieveccountdetails.get(0).get("payee_amount").toString())
															* 100);
													double amountf = rf / 100.0;

													try {
														if (chectStatus.equalsIgnoreCase("reverse")) {
															Double tooutamout = Double.parseDouble(totalpayingamount);
															receiptpdf.setTransferamount(String.valueOf(tooutamout));
														} else {
															receiptpdf.setTransferamount(String.valueOf(amountf));
														}
													} catch (Exception e) {
														receiptpdf.setTransferamount(String.valueOf(amountf));
													}

													receiptpdf.setLocalamount(retrieveccountdetails.get(0)
															.get("payin_amount").toString());

													rf = Math.round(Double.parseDouble(
															retrieveccountdetails.get(0).get("total_amount").toString())
															* 100);
													amountf = rf / 100.0;

													receiptpdf.setTotalorderamount(retrieveccountdetails.get(0)
															.get("total_amount").toString());// String.valueOf(amountf)

													String template = "BankReceipt.html";

													if (paymentmode == 0) {
														receiptpdf.setType("CASH PAYOUT");
														template = "CPReceipt.html";
													} else if (paymentmode == 1) {
														receiptpdf.setType("ACCOUNT CREDIT");
														template = "AccountReceipt.html";
													}
													receiptpdf.setCustoername(
															customerdetails.get(0).get("name").toString());

													receiptpdf.setCustomerno(
															customerno.replaceAll("(.{" + val + "})", "$0 ").trim());

													try {
														receiptpdf.setCustomeradd1(
																customerdetails.get(0).get("address1").toString());
													} catch (Exception e) {
														receiptpdf.setCustomeradd1("-----");
													}

													try {
														receiptpdf.setCustomeradd2(
																customerdetails.get(0).get("address2").toString());
													} catch (Exception e) {
														receiptpdf.setCustomeradd2("-----");
													}

													try {
														receiptpdf.setCustomercity(
																customerdetails.get(0).get("city").toString());
													} catch (Exception e) {
														receiptpdf.setCustomercity("-----");
													}

													try {
														receiptpdf.setCustomermail(
																customerdetails.get(0).get("email").toString());
													} catch (Exception e) {
														receiptpdf.setCustomermail("------");
													}

													try {
														receiptpdf.setAgent(json.get("agent_code").toString());
													} catch (Exception e) {
														receiptpdf.setAgent("-----");
													}

													try {
														receiptpdf.setCustomermobile(
																customerdetails.get(0).get("mobile").toString());
													} catch (Exception e) {
														receiptpdf.setCustomermobile("---------");
													}

													try {
														emailService.sendmailpdfdy(receiptpdf, template,
																"support@crosspaymt.com");
													} catch (Exception e) {

														logger.info("error in sending mail csh " + e.toString());

													}

													resultjson.put("status", "200");
													resultjson.put("message", "success");
													resultjson.put("sitereference", "test_crosspay68993");
													resultjson.put("currencyiso3a", currencyrates.getCCYCODEFROM());
													int r2 = (int) Math.round(fineam * 100);
													double finalam1 = r2 / 100.0;
													resultjson.put("mainamount", finalam1);
													resultjson.put("version", "2");
													resultjson.put("orderreference", orderid);
													resultjson.put("link",
															"https://payments.securetrading.net/process/payments/choice");
												} else {
													try {
														AccountCredit acc = new AccountCredit();
														acc.setTxnStatus(-1);
														acc.setOrderId(orderid);
														amountTransferDao.updateaccountcredits(acc);
													} catch (Exception ex) {

													}
													try {
														CashPayment acc = new CashPayment();
														acc.setTXNSTATUS(-1);
														acc.setORDERID(orderid);
														amountTransferDao.updatecashpays(acc);
													} catch (Exception ex) {

													}
													resultjson.put("status", "500");
													resultjson.put("message", "Internal Server Error");
												}
											} catch (Exception e) {

												try {
													AccountCredit acc = new AccountCredit();
													acc.setTxnStatus(-1);
													acc.setOrderId(orderid);
													amountTransferDao.updateaccountcredits(acc);
												} catch (Exception ex) {

												}
												try {
													CashPayment acc = new CashPayment();
													acc.setTXNSTATUS(-1);
													acc.setORDERID(orderid);
													amountTransferDao.updatecashpays(acc);
												} catch (Exception ex) {

												}
												resultjson.put("status", "406");
												resultjson.put("message", "Internal Server Error");
											}
										} catch (Exception e) {
											resultjson.put("status", "406");
											resultjson.put("message", "Beneficiary/User doesn't exist for this user");
										}

									} else {
										resultjson.put("status", "406");
										resultjson.put("message", "Invalid Total Amount Please try again.");
									}

								} catch (Exception ex) {
									resultjson.put("status", "406");
									resultjson.put("message", "error in currency retrieval");
								}

							} else {
								resultjson.put("status", "406");
								resultjson.put("message", "count should not be empty");
							}
						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "totalpayingamount should not be empty");
						}

					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "payeccyCode should not be empty");
					}
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "userId should not be empty");
				}
			} else {
				resultjson.put("status", "406");
				resultjson.put("message", "Beneficiary No should not be empty");
			}

		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Failure");
		}

		return resultjson.toString();
	}

	public String fetchTransactionNum(String orderid, String payee) {
		String transactionreference = null;
		try {

			URL url = new URL("https://webservices.securetrading.net/json/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// String authStr = "Username:manish@crosspayments.com,Password:N(]sp4PL";
			conn.setDoOutput(true);
			conn.setUseCaches(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Basic bWFuaXNoQGNyb3NzcGF5bWVudHMuY29tOk4oXXNwNFBM");
			// conn.setContentHandlerFactory(fac);
			conn.setRequestProperty("accept", "application/json");

			String input = "{\r\n" + "\"alias\": \"manish@crosspayments.com\",\r\n" + "\"version\": \"1.00\",\r\n"
					+ "\"request\": [{\r\n" + "  \"requesttypedescriptions\": [\"TRANSACTIONQUERY\"],\r\n"
					+ "  \"filter\":{\r\n" + "    \"sitereference\": [{\"value\":\"test_crosspay68993\"}],\r\n"
					+ "    \"currencyiso3a\": [{\"value\":\"" + payee + "\"}],\r\n"
					+ "    \"orderreference\": [{\"value\":\"" + orderid + "\"}]\r\n" + "  }\r\n" + "}]}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {

				InputStream content = (InputStream) conn.getInputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(content));
				String line;
				StringBuffer sb = new StringBuffer();
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// System.out.println(line);
				}
				JSONObject output = new JSONObject();
				JSONArray res = new JSONArray();
				try {

					output = new JSONObject(sb.toString());
					res = output.getJSONArray("response");
					JSONObject actualoutput = new JSONObject(res.get(0).toString());

					JSONArray records = actualoutput.getJSONArray("records");
					JSONObject finalres = records.getJSONObject(0);
					transactionreference = finalres.getString("transactionreference");
				} catch (Exception e) {
					logger.error("Error in transaction number fetching after payment", e);
				}
			}
		} catch (Exception e) {
			logger.error("Error in transaction number fetching after payment capture", e);
		}

		return transactionreference;
	}

	@RequestMapping("/getTransactionData")
	@ResponseBody
	public String transactionData(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			String currdate = json.get("date").toString();
			try {
				resultjson.put("status", "200");
				resultjson.put("message", "Success");
				resultjson.put("data", amountTransferDao.retrieveAccountACData(currdate));
			} catch (Exception ex) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				return resultjson.toString();
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping("/getCashTransactionData")
	@ResponseBody
	public String cashtransactionData(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			String currdate = json.get("date").toString();
			try {
				resultjson.put("status", "200");
				resultjson.put("message", "Success");
				resultjson.put("data", amountTransferDao.retrieveCashCPData(currdate));
			} catch (Exception ex) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				return resultjson.toString();
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input fields missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@Autowired
	private RegisterDao registerDao;

	@Autowired
	private VersionDao versionDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AmountTransferDao amountTransferDao;

	@Autowired
	private EmailService emailService;

}