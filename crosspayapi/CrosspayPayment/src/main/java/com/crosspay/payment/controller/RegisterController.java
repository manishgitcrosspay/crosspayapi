package com.crosspay.payment.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crosspay.payment.model.CustomTemplate;
import com.crosspay.payment.model.Customer;
import com.crosspay.payment.model.Login;
import com.crosspay.payment.model.LoginCredentialHistory;
import com.crosspay.payment.model.Register;
import com.crosspay.payment.model.SmtpAuthenticator;
import com.crosspay.payment.service.EmailService;
import com.crosspay.payment.service.LoginDao;
import com.crosspay.payment.service.RegisterDao;
import com.crosspay.payment.util.CommonUtil;
import com.crosspay.payment.util.Constants;
import com.crosspay.payment.util.CryptoUtil;
import com.crosspay.payment.util.SendSMS;

@Controller
public class RegisterController {

	Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@RequestMapping("/cp_admin")
	public String index() {
		return "cp_admin/index.html";
	}

	@RequestMapping("/cp_services")
	public String indexs() {
		return "cp_services/index.html";
	}

	@RequestMapping("/secureddd")
	public String securedd() {
		return "secure3d.html";
	}

	@RequestMapping("/sendmail")
	public String mail() {
		emailService.sendmailpdf("manish@crosspayments.com", "sravanthi6802@gmail.com", "spring", "test message");
		return "success";
	}

	/**
	 * GET /create --> Create a new user and save it in the database.
	 * 
	 * @throws JSONException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public String create(@RequestBody Map<String, Object> json) throws JSONException {

		JSONObject resultjson = new JSONObject();
		String pattern = "[0-9]{4}";
		logger.debug("Input for Registration from Mobile", json);

		try {

			// Initializing
			Register register = new Register();
			Customer customer = new Customer();
			Login login = new Login();

			// Setting values
			register.setCUSTOMEREMAIL(json.get("email").toString().trim().toUpperCase());
			register.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			register.setSIGNUPDATE(new Date());

			SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

			java.util.Date nowDate = new java.util.Date();
			String currentYear = formatNowYear.format(nowDate);
			double customerno = registerDao.retrieveCustomerNo();

			if (String.format("%12f", customerno).split("\\.")[0].startsWith("2017")) {
				String num = "201810000000000" + 1;
				customer.setCUSTOMERNO(num);
				register.setCUSTOMERNO(num);
				login.setCUSTOMERNO(num);
			} else if (String.format("%12f", customerno).split("\\.")[0].startsWith("2018")) {

				String sub = String.format("%16f", customerno).substring(4, 16).toString();

				double id = Double.valueOf(sub);

				id++;
				String[] v1 = String.format("%12f", id).split("\\.");
				String num = "2018" + v1[0];

				customer.setCUSTOMERNO(num);
				register.setCUSTOMERNO(num);
				login.setCUSTOMERNO(num);

			}

			customer.setCUSTOMERFIRSTNAME(json.get("firstname").toString().toUpperCase());
			customer.setCUSTOMERLASTNAME(json.get("lastname").toString().toUpperCase());
			customer.setCUSTOMEREMAIL(json.get("email").toString().toUpperCase());
			customer.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			customer.setCUSTOMERADDRESSCOUNTRYCODE(json.get("countrycode").toString());
			customer.setCUSTOMERDATEOFBIRTH(json.get("dateofbirth").toString().toUpperCase());
			customer.setCUSTOMERGENDER(json.get("gender").toString().toUpperCase());
			customer.setCREATEDON(new Date());

			if (json.get("countrycode").toString().equalsIgnoreCase("+44")) {
				customer.setCUSTOMERADDRESSCOUNTRYCODE("GB");

			} else if (json.get("countrycode").toString().equalsIgnoreCase("+91")) {
				customer.setCUSTOMERADDRESSCOUNTRYCODE("IN");
			}

			customer.setCUSTOMERMOBILE(
					json.get("countrycode").toString().trim() + json.get("mobile").toString().trim());

			try {
				customer.setCUSTOMERMIDDLENAME(json.get("middlename").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERMIDDLENAME("");
			}
			try {
				customer.setCUSTOMERADDRESS1(json.get("Address").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESS1(null);
			}

			try {
				customer.setCUSTOMERADDRESS2(json.get("Address2").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESS2(null);
			}

			try {
				customer.setCUSTOMERADDRESSCITY(json.get("city").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSCITY(null);
			}

			try {
				customer.setCUSTOMERADDRESSCOUNTRY(json.get("Country").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSCOUNTRY(null);
			}
			try {
				customer.setCUSTOMERADDRESSSTATE(json.get("County").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSSTATE(null);
			}

			customer.setUSERCATEGORIES_ID(0);
			customer.setCUSTOMERCATEGORY("pending");

			try {
				customer.setCUSTOMERADDRESSZIP(json.get("Zipcode").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSZIP(null);
			}
			customer.setCUSTOMERPOBOX(null);
			customer.setCUSTOMERPHONE(null);
			customer.setNOTIFYCUSTOMERBYEMAIL("0");
			customer.setNOTIFYCUSTOMERBYSMS("0");
			try {
				customer.setCUSTOMERNATIONALITYCODE(json.get("Nationality").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERNATIONALITYCODE(null);
			}
			customer.setCUSTOMERIMAGE(null);

			register.setRECORDSTATUS(0);
			register.setSIGNUPOTP(new CommonUtil().generateSixDigitNumericString());
			register.setSIGNUPCLIENT(null);

			login.setCUSTOMEREMAIL(json.get("email").toString().trim().toUpperCase());
			login.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			login.setRECORDSTATUS(0);
			login.setUSERCATEGORY(0);
			login.setTIMESTAMP(null);

			// To perform action
			if (json.get("email").toString().trim().matches(new Constants().getEMAIL_REGEX())) {

				if (json.get("password").toString().matches(pattern)) {
					CryptoUtil ct = new CryptoUtil();
					register.setPASSWORD(ct.encrypt(new Constants().getSaltkey(), json.get("password").toString()));
					login.setPASSWORD(ct.encrypt(new Constants().getSaltkey(), json.get("password").toString()));

				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Password should have 4 digits");
					return resultjson.toString();
				}
				int idd = 0;
				Register reg = registerDao.findByCUSTOMEREMAIL(json.get("email").toString());

				idd = reg.getSIGNUPS_ID();

				if (idd == 0) {

					reg = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());

					idd = reg.getSIGNUPS_ID();

					if (idd == 0) {

						try {

							registerDao.saveReg(register);
							registerDao.saveCustomer(customer);
							Register reg1 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString().trim(),
									register.getPASSWORD());
							idd = reg1.getSIGNUPS_ID();
							resultjson.put("status", "200");
							resultjson.put("message", "Success");
							resultjson.put("OTP", reg1.getSIGNUPOTP());
							String message = "Thank you for registering with Crosspay. Your OTP is "
									+ reg1.getSIGNUPOTP();

							try {
								new SendSMS().sendMessage(register.getCUSTOMERMOBILE().toString().trim(), message);
							} catch (Exception e) {
								logger.error("Error in sending sms while registration", e);
							}
							try {
								loginDao.saveLogin(login);
							} catch (Exception ex) {

							}
						} catch (Exception ex) {
							resultjson.put("status", "406");
							resultjson.put("message", "Failure" + ex.toString());
							return resultjson.toString();
						}
					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "already mobile exists please try with other mobilenumber.");
						return resultjson.toString();
					}
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "already mail exists please try with other mailid.");
					return resultjson.toString();
				}
				return resultjson.toString();
			} else {
				resultjson.put("status", "406");
				resultjson.put("message", "Enter valid EmailId");
				return resultjson.toString();
			}
		} catch (Exception e) {

			// logger.error("error in data retrieval from app", e.getMessage());
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
	}

	@RequestMapping(value = "/registertest", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public String createsam(@RequestBody Map<String, Object> json) throws JSONException {

		JSONObject resultjson = new JSONObject();
		String pattern = "[0-9]{4}";
		logger.debug("Input for Registration from Mobile", json);

		try {

			// Initializing
			Register register = new Register();
			Customer customer = new Customer();
			Login login = new Login();

			// Setting values
			register.setCUSTOMEREMAIL(json.get("email").toString().trim().toUpperCase());
			// register.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			register.setSIGNUPDATE(new Date());

			/*
			 * SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");
			 * 
			 * java.util.Date nowDate = new java.util.Date(); String currentYear =
			 * formatNowYear.format(nowDate);
			 * System.out.println("currentYear "+currentYear); double customerno =
			 * registerDao.retrieveCustomerNo();
			 * System.out.println("customerno "+customerno); if (customerno == 0) { String
			 * num = "GBR" + currentYear + "00000000" + 1; customer.setCUSTOMERNO(num);
			 * register.setCUSTOMERNO(num); login.setCUSTOMERNO(num); } else {
			 * 
			 * String[] v = String.format("%13f", customerno).split("\\.");
			 * 
			 * String num = "GBR" + v[0]; customer.setCUSTOMERNO(num);
			 * register.setCUSTOMERNO(num); login.setCUSTOMERNO(num); }
			 * 
			 * System.out.println("hellooo");
			 */

			customer.setCUSTOMERNO(json.get("customerno").toString().trim());
			register.setCUSTOMERNO(json.get("customerno").toString().trim());
			login.setCUSTOMERNO(json.get("customerno").toString().trim());

			customer.setCUSTOMERFIRSTNAME(json.get("firstname").toString().toUpperCase());
			customer.setCUSTOMERLASTNAME(json.get("lastname").toString().toUpperCase());
			customer.setCUSTOMEREMAIL(json.get("email").toString().toUpperCase());
			// customer.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			// customer.setCUSTOMERADDRESSCOUNTRYCODE(json.get("countrycode").toString());
			customer.setCUSTOMERDATEOFBIRTH(json.get("dateofbirth").toString().toUpperCase());
			customer.setCUSTOMERGENDER(json.get("gender").toString().toUpperCase());
			customer.setCREATEDON(new Date());

			if (json.get("countrycode").toString().equalsIgnoreCase("+44")) {
				customer.setCUSTOMERADDRESSCOUNTRYCODE("GB");

			} else if (json.get("countrycode").toString().equalsIgnoreCase("+91")) {
				customer.setCUSTOMERADDRESSCOUNTRYCODE("IN");
			}

			customer.setCUSTOMERMOBILE(
					json.get("countrycode").toString().trim() + json.get("mobile").toString().trim());
			register.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			try {
				customer.setCUSTOMERMIDDLENAME(json.get("middlename").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERMIDDLENAME("");
			}
			try {
				customer.setCUSTOMERADDRESS1(json.get("Address").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESS1(null);
			}

			try {
				customer.setCUSTOMERADDRESS2(json.get("Address2").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESS2(null);
			}

			try {
				customer.setCUSTOMERADDRESSCITY(json.get("city").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSCITY(null);
			}

			try {
				customer.setCUSTOMERADDRESSCOUNTRY(json.get("Country").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSCOUNTRY(null);
			}
			try {
				customer.setCUSTOMERADDRESSSTATE(json.get("County").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSSTATE(null);
			}

			customer.setUSERCATEGORIES_ID(0);
			customer.setCUSTOMERCATEGORY("pending");

			try {
				customer.setCUSTOMERADDRESSZIP(json.get("Zipcode").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSZIP(null);
			}
			customer.setCUSTOMERPOBOX(null);
			customer.setCUSTOMERPHONE(null);
			customer.setNOTIFYCUSTOMERBYEMAIL("0");
			customer.setNOTIFYCUSTOMERBYSMS("0");
			try {
				customer.setCUSTOMERNATIONALITYCODE(json.get("Nationality").toString().toUpperCase());
			} catch (Exception e) {
				customer.setCUSTOMERNATIONALITYCODE(null);
			}
			customer.setCUSTOMERIMAGE(null);

			register.setRECORDSTATUS(0);
			register.setSIGNUPOTP(new CommonUtil().generateSixDigitNumericString());
			register.setSIGNUPCLIENT(null);

			login.setCUSTOMEREMAIL(json.get("email").toString().trim().toUpperCase());
			login.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			login.setRECORDSTATUS(0);
			login.setUSERCATEGORY(0);
			login.setTIMESTAMP(null);

			// To perform action
			if (json.get("email").toString().trim().matches(new Constants().getEMAIL_REGEX())) {

				if (json.get("password").toString().matches(pattern)) {
					CryptoUtil ct = new CryptoUtil();
					register.setPASSWORD(ct.encrypt(new Constants().getSaltkey(), json.get("password").toString()));
					login.setPASSWORD(ct.encrypt(new Constants().getSaltkey(), json.get("password").toString()));

				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Password should have 4 digits");
					return resultjson.toString();
				}
				int idd = 0;
				Register reg = registerDao.findByCUSTOMEREMAIL(json.get("email").toString());

				idd = reg.getSIGNUPS_ID();

				if (idd == 0) {

					reg = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());

					idd = reg.getSIGNUPS_ID();

					if (idd == 0) {

						try {

							registerDao.saveReg(register);
							registerDao.saveCustomer(customer);
							Register reg1 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString().trim(),
									register.getPASSWORD());
							idd = reg1.getSIGNUPS_ID();
							resultjson.put("status", "200");
							resultjson.put("message", "Success");
							resultjson.put("OTP", reg1.getSIGNUPOTP());
							String message = "Thank you for registering with Crosspay. Your OTP is "
									+ reg1.getSIGNUPOTP();

							try {
								// new
								// SendSMS().sendMessage(register.getCUSTOMERMOBILE().toString().trim(),message);
							} catch (Exception e) {
								logger.error("Error in sending sms while registration", e);
							}
							try {
								loginDao.saveLogin(login);
							} catch (Exception ex) {

							}
						} catch (Exception ex) {
							resultjson.put("status", "406");
							resultjson.put("message", "Failure" + ex.toString());
							return resultjson.toString();
						}
					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "already mobile exists please try with other mobilenumber.");
						return resultjson.toString();
					}
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "already mail exists please try with other mailid.");
					return resultjson.toString();
				}
				return resultjson.toString();
			} else {
				resultjson.put("status", "406");
				resultjson.put("message", "Enter valid EmailId");
				return resultjson.toString();
			}
		} catch (Exception e) {

			// logger.error("error in data retrieval from app", e.getMessage());
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
	}

	@RequestMapping("/validateOTP")
	@ResponseBody
	public String validateOTP(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			Register register = new Register();
			Login login = new Login();

			register.setSIGNUPOTP(json.get("otp").toString());
			register.setCUSTOMERMOBILE(json.get("mobile").toString());
			try {
				login.setCUSTOMERMOBILE(json.get("mobile").toString());
				login.setRECORDSTATUS(1);
				Register res1 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());
				int id = res1.getSIGNUPS_ID();
				if (id == 0) {
					resultjson.put("status", "406");
					resultjson.put("message", "Not Registered with this mobile number");
					return resultjson.toString();
				} else {

					try {

						if (res1.getSIGNUPOTP().equalsIgnoreCase(register.getSIGNUPOTP())) {
							resultjson.put("status", "200");
							resultjson.put("message", "Success");
							resultjson.put("user_id", res1.getSIGNUPS_ID());
							register.setSIGNUPOTP("0");
							List<Map<String, Object>> cus = registerDao.retrieveCustomerProfile(res1.getCUSTOMERNO());
							resultjson.put("first_name", cus.get(0).get("first_name"));
							resultjson.put("last_name", cus.get(0).get("last_name"));
							resultjson.put("email", cus.get(0).get("email"));
							resultjson.put("mobile", cus.get(0).get("mobile"));
							int otpupdate = registerDao.updateOtp(register);
							loginDao.updateLoginStatus(login);
						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "Invalid OTP");
						}
					} catch (Exception e) {

					}
				}
			} catch (Exception ex) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				return resultjson.toString();
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST }, headers = { "Content-type=application/json" })
	@ResponseBody
	public String loginsss(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			Register reg = new Register();

			reg.setCUSTOMERMOBILE(json.get("mobile").toString().trim());

			try {
				List<Map<String, Object>> res = loginDao.checkLoginCredential(reg);
				String pswd = null;
				try {
					pswd = json.get("password").toString();
				} catch (Exception e) {

				}
				if (res.size() == 0) {

					resultjson.put("status", "406");
					resultjson.put("message", "Not Registered with this mobile number");
					return resultjson.toString();

				} else {

					Register res2 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());
					int id = res2.getSIGNUPS_ID();
					String otp = null;
					try {
						otp = res2.getSIGNUPOTP();
						if (otp.equalsIgnoreCase("0") || otp == null) {

							int recordStatus = Integer.parseInt(res.get(0).get("RECORDSTATUS").toString());

							if ((pswd == null)) {

								try {
									reg.setSIGNUPOTP(new CommonUtil().generateSixDigitNumericString());
									int update = registerDao.updateOtp(reg);
									otp = registerDao.retrieveOTP(reg);
								} catch (Exception e) {
									System.out.println("record status " + e.toString());
								}

								resultjson.put("status", "200");
								resultjson.put("message", "Please Verify Your Mobile Number for Accessing home Page");
								resultjson.put("OTP", otp);

							} else if ((pswd != null)) {// && (recordStatus != 0)

								CryptoUtil ct = new CryptoUtil();
								String encryptedpswd = ct.encrypt(new Constants().getSaltkey(), pswd);
								try {
									Register res1 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString(),
											encryptedpswd);

									if (res1.getSIGNUPS_ID() == 0) {
										resultjson.put("status", "406");
										resultjson.put("message", "Invalid Credentials.");
									} else {
										resultjson.put("status", "200");
										resultjson.put("message", "Success");
										resultjson.put("user_id", res1.getSIGNUPS_ID());
										try {
											List<Map<String, Object>> cus = registerDao
													.retrieveCustomerProfile(res2.getCUSTOMERNO());

											resultjson.put("first_name", cus.get(0).get("first_name"));
											resultjson.put("last_name", cus.get(0).get("last_name"));
											resultjson.put("email", cus.get(0).get("email"));
											resultjson.put("mobile", cus.get(0).get("mobile"));
										} catch (Exception e) {
											System.out.println("error in customerdetails retrieval " + e.toString());
										}

									}
								} catch (Exception e) {
									resultjson.put("status", "406");
									resultjson.put("message", "Invalid Credentials.");
									return resultjson.toString();
								}
							}

						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "Not a Verified User.");
							resultjson.put("OTP", otp);
							return resultjson.toString();
						}
					} catch (Exception e) {
						resultjson.put("status", "406");
						resultjson.put("message", "Failure");
					}

				}
			} catch (Exception ex) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				return resultjson.toString();
			}
		} catch (Exception ex) {
			try {
				Register reg = new Register();
				reg.setCUSTOMEREMAIL(json.get("email").toString().trim());
				String pswd = json.get("password").toString();
				try {
					List<Map<String, Object>> res = loginDao.checkLoginCredentialEmail(reg);
					if (res.size() == 0) {

						resultjson.put("status", "406");
						resultjson.put("message", "Not Registered with this Email Id");
						return resultjson.toString();

					} else {
						Register res2 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString());
						int id = res2.getSIGNUPS_ID();
						String otp = null;
						try {
							otp = res2.getSIGNUPOTP();
							if (otp.equalsIgnoreCase("0") || otp == null) {

								int recordStatus = Integer.parseInt(res.get(0).get("RECORDSTATUS").toString());
								System.out.println("record status " + recordStatus);

								CryptoUtil ct = new CryptoUtil();
								String encryptedpswd = ct.encrypt(new Constants().getSaltkey(), pswd);
								try {
									Register res1 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString(),
											encryptedpswd);

									if (res1.getSIGNUPS_ID() == 0) {
										resultjson.put("status", "406");
										resultjson.put("message", "Invalid Credentials.");
									} else {
										resultjson.put("status", "200");
										resultjson.put("message", "Success");
										resultjson.put("user_id", res1.getSIGNUPS_ID());
										try {
											List<Map<String, Object>> cus = registerDao
													.retrieveCustomerProfile(res2.getCUSTOMERNO());

											resultjson.put("first_name", cus.get(0).get("first_name"));
											resultjson.put("last_name", cus.get(0).get("last_name"));
											resultjson.put("email", cus.get(0).get("email"));
										} catch (Exception e) {
											System.out.println("error in customerdetails retrieval " + e.toString());
										}

									}
								} catch (Exception e) {
									resultjson.put("status", "406");
									resultjson.put("message", "Invalid Credentials.");
									return resultjson.toString();
								}

							} else {
								resultjson.put("status", "406");
								resultjson.put("message", "Not a Verified User.");
								resultjson.put("OTP", otp);
								return resultjson.toString();
							}
						} catch (Exception e) {
							resultjson.put("status", "406");
							resultjson.put("message", "Failure");
						}

					} ////////
				} catch (Exception e) {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
				}
			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Some input field missing");
				return resultjson.toString();
			}

		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/adminlogin", method = { RequestMethod.POST }, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String adminloginsss(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			Register reg = new Register();

			reg.setCUSTOMERMOBILE(json.get("mobile").toString().trim());

			try {
				List<Map<String, Object>> res = loginDao.checkadminLoginCredential(reg);
				String pswd = null;
				try {
					pswd = json.get("password").toString();
				} catch (Exception e) {

				}
				if (res.size() == 0) {

					resultjson.put("status", "406");
					resultjson.put("message", "Please Contact Admin.");
					return resultjson.toString();

				} else {

					Register res2 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());
					int id = res2.getSIGNUPS_ID();
					String otp = null;
					try {
						otp = res2.getSIGNUPOTP();
						if (otp.equalsIgnoreCase("0") || otp == null) {

							int recordStatus = Integer.parseInt(res.get(0).get("RECORDSTATUS").toString());
							System.out.println("record status " + recordStatus);
							if ((pswd == null)) {

								try {
									reg.setSIGNUPOTP(new CommonUtil().generateSixDigitNumericString());
									int update = registerDao.updateOtp(reg);
									otp = registerDao.retrieveOTP(reg);
								} catch (Exception e) {
									System.out.println("record status " + e.toString());
								}

								resultjson.put("status", "200");
								resultjson.put("message", "Please Verify Your Mobile Number for Accessing home Page");
								resultjson.put("OTP", otp);
								System.out.println("pswd null record status123" + resultjson);

							} else if ((pswd != null)) {// && (recordStatus != 0)

								CryptoUtil ct = new CryptoUtil();
								String encryptedpswd = ct.encrypt(new Constants().getSaltkey(), pswd);
								try {
									Register res1 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString(),
											encryptedpswd);

									if (res1.getSIGNUPS_ID() == 0) {
										resultjson.put("status", "406");
										resultjson.put("message", "Invalid Credentials.");
									} else {
										resultjson.put("status", "200");
										resultjson.put("message", "Success");
										resultjson.put("user_id", res1.getSIGNUPS_ID());
										try {
											List<Map<String, Object>> cus = registerDao
													.retrieveCustomerProfile(res2.getCUSTOMERNO());

											resultjson.put("first_name", cus.get(0).get("first_name"));
											resultjson.put("last_name", cus.get(0).get("last_name"));
											resultjson.put("email", cus.get(0).get("email"));
											resultjson.put("mobile", cus.get(0).get("mobile"));
										} catch (Exception e) {
											System.out.println("error in customerdetails retrieval " + e.toString());
										}

									}
								} catch (Exception e) {
									resultjson.put("status", "406");
									resultjson.put("message", "Invalid Credentials.");
									return resultjson.toString();
								}
							}

						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "Not a Verified User.");
							resultjson.put("OTP", otp);
							return resultjson.toString();
						}
					} catch (Exception e) {
						resultjson.put("status", "406");
						resultjson.put("message", "Failure");
					}

				}
			} catch (Exception ex) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				return resultjson.toString();
			}
		} catch (Exception ex) {
			try {
				Register reg = new Register();
				reg.setCUSTOMEREMAIL(json.get("email").toString().trim());
				String pswd = json.get("password").toString();
				try {
					List<Map<String, Object>> res = loginDao.checkLoginCredentialEmail(reg);
					if (res.size() == 0) {

						resultjson.put("status", "406");
						resultjson.put("message", "Not Registered with this Email Id");
						return resultjson.toString();

					} else {
						Register res2 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString());
						int id = res2.getSIGNUPS_ID();
						String otp = null;
						try {
							otp = res2.getSIGNUPOTP();
							if (otp.equalsIgnoreCase("0") || otp == null) {

								int recordStatus = Integer.parseInt(res.get(0).get("RECORDSTATUS").toString());

								CryptoUtil ct = new CryptoUtil();
								String encryptedpswd = ct.encrypt(new Constants().getSaltkey(), pswd);
								try {
									Register res1 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString(),
											encryptedpswd);

									if (res1.getSIGNUPS_ID() == 0) {
										resultjson.put("status", "406");
										resultjson.put("message", "Invalid Credentials.");
									} else {
										resultjson.put("status", "200");
										resultjson.put("message", "Success");
										resultjson.put("user_id", res1.getSIGNUPS_ID());
										try {
											List<Map<String, Object>> cus = registerDao
													.retrieveCustomerProfile(res2.getCUSTOMERNO());

											resultjson.put("first_name", cus.get(0).get("first_name"));
											resultjson.put("last_name", cus.get(0).get("last_name"));
											resultjson.put("email", cus.get(0).get("email"));
										} catch (Exception e) {
											System.out.println("error in customerdetails retrieval " + e.toString());
										}

									}
								} catch (Exception e) {
									resultjson.put("status", "406");
									resultjson.put("message", "Invalid Credentials.");
									return resultjson.toString();
								}

							} else {
								resultjson.put("status", "406");
								resultjson.put("message", "Not a Verified User.");
								resultjson.put("OTP", otp);
								return resultjson.toString();
							}
						} catch (Exception e) {
							resultjson.put("status", "406");
							resultjson.put("message", "Failure");
						}

					} ////////
				} catch (Exception e) {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
				}
			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Some input field missing");
				return resultjson.toString();
			}

		}
		return resultjson.toString();
	}

	@RequestMapping("/mpinVerification")
	@ResponseBody
	public String mpinVerification(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			Register reg = new Register();
			reg.setCUSTOMERMOBILE(json.get("mobile").toString());
			reg.setPASSWORD(new CryptoUtil().encrypt(new Constants().getSaltkey(), json.get("password").toString()));

			Register success = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());

		} catch (Exception ex) {
			resultjson.put("status", "300");
			resultjson.put("message", "Failure");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/changeMpin", method = { RequestMethod.POST }, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public String changeMpin(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		String pattern = "[0-9]{4}";
		try {
			String mobile = json.get("mobile").toString();
			String mpin = json.get("mpin").toString();
			String new_mpin = json.get("new_mpin").toString();
			if (mobile == null || mobile.equalsIgnoreCase("") || mobile.isEmpty()) {
				resultjson.put("status", "406");
				resultjson.put("message", "Mobile should not to be empty");
			} else if (mpin == null || mpin.equalsIgnoreCase("") || mpin.isEmpty()) {
				resultjson.put("status", "406");
				resultjson.put("message", "Mpin should not to be empty");
			} else if (new_mpin == null || new_mpin.equalsIgnoreCase("") || new_mpin.isEmpty()) {
				resultjson.put("status", "406");
				resultjson.put("message", "New Mpin should not to be empty");
			} else {
				if (new_mpin.toString().matches(pattern)) {
					try {
						Register reg = new Register();
						Login login = new Login();
						LoginCredentialHistory loginhistory = new LoginCredentialHistory();
						login.setCUSTOMERMOBILE(mobile);

						reg.setCUSTOMERMOBILE(mobile);
						reg.setPASSWORD(new CryptoUtil().encrypt(new Constants().getSaltkey(), mpin));

						Register success = registerDao.findByCUSTOMERMOBILE(mobile);
						int id = success.getSIGNUPS_ID();
						if (id == 0) {
							resultjson.put("status", "406");
							resultjson.put("message", "Not Registered with Mobile Number.");
						} else {
							String customer_no = success.getCUSTOMERNO();
							success = registerDao.findByCUSTOMERMOBILE(mobile, reg.getPASSWORD());
							
							id = success.getSIGNUPS_ID();
							if (id == 0) {
								resultjson.put("status", "406");
								resultjson.put("message", "MPIN Credential is wrong.");
							} else {
								reg.setPASSWORD(new CryptoUtil().encrypt(new Constants().getSaltkey(), new_mpin));
								login.setPASSWORD(new CryptoUtil().encrypt(new Constants().getSaltkey(), new_mpin));

								registerDao.updateMpin(reg);
								loginDao.updateLoginPassword(login);
								loginhistory.setCUSTOMERNO(customer_no);
								loginhistory.setPASSWORD(login.getPASSWORD());
								loginhistory.setOTPUSEDFORCHANGE(null);
								loginhistory.setPASSWORDCHANGECLIENT(null);
								loginhistory.setPASSWORDCHANGEDBY(null);
								loginhistory.setPASSWORDCHANGEREASON(null);
								loginhistory.setPASSWORDCREATEDON(new Date());
								loginhistory.setPASSWORDEXPIREDON(null);
								loginhistory.setPASSWORDNO(0);
								loginhistory.setTIMESTAMP(null);
								loginhistory.setUSERCATEGORY(0);
								loginDao.saveLoginHistory(loginhistory);
								try {
									List<Map<String, Object>> retrieveCustomerProfile = registerDao
											.retrieveCustomerProfile(customer_no);
									CustomTemplate ctemplate = new CustomTemplate();
									ctemplate.setTemplatename("mpin.html");
									ctemplate.setEmail(success.getCUSTOMEREMAIL());
									ctemplate.setMpin(new_mpin);
									ctemplate.setName(retrieveCustomerProfile.get(0).get("first_name").toString() + " "
											+ retrieveCustomerProfile.get(0).get("last_name").toString());
									ctemplate.setSubject("Change MPin");
									emailService.sendEmailTemplate(ctemplate);
								} catch (Exception e) {
									logger.error("Error in sending mail after changempin", e);
								}
								resultjson.put("status", "200");
								resultjson.put("message", "Success");
							}
						}

					} catch (Exception ex) {
						resultjson.put("status", "406");
						resultjson.put("message", "Failure");
					}
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "New Mpin should have 4 digits");
				}
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
		}
		return resultjson.toString();
	}

	@RequestMapping(value = "/changeMpinsamp")
	@ResponseBody
	public String changeMpinsamp(@RequestBody Map<String, Object> json) throws JSONException {
		String email = json.get("email").toString();
		/*
		 * JSONObject resultjson = new JSONObject(); String pattern = "[0-9]{4}"; try {
		 * String mobile = json.get("mobile").toString(); String email =
		 * json.get("email").toString(); String mpin = json.get("mpin").toString();
		 * String new_mpin = json.get("new_mpin").toString(); if (mobile == null ||
		 * mobile.equalsIgnoreCase("") || mobile.isEmpty()) { resultjson.put("status",
		 * "406"); resultjson.put("message", "Mobile should not to be empty"); } else if
		 * (mpin == null || mpin.equalsIgnoreCase("") || mpin.isEmpty()) {
		 * resultjson.put("status", "406"); resultjson.put("message",
		 * "Mpin should not to be empty"); } else if (new_mpin == null ||
		 * new_mpin.equalsIgnoreCase("") || new_mpin.isEmpty()) {
		 * resultjson.put("status", "406"); resultjson.put("message",
		 * "New Mpin should not to be empty"); } else {
		 * if(new_mpin.toString().matches(pattern)) { try { Register reg = new
		 * Register(); Login login = new Login(); LoginCredentialHistory loginhistory =
		 * new LoginCredentialHistory(); login.setCUSTOMERMOBILE(mobile);
		 * 
		 * reg.setCUSTOMERMOBILE(mobile); reg.setPASSWORD( new CryptoUtil().encrypt(new
		 * Constants().getSaltkey(), mpin));
		 * 
		 * Register success = registerDao.findByCUSTOMERMOBILE(mobile); int id =
		 * success.getSIGNUPS_ID(); if (id == 0) { resultjson.put("status", "406");
		 * resultjson.put("message", "Not Registered with Mobile Number."); } else {
		 * String customer_no = success.getCUSTOMERNO(); success =
		 * registerDao.findByCUSTOMERMOBILE(mobile, reg.getPASSWORD()); id =
		 * success.getSIGNUPS_ID(); if (id == 0) { resultjson.put("status", "406");
		 * resultjson.put("message", "MPIN Credential is wrong."); } else {
		 * reg.setPASSWORD(new CryptoUtil().encrypt(new Constants().getSaltkey(),
		 * new_mpin)); login.setPASSWORD(new CryptoUtil().encrypt(new
		 * Constants().getSaltkey(), new_mpin));
		 * 
		 * registerDao.updateMpin(reg); loginDao.updateLoginPassword(login);
		 * loginhistory.setCUSTOMERNO(customer_no);
		 * loginhistory.setPASSWORD(login.getPASSWORD());
		 * loginhistory.setOTPUSEDFORCHANGE(null);
		 * loginhistory.setPASSWORDCHANGECLIENT(null);
		 * loginhistory.setPASSWORDCHANGEDBY(null);
		 * loginhistory.setPASSWORDCHANGEREASON(null);
		 * loginhistory.setPASSWORDCREATEDON(new Date());
		 * loginhistory.setPASSWORDEXPIREDON(null); loginhistory.setPASSWORDNO(0);
		 * loginhistory.setTIMESTAMP(null); loginhistory.setUSERCATEGORY(0);
		 * loginDao.saveLoginHistory(loginhistory); try {
		 * 
		 * }catch(Exception e) {
		 * 
		 * } resultjson.put("status", "200"); resultjson.put("message", "Success"); } }
		 * 
		 * } catch (Exception ex) { resultjson.put("status", "406");
		 * resultjson.put("message", "Failure"); } }else { resultjson.put("status",
		 * "406"); resultjson.put("message", "New Mpin should have 4 digits"); } } }
		 * catch (Exception ex) { resultjson.put("status", "406");
		 * resultjson.put("message", "Some input field missing"); }
		 */
		String data = "";
		try {
			registerDao.changepswd(email);
			data = "success";
		} catch (Exception e) {
			data = "fail " + e.toString();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping("/forgotMpin")
	@ResponseBody
	public String forgotMpin(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			String mobile = null;
			try {
				mobile = json.get("mobile").toString();
				logger.info("Mobile from app" + mobile);
			} catch (Exception e) {
				logger.error("Error in mobile retrieval from app", e);
			}
			String email = null;
			try {
				email = json.get("email").toString();
				logger.info("email from app" + email);
			} catch (Exception e) {
				logger.error("Error in email retrieval from app", e);
			}

			if ((mobile == null || mobile.equalsIgnoreCase("") || mobile.isEmpty())
					&& (email == null || email.equalsIgnoreCase("") || email.isEmpty())) {
				resultjson.put("status", "406");
				if (mobile == null) {
					resultjson.put("message", "Mobile should not to be empty");
				} else if (email == null) {
					resultjson.put("message", "Email should not to be empty");
				}

			} else {
				try {
					Register reg = new Register();

					if (mobile == null) {
						if (email == null) {

						} else {
							reg.setCUSTOMEREMAIL(json.get("email").toString().trim());

							Register success = registerDao.findByCUSTOMEREMAIL(json.get("email").toString().trim());
							int id = success.getSIGNUPS_ID();
							if (id == 0) {
								resultjson.put("status", "406");
								resultjson.put("message", "Not Registered with Email Id.");
							} else {

								String otp = new CommonUtil().generateSixDigitNumericString();
								reg.setSIGNUPOTP(otp);
								int result = registerDao.updateOtpEmail(reg);
								if (result == 1) {
									resultjson.put("status", "200");
									resultjson.put("message", "Success");
									resultjson.put("OTP", otp);

								} else {
									resultjson.put("status", "406");
									resultjson.put("message", "OTP not generated please try again");
								}

							}
						}
					} else {
						reg.setCUSTOMERMOBILE(json.get("mobile").toString());

						Register success = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());
						int id = success.getSIGNUPS_ID();
						if (id == 0) {
							resultjson.put("status", "406");
							resultjson.put("message", "Not Registered with Mobile Number.");
						} else {

							String otp = new CommonUtil().generateSixDigitNumericString();
							reg.setSIGNUPOTP(otp);
							int result = registerDao.updateOtp(reg);

							if (result == 1) {
								resultjson.put("status", "200");
								resultjson.put("message", "Success");
								resultjson.put("OTP", otp);

								try {
									String message = "Please validate your mobile using OTP " + otp;
									new SendSMS().sendMessage(reg.getCUSTOMERMOBILE().toString(), message);
								} catch (Exception e) {
									logger.error("Error in sending sms while registration", e);
								}
							} else {
								resultjson.put("status", "406");
								resultjson.put("message", "OTP not generated please try again");
							}

						}
					}

				} catch (Exception ex) {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
				}
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
		}
		return resultjson.toString();
	}

	@RequestMapping("/validateForgotOTP")
	@ResponseBody
	public String validateForgotOTP(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			Register register = new Register();
			Login login = new Login();

			register.setSIGNUPOTP(json.get("otp").toString());
			String mobile = null;
			try {
				mobile = json.get("mobile").toString();
			} catch (Exception e) {

			}
			String email = null;
			try {
				email = json.get("email").toString();
			} catch (Exception e) {

			}

			if ((mobile == null || mobile.equalsIgnoreCase("") || mobile.isEmpty())
					&& (email == null || email.equalsIgnoreCase("") || email.isEmpty())) {
				resultjson.put("status", "406");
				resultjson.put("message", "Mobile/Email should not to be empty");
			} else {
				try {
					Register reg = new Register();

					if (mobile == null) {
						if (email == null) {
						} else {

							register.setCUSTOMEREMAIL(json.get("email").toString().trim());
							try {
								login.setCUSTOMEREMAIL(json.get("email").toString().trim());
								login.setRECORDSTATUS(1);
								Register res1 = registerDao.findByCUSTOMEREMAIL(json.get("email").toString().trim());
								int id = res1.getSIGNUPS_ID();
								if (id == 0) {
									resultjson.put("status", "406");
									resultjson.put("message", "Not Registered with this Email Id");
									return resultjson.toString();
								} else {

									try {

										if (res1.getSIGNUPOTP().equalsIgnoreCase(register.getSIGNUPOTP())) {
											resultjson.put("status", "200");
											resultjson.put("message", "Success");
											resultjson.put("MPIN", new CryptoUtil()
													.decrypt(new Constants().getSaltkey(), res1.getPASSWORD()));
											register.setSIGNUPOTP("0");
											int otpupdate = registerDao.updateOtpEmail(register);
											loginDao.updateLoginStatus(login);
										} else {
											resultjson.put("status", "406");
											resultjson.put("message", "Invalid OTP");
										}
									} catch (Exception e) {

									}
								}
							} catch (Exception ex) {
								resultjson.put("status", "406");
								resultjson.put("message", "Failure");
								return resultjson.toString();
							}
						}
					} else {
						register.setCUSTOMERMOBILE(json.get("mobile").toString());
						try {
							login.setCUSTOMERMOBILE(json.get("mobile").toString());
							login.setRECORDSTATUS(1);
							Register res1 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());
							int id = res1.getSIGNUPS_ID();
							if (id == 0) {
								resultjson.put("status", "406");
								resultjson.put("message", "Not Registered with this mobile number");
								return resultjson.toString();
							} else {

								try {

									if (res1.getSIGNUPOTP().equalsIgnoreCase(register.getSIGNUPOTP())) {
										resultjson.put("status", "200");
										resultjson.put("message", "Success");
										resultjson.put("MPIN", new CryptoUtil().decrypt(new Constants().getSaltkey(),
												res1.getPASSWORD()));
										register.setSIGNUPOTP("0");
										int otpupdate = registerDao.updateOtp(register);
										loginDao.updateLoginStatus(login);
									} else {
										resultjson.put("status", "406");
										resultjson.put("message", "Invalid OTP");
									}
								} catch (Exception e) {

								}
							}
						} catch (Exception ex) {
							resultjson.put("status", "406");
							resultjson.put("message", "Failure");
							return resultjson.toString();
						}
					}

				} catch (Exception ex) {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
					return resultjson.toString();
				}
			}

		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}

		return resultjson.toString();
	}

	@RequestMapping("/logout")
	@ResponseBody
	public String logout(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			Register reg = new Register();
			Login logincredentials = new Login();
			reg.setCUSTOMERMOBILE(json.get("mobile").toString().trim());

			try {
				Register reg1 = registerDao.findByCUSTOMERMOBILE(json.get("mobile").toString());
				logincredentials.setCUSTOMERMOBILE(reg.getCUSTOMERMOBILE());
				if (reg1.getSIGNUPS_ID() == 0) {
					resultjson.put("status", "406");
					resultjson.put("message", "Not Registered with this mobile number");
				} else {
					int status = loginDao.updateLoginStatus(logincredentials);
					if (status == 1) {
						resultjson.put("status", "200");
						resultjson.put("message", "success");
					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "Failure");
					}
				}
			} catch (Exception ex) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				return resultjson.toString();
			}

		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping("/updateProfile")
	@ResponseBody
	public String updateProfile(@RequestBody Map<String, Object> json) throws JSONException {
		String userId = "";
		JSONObject resultjson = new JSONObject();
		String patterns = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		String pattern = "[0-9]{4}";
		logger.debug("Input for Registration from Mobile", json);

		try {

			// Initializing
			Register register = new Register();
			Customer customer = new Customer();
			Login login = new Login();

			// Setting values
			userId = json.get("user_id").toString();
			register.setCUSTOMEREMAIL(json.get("email").toString().trim());
			register.setCUSTOMERMOBILE(json.get("mobile").toString().trim());

			customer.setCUSTOMERFIRSTNAME(json.get("firstname").toString());
			customer.setCUSTOMERLASTNAME(json.get("lastname").toString());
			customer.setCUSTOMEREMAIL(json.get("email").toString().trim());
			customer.setCUSTOMERMOBILE(json.get("mobile").toString().trim());
			try {
				customer.setCUSTOMERMIDDLENAME(json.get("middlename").toString());
			} catch (Exception e) {
				customer.setCUSTOMERMIDDLENAME("");
				logger.error("Error in middlename from app", e);
			}

			customer.setCUSTOMERADDRESSCOUNTRYCODE(json.get("countrycode").toString());
			customer.setCUSTOMERDATEOFBIRTH(json.get("dateofbirth").toString());
			customer.setCUSTOMERGENDER(json.get("gender").toString());

			customer.setCUSTOMERADDRESS1(json.get("address1").toString());
			try {
				customer.setCUSTOMERADDRESS2(json.get("address").toString());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESS2("");
			}
			customer.setCUSTOMERADDRESSCITY(json.get("city").toString());
			customer.setCUSTOMERADDRESSSTATE(json.get("state").toString());
			try {
				customer.setCUSTOMERADDRESSZIP(json.get("pincode").toString());
			} catch (Exception e) {
				customer.setCUSTOMERADDRESSZIP(null);
			}
			try {
				customer.setCUSTOMERPOBOX(json.get("pobox").toString());
			} catch (Exception e) {
				customer.setCUSTOMERPOBOX(null);
			}
			try {
				customer.setCUSTOMERPHONE(json.get("phone").toString());
			} catch (Exception e) {
				customer.setCUSTOMERPHONE(null);
			}
			customer.setCUSTOMERNATIONALITYCODE(json.get("nationality").toString());
			customer.setCUSTOMERADDRESSCOUNTRY(json.get("country").toString());
			try {
				customer.setID_PROOF_TYPE(json.get("id_proof_type").toString());
				customer.setISSUED_BY(json.get("issued_by").toString());
				String issued_date = json.get("issued_date").toString();
				SimpleDateFormat formatNowYear1 = new SimpleDateFormat("yyyy-mm-dd");

				Date date = formatNowYear1.parse(issued_date);
				customer.setISSUED_DATE(date);
				customer.setEXPIRY_DATE(formatNowYear1.parse(json.get("expiry_date").toString()));
			} catch (Exception e) {
				customer.setID_PROOF_TYPE(null);
				customer.setISSUED_BY(null);
				customer.setISSUED_DATE(null);
				customer.setEXPIRY_DATE(null);
			}
			// customer.setCUSTOMERIMAGE(null);

			login.setCUSTOMEREMAIL(json.get("email").toString().trim());
			login.setCUSTOMERMOBILE(json.get("mobile").toString().trim());

			String customerno = registerDao.retrieveCustomerNumber(userId);
			customer.setCUSTOMERNO(customerno);
			register.setCUSTOMERNO(customerno);
			login.setCUSTOMERNO(customerno);

			// To perform action
			if (json.get("email").toString().trim().matches(new Constants().getEMAIL_REGEX())) {

				/*
				 * Boolean pswd = isValidPassword(json.get("password").toString(), pattern); if
				 * (json.get("password").toString().matches(pattern)) { CryptoUtil ct = new
				 * CryptoUtil(); register.setPASSWORD(ct.encrypt(new Constants().getSaltkey(),
				 * json.get("password").toString())); login.setPASSWORD(ct.encrypt(new
				 * Constants().getSaltkey(), json.get("password").toString()));
				 */
				try {
					int regsuccess = registerDao.updateRegdetails(register);

					int loginsucess = loginDao.updateLogindetails(login);

					int customersuccess = registerDao.updateCustomerdetails(customer);

					if (regsuccess == 1 && loginsucess == 1 && customersuccess == 1) {
						resultjson.put("status", "200");
						resultjson.put("message", "Profile updated successfully");
					} else {
						resultjson.put("status", "406");
						resultjson.put("message", "Profile Updation not done");
					}
				} catch (Exception e) {
					resultjson.put("status", "406");
					resultjson.put("message", "Internal Server Problem");
				}
				/*
				 * } else { resultjson.put("status", "406"); resultjson.put("message",
				 * "Password should have 4 digits"); return resultjson.toString(); }
				 */

			} else {
				resultjson.put("status", "406");
				resultjson.put("message", "Enter valid EmailId");
				return resultjson.toString();
			}
			return resultjson.toString();
		} catch (Exception e) {
			// logger.error("error in data retrieval from app", e.getMessage());
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
	}

	@RequestMapping("/viewProfile")
	@ResponseBody
	public String viewProfile(@RequestBody Map<String, Object> json) throws JSONException {
		String userId = "";
		JSONObject resultjson = new JSONObject();
		try {
			userId = json.get("user_id").toString();
			String customerno = registerDao.retrieveCustomerNumber(userId);
			List<Map<String, Object>> output = registerDao.retrieveCustomerProfile(customerno);
			if (output.isEmpty()) {
				resultjson.put("status", "201");
				resultjson.put("message", "Success");
				resultjson.put("data", "No Content");
			} else {
				resultjson.put("status", "200");
				resultjson.put("message", "Success");
				resultjson.put("data", output);
			}
		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
		}
		return resultjson.toString();
	}

	@Autowired
	private RegisterDao registerDao;

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private EmailService emailService;

}