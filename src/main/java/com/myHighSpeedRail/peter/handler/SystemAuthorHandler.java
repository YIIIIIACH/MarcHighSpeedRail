package com.myHighSpeedRail.peter.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.configurationprocessor.json.JSONArray;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
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

//	private HashMap<Integer, JSONArray> authorJsonMap;
	private HashMap<Integer, ArrayList<Integer>> authorJsonMap;

	private List<Systems> systemList;

	private int count;

	// 現有系統個數
	private Long SystemNumber;

	public EmployeeSystemAuthor getEmpSystemAuthor(Employee e) {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();

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

			EmployeeSystemAuthor esa = new EmployeeSystemAuthor();
			esa.setAuthorJson(authorJsonMap);
			esa.setSystemList(systemList);

			return esa;

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

			EmployeeSystemAuthor esa = new EmployeeSystemAuthor();
			esa.setAuthorJson(authorJsonMap);
			esa.setSystemList(systemList);

			return esa;

		}

	}

	public HashMap<Integer, EmployeeSystemAuthor> getAllEmployeeSystemAccess() {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();

		// 把所有系統放進List存起來
		systemList = sService.findAllSystems();

		List<SystemAuthor> saList = sService.findAllSystemAuthors();
		HashMap<Integer, EmployeeSystemAuthor> esaMap = new HashMap<Integer, EmployeeSystemAuthor>();

		saList.forEach(sa -> {
			System.out.println(sa.getEmployee());
			if (sa.getEmployee() != null) {
				String authorJson = sa.getAuthorJson();

				encapsulateJsonToMap(authorJson);

				EmployeeSystemAuthor esa = new EmployeeSystemAuthor();
				esa.setAuthorJson(authorJsonMap);
				esa.setSystemList(systemList);
				esaMap.put(sa.getEmployee().getEmployeeId(), esa);

			}
		});
		return esaMap;
	}

	public HashMap<Integer, EmployeeSystemAuthor> getAllDepartmentSystemAccess() {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();

		// 把所有系統放進List存起來
		systemList = sService.findAllSystems();

		List<SystemAuthor> saList = sService.findAllSystemAuthors();
		HashMap<Integer, EmployeeSystemAuthor> dsaMap = new HashMap<Integer, EmployeeSystemAuthor>();

		saList.forEach(sa -> {
			System.out.println(sa.getDepartment());
			if (sa.getDepartment() != null) {
				String authorJson = sa.getAuthorJson();

				encapsulateJsonToMap(authorJson);

				EmployeeSystemAuthor esa = new EmployeeSystemAuthor();
				esa.setAuthorJson(authorJsonMap);
				esa.setSystemList(systemList);
				dsaMap.put(sa.getDepartment().getDepartmentId(), esa);

			}
		});
		return dsaMap;
	}

	public HashMap<Integer, EmployeeSystemAuthor> getSystemAccessByEmployeeId(Integer id) {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();

		// 把所有系統放進List存起來
		systemList = sService.findAllSystems();

		SystemAuthor sa = sService.findSystemAuthorByEmployeeId(id);

		HashMap<Integer, EmployeeSystemAuthor> esaMap = new HashMap<Integer, EmployeeSystemAuthor>();

		String authorJson = sa.getAuthorJson();

		encapsulateJsonToMap(authorJson);

		EmployeeSystemAuthor esa = new EmployeeSystemAuthor();

		esa.setAuthorJson(authorJsonMap);
		esa.setSystemList(systemList);

		// 測試這個function
		encapsulateMapToJson(authorJsonMap);

		esaMap.put(sa.getEmployee().getEmployeeId(), esa);

		return esaMap;

	}

	public void updateSystemAccessByEmployeeId(Integer id, EmployeeSystemAuthor esa) {

		HashMap<Integer, ArrayList<Integer>> authorJson = esa.getAuthorJson();

		Employee e = new Employee();
		e.setEmployeeId(id);

		String authorJsonString = encapsulateMapToJson(authorJson);
		SystemAuthor sa = new SystemAuthor();

		sa.setAuthorJson(authorJsonString);
		sa.setEmployee(e);

		sService.updateEmployeeSystemAuthor(sa);

	}

	public HashMap<Integer, EmployeeSystemAuthor> getSystemAccessByDeaprtmentId(Integer id) {
		// 找到現有系統個數
		SystemNumber = sService.findSystemCount();

		// 把所有系統放進List存起來
		systemList = sService.findAllSystems();

		SystemAuthor sa = sService.findSystemAuthorByDepartmentId(id);

		HashMap<Integer, EmployeeSystemAuthor> dsaMap = new HashMap<Integer, EmployeeSystemAuthor>();

		String authorJson = sa.getAuthorJson();

		encapsulateJsonToMap(authorJson);

		EmployeeSystemAuthor esa = new EmployeeSystemAuthor();

		esa.setAuthorJson(authorJsonMap);
		esa.setSystemList(systemList);

		// 測試這個function
		encapsulateMapToJson(authorJsonMap);

		dsaMap.put(sa.getDepartment().getDepartmentId(), esa);

		return dsaMap;

	}

	public void updateSystemAccessByDepartmentId(Integer id, EmployeeSystemAuthor esa) {

		System.out.println("id: " + id);
		System.out.println("esa: " + esa);

		HashMap<Integer, ArrayList<Integer>> authorJson = esa.getAuthorJson();

		Department d = new Department();
		d.setDepartmentId(id);

		String authorJsonString = encapsulateMapToJson(authorJson);
		SystemAuthor sa = new SystemAuthor();

		sa.setAuthorJson(authorJsonString);
		sa.setDepartment(d);

		sService.updateDepartmentSystemAuthor(sa);
		;

	}

	public void addEmployeeSystemAuthor(Integer id, EmployeeSystemAuthor esa) {

		HashMap<Integer, ArrayList<Integer>> authorJson = esa.getAuthorJson();

		String authorString = encapsulateMapToJson(authorJson);

		SystemAuthor sa = new SystemAuthor();
		Employee e = new Employee();
		e.setEmployeeId(id);

		sa.setAuthorJson(authorString);
		sa.setEmployee(e);
		
		sService.addSystemAuthor(sa);
	}

	public void addDepartmentSystemAuthor(Integer id, EmployeeSystemAuthor esa) {

		HashMap<Integer, ArrayList<Integer>> authorJson = esa.getAuthorJson();

		String authorString = encapsulateMapToJson(authorJson);

		SystemAuthor sa = new SystemAuthor();
		Department d = new Department();
		d.setDepartmentId(id);

		sa.setAuthorJson(authorString);
		sa.setDepartment(d);
		
		sService.addSystemAuthor(sa);
	}

	// 把權限裝進Map裡面
	private void encapsulateJsonToMap(String authorJSON) {

		authorJsonMap = new HashMap<Integer, ArrayList<Integer>>();

		for (int i = 1; i <= SystemNumber; i++) {
			try {
				String s = String.valueOf(i);
				JSONObject jsonObj = new JSONObject(authorJSON);
				JSONArray jsonArray = jsonObj.getJSONObject("authorJson").getJSONArray(s);
				ArrayList<Integer> jsonList = new ArrayList<Integer>();
				jsonArray.forEach(sa -> {
					jsonList.add((Integer) sa);
				});
				authorJsonMap.put(i, jsonList);

			} catch (Exception e) {
				System.out.println("未設定系統權限，系統ID: " + i);
			}
		}
	}

	// 把Map裡面的權限建成字串
	private String encapsulateMapToJson(HashMap<Integer, ArrayList<Integer>> saMap) {

		count = 1;

//		{"authorJson":{"1":[1,0,1,0,0],"2":[1,1,1,1,0]}}

		StringBuilder sb = new StringBuilder();

		sb.append("{\"authorJson\":{\"");

		saMap.forEach((k, v) -> {
			sb.append(k);
			sb.append("\":");
			sb.append(v);
			if (count != SystemNumber) {
				sb.append(",\"");
				count = count + 1;
			} else if (count == SystemNumber) {
				count = 1;
				sb.append("}}");
				return;
			}
		});

		return sb.toString();
	}

}
