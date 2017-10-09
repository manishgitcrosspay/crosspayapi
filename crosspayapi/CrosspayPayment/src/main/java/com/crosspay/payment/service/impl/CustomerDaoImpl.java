package com.crosspay.payment.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.Beneficiary;
import com.crosspay.payment.model.BeneficiaryBank;
import com.crosspay.payment.model.BeneficiaryIds;
import com.crosspay.payment.model.Customer;
import com.crosspay.payment.service.CustomerDao;
import com.mysql.jdbc.Blob;

@Service
public class CustomerDaoImpl implements CustomerDao {

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
	public void delete(Customer arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Customer> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Customer> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveBeneficiary(Beneficiary reg) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			i = jdbcTemplate.update("insert into BENEFICIARY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, null, null, reg.getBENEFICIARYADDRESSCITY(),
							reg.getBENEFICIARYADDRESSCOUNTRYCODE(), reg.getBENEFICIARYADDRESSSTATE(),
							reg.getBENEFICIARYADDRESSZIP(), reg.getBENEFICIARYEMAIL(), null,
							reg.getBENEFICIARYFIRSTNAME(), reg.getBENEFICIARYGENDER(), reg.getBENEFICIARYLASTNAME(),
							reg.getBENEFICIARYMIDDLENAME(), reg.getBENEFICIARYMOBILE(), reg.getBENEFICIARYNO(),
							reg.getBENEFICIARYPHONE(), reg.getBENEFICIARYPOBOX(), reg.getBENEFICIARYSTATUS(),
							reg.getCREATEDON(), reg.getCUSTOMERNO(), reg.getDATEOFBIRTH(), reg.getNOTIFYBENEBYEMAIL(),
							reg.getNOTIFYBENEBYSMS(),reg.getIMAGE() });
		} catch (Exception e) {
			System.out.println("error in inserting data beneficiary " + e.toString());
		}
		return i;
	}

	@Override
	public int saveBeneficiaryBank(BeneficiaryBank reg) {
		int i = 0;
		try {
			i = jdbcTemplate.update("insert into BENEFICIARYBANK VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, reg.getBENEFICIARYACCOUNTCCYCODE(), reg.getBENEFICIARYACCOUNTNAME(),
							reg.getBENEFICIARYBANKACCOUNTNO(), reg.getBENEFICIARYBANKCODE(),
							reg.getBENEFICIARYBANKNAME(), reg.getBENEFICIARYNO(), reg.getCREATEDON(),
							reg.getCUSTOMERNO(), reg.getRECORDSTATUS(), reg.getSWIFTIFSC(), reg.getBANKTYPE(),reg.getBENEFICIARYBRANCHNAME() });
		} catch (Exception e) {
			System.out.println("error in inserting data BeneficiaryBank " + e.toString());
		}
		return i;
	}

	@Override
	public int saveBeneficiaryIds(BeneficiaryIds ids) {
		int status = 0;
		try {
			status = jdbcTemplate.update("insert into BENEFICIARYIDS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { 0, ids.getBENEFICIARYID(), ids.getBENEFICIARYIDISSUEDAT(),
							ids.getBENEFICIARYIDISSUEDATE(), ids.getBENEFICIARYIDISSUEDBY(),
							ids.getBENEFICIARYIDOTHERTYPE(), ids.getBENEFICIARYIDSTATUS(), ids.getBENEFICIARYIDTYPE(),
							ids.getBENEFICIARYIDTYPEDESC(), ids.getBENEFICIARYIDVALIDTHRU(), ids.getBENEFICIARYNO(),
							ids.getCREATEDON(), ids.getCUSTOMERNO() });
		} catch (Exception e) {
			System.out.println("error in adding benids" + e.toString());
		}
		return status;
	}

	@Override
	public int updateBeneficiary(Beneficiary reg) {
		System.out.println("sad" + reg.getBENEFICIARYFIRSTNAME() + reg.getBENEFICIARYMIDDLENAME()
				+ reg.getBENEFICIARYLASTNAME() + reg.getBENEFICIARYEMAIL() + reg.getBENEFICIARYPHONE()
				+ reg.getBENEFICIARYGENDER() + reg.getBENEFICIARYADDRESSCOUNTRYCODE() + reg.getDATEOFBIRTH()
				+ reg.getBENEFICIARYADDRESSZIP() + reg.getBENEFICIARYPOBOX() + reg.getBENEFICIARYNO());
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"update BENEFICIARY set BENEFICIARYFIRSTNAME = ?, BENEFICIARYMIDDLENAME = ?, BENEFICIARYLASTNAME = ?,"
							+ " BENEFICIARYEMAIL = ?, BENEFICIARYPHONE =?,BENEFICIARYGENDER=?,BENEFICIARYADDRESSCOUNTRYCODE=?,DATEOFBIRTH=?,BENEFICIARYADDRESSZIP=?,"
							+ "BENEFICIARYPOBOX= ?,BENEFICIARYADDRESSSTATE =?,BENEFICIARYADDRESSCITY =?,IMAGE = ?,BENEFICIARYMOBILE = ? where BENEFICIARYNO =?",
					new Object[] { reg.getBENEFICIARYFIRSTNAME(), reg.getBENEFICIARYMIDDLENAME(),
							reg.getBENEFICIARYLASTNAME(), reg.getBENEFICIARYEMAIL(), reg.getBENEFICIARYPHONE(),
							reg.getBENEFICIARYGENDER(), reg.getBENEFICIARYADDRESSCOUNTRYCODE(), reg.getDATEOFBIRTH(),
							reg.getBENEFICIARYADDRESSZIP(), reg.getBENEFICIARYPOBOX(), reg.getBENEFICIARYADDRESSSTATE(),
							reg.getBENEFICIARYADDRESSCITY(),reg.getIMAGE(),reg.getBENEFICIARYMOBILE(), reg.getBENEFICIARYNO() });
		} catch (Exception e) {
			System.out.println("error in updation" + e.toString());
		}
		System.out.println("ouput" + i);
		// TODO Auto-generated method stub
		return i;
	}

	@Override
	public int updateBeneficiaryBank(BeneficiaryBank reg) {
		int i = 0;
		try {
			i = jdbcTemplate.update(
					"update BENEFICIARYBANK set BENEFICIARYACCOUNTNAME=?,BENEFICIARYBANKCODE=?,BENEFICIARYBANKNAME =?,BENEFICIARYBANKACCOUNTNO =?,"
							+ "SWIFTIFSC =?,BENEFICIARYACCOUNTCCYCODE=?,BANKTYPE=?,BENEFICIARYBRANCHNAME =? where BENEFICIARYNO =?",
					new Object[] { reg.getBENEFICIARYACCOUNTNAME(), reg.getBENEFICIARYBANKCODE(),
							reg.getBENEFICIARYBANKNAME(), reg.getBENEFICIARYBANKACCOUNTNO(), reg.getSWIFTIFSC(),
							reg.getBENEFICIARYACCOUNTCCYCODE(), reg.getBANKTYPE(),reg.getBENEFICIARYBRANCHNAME(), reg.getBENEFICIARYNO() });
		} catch (Exception e) {
			System.out.println("error in updation" + e.toString());
		}

		return i;
	}

	@Override
	public int retrieveBeneficiaryNo() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from BENEFICIARY");
		int id = 0;
		try {
			String BENEFICIARYNO = result.get(result.size() - 1).get("BENEFICIARYNO").toString();

			id = Integer.parseInt(BENEFICIARYNO);
		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
		}
		return id;

	}

	@Override
	public int retrieveBeneficiaryIdNo() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from BENEFICIARYIDS");
		int id = 0;
		try {
			String BENEFICIARYNO = result.get(result.size() - 1).get("BENEFICIARYNO").toString();
			id = Integer.parseInt(BENEFICIARYNO);
		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
		}
		return id;

	}

	@Override
	public int retrieveBeneficiaryAccountNo(String accntnum, String userId) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"select * from BENEFICIARYBANK where BENEFICIARYBANKACCOUNTNO = ?", new Object[] { accntnum });
		int id = 0;
		try {
			String BENEFICIARYNO = result.get(result.size() - 1).get("BENEFICIARYBANKACCOUNTNO").toString();
			System.out.println("BENEFICIARYNO in " + BENEFICIARYNO);

			id++;
		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
		}
		return id;

	}

	@Override
	public List<Map<String, Object>> retrieveBeneficiaryDetails(String customerno) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from BENEFICIARY where CUSTOMERNO =?",
				new Object[] { customerno });

		List<Map<String, Object>> benresult = jdbcTemplate
				.queryForList("select * from BENEFICIARYBANK where CUSTOMERNO =?", new Object[] { customerno });

		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		
		final String retrieveBlobAsString = null;
		String getQuery = "select * from BENEFICIARY where CUSTOMERNO = '" + customerno + "'";
		LobHandler lobHandler = new DefaultLobHandler();
		 System.out.println( "dfghjkl before");
		 try {
		 jdbcTemplate.query(getQuery, new RowMapper() {
		            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		                // TODO Auto-generated method stub
		            	Map<String, Object> obj = new HashMap<String, Object>();
		             
		            	   System.out.println( "1111111"+rowNum);
		            	   try {
		                byte[] requestData = lobHandler.getBlobAsBytes(rs,"IMAGE");
		              
		                System.out.println( "dfghjkl");

		                System.out.println("zghdfghdfgdfgj"+ new String(requestData));
		                obj.put("image", new String(requestData));
		            	   }catch(Exception e) {
		            		   System.out.println( "null dfghjkl");
		            		   obj.put("image", null);
		            	   }
		                
		                String d = lobHandler.getClobAsString(rs, "BENEFICIARYFIRSTNAME");
		                obj.put("first_name", lobHandler.getClobAsString(rs, "BENEFICIARYFIRSTNAME"));
						try {
							obj.put("middle_name", lobHandler.getClobAsString(rs, "BENEFICIARYMIDDLENAME"));
						} catch (Exception e) {
							obj.put("middle_name", "");
						}
						obj.put("last_name", lobHandler.getClobAsString(rs, "BENEFICIARYLASTNAME"));
						// obj.put("email", result.get(i).get("BENEFICIARYEMAIL").toString());
						try {
							obj.put("phone_num", lobHandler.getClobAsString(rs, "BENEFICIARYPHONE"));
						} catch (Exception e) {
							obj.put("phone_num", "");
						}

						try {
							obj.put("mobile", lobHandler.getClobAsString(rs, "BENEFICIARYMOBILE"));
						} catch (Exception e) {
							obj.put("mobile", "");
						}
						
						try {
							obj.put("ccycode", benresult.get(rowNum).get("BENEFICIARYACCOUNTCCYCODE").toString());
						} catch (Exception e) {
							obj.put("ccycode", "");
						}

						
						try {
							obj.put("call_name", obj.get("first_name")+" "+obj.get("middle_name")+" "+obj.get("last_name"));
						} catch (Exception e) {
							obj.put("call_name", "");
						}

						obj.put("gender", lobHandler.getClobAsString(rs, "BENEFICIARYGENDER"));
						obj.put("country", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSCOUNTRYCODE"));
						obj.put("beneficiary_no", lobHandler.getClobAsString(rs, "BENEFICIARYNO"));
						try {
							obj.put("pobox", lobHandler.getClobAsString(rs, "BENEFICIARYPOBOX"));
						} catch (Exception e) {

						}
						try {
							obj.put("pincode", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSZIP"));
						} catch (Exception e) {

						}
						try {
							obj.put("city", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSCITY"));
							obj.put("state", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSSTATE"));
						} catch (Exception e) {
							obj.put("city", "");
							obj.put("state", "");
						}
						try {
							obj.put("bank_name", benresult.get(rowNum).get("BENEFICIARYBANKNAME"));
							obj.put("account_num", benresult.get(rowNum).get("BENEFICIARYBANKACCOUNTNO").toString());
							obj.put("ifsc", benresult.get(rowNum).get("SWIFTIFSC"));
							obj.put("branch_name", benresult.get(rowNum).get("BENEFICIARYBRANCHNAME"));
						}catch(Exception e) {
							obj.put("bank_name", "");
							obj.put("account_num", "");
							obj.put("ifsc", "");
							obj.put("branch_name", "");
						}
						
						
		                
		                output.add(obj);
		                return new ArrayList();
		            }});
		 }catch(Exception e) {
			  System.out.println( "22222 "+e.toString());
		 }

		/*try {
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("first_name", result.get(i).get("BENEFICIARYFIRSTNAME").toString());
				try {
					obj.put("middle_name", result.get(i).get("BENEFICIARYMIDDLENAME").toString());
				} catch (Exception e) {
					obj.put("middle_name", "");
				}
				obj.put("last_name", result.get(i).get("BENEFICIARYLASTNAME").toString());
				// obj.put("email", result.get(i).get("BENEFICIARYEMAIL").toString());
				try {
					obj.put("phone_num", result.get(i).get("BENEFICIARYPHONE").toString());
				} catch (Exception e) {
					obj.put("phone_num", "");
				}

				try {
					obj.put("mobile", result.get(i).get("BENEFICIARYMOBILE").toString());
				} catch (Exception e) {
					obj.put("mobile", "");
				}
				
				try {
					obj.put("ccycode", benresult.get(i).get("BENEFICIARYACCOUNTCCYCODE").toString());
				} catch (Exception e) {
					obj.put("ccycode", "");
				}

				
				try {
					obj.put("call_name", obj.get("first_name")+" "+obj.get("middle_name")+" "+obj.get("last_name"));
				} catch (Exception e) {
					obj.put("call_name", "");
				}

				obj.put("gender", result.get(i).get("BENEFICIARYGENDER").toString());
				obj.put("country", result.get(i).get("BENEFICIARYADDRESSCOUNTRYCODE").toString());
				obj.put("beneficiary_no", result.get(i).get("BENEFICIARYNO").toString());
				try {
					obj.put("pobox", result.get(i).get("BENEFICIARYPOBOX").toString());
				} catch (Exception e) {

				}
				try {
					obj.put("pincode", result.get(i).get("BENEFICIARYADDRESSZIP").toString());
				} catch (Exception e) {

				}
				try {
					obj.put("city", result.get(i).get("BENEFICIARYADDRESSCITY").toString());
					obj.put("state", result.get(i).get("BENEFICIARYADDRESSSTATE").toString());
				} catch (Exception e) {
					obj.put("city", "");
					obj.put("state", "");
				}
				try {
					obj.put("bank_name", benresult.get(i).get("BENEFICIARYBANKNAME"));
					obj.put("account_num", benresult.get(i).get("BENEFICIARYBANKACCOUNTNO").toString());
					obj.put("ifsc", benresult.get(i).get("SWIFTIFSC"));
				}catch(Exception e) {
					obj.put("bank_name", "");
					obj.put("account_num", "");
					obj.put("ifsc", "");
				}
				
			
				
				System.out.println("beneficiary " + obj);
				output.add(obj);
			}

		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYDETAILS retrieval" + e.toString());
		}*/
		return output;

	}

	@Override
	public List<Map<String, Object>> retrieveBeneficiaryDatas(String beneficiaryno) {
		System.out.println("benno " + beneficiaryno);
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from BENEFICIARY where BENEFICIARYNO =?",
				new Object[] { beneficiaryno });
		System.out.println("result " + result.size());
		List<Map<String, Object>> benresult = jdbcTemplate
				.queryForList("select * from BENEFICIARYBANK where BENEFICIARYNO =?", new Object[] { beneficiaryno });
		System.out.println("benresult " + benresult.size());
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();

		final String retrieveBlobAsString = null;
		String getQuery = "select * from BENEFICIARY where BENEFICIARYNO = '" + beneficiaryno + "'";
		LobHandler lobHandler = new DefaultLobHandler();
		 System.out.println( "dfghjkl before");
		 try {
		 jdbcTemplate.query(getQuery, new RowMapper() {
		            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		                // TODO Auto-generated method stub
		            	Map<String, Object> obj = new HashMap<String, Object>();
		             
		            	   System.out.println( "1111111"+rowNum);
		            	   try {
		                byte[] requestData = lobHandler.getBlobAsBytes(rs,"IMAGE");
		              
		                System.out.println( "dfghjkl");

		                System.out.println("zghdfghdfgdfgj"+ new String(requestData));
		                obj.put("image", new String(requestData));
		            	   }catch(Exception e) {
		            		   System.out.println( "null dfghjkl");
		            		   obj.put("image", null);
		            	   }
		                
		                String d = lobHandler.getClobAsString(rs, "BENEFICIARYFIRSTNAME");
		                obj.put("first_name", lobHandler.getClobAsString(rs, "BENEFICIARYFIRSTNAME"));
						try {
							obj.put("middle_name", lobHandler.getClobAsString(rs, "BENEFICIARYMIDDLENAME"));
						} catch (Exception e) {
							obj.put("middle_name", "");
						}
						obj.put("last_name", lobHandler.getClobAsString(rs, "BENEFICIARYLASTNAME"));
						// obj.put("email", result.get(i).get("BENEFICIARYEMAIL").toString());
						try {
							obj.put("phone_num", lobHandler.getClobAsString(rs, "BENEFICIARYPHONE"));
						} catch (Exception e) {
							obj.put("phone_num", "");
						}

						try {
							obj.put("mobile", lobHandler.getClobAsString(rs, "BENEFICIARYMOBILE"));
						} catch (Exception e) {
							obj.put("mobile", "");
						}
						
						try {
							obj.put("ccycode", benresult.get(rowNum).get("BENEFICIARYACCOUNTCCYCODE").toString());
						} catch (Exception e) {
							obj.put("ccycode", "");
						}

						
						try {
							obj.put("call_name", obj.get("first_name")+" "+obj.get("middle_name")+" "+obj.get("last_name"));
						} catch (Exception e) {
							obj.put("call_name", "");
						}

						obj.put("gender", lobHandler.getClobAsString(rs, "BENEFICIARYGENDER"));
						obj.put("country", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSCOUNTRYCODE"));
						obj.put("beneficiary_no", lobHandler.getClobAsString(rs, "BENEFICIARYNO"));
						try {
							obj.put("pobox", lobHandler.getClobAsString(rs, "BENEFICIARYPOBOX"));
						} catch (Exception e) {

						}
						try {
							obj.put("pincode", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSZIP"));
						} catch (Exception e) {

						}
						try {
							obj.put("city", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSCITY"));
							obj.put("state", lobHandler.getClobAsString(rs, "BENEFICIARYADDRESSSTATE"));
						} catch (Exception e) {
							obj.put("city", "");
							obj.put("state", "");
						}
						try {
							obj.put("bank_name", benresult.get(rowNum).get("BENEFICIARYBANKNAME"));
							obj.put("account_num", benresult.get(rowNum).get("BENEFICIARYBANKACCOUNTNO").toString());
							obj.put("ifsc", benresult.get(rowNum).get("SWIFTIFSC"));
							obj.put("branch_name", benresult.get(rowNum).get("BENEFICIARYBRANCHNAME"));
						}catch(Exception e) {
							obj.put("bank_name", "");
							obj.put("account_num", "");
							obj.put("ifsc", "");
							obj.put("branch_name", "");
							
						}
		                
		                output.add(obj);
		                return new ArrayList();
		            }});
		 }catch(Exception e) {
			  System.out.println( "22222 "+e.toString());
		 }

	/*	try {

			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("first_name", result.get(0).get("BENEFICIARYFIRSTNAME").toString());
			try {
				obj.put("middle_name", result.get(0).get("BENEFICIARYMIDDLENAME").toString());
			} catch (Exception e) {
				obj.put("middle_name", "");
			}
			obj.put("last_name", result.get(0).get("BENEFICIARYLASTNAME").toString());
			// obj.put("email", result.get(0).get("BENEFICIARYEMAIL").toString());
			try {
			obj.put("phone_num", result.get(0).get("BENEFICIARYPHONE").toString());
			} catch (Exception e) {
				obj.put("phone_num", null);
			}
			obj.put("gender", result.get(0).get("BENEFICIARYGENDER").toString());
			try {
				// obj.put("dob", result.get(0).get("DATEOFBIRTH").toString());
			} catch (Exception e) {
				// obj.put("dob", "null");
			}
			obj.put("country", result.get(0).get("BENEFICIARYADDRESSCOUNTRYCODE").toString());
			obj.put("beneficiary_no", result.get(0).get("BENEFICIARYNO").toString());
			try {
				obj.put("pobox", result.get(0).get("BENEFICIARYPOBOX").toString());
			} catch (Exception e) {
				obj.put("pobox", "null");
			}

			try {
				obj.put("mobile", result.get(0).get("BENEFICIARYMOBILE").toString());
			} catch (Exception e) {
				obj.put("mobile", "null");
			}
			try {
				obj.put("pincode", result.get(0).get("BENEFICIARYADDRESSZIP").toString());
			} catch (Exception e) {
				obj.put("pincode", "null");
			}
			if (benresult.get(0).get("BENEFICIARYBANKNAME") == null) {
				// obj.put("account_num",
				// benresult.get(0).get("BENEFICIARYBANKACCOUNTNO").toString());
				obj.put("city", result.get(0).get("BENEFICIARYADDRESSSTATE").toString());
				obj.put("state", result.get(0).get("BENEFICIARYADDRESSCITY").toString());
				obj.put("bankType", 0);
			} else {
				obj.put("bank_name", benresult.get(0).get("BENEFICIARYBANKNAME"));
				obj.put("account_num", benresult.get(0).get("BENEFICIARYBANKACCOUNTNO").toString());
				obj.put("ifsc", benresult.get(0).get("SWIFTIFSC"));
				obj.put("bankType", 1);
				try {
					obj.put("city", result.get(0).get("BENEFICIARYADDRESSCITY").toString());
				} catch (Exception e) {
					obj.put("city", "null");
				}
				try {
					obj.put("state", result.get(0).get("BENEFICIARYADDRESSSTATE").toString());
				} catch (Exception e) {
					obj.put("state", "null");
				}
			}
			System.out.println("beeficiary " + obj);
			try {
				obj.put("address1", result.get(0).get("BENEFICIARYADDRESS1").toString());
			} catch (Exception e) {
				obj.put("address1", "null");
			}
			try {
				obj.put("address2", result.get(0).get("BENEFICIARYADDRESS2").toString());
			} catch (Exception e) {
				obj.put("address2", "null");
			}

			output.add(obj);

		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYDETAILS retrieval" + e.toString());
		}*/
		return output;

	}

	@Override
	public List<Map<String, Object>> retrieveBeneficiaryData(String beneficiaryno) {
		System.out.println("benno " + beneficiaryno);
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from BENEFICIARY where BENEFICIARYNO =?",
				new Object[] { beneficiaryno });
		System.out.println("result " + result.size());
		List<Map<String, Object>> benresult = jdbcTemplate
				.queryForList("select * from BENEFICIARYBANK where BENEFICIARYNO =?", new Object[] { beneficiaryno });
		System.out.println("benresult " + benresult.size());
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();

	

		try {

			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("first_name", result.get(0).get("BENEFICIARYFIRSTNAME").toString());
			try {
				obj.put("middle_name", result.get(0).get("BENEFICIARYMIDDLENAME").toString());
			} catch (Exception e) {
				obj.put("middle_name", "");
			}
			obj.put("last_name", result.get(0).get("BENEFICIARYLASTNAME").toString());
			// obj.put("email", result.get(0).get("BENEFICIARYEMAIL").toString());
			try {
			obj.put("phone_num", result.get(0).get("BENEFICIARYPHONE").toString());
			} catch (Exception e) {
				obj.put("phone_num", null);
			}
			obj.put("gender", result.get(0).get("BENEFICIARYGENDER").toString());
			try {
				// obj.put("dob", result.get(0).get("DATEOFBIRTH").toString());
			} catch (Exception e) {
				// obj.put("dob", "null");
			}
			obj.put("country", result.get(0).get("BENEFICIARYADDRESSCOUNTRYCODE").toString());
			obj.put("beneficiary_no", result.get(0).get("BENEFICIARYNO").toString());
			try {
				obj.put("pobox", result.get(0).get("BENEFICIARYPOBOX").toString());
			} catch (Exception e) {
				obj.put("pobox", "null");
			}

			try {
				obj.put("mobile", result.get(0).get("BENEFICIARYMOBILE").toString());
			} catch (Exception e) {
				obj.put("mobile", "null");
			}
			try {
				obj.put("pincode", result.get(0).get("BENEFICIARYADDRESSZIP").toString());
			} catch (Exception e) {
				obj.put("pincode", "null");
			}
			if (benresult.get(0).get("BENEFICIARYBANKNAME") == null) {
				// obj.put("account_num",
				// benresult.get(0).get("BENEFICIARYBANKACCOUNTNO").toString());
				obj.put("city", result.get(0).get("BENEFICIARYADDRESSSTATE").toString());
				obj.put("state", result.get(0).get("BENEFICIARYADDRESSCITY").toString());
				obj.put("bankType", 0);
			} else {
				obj.put("bank_name", benresult.get(0).get("BENEFICIARYBANKNAME"));
				obj.put("account_num", benresult.get(0).get("BENEFICIARYBANKACCOUNTNO").toString());
				obj.put("ifsc", benresult.get(0).get("SWIFTIFSC"));
				obj.put("branch_name", benresult.get(0).get("BENEFICIARYBRANCHNAME"));
				obj.put("bankType", 1);
				try {
					obj.put("city", result.get(0).get("BENEFICIARYADDRESSCITY").toString());
				} catch (Exception e) {
					obj.put("city", "null");
				}
				try {
					obj.put("state", result.get(0).get("BENEFICIARYADDRESSSTATE").toString());
				} catch (Exception e) {
					obj.put("state", "null");
				}
			}
			System.out.println("beneficiary " + obj);
			try {
				obj.put("address1", result.get(0).get("BENEFICIARYADDRESS1").toString());
			} catch (Exception e) {
				obj.put("address1", "null");
			}
			try {
				obj.put("address2", result.get(0).get("BENEFICIARYADDRESS2").toString());
			} catch (Exception e) {
				obj.put("address2", "null");
			}

			output.add(obj);

		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYDETAILS retrieval" + e.toString());
		}
		return output;

	}
	
	@Override
	public BeneficiaryBank retrieveBeneficiaryBankDetails(String customerno) {
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("select * from BENEFICIARYBANK where CUSTOMERNO =?", new Object[] { customerno });
		BeneficiaryBank ben = new BeneficiaryBank();
		int id = 0;
		try {
			String BENEFICIARYNO = result.get(result.size() - 1).get("BENEFICIARYNO").toString();
			System.out.println("BENEFICIARYNO in " + BENEFICIARYNO);

			id = Integer.parseInt(BENEFICIARYNO);
		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
		}
		return ben;

	}

	@Override
	public BeneficiaryBank retrieveBeneficiaryBankData(String beneficiaryno) {
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("select * from BENEFICIARYBANK where BENEFICIARYNO =?", new Object[] { beneficiaryno });
		BeneficiaryBank ben = new BeneficiaryBank();
		int id = 0;
		try {
			String BENEFICIARYNO = result.get(result.size() - 1).get("BENEFICIARYNO").toString();
			System.out.println("BENEFICIARYNO in " + BENEFICIARYNO);

			id = Integer.parseInt(BENEFICIARYNO);
		} catch (Exception e) {
			System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
		}
		return ben;

	}

	@Override
	public int delBeneficiaryIds(int beneficiary_no) {
		int del = 0;
		int delBen = 0;
		int delBenbank = 0;
		int delBenids = 0;
		try {
			delBen = jdbcTemplate.update("delete from BENEFICIARY where BENEFICIARYNO = ?",
					new Object[] { beneficiary_no });
		} catch (Exception e) {
			System.out.println("error in beneficiary deletion" + e.toString());
		}
		try {
			delBenbank = jdbcTemplate.update("delete from BENEFICIARYBANK where BENEFICIARYNO = ?",
					new Object[] { beneficiary_no });
		} catch (Exception e) {
			System.out.println("error in beneficiarybank deletion" + e.toString());
		}
		try {
			delBenids = jdbcTemplate.update("delete from BENEFICIARYIDS where BENEFICIARYNO = ?",
					new Object[] { beneficiary_no });
		} catch (Exception e) {
			System.out.println("error in beneficiaryids deletion" + e.toString());
		}

		if (delBen == 1 && delBenbank == 1 && delBenids == 1) {
			del = 1;
		}

		return del;
	}

	@Override
	public int checkBeneficiaryNo(String customerno, String mobile, String accnum) {
		System.out.println("checkben "+customerno+"=="+mobile+"==="+accnum);
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"select * from BENEFICIARY where CUSTOMERNO=? and BENEFICIARYMOBILE = ?",
				new Object[] { customerno, mobile });
		int id = 0;
		if (accnum == null) {
			System.out.println("accnum null ");
			try {
				id = result.size();
			} catch (Exception e) {
				System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
			}
		} else {
			System.out.println("result.size() null "+result.size());
			List<Map<String, Object>> result1 = jdbcTemplate.queryForList(
					"select * from BENEFICIARYBANK where CUSTOMERNO=? and BENEFICIARYBANKACCOUNTNO = ?",
					new Object[] { customerno, accnum });
			System.out.println("result1.size() null "+result1.size());
			try {

				if (result.size() > 0 && result1.size() > 0) {
					id = result.size();
				}
			} catch (Exception e) {
				System.out.println("Error in BENEFICIARYNO retrieval" + e.toString());
			}
		}
		System.out.println("final size "+id);
		return id;

	}

	/**
	 * This method will find an User instance in the database by its email. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */

}