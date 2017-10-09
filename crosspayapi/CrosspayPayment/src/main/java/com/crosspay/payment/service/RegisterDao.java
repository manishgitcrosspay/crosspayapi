package com.crosspay.payment.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.Customer;
import com.crosspay.payment.model.Register;

@Service
public interface RegisterDao extends CrudRepository<Register, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
  public Register findByCUSTOMEREMAIL(String email,String password);
  
  public Register findByCUSTOMEREMAIL(String email);
  
  public Register findByCUSTOMERMOBILE(String mobile,String password);
  
  public Register findByCUSTOMERMOBILE(String mobile);
  
  public int saveReg(Register reg);
  
  public double retrieveCustomerNo();
  
  public int saveCustomer(Customer cus);
  
  public int updateReg(Register reg);
  
  public int updateRegdetails(Register reg);
  
  public String retrieveOTP(Register reg);
  
  public int updateOtp(Register reg);
  
  public int updateMpin(Register reg);
  
  public int updateOtpEmail(Register reg);
  
  public String retrieveCustomerNumber(String userid);
  
  public int updateCustomerdetails(Customer customer);
  
  public List<Map<String, Object>> retrieveCustomerProfile(String customerno);
  
  public int retrieveUserId(String customerno);
  
  public void changepswd(String email) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;

}