package com.crosspay.payment.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crosspay.payment.model.CurrencyRates;
import com.crosspay.payment.model.CurrencyRatesHistory;
import com.crosspay.payment.service.AmountTransferDao;
import com.crosspay.payment.service.VersionDao;

@RestController
@RequestMapping("/upload")
public class RestApiController {

	Logger logger = LoggerFactory.getLogger(RestApiController.class);

	private static String UPLOADED_FOLDER = "C://Users//srini//Music//";

	@PostMapping("/multi/model")
	public ResponseEntity<?> multiUploadFileModel(@RequestParam("file") MultipartFile uploadfiles) {

		
		logger.debug("Multiple file upload! With UploadModel");

		try {

			saveUploadedFiles(uploadfiles);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

	// save file
	private void saveUploadedFiles(MultipartFile file) throws IOException {

		if (file.isEmpty()) {
			logger.debug("file empty");
		}

		byte[] bytes = file.getBytes();
		logger.debug("file not empty" + bytes);
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		logger.debug("file path" + path);
		Files.write(path, bytes);
		try {
			Blob blob = new SerialBlob(bytes);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/getCountry")
	@ResponseBody
	public String retireveCountry() throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			resultjson.put("status", "200");
			resultjson.put("message", "Success");
			resultjson.put("country", versionDao.retrieveCountries());
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Failure");
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@RequestMapping("/getExchangeRate")
	@ResponseBody
	public String rateExchange(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			CurrencyRates currencyrates = new CurrencyRates();
			CurrencyRates currencyrateoutput = new CurrencyRates();
			currencyrates.setCCYCODEFROM(json.get("ccyfrom").toString());
			currencyrates.setCCYCODETO(json.get("ccyto").toString());
			float fromval = Float.parseFloat(json.get("fromvalue").toString());
			String transfertype = "AC";

			try {
				currencyrateoutput = versionDao.retieveData(currencyrates);
				resultjson.put("ccyfrom", currencyrates.getCCYCODEFROM().toString());
				resultjson.put("ccyto", currencyrates.getCCYCODETO().toString());
				Double out = (currencyrateoutput.getXCHGRATE()) * (fromval);
				float finalcommission = 0;
				float cashfinalcommission = 0;
				try {
				//finalcommission = amountTransferDao.retrieveCommission(fromval,transfertype);
				finalcommission = amountTransferDao.retrieveACCommission(fromval,currencyrates.getCCYCODEFROM(),currencyrates.getCCYCODETO());
				
				}catch(Exception e) {
					logger.error("Error in commission retrieval",e);
				}
				 
				resultjson.put("status", "200");
				resultjson.put("message", "Success");
				resultjson.put("INR", currencyrateoutput.getXCHGRATE());
				  int r = (int) Math.round(out*100);
				  out = r / 100.0;
				 
				resultjson.put("TotalRate",out);//Math.floor(out)
				resultjson.put("commission", finalcommission);
				try {
				//	currencyrateoutput = versionDao.retieveCashData(currencyrates);
				//cashfinalcommission = amountTransferDao.retrieveCommission(fromval,"CP");
					cashfinalcommission = amountTransferDao.retrieveCPCommission(fromval,currencyrates.getCCYCODEFROM(),currencyrates.getCCYCODETO());
				resultjson.put("cashcommission", cashfinalcommission);
				/* out = (currencyrateoutput.getXCHGRATE()) * (fromval);
				 resultjson.put("CASHINR", currencyrateoutput.getXCHGRATE());
				resultjson.put("TotalCashRate", out)*/;
				}catch(Exception e) {
					
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
	
	@RequestMapping("/getExchangeRateReverse")
	@ResponseBody
	public String rateExchangereverse(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			CurrencyRates currencyrates = new CurrencyRates();
			CurrencyRates currencyrateoutput = new CurrencyRates();
			currencyrates.setCCYCODEFROM(json.get("ccyfrom").toString());
			currencyrates.setCCYCODETO(json.get("ccyto").toString());
			float fromval = Float.parseFloat(json.get("tovalue").toString());
			String transfertype = "AC";

			try {
				currencyrateoutput = versionDao.retieveData(currencyrates);
				resultjson.put("ccyfrom", currencyrates.getCCYCODEFROM().toString());
				resultjson.put("ccyto", currencyrates.getCCYCODETO().toString());
				Double out =  (fromval/(currencyrateoutput.getXCHGRATE()) );
				float finalcommission = 0;
				float cashfinalcommission = 0;
				try {
				//finalcommission = amountTransferDao.retrieveCommission(fromval,transfertype);
				finalcommission = amountTransferDao.retrieveACCommission(Float.parseFloat(String.valueOf(out)),currencyrates.getCCYCODEFROM(),currencyrates.getCCYCODETO());
				
				}catch(Exception e) {
					logger.error("Error in commission retrieval",e);
				}
				
				resultjson.put("status", "200");
				resultjson.put("message", "Success");
				resultjson.put("INR", currencyrateoutput.getXCHGRATE());
				  int r = (int) Math.round(out*100);
				  out = r / 100.0;
				
				resultjson.put("TotalRate",out);//Math.floor(out)
				resultjson.put("commission", finalcommission);
				try {
				//	currencyrateoutput = versionDao.retieveCashData(currencyrates);
				//cashfinalcommission = amountTransferDao.retrieveCommission(fromval,"CP");
					cashfinalcommission = amountTransferDao.retrieveCPCommission(Float.parseFloat(String.valueOf(out)),currencyrates.getCCYCODEFROM(),currencyrates.getCCYCODETO());
				resultjson.put("cashcommission", cashfinalcommission);
				/* out = (currencyrateoutput.getXCHGRATE()) * (fromval);
				 resultjson.put("CASHINR", currencyrateoutput.getXCHGRATE());
				resultjson.put("TotalCashRate", out)*/;
				}catch(Exception e) {
					
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
	
	@RequestMapping("/getExchangeRateCurrency")
	@ResponseBody
	public String rateExchangeCurrency() throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> output1 = new ArrayList<Map<String, Object>>();
			output = versionDao.retrieveCountriesCurrencies("ccycodefrom");
			if(output.size()>0) {
				resultjson.put("fromCurrency", output);
			}else {
				resultjson.put("fromCurrency", "No Content");
			}
			output1 = versionDao.retrieveCountriesCurrencies("ccycodeto");
			if(output1.size()>0) {
				resultjson.put("toCurrency", output1);
			}else {
				resultjson.put("toCurrency", "No Content");
			}
			resultjson.put("status", "200");
			resultjson.put("message", "success");
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Failure");
			return resultjson.toString();
		}
		return resultjson.toString();
	}
	
	@RequestMapping("/getExchangeRateCurrencyValue")
	 @ResponseBody
	 public String rateExchangeCurrencyval(@RequestBody Map<String, Object> json) throws JSONException {
	  JSONObject resultjson = new JSONObject();
	  String ccyfrom = null;
	  String ccyto = null;
	  String instrumenttype = null;
	  String val = null;
	  try {
	   ccyfrom = json.get("ccyfrom").toString();
	   ccyto = json.get("ccyto").toString();
	   instrumenttype = json.get("instrument_type").toString();
	   CurrencyRates curr = new CurrencyRates();
	   curr.setCCYCODEFROM(ccyfrom);
	   curr.setCCYCODETO(ccyto);
	   curr.setINSTRUMENT_TYPE(instrumenttype);
	  try {
	   
	   val = versionDao.retrieveCurrencyval(curr);
	   resultjson.put("value", val);
	   resultjson.put("status", "200");
	   resultjson.put("message", "success");
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
	
	@RequestMapping("/updateExchangeRate")
	@ResponseBody
	public String updaterateExchange(@RequestBody Map<String, Object> json) throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			CurrencyRates currencyrates = new CurrencyRates();
			CurrencyRatesHistory currencyratehistory = new CurrencyRatesHistory();
			currencyrates.setCCYCODEFROM(json.get("ccyfrom").toString());
			currencyrates.setCCYCODETO(json.get("ccyto").toString());
			currencyrates.setRATESETBYUSERID(json.get("user_id").toString());
			float rate = Float.parseFloat(json.get("rate").toString());
		
			String transfertype = "AC";
			transfertype = json.get("instrument_type").toString();
			currencyrates.setINSTRUMENT_TYPE(transfertype);
			currencyrates.setXCHGRATE(rate);
			currencyratehistory.setRATESETBYUSERID(json.get("user_id").toString());
			currencyratehistory.setINSTRUMENT_TYPE(transfertype);
			currencyratehistory.setXCHGRATE(rate);
			currencyratehistory.setXCHGRATEHIGH(rate);
			currencyratehistory.setXCHGRATELOW(rate);
			currencyratehistory.setASONDATE(new Date());
			currencyratehistory.setRECORDDATE(new Date());
			currencyratehistory.setCCYCODEFROM(json.get("ccyfrom").toString());
			currencyratehistory.setCCYCODETO(json.get("ccyto").toString());
			try {
				int update = versionDao.updateCurrency(currencyrates, currencyratehistory);
				if(update > 0) {
					resultjson.put("status", "200");
					resultjson.put("message", "Success");
					return resultjson.toString();
				}else {
					resultjson.put("status", "406");
					resultjson.put("message", "Failure");
					return resultjson.toString();
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
		
	}
	
	@RequestMapping("/getBank")
	@ResponseBody
	public String retireveBank() throws JSONException {
		JSONObject resultjson = new JSONObject();
		try {
			resultjson.put("status", "200");
			resultjson.put("message", "Success");
			resultjson.put("bank", versionDao.retrieveBanks());
		} catch (Exception ex) {
			resultjson.put("status", "406");
			resultjson.put("message", "Failure"+ex.toString());
			return resultjson.toString();
		}
		return resultjson.toString();
	}

	@Autowired
	private VersionDao versionDao;
	
	@Autowired
	private AmountTransferDao  amountTransferDao;

}