package com.myHighSpeedRail.peter.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.dto.SessionLoginEmployeeDTO;
import com.myHighSpeedRail.peter.handler.SystemAuthorHandler;
import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.EmployeeHistoricalDepartment;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.service.DepartmentService;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eService;

//	@Autowired
//	private static DepartmentService dService;
//
//	@Autowired
//	private static SystemsService sService;

	@Autowired
	private SystemAuthorHandler sahService;

	@PostMapping("/employee/login")
	@ResponseBody
	public ResponseEntity<SessionLoginEmployeeDTO> postLogin(@RequestParam("empAccount") String loginAccount,
			@RequestParam("psw") String loginPwd, HttpSession httpSession) {

		Employee result = eService.checklogin(loginAccount, loginPwd);
		System.out.println("result: " + result);

		if (result != null) {
			SessionLoginEmployeeDTO empDTO = new SessionLoginEmployeeDTO();
			empDTO.setEmpId(result.getEmployeeId());
			empDTO.setEmpName(result.getEmployeeName());
			return ResponseEntity.status(HttpStatus.OK).body(empDTO);
		}

		return ResponseEntity.notFound().build();
	}

	@ResponseBody
	@GetMapping("/test/map")
	public void testMap() {
		HashMap<Integer, String[]> map = new HashMap<Integer, String[]>();
		String[] a = { "true", "false", "false" };
		map.put(1, a);
		String[] b = map.get(1);
		System.out.println("testmap: " + map.get(1));
		System.out.println("a: " + a[0]);
		System.out.println("b: " + b[0]);
	}

	@ResponseBody
	@PostMapping("/employee/add")
	public void addEmpData(@RequestBody Employee emp) {
		eService.employeeAdd(emp);
	}

	@ResponseBody
	@GetMapping("/employee/system-author/{id}")
	public Boolean testEmpSystemAuthor(@PathVariable("id") Integer id) throws JSONException {
//		Employee emp = eService.findEmployeeById(id);
//		if (emp != null) {
//			Boolean access = sahService.rightsOfView(emp, "測試系統一");
//			return access;
//		}
		if (id != null) {
			Boolean access = sahService.rightsOfView(id, "測試系統一");
			return access;
		}
//		SystemAuthorHandler.test();
		return false;
	}

//	public SystemAuthor findEmpSystemAuthor(Integer id) {
//		SystemAuthor authorList = eService.getEmployeeSystemAuthor(id);
//		return authorList;
//	}

	@ResponseBody
	@PostMapping("/employee/department/add")
	public void addEmpDepartment(@RequestBody EmployeeHistoricalDepartment ehd) {
		eService.EmployeeAddDepartment(ehd);
	}

	@ResponseBody
	@GetMapping("/employee/department/{id}")
	public Department getEmpLatestDepartment(@PathVariable("id") Integer empId) {
		Department dept = eService.findLatestDepartment(empId);
		return dept;
	}

//	@PostMapping("/users/login")
//	public String postLogin(
//			@RequestParam("uname") String loginName, 
//			@RequestParam("psw") String loginPwd,
//			HttpSession httpSession,
//            Model model) {
//		
//		Users result = uService.checklogin(loginName, loginPwd);
//		
//		if(result != null) {
//			SessionLoginUserDTO userDTO = new SessionLoginUserDTO();
//			userDTO.setId(result.getId());
//			userDTO.setUsername(result.getUsername());
//			
//			httpSession.setAttribute("loginUser", userDTO);
//		}else {
//			model.addAttribute("loginFail", "帳號密碼錯誤");
//		}
//		
//		
//		return "users/loginPage";
//	}

}
