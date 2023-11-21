package com.myHighSpeedRail.peter.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.service.DepartmentService;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;

@Service
public class SystemAuthorHandler {

	@Autowired
	private EmployeeService eService;

	@Autowired
	private DepartmentService dService;

	@Autowired
	private SystemsService sService;

	private JSONArray getSystemById(String authorJSON, Integer sysId) {

		JSONObject j;
		String sysIdString = String.valueOf(sysId);
		try {

//			String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

//			j = new JSONObject(tmp);
			j = new JSONObject(authorJSON);
			System.out.println("DDDDDDDDDD" + j);

//			Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone");
//			Object jsonOb = j.getJSONObject("authorJson").getJSONArray(sysidString);
			JSONArray jsonArray = j.getJSONObject("authorJson").getJSONArray(sysIdString);

//			System.out.println("AAAAAAAA" + jsonArray);

//			return jsonOb;

			return jsonArray;

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return null;
	}

	// 判斷能否做View
	public Boolean rightsOfView(Integer empId, String sysName) {

		Employee emp = eService.findEmployeeById(empId);

		if (emp == null) {
			System.out.println("沒有此員工ID");// 這個要不要做?
			return false;
		}

		Systems system = sService.findSystemByName(sysName);
		SystemAuthor empSystemAuthor = eService.getEmployeeSystemAuthor(emp.getEmployeeId());
		System.out.println("PPPPPPP" + empSystemAuthor);

		// eService如果沒有找到匹配的權限，回傳NULL，這邊接到要做處理
		if (!(empSystemAuthor == null)) {
			String empauthorJson = empSystemAuthor.getAuthorJson();
			System.out.println("empauthorJson: " + empauthorJson);
			System.out.println(empauthorJson);
			JSONArray author = getSystemById(empSystemAuthor.getAuthorJson(), system.getSystemId());

			try {
				if (author.get(0).equals(1)) {
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return false;

		} else {
			emp.getEmployeeHistoricalDepartment();
			SystemAuthor departmentSystemAuthor = dService.getDepartmentSystemAuthor(1);
			return false;
		}
	}

	// 判斷能否做Create
	public Boolean rightsOfCreate() {
		System.out.println("AAAAAAAAAAAAAAAAtest");
		return false;
	}

	// 判斷能否做Research
	public Boolean rightsOfResearch() {
		System.out.println("AAAAAAAAAAAAAAAAtest");
		return false;
	}

	// 判斷能否做Update
	public Boolean rightsOfUpdate() {
		System.out.println("AAAAAAAAAAAAAAAAtest");
		return false;
	}

	// 判斷能否做Delete
	public Boolean rightsOfDelete() {
		System.out.println("AAAAAAAAAAAAAAAAtest");
		return false;
	}

}
