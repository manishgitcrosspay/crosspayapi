package com.crosspay.payment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.crosspay.payment.model.CurrencyRates;
import com.crosspay.payment.model.CurrencyRatesHistory;
import com.crosspay.payment.model.Version;
import com.crosspay.payment.service.VersionDao;

@Service
public class VersionDaoImpl implements VersionDao {

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
	public void delete(Version arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Version> arg0) {
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
	public Iterable<Version> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Version> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Version> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Version> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public String findByNewversion() { // TODO Auto-generated method
	 * stub return null; }
	 */

	@Override
	public int savever(Version arg0) {
		return jdbcTemplate.update("insert into version values (?,?,?,?)", new Object[] { 0,
				arg0.getPREVIOUS_RELEASE_NUMBER(), arg0.getRELEASENUMBER(), arg0.getRELEASE_DETAILS() });

	}

	/**
	 * This method will find an User instance in the database by its email. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */
	@Override
	public Version findByRELEASENUMBER() {
		// String sql = "select newversion from version";

		Query query = em.createQuery("from version");
		List<Version> val = query.getResultList();
		System.out.println("output " + val.size());

		Version ver = new Version();
		ver.setRELEASENUMBER(val.get(0).getRELEASENUMBER());
		return ver;

	}

	@Override
	public List<Map<String, Object>> retrieveCountries() {

		System.out.println("output BEFORE");
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> val = jdbcTemplate.queryForList("select * from COUNTRY");

		System.out.println("output " + val.size());

		for (int i = 0; i < val.size(); i++) {
			Map<String, Object> obj = new HashMap<String, Object>();
			try {
				obj.put("country_code", val.get(i).get("COUNTRYCODE").toString());
				obj.put("country_name", val.get(i).get("COUNTRYNAME").toString());
				obj.put("country_image", val.get(i).get("COUNTRYIMAGE").toString());
				System.out.println("iter " + obj);
				output.add(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("output " + output);
		// finallist.put(output);
		return output;
	}

	@Override
	public CurrencyRates retieveData(CurrencyRates curr) {

		List<Map<String, Object>> val = jdbcTemplate.queryForList(
				"select * from CURRENCYRATES where CCYCODEFROM = ? AND CCYCODETO = ? and INSTRUMENT_TYPE = 'AC' ",
				new Object[] { curr.getCCYCODEFROM(), curr.getCCYCODETO() });

		CurrencyRates currencyrates = new CurrencyRates();

		try {
			currencyrates.setXCHGRATE(Double.parseDouble(val.get(0).get("XCHGRATE").toString()));

		} catch (Exception e) {

		}
		return currencyrates;
	}

	@Override
	public CurrencyRates retieveCashData(CurrencyRates curr) {

		List<Map<String, Object>> val = jdbcTemplate.queryForList(
				"select * from CURRENCYRATES where CCYCODEFROM = ? AND CCYCODETO = ? and INSTRUMENT_TYPE = 'CP' ",
				new Object[] { curr.getCCYCODEFROM(), curr.getCCYCODETO() });

		CurrencyRates currencyrates = new CurrencyRates();

		try {
			currencyrates.setXCHGRATE(Double.parseDouble(val.get(0).get("XCHGRATE").toString()));

		} catch (Exception e) {

		}
		return currencyrates;
	}

	@Override
	public int updateCurrency(CurrencyRates curr, CurrencyRatesHistory currh) {
		int i = 0;
		try {
			int j = jdbcTemplate.update(
					"update CURRENCYRATES set xchgrate = ?,ratesetbyuserid = ? where ccycodefrom = ? and ccycodeto = ? and instrument_type = ?",
					new Object[] { curr.getXCHGRATE(), curr.getRATESETBYUSERID(), curr.getCCYCODEFROM(),
							curr.getCCYCODETO(), curr.getINSTRUMENT_TYPE() });
			System.out.println("upadtes " + j);
			if (j > 0) {
				int k = jdbcTemplate.update("insert into CURRENCYRATESHISTORY values (?,?,?,?,?,?,?,?,?,?)",
						new Object[] { 0, currh.getASONDATE(), currh.getCCYCODEFROM(), currh.getCCYCODETO(),
								currh.getINSTRUMENT_TYPE(), currh.getRATESETBYUSERID(), currh.getRECORDDATE(),
								currh.getXCHGRATE(), currh.getXCHGRATEHIGH(), currh.getXCHGRATELOW() });
				System.out.println("upadtes c" + k);
				if (k == 1) {
					i = 1;
				}
			}
		} catch (Exception e) {
			System.out.println("upadtes exc" + e.toString());
		}
		return i;
	}

	@Override
	public List<Map<String, Object>> retrieveCountriesCurrencies(String cc) {
		List<Map<String, Object>> val = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		if (cc.equalsIgnoreCase("ccycodefrom")) {
			val = jdbcTemplate.queryForList("select distinct(ccycodefrom) from CURRENCYRATES", new Object[] {});
			for (int i = 0; i < val.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("country", val.get(i).get("ccycodefrom").toString());
					output.add(obj);
				} catch (Exception e) {

				}
			}
		} else if (cc.equalsIgnoreCase("ccycodeto")) {
			val = jdbcTemplate.queryForList("select distinct(ccycodeto) from CURRENCYRATES", new Object[] {});
			for (int i = 0; i < val.size(); i++) {
				Map<String, Object> obj = new HashMap<String, Object>();
				try {
					obj.put("country", val.get(i).get("ccycodeto").toString());
					output.add(obj);
				} catch (Exception e) {

				}
			}
		}

		return output;

	}

	@Override
	public String retrieveCurrencyval(CurrencyRates curr) {
		String val = null;
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		try {
			output = jdbcTemplate.queryForList(
					"select xchgrate from CURRENCYRATES where ccycodefrom = ? and ccycodeto = ? and instrument_type = ?",
					new Object[] { curr.getCCYCODEFROM(), curr.getCCYCODETO(), curr.getINSTRUMENT_TYPE() });
			val = output.get(0).get("xchgrate").toString();
		} catch (Exception e) {

		}
		return val;
	}

	@Override
	public List<Map<String, Object>> retrieveBanks() {

		System.out.println("output BEFORE");
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> val = jdbcTemplate.queryForList("select * from BANKS");

		System.out.println("output " + val.size());

		for (int i = 0; i < val.size(); i++) {
			Map<String, Object> obj = new HashMap<String, Object>();
			try {
				obj.put("bank_code", val.get(i).get("BANKCODE").toString());
				obj.put("bank_name", val.get(i).get("BANKNAME").toString());
				obj.put("bank_city", val.get(i).get("BANKCITY").toString());
				obj.put("bank_country_code", val.get(i).get("BANKCOUNTRYCODE").toString());
				System.out.println("iter " + obj);
				output.add(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("output " + output);
		// finallist.put(output);
		return output;
	}

}