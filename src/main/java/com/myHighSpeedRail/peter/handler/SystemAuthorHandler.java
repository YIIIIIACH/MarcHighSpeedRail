package com.myHighSpeedRail.peter.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.service.DepartmentService;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;

/**
 * 傳入系統名稱、員工ID，返回權限為true或false
 */
@Service
public class SystemAuthorHandler {

	@Autowired
	private EmployeeService eService;

	@Autowired
	private DepartmentService dService;

	@Autowired
	private SystemsService sService;
	
	private String authorJsonSave;
	
	//現有系統個數
	private String SystmeNumbers;

	// 解析JSON字串
	private JSONArray getSystemById(String authorJSON, Integer sysId) {

		JSONObject j;
		String sysIdString = String.valueOf(sysId);
		try {

//			String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

//			j = new JSONObject(tmp);
			j = new JSONObject(authorJSON);
			System.out.println("JSONObject: " + j);

//			Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone");
//			Object jsonOb = j.getJSONObject("authorJson").getJSONArray(sysidString);
			JSONObject jObj = j.getJSONObject("authorJson");
			
			JSONArray jsonArray = j.getJSONObject("authorJson").getJSONArray(sysIdString);
			System.out.println("jsonArray: "+jsonArray);

//			return jsonOb;

			return jsonArray;

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return null;
	}

	// 判斷系統權限
	public Boolean systemAccessJudgment(Integer empId, String sysName, Integer judgeKind) {
		Employee emp = eService.findEmployeeById(empId);

		if (emp == null) {
			System.out.println("沒有此員工ID");// 這個要不要做?
			return false;
		}

		Systems system = sService.findSystemByName(sysName);

		if (system == null) {
			System.out.println("沒有此系統名稱");// 這個要不要做?
			return false;
		}

		SystemAuthor empSystemAuthor = eService.getEmployeeSystemAuthor(emp.getEmployeeId());
		System.out.println("empSystemAuthor: " + empSystemAuthor);

		// eService如果沒有找到匹配的權限，回傳NULL，這邊接到要做處理
		if (!(empSystemAuthor == null)) {
//			String empAuthorJson = empSystemAuthor.getAuthorJson();
//			System.out.println("empauthorJson: " + empAuthorJson);
//			System.out.println(empAuthorJson);
			JSONArray author = getSystemById(empSystemAuthor.getAuthorJson(), system.getSystemId());

			try {
				if (author.get(judgeKind).equals(1)) {
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return false;

		} else {
//			List<EmployeeHistoricalDepartment> ehdList = emp.getEmployeeHistoricalDepartment();
//			EmployeeHistoricalDepartment ehd = ehdList.get(0);
//			ehd.get
			Department dept = eService.findLatestDepartment(empId);
			SystemAuthor departmentSystemAuthor = dService.getDepartmentSystemAuthor(dept.getDepartmentId());
			String deptAuthorJson = departmentSystemAuthor.getAuthorJson();
			System.out.println("deptAuthorJson: " + deptAuthorJson);
			System.out.println(deptAuthorJson);
			JSONArray author = getSystemById(departmentSystemAuthor.getAuthorJson(), system.getSystemId());
			try {
				if (author.get(0).equals(1)) {
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	// 判斷能否做View
	public Boolean rightsOfView(Integer empId, String sysName) {
		Boolean result = systemAccessJudgment(empId, sysName, 0);
		return result;
	}

	// 判斷能否做Create
	public Boolean rightsOfCreate(Integer empId, String sysName) {
		Boolean result = systemAccessJudgment(empId, sysName, 1);
		return result;
	}

	// 判斷能否做Read
	public Boolean rightsOfRead(Integer empId, String sysName) {
		Boolean result = systemAccessJudgment(empId, sysName, 2);
		return result;
	}

	// 判斷能否做Update
	public Boolean rightsOfUpdate(Integer empId, String sysName) {
		Boolean result = systemAccessJudgment(empId, sysName, 3);
		return result;
	}

	// 判斷能否做Delete
	public Boolean rightsOfDelete(Integer empId, String sysName) {
		Boolean result = systemAccessJudgment(empId, sysName, 4);
		return result;
	}

}
