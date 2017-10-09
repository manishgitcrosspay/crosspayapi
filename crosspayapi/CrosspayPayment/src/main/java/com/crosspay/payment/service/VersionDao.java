package com.crosspay.payment.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.CurrencyRates;
import com.crosspay.payment.model.CurrencyRatesHistory;
import com.crosspay.payment.model.Version;

@Service
public interface VersionDao extends CrudRepository<Version, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
	
	
  public Version findByRELEASENUMBER();
  
  public int savever(Version v);
  
  public List<Map<String, Object>> retrieveCountries();
  
  public CurrencyRates retieveData(CurrencyRates curr);
  
  public CurrencyRates retieveCashData(CurrencyRates curr);
  
  public int updateCurrency(CurrencyRates curr,CurrencyRatesHistory currh);
  
  public List<Map<String, Object>> retrieveCountriesCurrencies(String cc);
  
  public String retrieveCurrencyval(CurrencyRates curr);
  
  public List<Map<String, Object>> retrieveBanks();

}