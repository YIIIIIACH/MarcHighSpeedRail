package com.myHighSpeedRail.peter.handler;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.service.DepartmentService;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;

/**
 * 傳入系統名稱、員工ID，返回權限為true或false
 */

@Component
public class SystemAuthorHandler {

	@Autowired
	private EmployeeService eService;

	@Autowired
	private DepartmentService dService;

	@Autowired
	private SystemsService sService;

	private HashMap<Integer, JSONArray> authorJsonMap;

	// 現有系統個數
	private Long SystemNumber;

//	// 解析JSON字串
//		private JSONArray getSystemById(String authorJSON, Integer sysId) {
//
//			JSONObject j;
//			String sysIdString = String.valueOf(sysId);
//			try {
//
////				String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";
//
////				j = new JSONObject(tmp);
//				j = new JSONObject(authorJSON);
//				System.out.println("JSONObject: " + j);
//
////				Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone");
////				Object jsonOb = j.getJSONObject("authorJson").getJSONArray(sysidString);
//				JSONObject jObj = j.getJSONObject("authorJson");
//
//				JSONArray jsonArray = j.getJSONObject("authorJson").getJSONArray(sysIdString);
//				System.out.println("jsonArray: " + jsonArray);
//
//				System.out.println("authorJsonMap: " + authorJsonMap);
//
////				return jsonOb;
//
//				return jsonArray;
//
//			} catch (Exception e) {
//				System.err.println("Error: " + e.getMessage());
//			}
//
//			return null;
//		}

//	// 判斷系統權限
//	public Boolean systemAccessJudgment(Integer empId, String sysName, Integer judgeKind) {
//		Employee emp = eService.findEmployeeById(empId);
//
//		if (emp == null) {
//			System.out.println("沒有此員工ID");// 這個要不要做?
//			return false;
//		}
//
//		Systems system = sService.findSystemByName(sysName);
//
//		if (system == null) {
//			System.out.println("沒有此系統名稱");// 這個要不要做?
//			return false;
//		}
//
//		SystemAuthor empSystemAuthor = eService.getEmployeeSystemAuthor(emp.getEmployeeId());
//		System.out.println("empSystemAuthor: " + empSystemAuthor);
//
//		// eService如果沒有找到匹配的權限，回傳NULL，這邊接到要做處理
//		if (!(empSystemAuthor == null)) {
////			String empAuthorJson = empSystemAuthor.getAuthorJson();
////			System.out.println("empauthorJson: " + empAuthorJson);
////			System.out.println(empAuthorJson);
//			JSONArray author = getSystemById(empSystemAuthor.getAuthorJson(), system.getSystemId());
//
//			try {
//				if (author.get(judgeKind).equals(1)) {
//					return true;
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//
//			return false;
//
//		} else {
////			List<EmployeeHistoricalDepartment> ehdList = emp.getEmployeeHistoricalDepartment();
////			EmployeeHistoricalDepartment ehd = ehdList.get(0);
////			ehd.get
//			Department dept = eService.findLatestDepartment(empId);
//			SystemAuthor departmentSystemAuthor = dService.getDepartmentSystemAuthor(dept.getDepartmentId());
//			String deptAuthorJson = departmentSystemAuthor.getAuthorJson();
//			System.out.println("deptAuthorJson: " + deptAuthorJson);
//			System.out.println(deptAuthorJson);
//			JSONArray author = getSystemById(departmentSystemAuthor.getAuthorJson(), system.getSystemId());
//			try {
//				if (author.get(0).equals(1)) {
//					return true;
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
//	}

	public EmployeeSystemAuthor getEmpSystemAccess(Employee e) {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();
		System.out.println("count: " + sService.findSystemCount());

		Employee emp = eService.findEmployeeById(e.getEmployeeId());

		// 判斷是否真的有這個員工
		if (emp == null) {
			System.out.println("沒有此員工ID");// 這個要不要做?
			return null;
		}

		// 從員工Id尋找權限
		SystemAuthor empSystemAuthor = eService.getEmployeeSystemAuthor(emp.getEmployeeId());

		// eService如果沒有找到匹配的權限，回傳NULL，這邊接到要做處理
		if (!(empSystemAuthor == null)) {
			// 取得系統json字串
			String empAuthorJson = empSystemAuthor.getAuthorJson();

			encapsulateJsonToMap(empAuthorJson);
			
			EmployeeSystemAuthor esa = new EmployeeSystemAuthor(authorJsonMap);

			return esa;
//			JSONArray author = getSystemById(empSystemAuthor.getAuthorJson(), system.getSystemId());

		} else {
			Department dept = eService.findLatestDepartment(e.getEmployeeId());
			// 從部門Id尋找權限
			SystemAuthor departmentSystemAuthor = dService.getDepartmentSystemAuthor(dept.getDepartmentId());

			if (!(departmentSystemAuthor == null)) {
				System.out.println("沒有這個部門ID的權限");
				return null;
			}

			String deptAuthorJson = departmentSystemAuthor.getAuthorJson();

			encapsulateJsonToMap(deptAuthorJson);
			
			EmployeeSystemAuthor esa = new EmployeeSystemAuthor(authorJsonMap);

			return esa;


		}

	}

	public void encapsulateJsonToMap(String authorJSON) {
		// 把權限裝進Map裡面
		authorJsonMap = new HashMap<Integer, JSONArray>();
		for (int i = 1; i < SystemNumber; i++) {
			try {
				String s = String.valueOf(i);
				JSONObject jsonObj = new JSONObject(authorJSON);
				JSONArray jsonArray = jsonObj.getJSONObject("authorJson").getJSONArray(s);
				authorJsonMap.put(i, jsonArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
