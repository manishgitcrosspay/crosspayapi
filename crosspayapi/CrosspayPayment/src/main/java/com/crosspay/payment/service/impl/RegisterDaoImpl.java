package com.crosspay.payment.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.CustomTemplate;
import com.crosspay.payment.model.Customer;
import com.crosspay.payment.model.Register;
import com.crosspay.payment.service.RegisterDao;
import com.crosspay.payment.util.CommonUtil;
import com.crosspay.payment.util.Constants;
import com.crosspay.payment.util.CryptoUtil;

@Service
public class RegisterDaoImpl implements RegisterDao {

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


	/**
	 * This method will find an User instance in the database by its email. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */



	@Override
	public void delete(Register arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Register> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Register> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Register> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Register> S save(S arg0) {		
		return null;
	}

	@Override
	public <S extends Register> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int saveReg(Register reg) {
		
		return jdbcTemplate.update("insert into SIGNUPS values (?,?,?,?,?,?,?,?,?)",new Object[]{0,reg.getCUSTOMEREMAIL(),reg.getCUSTOMERMOBILE(),reg.getCUSTOMERNO(),reg.getPASSWORD(),reg.getRECORDSTATUS(),reg.getSIGNUPCLIENT(),reg.getSIGNUPDATE(),reg.getSIGNUPOTP()});
		//return jdbcTemplate.update("insert into user values (?,?,?,?,?,?,?,?,?)",new Object[]{0,arg0.getCountrycode(),arg0.getDateofbirth(),arg0.getEmail(),arg0.getFirstname(),arg0.getGender(),arg0.getLastname(),arg0.getMobile(),arg0.getPassword()});
	  //return 0;
	}
	
	@Override
	public int saveCustomer(Customer cus) {
		
		return jdbcTemplate.update("insert into CUSTOMER values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{0,cus.getCREATEDON(),cus.getCUSTOMERADDRESS1(),cus.getCUSTOMERADDRESS2(),cus.getCUSTOMERADDRESSCITY(),cus.getCUSTOMERADDRESSCOUNTRY(),cus.getCUSTOMERADDRESSCOUNTRYCODE(),cus.getCUSTOMERADDRESSSTATE(),cus.getCUSTOMERADDRESSZIP(),cus.getCUSTOMERCATEGORY(),cus.getCUSTOMERDATEOFBIRTH(),cus.getCUSTOMEREMAIL(),cus.getCUSTOMERFIRSTNAME(),cus.getCUSTOMERGENDER(),cus.getCUSTOMERIMAGE(),cus.getCUSTOMERLASTNAME(),cus.getCUSTOMERMIDDLENAME(),cus.getCUSTOMERMOBILE(),cus.getCUSTOMERNATIONALITYCODE(),cus.getCUSTOMERNO(),cus.getCUSTOMERPHONE(),cus.getCUSTOMERPOBOX(),cus.getNOTIFYCUSTOMERBYEMAIL(),cus.getNOTIFYCUSTOMERBYSMS(),cus.getUSERCATEGORIES_ID(),null,null,null,null});
		//return jdbcTemplate.update("insert into user values (?,?,?,?,?,?,?,?,?)",new Object[]{0,arg0.getCountrycode(),arg0.getDateofbirth(),arg0.getEmail(),arg0.getFirstname(),arg0.getGender(),arg0.getLastname(),arg0.getMobile(),arg0.getPassword()});
	  //return 0;
	}

	@Override
	public Register findByCUSTOMERMOBILE(String mobile, String password) {
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("select * from SIGNUPS where customermobile =? and password=?", new Object[] { mobile, password });
		
		Register reg = new Register();
		try {
		reg.setSIGNUPS_ID(Integer.parseInt(result.get(0).get("signups_id").toString()));
		}catch(Exception e) {
			reg.setSIGNUPS_ID(0);
		}
		return reg;
	}
	
	@Override
	public Register findByCUSTOMEREMAIL(String email, String password) {

		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from SIGNUPS where customeremail =? and password=?",
				new Object[] { email, password });

		Register ver = new Register();
		
		
		ver.setSIGNUPS_ID(Integer.parseInt(result.get(0).get("signups_id").toString()));
		ver.setSIGNUPOTP(result.get(0).get("signupotp").toString());
		return ver;

	}

	@Override
	public Register findByCUSTOMEREMAIL(String email) {
		Register ver = new Register();
		try {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from SIGNUPS where customeremail =?",
				new Object[] { email });

		
		ver.setSIGNUPS_ID(result.size());
		try {
			ver.setSIGNUPOTP(result.get(0).get("SIGNUPOTP").toString());
			ver.setCUSTOMERNO(result.get(0).get("CUSTOMERNO").toString());
			ver.setSIGNUPS_ID(Integer.parseInt(result.get(0).get("SIGNUPS_ID").toString()));
			ver.setPASSWORD(result.get(0).get("PASSWORD").toString());
			ver.setCUSTOMERMOBILE(result.get(0).get("MOBILE").toString());
		}catch(Exception e) {
			System.out.println("Error in otp retrieval"+e.toString());
		}
		}catch(Exception e) {
			System.out.println("error in retrieval of email "+e.toString());
			ver.setSIGNUPS_ID(0);
		}
		return ver;

	}
	
	
	
	@Override
	 public int retrieveUserId(String customerno) {
	  List<Map<String, Object>> result = jdbcTemplate
	    .queryForList("select * from SIGNUPS where CUSTOMERNO = ?",new Object[]{customerno});
	  int id = 0;
	  try {
	   id = Integer.parseInt(result.get(0).get("SIGNUPS_ID").toString());
	  System.out.println("customer in "+id);
	  }catch(Exception e) {
	   System.out.println("Exception in otp"+e.toString());
	  }
	  
	  
	  return id;
	  
	 }
	
	@Override
	public Register findByCUSTOMERMOBILE(String mobile) {
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("select * from SIGNUPS where customermobile =?", new Object[] { mobile });
		
		Register reg = new Register();
		reg.setSIGNUPS_ID(result.size());
		try {
			reg.setSIGNUPS_ID(Integer.parseInt(result.get(0).get("SIGNUPS_ID").toString()));
			reg.setSIGNUPOTP(result.get(0).get("SIGNUPOTP").toString());
			reg.setCUSTOMERNO(result.get(0).get("CUSTOMERNO").toString());
			reg.setPASSWORD(result.get(0).get("PASSWORD").toString());
			reg.setCUSTOMEREMAIL(result.get(0).get("CUSTOMEREMAIL").toString());
		}catch(Exception e) {
			System.out.println("Error in otp retrieval"+e.toString());
		}
		return reg;
	}
	
	@Override
	public double retrieveCustomerNo() {
		System.out.println("retrieveCustomerNo in ");
		double id = 0;
		try {
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("select * from CUSTOMER");
		System.out.println("result in ");
		
		try {
			System.out.println("customer in "+result.size());
			System.out.println("customer in SADASD"+result.get(result.size()-1).get("CUSTOMERNO"));
		String CUSTOMERNO = result.get(result.size()-1).get("CUSTOMERNO").toString();
		System.out.println("customer in "+CUSTOMERNO);
		
		String sub = CUSTOMERNO.substring(4, 16).toString();
		System.out.println("customer in substring"+sub);
		//sub = "2017000000001";
		id = Double.valueOf(sub);
		System.out.println(String.format("%12f", id ));
		
		System.out.println("customer id before"+String.valueOf(id));
		id++;
		System.out.println("customer id "+id);
		id = Double.parseDouble(CUSTOMERNO);
		}catch(Exception e) {
			System.out.println("Error in customerid retrieval"+e.toString());
		}
		}catch(Exception e) {
			System.out.println("Error in data retrieval"+e.toString());
		}
		return id;
		
	}
	
	@Override
	public String retrieveOTP(Register reg) {
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("select * from SIGNUPS where CUSTOMERMOBILE = ?",new Object[]{reg.getCUSTOMERMOBILE()});
		String OTP = null;
		try {
			OTP = result.get(0).get("SIGNUPOTP").toString();
		System.out.println("customer in "+OTP);
		}catch(Exception e) {
			System.out.println("Exception in otp"+e.toString());
		}
		
		
		return OTP;
		
	}

	@Override
	public int updateReg(Register reg) {
		
		return jdbcTemplate.update("update SIGNUPS set SIGNUPOTP = ? where CUSTOMERMOBILE = ?",new Object[]{reg.getSIGNUPOTP(), reg.getCUSTOMERMOBILE()});
	}
	
	@Override
	public int updateRegdetails(Register reg) {
		
		return jdbcTemplate.update("update SIGNUPS set CUSTOMEREMAIL = ?,CUSTOMERMOBILE = ? WHERE CUSTOMERNO =?",new Object[]{reg.getCUSTOMEREMAIL(), reg.getCUSTOMERMOBILE(),reg.getCUSTOMERNO()});
	}
	
	@Override
	public int updateCustomerdetails(Customer customer) {
		
		int status = 0;
		try {
			status = jdbcTemplate.update("update CUSTOMER set CUSTOMERADDRESS1 = ?,CUSTOMERADDRESS2 = ?,CUSTOMERADDRESSCITY=?,CUSTOMERADDRESSCOUNTRY=?,CUSTOMERADDRESSCOUNTRYCODE=?,CUSTOMERADDRESSSTATE=?,CUSTOMERADDRESSZIP=?,CUSTOMERDATEOFBIRTH =?,CUSTOMEREMAIL =?,CUSTOMERFIRSTNAME=?,CUSTOMERGENDER=?,CUSTOMERLASTNAME=?,CUSTOMERMOBILE=?,CUSTOMERNATIONALITYCODE=?,CUSTOMERPHONE=?,CUSTOMERPOBOX=?,ID_PROOF_TYPE=?,ISSUED_BY=?,ISSUED_DATE=?,EXPIRY_DATE=?,CUSTOMERMIDDLENAME=? WHERE CUSTOMERNO =?",new Object[]{customer.getCUSTOMERADDRESS1(),customer.getCUSTOMERADDRESS2(),customer.getCUSTOMERADDRESSCITY(),customer.getCUSTOMERADDRESSCOUNTRY(),customer.getCUSTOMERADDRESSCOUNTRYCODE(),customer.getCUSTOMERADDRESSSTATE(),customer.getCUSTOMERADDRESSZIP(),customer.getCUSTOMERDATEOFBIRTH(),customer.getCUSTOMEREMAIL(),customer.getCUSTOMERFIRSTNAME(),customer.getCUSTOMERGENDER(),customer.getCUSTOMERLASTNAME(),customer.getCUSTOMERMOBILE(),customer.getCUSTOMERNATIONALITYCODE(),customer.getCUSTOMERPHONE(),customer.getCUSTOMERPOBOX(),customer.getID_PROOF_TYPE(),customer.getISSUED_BY(),customer.getISSUED_DATE(),customer.getEXPIRY_DATE(),customer.getCUSTOMERMIDDLENAME(),customer.getCUSTOMERNO()});
		}catch(Exception e) {
			System.out.println("status customer"+e.toString());
		}
		return status;
	}
	
	@Override
	public int updateOtp(Register reg) {
		return jdbcTemplate.update("update SIGNUPS set SIGNUPOTP = ? where CUSTOMERMOBILE = ?",new Object[]{reg.getSIGNUPOTP(),reg.getCUSTOMERMOBILE()});
	}
	
	@Override
	public int updateOtpEmail(Register reg) {
		return jdbcTemplate.update("update SIGNUPS set SIGNUPOTP = ? where CUSTOMEREMAIL = ?",new Object[]{reg.getSIGNUPOTP(),reg.getCUSTOMEREMAIL()});
	}
	
	@Override
	public int updateMpin(Register reg){
		return jdbcTemplate.update("update SIGNUPS set PASSWORD = ? where CUSTOMERMOBILE = ?",new Object[]{reg.getPASSWORD(),reg.getCUSTOMERMOBILE()});
	}
	
	 @Override
	 public String retrieveCustomerNumber(String userid) {
	  List<Map<String, Object>> result = jdbcTemplate
	    .queryForList("select * from SIGNUPS where SIGNUPS_ID = ?",new Object[] {userid});
	  String id = null;
	  try {
	  String CUSTOMERNO = result.get(0).get("CUSTOMERNO").toString();
	  System.out.println("customer in "+CUSTOMERNO);
	  
	  id = CUSTOMERNO;
	  }catch(Exception e) {
	   System.out.println("Error in customerid retrieval"+e.toString());
	  }
	  return id;
	  
	 }
	 
	 @Override
		public List<Map<String, Object>> retrieveCustomerProfile(String customerno) {
		 System.out.println("customer no "+customerno);
			List<Map<String, Object>> result = jdbcTemplate.queryForList(
					"select * from CUSTOMER where CUSTOMERNO = ?", new Object[] { customerno });
			
			List<Map<String, Object>> resultpr = jdbcTemplate.queryForList("select * from SIGNUPS where customerno =?",
					new Object[] { customerno });
			
			List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
			try {
				 System.out.println("result no "+result.size());
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> obj = new HashMap<String, Object>();
					obj.put("first_name", result.get(i).get("CUSTOMERFIRSTNAME").toString());
					System.out.println("obj1" + obj);
					obj.put("middle_name", result.get(i).get("CUSTOMERMIDDLENAME"));
					System.out.println("obj2" + obj);
					obj.put("last_name", result.get(i).get("CUSTOMERLASTNAME"));
					System.out.println("obj3" + obj);
					obj.put("email", result.get(i).get("CUSTOMEREMAIL").toString());
					System.out.println("obj4" + obj);
					obj.put("phone_num", result.get(i).get("CUSTOMERPHONE"));
					System.out.println("obj5" + obj);
					obj.put("gender", result.get(i).get("CUSTOMERGENDER").toString());
					System.out.println("obj6" + obj);
					obj.put("dob", result.get(i).get("CUSTOMERDATEOFBIRTH").toString());
					System.out.println("obj7" + obj);
					obj.put("country", result.get(i).get("CUSTOMERADDRESSCOUNTRY"));
					System.out.println("obj8" + obj);
					
					
					try {
					obj.put("mobile", resultpr.get(i).get("CUSTOMERMOBILE"));
					}catch(Exception e) {
						obj.put("mobile", result.get(i).get("CUSTOMERMOBILE"));
					}
					
				//	obj.put("id_proof", result.get(i).get("ID_PROOF_TYPE"));
				//	obj.put("issued_by", result.get(i).get("ISSUED_BY"));
					obj.put("city", result.get(i).get("CUSTOMERADDRESSCITY"));
					obj.put("pincode", result.get(i).get("CUSTOMERADDRESSZIP"));
					obj.put("nationality", result.get(i).get("CUSTOMERNATIONALITYCODE"));
					obj.put("address1", result.get(i).get("CUSTOMERADDRESS1"));
					obj.put("address2", result.get(i).get("CUSTOMERADDRESS2"));
					obj.put("state", result.get(i).get("CUSTOMERADDRESSSTATE"));
					
					try {
						obj.put("name",result.get(i).get("CUSTOMERFIRSTNAME").toString()+" "+result.get(i).get("CUSTOMERMIDDLENAME").toString()+" "+result.get(i).get("CUSTOMERLASTNAME").toString());
					}catch(Exception e) {
						obj.put("name", result.get(i).get("CUSTOMERFIRSTNAME").toString()+" "+result.get(i).get("CUSTOMERLASTNAME").toString());
					}
					System.out.println("obj" + obj);
					output.add(obj);
					
				}
				System.out.println("output" + output);
			} catch (Exception e) {
				System.out.println("Error in CUSTOMER retrieval" + e.toString());
			}
			return output;

		}
	 
	 public  void changepswd(String email) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from SIGNUPS ");
			int count = 0;
			//result.size()
			
			String FirstName = "";
			String LastName = "";
		//	String email= "";
			
			System.out.println(result);
			
			for (Map<String, Object> map : result) {
			    for (Map.Entry<String, Object> entry : map.entrySet()) {
			    	
			    	  if(entry.getKey().equals("customeremail")) {
			    		//  email = (String) entry.getValue();
			    		  email =  email;
			    		  System.out.println(email);
			    	  }
			    	  
			    	  
			    	  
			    	 
			    	  
			    	  
			    }
			    
			    List<Map<String, Object>> result1 = jdbcTemplate.queryForList("select * from CUSTOMER where CUSTOMEREMAIL = ? ",new Object[]{email});
		    	  
		    	  for (Map<String, Object> maps : result1) {
					    for (Map.Entry<String, Object> entrys : maps.entrySet()) {
					    	
					    
		    	  if(entrys.getKey().equals("customerfirstname")) {
		    		  FirstName = (String) entrys.getValue();
		    		  System.out.println(FirstName);
		    	  }
		    	  
		    	  if(entrys.getKey().equals("customerlastname")) {
		    		  LastName = (String) entrys.getValue();
		    		  System.out.println(LastName);
		    	  }
		    	  }
		    	  }
		    	  
		    	  
		    	//	List<Map<String, Object>> result1 = jdbcTemplate.queryForList("select * from CUSTOMER where CUSTOMEREMAIL = ? ",new Object[]{email});
					String name = FirstName+" "+  LastName;
					
					String password = new CommonUtil().generateFourDigitNumericString();
					String encryptedpswd = new CryptoUtil().encrypt(new Constants().getSaltkey(),password);
					
				//	System.out.println("name pswd "+i+"=="+email);
					if(email.equalsIgnoreCase(email)) {
						System.out.println("hellooo "+email);
					
					int j = jdbcTemplate.update("update SIGNUPS set PASSWORD = ? where CUSTOMEREMAIL = ?",new Object[]{encryptedpswd,email});
					int k = jdbcTemplate.update("update LOGINCREDENTIALS set PASSWORD = ? where CUSTOMEREMAIL = ?",new Object[]{encryptedpswd,email});
					
					
					CustomTemplate custom = new CustomTemplate();
					
					custom.setEmail(email);
					custom.setMpin(password);
					custom.setName(name);
					custom.setSubject("Crosspay New MPIN Generation");
					custom.setTemplatename("welcomempin.html");
					
					if(j == 1 && k == 1) {
						
						new EmailServiceImpl().sendEmailTemplate(custom);
						
						
					}
					}
			        	
							
			    	         count++;
						/*	if(count == 50) {
								
								break;
							}*/
			        	 
			        break;	 
			        
			    }
			
			
	
		}
		

		

}