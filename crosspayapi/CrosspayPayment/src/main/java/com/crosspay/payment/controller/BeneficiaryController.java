package com.crosspay.payment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crosspay.payment.model.Beneficiary;
import com.crosspay.payment.model.BeneficiaryBank;
import com.crosspay.payment.model.BeneficiaryIds;
import com.crosspay.payment.model.Register;
import com.crosspay.payment.service.CustomerDao;
import com.crosspay.payment.service.RegisterDao;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

	Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
	String defaultimage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAIAAAAiOjnJAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAxRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTM4IDc5LjE1OTgyNCwgMjAxNi8wOS8xNC0wMTowOTowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OTg1M0FFNEM5MkVGMTFFNzgyM0VEQ0NERTEwMzVBMzEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OTg1M0FFNEI5MkVGMTFFNzgyM0VEQ0NERTEwMzVBMzEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgV2luZG93cyI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJEQ0U0RjNENUM0OTI0NDA5MTRDODJFNkE5QTdEREVBNCIgc3RSZWY6ZG9jdW1lbnRJRD0iRENFNEYzRDVDNDkyNDQwOTE0QzgyRTZBOUE3RERFQTQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4iUPcbAAAc9UlEQVR42uyd6XdTVRfGkzRJpzRN0qYtVIZC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";

	@RequestMapping("/add")
	@ResponseBody
	public String rateExchange(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			
			String userId = json.get("user_id").toString();
			Beneficiary beneficiary = new Beneficiary();
			BeneficiaryBank beneficiarybank = new BeneficiaryBank();
			BeneficiaryIds beneficiaryids = new BeneficiaryIds();

			beneficiary.setBENEFICIARYFIRSTNAME(json.get("first_name").toString().trim());
		//	int bankType = Integer.parseInt(json.get("bank_type").toString());
			try {
				beneficiary.setBENEFICIARYMIDDLENAME(json.get("middle_name").toString().trim());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYMIDDLENAME(null);
				logger.error("Error in middle name retrieval", e);
			}
			beneficiary.setBENEFICIARYLASTNAME(json.get("last_name").toString().trim());
			beneficiary.setBENEFICIARYGENDER(json.get("gender").toString().trim());
			try {
				beneficiary.setBENEFICIARYPHONE(json.get("phone").toString().trim());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYPHONE("");
			}
			beneficiary.setBENEFICIARYADDRESSCOUNTRYCODE(json.get("country").toString());
			try {
				beneficiary.setDATEOFBIRTH(json.get("dob").toString());
			} catch (Exception e) {
				beneficiary.setDATEOFBIRTH(null);
			}
			beneficiary.setCREATEDON(new Date());
			beneficiary.setBENEFICIARYSTATUS(0);
			beneficiary.setNOTIFYBENEBYEMAIL(null);
			beneficiary.setNOTIFYBENEBYSMS(null);
			beneficiary.setBENEFICIARYEMAIL(null);
			try {
				if(json.get("image").toString().length()>0) {
					beneficiary.setIMAGE( new SerialBlob(json.get("image").toString().getBytes()));
				}else {
					beneficiary.setIMAGE(new SerialBlob(defaultimage.getBytes()));
				}
			}catch(Exception e) {
				beneficiary.setIMAGE(new SerialBlob(defaultimage.getBytes()));
			}
			try {
				beneficiary.setBENEFICIARYADDRESSZIP(json.get("pincode").toString());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYADDRESSZIP(null);
				logger.error("Error in middle name retrieval", e);
			}
			try {
				beneficiary.setBENEFICIARYMOBILE(json.get("mobile").toString().trim());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYMOBILE(null);
				logger.error("Error in middle name retrieval", e);
			}
			try {
				beneficiary.setBENEFICIARYPOBOX(json.get("pobox").toString());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYPOBOX(null);
				logger.error("Error in middle name retrieval", e);
			}

			beneficiarybank.setBENEFICIARYACCOUNTNAME(json.get("call_name").toString());
			beneficiarybank.setCREATEDON(new Date());
			beneficiarybank.setRECORDSTATUS(0);
			
			String customerno = registerDao.retrieveCustomerNumber(userId);
			
			beneficiary.setCUSTOMERNO(customerno);
			beneficiarybank.setCUSTOMERNO(customerno);
			beneficiarybank.setBENEFICIARYACCOUNTCCYCODE(json.get("ccycode").toString());
			beneficiarybank.setBANKTYPE(0);
			beneficiaryids.setBENEFICIARYIDTYPE(0);
			try {
			beneficiary.setBENEFICIARYADDRESSSTATE(json.get("state").toString());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYADDRESSSTATE("");
			}
			try {
			beneficiary.setBENEFICIARYADDRESSCITY(json.get("city").toString());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYADDRESSCITY("");
			}
		//	if (bankType == 0) {
				
				beneficiarybank.setBENEFICIARYBANKNAME(null);
				beneficiarybank.setBENEFICIARYBANKCODE(null);
				beneficiarybank.setSWIFTIFSC(null);
			
				try {
					
					beneficiary.setBENEFICIARYPHONE(json.get("phone").toString().trim());
					beneficiarybank.setBANKTYPE(0);
					beneficiaryids.setBENEFICIARYIDTYPE(0);
					
					beneficiaryids.setBENEFICIARYIDTYPEDESC("CASH");
				} catch (Exception e) {
					
					beneficiary.setBENEFICIARYPHONE(null);
					/*resultjson.put("status", "406");
					resultjson.put("message", "Some input field missing");
					return resultjson.toString();*/
				}
				
			//} else {

			
			
				try {
					beneficiarybank.setBENEFICIARYBANKACCOUNTNO(json.get("account_number").toString());
					
					beneficiarybank.setBENEFICIARYBANKNAME(json.get("bank_name").toString());
					beneficiarybank.setBENEFICIARYBANKCODE(null);
					beneficiarybank.setBANKTYPE(1);
					try {
					beneficiarybank.setBENEFICIARYBRANCHNAME(json.get("branch_name").toString());
					}catch(Exception e) {
						beneficiarybank.setBENEFICIARYBRANCHNAME(null);
					}
					beneficiaryids.setBENEFICIARYIDTYPE(1);
					beneficiaryids.setBENEFICIARYIDTYPEDESC("Bank");
					try {
						beneficiarybank.setSWIFTIFSC(json.get("ifsc").toString());
					}catch(Exception e) {
						beneficiarybank.setSWIFTIFSC(null);
					}
				} catch (Exception e) {
					beneficiarybank.setBENEFICIARYBANKACCOUNTNO(null);
					beneficiarybank.setSWIFTIFSC(null);
					beneficiarybank.setBENEFICIARYBANKNAME(null);
					beneficiarybank.setBENEFICIARYBANKCODE(null);
					beneficiarybank.setBENEFICIARYBRANCHNAME(null);
				/*	resultjson.put("status", "406");
					resultjson.put("message", "Some input field missing");
					return resultjson.toString();*/
				}
				
					
				

			//}

			
			if (!beneficiary.getBENEFICIARYFIRSTNAME().toString().equalsIgnoreCase("")
					|| !beneficiary.getBENEFICIARYFIRSTNAME().toString().isEmpty()) {
				if (!beneficiary.getBENEFICIARYLASTNAME().toString().isEmpty()
						|| !beneficiary.getBENEFICIARYLASTNAME().toString().equalsIgnoreCase("")) {
				/*	if (!beneficiary.getBENEFICIARYADDRESSSTATE().toString().isEmpty()
							|| !beneficiary.getBENEFICIARYADDRESSSTATE().toString().equalsIgnoreCase("")) {*/
						if (!beneficiary.getBENEFICIARYGENDER().toString().isEmpty()
								|| !beneficiary.getBENEFICIARYGENDER().toString().equalsIgnoreCase("")) {
							/*if (!beneficiary.getBENEFICIARYADDRESSCITY().toString().isEmpty()
									|| !beneficiary.getBENEFICIARYADDRESSCITY().toString().equalsIgnoreCase("")) {*/
								if (!beneficiarybank.getBENEFICIARYACCOUNTCCYCODE().toString().isEmpty()
										|| !beneficiarybank.getBENEFICIARYACCOUNTCCYCODE().toString()
												.equalsIgnoreCase("")) {
									// if(!beneficiarybank.getBENEFICIARYBANKACCOUNTNO().toString().isEmpty() ||
									// !beneficiarybank.getBENEFICIARYBANKACCOUNTNO().toString().equalsIgnoreCase(""))
									// {
									if (!beneficiary.getBENEFICIARYADDRESSCOUNTRYCODE().toString().isEmpty()
											|| !beneficiary.getBENEFICIARYADDRESSCOUNTRYCODE().toString()
													.equalsIgnoreCase("")) {

										try {
											int benno = 0;
											try {
												benno = customerDao.retrieveBeneficiaryNo();
											} catch (Exception e) {
												logger.error("Exception in beneiciaryno retrieval", e);
											}
											benno = benno+1;
											beneficiary.setBENEFICIARYNO(benno);
											beneficiarybank.setBENEFICIARYNO(benno);

											beneficiaryids.setBENEFICIARYNO(benno);
											beneficiaryids.setCUSTOMERNO(customerno);
											/*beneficiaryids.setBENEFICIARYIDTYPE(bankType);
											if (bankType == 0) {
												beneficiaryids.setBENEFICIARYIDTYPEDESC("Bank");
											} else {
												// beneficiaryids.setBENEFICIARYIDTYPEDESC("IBAN");
												beneficiaryids.setBENEFICIARYIDTYPEDESC("CASH");
											}*/
											beneficiaryids.setBENEFICIARYID(null);
											beneficiaryids.setBENEFICIARYIDISSUEDATE(new Date());
											beneficiaryids.setCREATEDON(new Date());
											beneficiaryids.setBENEFICIARYIDSTATUS(1);
											beneficiaryids.setBENEFICIARYIDOTHERTYPE(null);
											beneficiaryids.setBENEFICIARYIDISSUEDBY(null);
											beneficiaryids.setBENEFICIARYIDVALIDTHRU(new Date());
											beneficiaryids.setBENEFICIARYIDISSUEDAT(null);
											
											int duplicateben = customerDao.checkBeneficiaryNo(customerno, beneficiary.getBENEFICIARYMOBILE(),beneficiarybank.getBENEFICIARYBANKACCOUNTNO());
											
											if(duplicateben > 0) {
												resultjson.put("status", "406");
												resultjson.put("message", "Already beneficiary exists.");
												return resultjson.toString();
											}
											/*
											 * try { int accnum =
											 * customerDao.retrieveBeneficiaryAccountNo(json.get("account_number").
											 * toString(),userId); if(accnum == 0) {
											 * 
											 * }else { resultjson.put("status", "406"); resultjson.put("message",
											 * "Already this account number exists"); return resultjson.toString(); }
											 * }catch(Exception e) {
											 * 
											 * }
											 */
											int saveben = customerDao.saveBeneficiary(beneficiary);
											
											int savebenbank = customerDao.saveBeneficiaryBank(beneficiarybank);
											
											int savebenids = customerDao.saveBeneficiaryIds(beneficiaryids);
											

											if (saveben == 1 && savebenbank == 1 && savebenids == 1) {
												resultjson.put("status", "200");
												resultjson.put("message", "Success");
											} else {
												resultjson.put("status", "406");
												resultjson.put("message", "Failure");
											}

										} catch (Exception e) {
											resultjson.put("status", "406");
											resultjson.put("message", "Failure");
										}

									} else {
										resultjson.put("status", "406");
										resultjson.put("message", "Country should not be empty");
									}
									/*
									 * }else { resultjson.put("status", "406"); resultjson.put("message",
									 * "Account number should not be empty"); }
									 */

								} else {
									resultjson.put("status", "406");
									resultjson.put("message", "CurrencyCode should not be empty");
								}
							/*} else {
								resultjson.put("status", "406");
								resultjson.put("message", "City should not be empty");
							}*/
						} else {
							resultjson.put("status", "406");
							resultjson.put("message", "Gender should not be empty");
						}
					/*} else {
						resultjson.put("status", "406");
						resultjson.put("message", "State should not be empty");
					}*/

				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Lastname should not be empty");
				}

			} else {
				resultjson.put("status", "406");
				resultjson.put("message", "Firstname should not be empty");
			}

		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping("/view")
	@ResponseBody
	public String viewBeneficiary(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {

			String userId = json.get("user_id").toString();
			try {
				String customerno = registerDao.retrieveCustomerNumber(userId);

				List<Map<String, Object>> beneficiaryDetails = new ArrayList<Map<String, Object>>();
				beneficiaryDetails = customerDao.retrieveBeneficiaryDetails(customerno);
				if (beneficiaryDetails.isEmpty()) {
					resultjson.put("status", "201");
					resultjson.put("message", "Success");
					resultjson.put("data", "No Content");
				} else {
					resultjson.put("status", "200");
					resultjson.put("message", "Success");
					resultjson.put("data", beneficiaryDetails);
				}

			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}
	
	@RequestMapping("/viewBendetails")
	 @ResponseBody
	 public String viewBendetails(@RequestBody Map<String, Object> json) throws JSONException {
	  JSONObject resultjson = new JSONObject();
	  try {

	   String customerno = json.get("customerno").toString();
	   int customerid = 0;
	   try {
	    customerid = registerDao.retrieveUserId(customerno);
	    List<Map<String, Object>> customerProfile = registerDao.retrieveCustomerProfile(customerno);
	    if (customerProfile.isEmpty()) {
	     resultjson.put("status", "201");
	     resultjson.put("message", "Success");
	     resultjson.put("customer", "No Content");
	    }else {
	    List<Map<String, Object>> beneficiaryDetails = new ArrayList<Map<String, Object>>();
	    beneficiaryDetails = customerDao.retrieveBeneficiaryDetails(customerno);
	    if (beneficiaryDetails.isEmpty()) {
	     resultjson.put("status", "200");
	     resultjson.put("message", "Success");
	     resultjson.put("data", "No Content");
	     resultjson.put("customer", customerProfile);
	     resultjson.put("customerno", customerno);
	     resultjson.put("customerid", customerid);
	    } else {
	     resultjson.put("status", "200");
	     resultjson.put("message", "Success");
	     resultjson.put("data", beneficiaryDetails);
	     resultjson.put("customer", customerProfile);
	     resultjson.put("customerno", customerno);
	     resultjson.put("customerid", customerid);
	    }

	   } 
	   }catch (Exception e) {
	    resultjson.put("status", "406");
	    resultjson.put("message", "Failure");
	   }
	  } catch (Exception ex) {
	   resultjson.put("status", "406");
	   resultjson.put("message", "Some input field missing");
	   return resultjson.toString();
	  }
	  return resultjson.toString();
	 }
	
	@RequestMapping("/viewBendetailsbyemail")
	 @ResponseBody
	 public String viewBendetailsbyemail(@RequestBody Map<String, Object> json) throws JSONException {
	  JSONObject resultjson = new JSONObject();
	  String customerno =  null;
	  String email = null;
	  String mobile = null;
	  String userId = null;
	  int customerid = 0;
	  try {

	   try {
	   email = json.get("email").toString();
	   Register reg = registerDao.findByCUSTOMEREMAIL(email);
	   customerno = reg.getCUSTOMERNO();
	   customerid = reg.getSIGNUPS_ID();
	   }catch(Exception e) {
	    
	   }
	   try {
	    mobile = json.get("mobile").toString();
	    Register reg = registerDao.findByCUSTOMERMOBILE(mobile);
	    customerno = reg.getCUSTOMERNO();
	    customerid = reg.getSIGNUPS_ID();
	    }catch(Exception e) {
	     
	    }
	   if(email!=null || mobile!=null) {
	   
	    try {
	     List<Map<String, Object>> customerProfile = registerDao.retrieveCustomerProfile(customerno);
	     if (customerProfile.isEmpty()) {
	      resultjson.put("status", "201");
	      resultjson.put("message", "Success");
	      resultjson.put("customer", "No Content");
	     }else {
	     List<Map<String, Object>> beneficiaryDetails = new ArrayList<Map<String, Object>>();
	     beneficiaryDetails = customerDao.retrieveBeneficiaryDetails(customerno);
	     if (beneficiaryDetails.isEmpty()) {
	      resultjson.put("status", "200");
	      resultjson.put("message", "Success");
	      resultjson.put("data", "No Content");
	      resultjson.put("customerid", customerid);
	      resultjson.put("customer", customerProfile);
	      resultjson.put("customerno", customerno);
	     } else {
	      resultjson.put("status", "200");
	      resultjson.put("message", "Success");
	      resultjson.put("data", beneficiaryDetails);
	      resultjson.put("customer", customerProfile);
	      resultjson.put("customerno", customerno);
	      resultjson.put("customerid", customerid);
	     }

	    } 
	    }catch (Exception e) {
	     resultjson.put("status", "406");
	     resultjson.put("message", "Failure");
	    }
	   }else {
	    resultjson.put("status", "406");
	    resultjson.put("message", "Some input field missing");
	    return resultjson.toString();
	   }
	  } catch (Exception ex) {
	   resultjson.put("status", "406");
	   resultjson.put("message", "Some input field missing");
	   return resultjson.toString();
	  }
	  return resultjson.toString();
	 }

	@RequestMapping("/updateBeneficiary")
	@ResponseBody
	public String updateBeneficiary(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {

			String userId = json.get("user_id").toString();
			int beneficiaryno = Integer.parseInt(json.get("beneficiary_no").toString());
			Beneficiary beneficiary = new Beneficiary();
			BeneficiaryBank beneficiarybank = new BeneficiaryBank();
			BeneficiaryIds beneficiaryids = new BeneficiaryIds();

			beneficiary.setBENEFICIARYFIRSTNAME(json.get("first_name").toString().trim());
			try {
				if(json.get("image").toString().length()>0) {
					beneficiary.setIMAGE( new SerialBlob(json.get("image").toString().getBytes()));
				}else {
					beneficiary.setIMAGE(new SerialBlob(defaultimage.getBytes()));
				}
			}catch(Exception e) {
				beneficiary.setIMAGE(new SerialBlob(defaultimage.getBytes()));
			}
		//	int bankType = Integer.parseInt(json.get("bank_type").toString());
			try {
				beneficiary.setBENEFICIARYMIDDLENAME(json.get("middle_name").toString().trim());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYMIDDLENAME(null);
				logger.error("Error in middle name retrieval", e);
			}
			beneficiary.setBENEFICIARYLASTNAME(json.get("last_name").toString().trim());
			beneficiary.setBENEFICIARYGENDER(json.get("gender").toString().trim());
			try {
			beneficiary.setBENEFICIARYPHONE(json.get("phone").toString().trim());
			}catch(Exception e) {
				beneficiary.setBENEFICIARYPHONE(null);
			}
			beneficiary.setBENEFICIARYADDRESSCOUNTRYCODE(json.get("country").toString());
			try {
				beneficiary.setDATEOFBIRTH(json.get("dob").toString());
			} catch (Exception e) {
				beneficiary.setDATEOFBIRTH(null);
				logger.error("Error in middle name retrieval", e);
			}
			// beneficiary.setCREATEDON(new Date());
			// beneficiary.setBENEFICIARYSTATUS(0);
			// beneficiary.setNOTIFYBENEBYEMAIL(null);
			// beneficiary.setNOTIFYBENEBYSMS(null);
			try {
				beneficiary.setBENEFICIARYADDRESSZIP(json.get("pincode").toString());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYADDRESSZIP(null);
				logger.error("Error in middle name retrieval", e);
			}
			try {
				beneficiary.setBENEFICIARYMOBILE(json.get("mobile").toString().trim());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYMOBILE(null);
				logger.error("Error in middle name retrieval", e);
			}
			try {
				beneficiary.setBENEFICIARYPOBOX(json.get("pobox").toString());
			} catch (Exception e) {
				beneficiary.setBENEFICIARYPOBOX(null);
				logger.error("Error in middle name retrieval", e);
			}

			beneficiarybank.setBENEFICIARYACCOUNTNAME(json.get("call_name").toString());
			// beneficiarybank.setCREATEDON(new Date());
			beneficiarybank.setRECORDSTATUS(0);
			beneficiarybank.setBENEFICIARYACCOUNTCCYCODE(json.get("ccycode").toString());
			String customerno = registerDao.retrieveCustomerNumber(userId);
			beneficiary.setCUSTOMERNO(customerno);
			beneficiarybank.setCUSTOMERNO(customerno);
			beneficiarybank.setBANKTYPE(0);
			beneficiaryids.setBENEFICIARYIDTYPE(0);
			
			try {
				beneficiary.setBENEFICIARYADDRESSSTATE(json.get("state").toString());
				} catch (Exception e) {
					beneficiary.setBENEFICIARYADDRESSSTATE("");
				}
				try {
				beneficiary.setBENEFICIARYADDRESSCITY(json.get("city").toString());
				} catch (Exception e) {
					beneficiary.setBENEFICIARYADDRESSCITY("");
				}
				
			beneficiaryids.setBENEFICIARYIDTYPEDESC("CASH");
			
		//	if (bankType == 0) {
				/*beneficiarybank.setBENEFICIARYBANKNAME(null);
				beneficiarybank.setBENEFICIARYBANKCODE(null);
				beneficiarybank.setSWIFTIFSC(null);*/
			
//			} else {
			
				try {
					beneficiarybank.setBENEFICIARYBANKACCOUNTNO(json.get("account_number").toString());
				
					beneficiarybank.setBENEFICIARYBANKNAME(json.get("bank_name").toString());
					beneficiaryids.setBENEFICIARYIDTYPEDESC("Bank");
					beneficiarybank.setBENEFICIARYBANKCODE(null);
					beneficiarybank.setBANKTYPE(1);
					beneficiaryids.setBENEFICIARYIDTYPE(1);
					try {
						beneficiarybank.setBENEFICIARYBRANCHNAME(json.get("branch_name").toString());
						}catch(Exception e) {
							beneficiarybank.setBENEFICIARYBRANCHNAME(null);
						}
						
						try {
							beneficiarybank.setSWIFTIFSC(json.get("ifsc").toString());
						}catch(Exception e) {
							beneficiarybank.setSWIFTIFSC(null);
						}
				} catch (Exception e) {
					beneficiarybank.setBENEFICIARYBANKACCOUNTNO(null);
					beneficiarybank.setSWIFTIFSC(null);
					beneficiarybank.setBENEFICIARYBANKNAME(null);
					beneficiarybank.setBENEFICIARYBRANCHNAME(null);
					/*resultjson.put("status", "406");
					resultjson.put("message", "Some input field missing");
					return resultjson.toString();*/
				}
				
					
					
				
					
				
			//}

			try {

				beneficiary.setBENEFICIARYNO(beneficiaryno);
				beneficiarybank.setBENEFICIARYNO(beneficiaryno);
				beneficiaryids.setBENEFICIARYNO(beneficiaryno);
				beneficiaryids.setCUSTOMERNO(customerno);
				

				beneficiaryids.setBENEFICIARYID(null);
				beneficiaryids.setBENEFICIARYIDISSUEDATE(new Date());
				beneficiaryids.setCREATEDON(new Date());
				beneficiaryids.setBENEFICIARYIDSTATUS(1);
				beneficiaryids.setBENEFICIARYIDOTHERTYPE(null);
				beneficiaryids.setBENEFICIARYIDISSUEDBY(null);
				beneficiaryids.setBENEFICIARYIDVALIDTHRU(new Date());
				beneficiaryids.setBENEFICIARYIDISSUEDAT(null);
				beneficiary.setBENEFICIARYEMAIL(null);
				int saveben = customerDao.updateBeneficiary(beneficiary);
				
				int savebenbank = customerDao.updateBeneficiaryBank(beneficiarybank);
				
				int savebenids = customerDao.saveBeneficiaryIds(beneficiaryids);
				
				if (saveben > 0 && savebenbank > 0 && savebenids > 0) {
					resultjson.put("status", "200");
					resultjson.put("message", "Success");
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
				}

			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
			}
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping("/deleteBeneficiary")
	@ResponseBody
	public JSONObject deleteBeneficiary(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			int beneficiaryno = Integer.parseInt(json.get("beneficiary_no").toString());
			try {
				int del = customerDao.delBeneficiaryIds(beneficiaryno);
				if (del == 1) {
					resultjson.put("status", "200");
					resultjson.put("message", "Success");
				} else {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
				}
			} catch (Exception e) {
				resultjson.put("status", "406");
				resultjson.put("message", "Failure");
				logger.error("Error in deletebeneficiary", e);
			}
		} catch (Exception e) {
			resultjson.put("status", "406");
			resultjson.put("message", "Some input field missing");
			logger.error("Error in deletebeneficiary data retrieval from app", e);
		}
		return resultjson;
	}

	@Autowired
	private RegisterDao registerDao;

	@Autowired
	private CustomerDao customerDao;

}