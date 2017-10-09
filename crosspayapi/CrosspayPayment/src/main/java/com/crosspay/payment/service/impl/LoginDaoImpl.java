package com.crosspay.payment.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.Login;
import com.crosspay.payment.model.LoginCredentialHistory;
import com.crosspay.payment.model.Register;
import com.crosspay.payment.service.LoginDao;

@Service
public class LoginDaoImpl implements LoginDao {

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
	public List<Map<String, Object>> checkLoginCredential(Register reg) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"select * from LOGINCREDENTIALS where CUSTOMERMOBILE = ?", new Object[] { reg.getCUSTOMERMOBILE() });
		return result;

	}
	
	
	@Override
	public List<Map<String, Object>> checkadminLoginCredential(Register reg) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"select * from LOGINCREDENTIALS where CUSTOMERMOBILE = ? AND USERCATEGORY = '1'", new Object[] { reg.getCUSTOMERMOBILE()});
		return result;

	}
	
	@Override
	public List<Map<String, Object>> checkLoginCredentialEmail(Register reg) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList(
				"select * from LOGINCREDENTIALS where CUSTOMEREMAIL = ?", new Object[] { reg.getCUSTOMEREMAIL() });
		return result;

	}
	
	@Override
	public int saveLogin(Login login) {
		
		return jdbcTemplate.update("insert into LOGINCREDENTIALS values (?,?,?,?,?,?,?,?)",new Object[]{0,login.getCUSTOMEREMAIL(),login.getCUSTOMERMOBILE(),login.getCUSTOMERNO(),login.getPASSWORD(),login.getRECORDSTATUS(),login.getTIMESTAMP(),login.getUSERCATEGORY()});

	}
	
	@Override
    public int updateLoginStatus(Login login) {
		 
		return jdbcTemplate.update("update LOGINCREDENTIALS set RECORDSTATUS = ? where CUSTOMERMOBILE = ?",new Object[]{login.getRECORDSTATUS(),login.getCUSTOMERMOBILE()});
	 }
	
	@Override
    public int updateLoginPassword(Login login) {
		 
		return jdbcTemplate.update("update LOGINCREDENTIALS set PASSWORD = ? where CUSTOMERMOBILE = ?",new Object[]{login.getPASSWORD(),login.getCUSTOMERMOBILE()});
	 }
	
	@Override
	public int saveLoginHistory(LoginCredentialHistory his){
		
		return jdbcTemplate.update("insert into LOGINCREDENTIALSHISTORY values (?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{0,his.getCUSTOMERNO(),his.getOTPUSEDFORCHANGE(),his.getPASSWORD(),his.getPASSWORDCHANGECLIENT(),his.getPASSWORDCHANGEDBY(),his.getPASSWORDCHANGEREASON(),his.getPASSWORDCREATEDON(),his.getPASSWORDEXPIREDON(),his.getPASSWORDNO(),his.getTIMESTAMP(),his.getUSERCATEGORY()});

	}
	
	@Override
	public int updateLogindetails(Login login) {
		return jdbcTemplate.update("update LOGINCREDENTIALS set CUSTOMEREMAIL = ?,CUSTOMERMOBILE = ? WHERE CUSTOMERNO =?",new Object[]{login.getCUSTOMEREMAIL(),login.getCUSTOMERMOBILE(),login.getCUSTOMERNO()});
	}
	


	@Override
	public void delete(Login arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Login> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Login findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Login> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Login> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Login> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Login> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}