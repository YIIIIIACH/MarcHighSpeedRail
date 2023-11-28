package com.myHighSpeedRail.peter.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.handler.EmployeeSystemAuthor;
import com.myHighSpeedRail.peter.handler.SystemAuthorHandler;
import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.EmployeeHistoricalDepartment;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;
import com.myHighSpeedRail.peter.vo.SessionLoginEmployee;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eService;

//	@Autowired
//	private static DepartmentService dService;
//
	@Autowired
	private SystemsService sService;

	@Autowired
	private SystemAuthorHandler sahService;

	// 登入並拿取員工資料包含權限
	@ResponseBody
	@PostMapping("/employee/login")
	public ResponseEntity<?> employeeLogin(@RequestParam("empAccount") String loginAccount,
			@RequestParam("psw") String loginPwd, HttpSession httpSession) {

		Employee result = eService.checklogin(loginAccount, loginPwd);
		System.out.println("result: " + result);

		if (result != null) {
			EmployeeSystemAuthor esa = sahService.getEmpSystemAccess(result);

			SessionLoginEmployee sessionEmp = new SessionLoginEmployee();
			sessionEmp.setEmpId(result.getEmployeeId());
			sessionEmp.setEmpName(result.getEmployeeName());
			sessionEmp.setEsa(esa);
			return ResponseEntity.status(HttpStatus.OK).body("登入成功");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("帳號或密碼錯誤");
	}

//	@PostMapping("/api/users/login")
//	public ResponseEntity<?> loginPost(@RequestBody UserNameAndPwdDto userDTO, HttpSession httpSession) {
//
//		Users result = uService.checkLogin(userDTO.getUsername(), userDTO.getPwd());
//
//		if (result != null) {
//			System.out.println("資料庫帳號密碼正確");
//
//			LoginUserDTO loginUser = new LoginUserDTO(result.getId(), result.getUsername(), Boolean.TRUE);
//
//			httpSession.setAttribute("loginUser", loginUser);
//
//			return new ResponseEntity<String>("登入成功", HttpStatus.OK); // 200
//		} else {
//			System.out.println("帳號密碼錯誤");
//			return new ResponseEntity<String>("帳號密碼錯誤", HttpStatus.UNAUTHORIZED); // 401
//		}
//	}

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
	public ResponseEntity<?> addEmpData(@RequestBody Employee emp) {
		
		if(eService.checkEmpAccountIfExist(emp.getEmployeeAccount())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("員工帳號已存在");
		}
			eService.employeeAdd(emp);
			return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
	}

	@ResponseBody
	@GetMapping("/employee/system-author/{id}")
	public Boolean testEmpSystemAuthor(@PathVariable("id") Integer id) throws JSONException {

		Employee emp = eService.findEmployeeById(id);
		if (id != null) {
			EmployeeSystemAuthor esa = sahService.getEmpSystemAccess(emp);

			Boolean access = esa.rightsOfView(id, "測試系統一");

			return access;
		}

		return false;
	}

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

	@ResponseBody
	@GetMapping("/employee/all")
	public List<Employee> findAllEmployees() {
		return eService.EmployeefindAll();
	}
	
	@ResponseBody
	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
		Employee e = eService.findEmployeeById(emp.getEmployeeId());
		if(e!=null) {		
			Employee udEmp = eService.employeeUpdate(emp);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("更新成功");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("沒有此員工");
	}	
	
	@ResponseBody
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id) {
		Employee e = eService.findEmployeeById(id);
		if(e!=null) {		
			eService.EmployeeDeleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("刪除成功");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("沒有此員工");
	}

//	// 拿取session內的員工資料裝進map中但不包含權限
//	@GetMapping("/employee/map")
//	public ResponseEntity<?> testSessionValue(HttpSession httpSession) {
//
//		System.out.println("檢查登入 controller");
//
//		LoginUserDTO loginUser = (LoginUserDTO) httpSession.getAttribute("loginUser");
//
//		if (loginUser == null) {
//			System.out.println("session attribute 空的");
//			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
//		}
//
//		Map<String, String> responseMap = new HashMap<>();
//
//		responseMap.put("userId", loginUser.getUserId().toString());
//		responseMap.put("userName", loginUser.getUsername());
//
//		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
//	}

}
