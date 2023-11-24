package com.myHighSpeedRail.peter.handler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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

	private List<Systems> systemList;

	@Autowired
	private SystemsService sService;

	public EmployeeSystemAuthor(Map<Integer, JSONArray> authorJson, List<Systems> systemList) {
		this.authorJson = authorJson;
		this.systemList = systemList;
	}

	// 藉由systemName找systemId
	private void getSytemId(String systemName, List<Systems> functionList) {


		// 使用Java 8的Stream API尋找符合條件的物件
		Optional<Systems> foundObject = systemList.stream().filter(obj -> obj.getSystemName().equals(systemName))
				.findFirst();


		if (foundObject.isPresent()) {
			// 找到物件，進行相應的處理
			Systems result = foundObject.get();
			systemId = result.getSystemId();
			System.out.println("result.getSystemId(): " + result.getSystemId());
			System.out.println("systemId: " + systemId);
		} else {
			// 沒有找到符合條件的物件
			System.out.println("沒有此系統名稱");
		}
	}

	// 判斷權限為有(true)/沒有(false)
	private Boolean accessJedgement(Integer judgeKind) {

		if (systemId == null) {
			return false;
		}
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
		getSytemId(sysName, systemList);
		Boolean result = accessJedgement(0);
		return result;
	}

	// 判斷能否做Create
	public Boolean rightsOfCreate(Integer empId, String sysName) {
		getSytemId(sysName, systemList);
		Boolean result = accessJedgement(1);
		return result;
	}

	// 判斷能否做Read
	public Boolean rightsOfRead(Integer empId, String sysName) {
		getSytemId(sysName, systemList);
		Boolean result = accessJedgement(2);
		return result;
	}

	// 判斷能否做Update
	public Boolean rightsOfUpdate(Integer empId, String sysName) {
		getSytemId(sysName, systemList);
		Boolean result = accessJedgement(3);
		return result;
	}

	// 判斷能否做Delete
	public Boolean rightsOfDelete(Integer empId, String sysName) {
		getSytemId(sysName, systemList);
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

	public List<Systems> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<Systems> systemList) {
		this.systemList = systemList;
	}

}
