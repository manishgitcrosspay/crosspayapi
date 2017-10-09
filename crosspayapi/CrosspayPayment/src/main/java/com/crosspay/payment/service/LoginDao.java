package com.crosspay.payment.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.Login;
import com.crosspay.payment.model.LoginCredentialHistory;
import com.crosspay.payment.model.Register;

@Service
public interface LoginDao extends CrudRepository<Login, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
  
  public List<Map<String, Object>> checkLoginCredential(Register reg);
  
  public List<Map<String, Object>> checkadminLoginCredential(Register reg); 
  
  public int saveLogin(Login reg);
  
  public int updateLoginStatus(Login reg);
  
  public int updateLoginPassword(Login reg);
  
  public int updateLogindetails(Login reg);
  
  public int saveLoginHistory(LoginCredentialHistory reg);
  
  public List<Map<String, Object>> checkLoginCredentialEmail(Register reg);

}