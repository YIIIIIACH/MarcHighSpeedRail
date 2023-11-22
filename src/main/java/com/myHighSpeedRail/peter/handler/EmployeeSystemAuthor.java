package com.myHighSpeedRail.peter.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.service.SystemsService;

@Component
public class EmployeeSystemAuthor {

	private Integer systemId;

	private Map<Integer, JSONArray> authorJson;
	
//	private List<Systems> systemList;

	@Autowired
	private SystemsService sService;

	public EmployeeSystemAuthor() {
	}

	public EmployeeSystemAuthor(Map<Integer, JSONArray> authorJson) {
		super();
		this.authorJson = authorJson;
	}

	public EmployeeSystemAuthor(Integer systemId, Map<Integer, JSONArray> authorJson) {
		this.systemId = systemId;
		this.authorJson = authorJson;
	}

	// 藉由systemName找systemId
	private void getSytemId(String systemName) {
		Systems system = sService.findSystemByName(systemName);

		if (system == null) {
			System.out.println("沒有此系統名稱");// 這個要不要做?
//			return null;
		}
		System.out.println("system.systemId: " + system.getSystemId());
		setSystemId(system.getSystemId());
//		return new EmployeeSystemAuthor(system.getSystemId(), authorJson);
	}

	// 判斷權限為有(true)/沒有(false)
	private Boolean accessJedgement(Integer judgeKind) {
		System.out.println("authorJson: " + authorJson);
		System.out.println("systemId: " + systemId);
		JSONArray author = authorJson.get(systemId);
		System.out.println("author: " + author);
		try {
			if (author.get(judgeKind).equals(1)) {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 判斷能否做View
	public Boolean rightsOfView(Integer empId, String sysName) {
		getSytemId(sysName);
		Boolean result = accessJedgement(0);
		return result;
	}

	// 判斷能否做Create
	public Boolean rightsOfCreate(Integer empId, String sysName) {
		getSytemId(sysName);
		Boolean result = accessJedgement(1);
		return result;
	}

	// 判斷能否做Read
	public Boolean rightsOfRead(Integer empId, String sysName) {
		getSytemId(sysName);
		Boolean result = accessJedgement(2);
		return result;
	}

	// 判斷能否做Update
	public Boolean rightsOfUpdate(Integer empId, String sysName) {
		getSytemId(sysName);
		Boolean result = accessJedgement(3);
		return result;
	}

	// 判斷能否做Delete
	public Boolean rightsOfDelete(Integer empId, String sysName) {
		getSytemId(sysName);
		Boolean result = accessJedgement(4);
		return result;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Map<Integer, JSONArray> getAuthorJson() {
		return authorJson;
	}

	public void setAuthorJson(Map<Integer, JSONArray> authorJson) {
		this.authorJson = authorJson;
	}

}
