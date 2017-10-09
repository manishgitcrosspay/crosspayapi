package com.crosspay.payment.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crosspay.payment.model.Version;
import com.crosspay.payment.service.VersionDao;

@Controller
public class VersionController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private VersionDao versionDao;

	@RequestMapping(value = "/welcomefssss", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/createversion", method = RequestMethod.POST)
	@ResponseBody
	public String createversion(@RequestBody Map<String, Object> json) throws JSONException {
		String userId = "";
		Version ver = null;
		JSONObject releaseInfo = new JSONObject();

		// System.out.println(json);
		try {

			ver = new Version(json.get("currentversion").toString(), json.get("newversion").toString(),
					json.get("versionname").toString());

			
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from version");
			
			userId = result.get(0).get("newversion").toString();

			releaseInfo.put("currentVersion", result.get(result.size() - 1).get("newversion").toString());
			releaseInfo.put("previousVersion", result.get(result.size() - 1).get("currentversion").toString());
			releaseInfo.put("status", "200");
			releaseInfo.put("message", "Success");
		} catch (Exception ex) {
			releaseInfo.put("status", "300");
			releaseInfo.put("message", "Failure");
			return releaseInfo.toString();
		}
		return releaseInfo.toString();
	}

	@RequestMapping("/getVersion")
	@ResponseBody
	public String getVersion() throws JSONException {
		String userId = "";
		JSONObject releaseInfo = new JSONObject();
		try {
			Iterable<Version> ver = versionDao.findAll();
			// ver.
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from version");
			
			userId = result.get(0).get("newversion").toString();
			// .query(jdbcTemplate.execute("select newversion from version"));

			releaseInfo.put("currentVersion", result.get(0).get("newversion").toString());
			releaseInfo.put("previousVersion", result.get(0).get("currentversion").toString());
			releaseInfo.put("status", "200");
			releaseInfo.put("message", "Success");

		} catch (Exception ex) {
			releaseInfo.put("status", "300");
			releaseInfo.put("message", "failure");
			return releaseInfo.toString();
		}
		return releaseInfo.toString();
	}

}