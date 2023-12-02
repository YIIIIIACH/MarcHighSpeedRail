package com.myHighSpeedRail.peter.handler;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.service.DepartmentService;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;

/**
 * 輸入員工id尋找屬於此員工的權限，返還裝有權限與系統表的物件
 * 
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

	private List<Systems> systemList;

	// 現有系統個數
	private Long SystemNumber;

	public EmployeeSystemAuthor getEmpSystemAccess(Employee e) {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();
		System.out.println("number of systems: " + sService.findSystemCount());

		// 把所有系統放進List存起來
		systemList = sService.findAllSystems();

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

//			EmployeeSystemAuthor esa = new EmployeeSystemAuthor(authorJsonMap, systemList);
			EmployeeSystemAuthor esa = new EmployeeSystemAuthor();
			esa.setAuthorJson(authorJsonMap);
			esa.setSystemList(systemList);

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

//			EmployeeSystemAuthor esa = new EmployeeSystemAuthor(authorJsonMap, systemList);
			EmployeeSystemAuthor esa = new EmployeeSystemAuthor();
			esa.setAuthorJson(authorJsonMap);
			esa.setSystemList(systemList);

			return esa;

		}

	}

	private void encapsulateJsonToMap(String authorJSON) {
		// 把權限裝進Map裡面
		authorJsonMap = new HashMap<Integer, JSONArray>();
		for (int i = 1; i <= SystemNumber; i++) {
			try {
				String s = String.valueOf(i);
//				System.out.println("i: " + s);
				JSONObject jsonObj = new JSONObject(authorJSON);
				JSONArray jsonArray = jsonObj.getJSONObject("authorJson").getJSONArray(s);
				System.out.println("jsonArray: " + jsonArray);
				authorJsonMap.put(i, jsonArray);

			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("未設定系統權限，系統ID: " + i);
			}
		}
	}

}
