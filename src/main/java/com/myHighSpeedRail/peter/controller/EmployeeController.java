package com.myHighSpeedRail.peter.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.dto.EmployeeAccountAndPasswordDTO;
import com.myHighSpeedRail.peter.dto.EmployeeDetailDTO;
import com.myHighSpeedRail.peter.dto.SessionLoginEmployeeDTO;
import com.myHighSpeedRail.peter.handler.EmployeeSystemAuthor;
import com.myHighSpeedRail.peter.handler.SystemAuthorHandler;
import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.EmergencyContactPerson;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.EmployeeEducationalQualifications;
import com.myHighSpeedRail.peter.model.EmployeeHistoricalDepartment;
import com.myHighSpeedRail.peter.model.EmployeeTitle;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;
import com.myHighSpeedRail.peter.vo.SessionLoginEmployee;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private PasswordEncoder pwdEncoder;

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
			@RequestParam("psw") String loginPwd, HttpSession httpSession, HttpServletResponse res,HttpServletRequest req) {

		SessionLoginEmployee emp = (SessionLoginEmployee) httpSession.getAttribute("loginEmployee");
		
		String id = httpSession.getId();
		System.out.println(id);
		
		String currOrigin = ((HttpServletRequest)req).getHeader("Origin");
		
		Cookie c = new Cookie("JSESSIONID",id);
		c.setMaxAge(3600);
		c.setSecure(false);
		c.setHttpOnly(true);
		res.addCookie(c);
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", currOrigin==null? "true":currOrigin);
		res.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type,version-info, X-Request-With");
		res.setContentType("application/json;charset=UTF-8");

		if (emp != null) {
			httpSession.removeAttribute("loginEmployee");
		}

		Employee result = eService.checklogin(loginAccount, loginPwd);

		if (result != null) {
			EmployeeSystemAuthor esa = sahService.getEmpSystemAuthor(result);

			SessionLoginEmployee sessionEmp = new SessionLoginEmployee();
			sessionEmp.setEmpId(result.getEmployeeId());
			sessionEmp.setEmpName(result.getEmployeeName());
			sessionEmp.setDaptId(result.getEmployeeHistoricalDepartment().get(0).getDepartment().getDepartmentId());
			sessionEmp.setDeptName(result.getEmployeeHistoricalDepartment().get(0).getDepartment().getDepartmentName());
			sessionEmp.setEsa(esa);
			httpSession.setAttribute("loginEmployee", sessionEmp);
			return ResponseEntity.status(HttpStatus.OK).body("登入成功");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("帳號或密碼錯誤");
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
	public ResponseEntity<?> addEmpData(@RequestBody Employee emp) {

		if (eService.checkEmpAccountIfExist(emp.getEmployeeAccount())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("員工帳號已存在");
		}
		List<EmergencyContactPerson> ecList = emp.getEmergencyContactPerson();
		ecList.forEach(emergencyContactPerson -> {
			emergencyContactPerson.setEmployee(emp);
		});

		List<EmployeeEducationalQualifications> eqList = emp.getEmployeeEducationalQualifications();
		eqList.forEach(employeeEducationalQualifications -> {
			employeeEducationalQualifications.setEmployee(emp);
		});

		List<EmployeeTitle> tList = emp.getEmployeeTitle();
		eqList.forEach(employeeTitle -> {
			employeeTitle.setEmployee(emp);
		});

		eService.employeeAdd(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
	}

	@ResponseBody
	@GetMapping("/employee/system-author/{id}")
	public Boolean testEmpSystemAuthor(@PathVariable("id") Integer id) throws JSONException {

		Employee emp = eService.findEmployeeById(id);
		if (id != null) {
			EmployeeSystemAuthor esa = sahService.getEmpSystemAuthor(emp);

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
	@GetMapping("/employee/session-all")
	public List<SessionLoginEmployeeDTO> findAllInSessionEmployeeDTO() {

		List<Employee> empList = eService.EmployeefindAll();
		LinkedList<SessionLoginEmployeeDTO> sessionEmpList = new LinkedList<SessionLoginEmployeeDTO>();

		for (Employee e : empList) {
			SessionLoginEmployeeDTO sleDTO = new SessionLoginEmployeeDTO();
			sleDTO.setEmpId(e.getEmployeeId());
			sleDTO.setEmpName(e.getEmployeeName());
			sessionEmpList.add(sleDTO);
		}
		return sessionEmpList;
	}

	@ResponseBody
	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
		Employee e = eService.findEmployeeById(emp.getEmployeeId());
		if (e != null) {
			Employee udEmp = eService.employeeUpdate(emp);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("更新成功");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("沒有此員工");
	}

	@ResponseBody
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Integer id) {
		Employee e = eService.findEmployeeById(id);
		if (e != null) {
			eService.EmployeeDeleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("刪除成功");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("沒有此員工");
	}

	// 拿取session內的員工資料裝進map中但不包含權限
	@GetMapping("/employee/dto")
	public ResponseEntity<?> getLoginInfo(HttpSession httpSession) {

		SessionLoginEmployee emp = (SessionLoginEmployee) httpSession.getAttribute("loginEmployee");

		if (emp == null) {
			System.out.println("session attribute 空的");
			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
		}

		SessionLoginEmployeeDTO empDTO = new SessionLoginEmployeeDTO();
		empDTO.setEmpId(emp.getEmpId());
		empDTO.setEmpName(emp.getEmpName());

		return ResponseEntity.status(HttpStatus.OK).body(empDTO);
	}

	@ResponseBody
	@GetMapping("/employee/detail/{id}")
	public EmployeeDetailDTO findEmployeeDetailById(@PathVariable("id") Integer id) {

		Employee e = eService.findEmployeeById(id);
		EmployeeDetailDTO edDTO = new EmployeeDetailDTO();

		edDTO.setEmergencyContactPerson(e.getEmergencyContactPerson());
		edDTO.setEmpId(e.getEmployeeId());
		edDTO.setEmployeeArrivalDate(e.getEmployeeArrivalDate());
		edDTO.setEmployeeBasicSalary(e.getEmployeeBasicSalary());
		edDTO.setEmployeeBirth(e.getEmployeeBirth());
		edDTO.setEmployeeContactAddress(e.getEmployeeContactAddress());
		edDTO.setEmployeeContactNumber(e.getEmployeeContactNumber());
		edDTO.setEmployeeEducationalQualifications(e.getEmployeeEducationalQualifications());
		edDTO.setEmployeeEmail(e.getEmployeeEmail());
		edDTO.setEmployeeGender(e.getEmployeeGender());
		edDTO.setEmployeeHistoricalBaseSalary(e.getEmployeeHistoricalBaseSalary());
		List<EmployeeHistoricalDepartment> eList = e.getEmployeeHistoricalDepartment();
		eList.forEach(employeeHistoricalDepartment -> {
			employeeHistoricalDepartment.getDepartment().setSystemAuthor(null);
		});

		edDTO.setEmployeeHistoricalDepartment(eList);

		edDTO.setEmployeeIdNumber(e.getEmployeeIdNumber());
		edDTO.setEmployeePhoneNumber(e.getEmployeePhoneNumber());
		edDTO.setEmployeePhoto(e.getEmployeePhoto());
		edDTO.setEmployeeResidenceAddress(e.getEmployeeResidenceAddress());
		edDTO.setEmployeeSalaryKind(e.getEmployeeSalaryKind());
		edDTO.setEmployeeTitle(e.getEmployeeTitle());
		edDTO.setEmployeeWorkExperience(e.getEmployeeWorkExperience());
		edDTO.setEmpName(e.getEmployeeName());
		return edDTO;
	}

	@ResponseBody
	@PutMapping("/employee/detail")
	public ResponseEntity<String> updateEmployeeDetail(@RequestBody EmployeeDetailDTO edDTO) {

		Employee oldE = eService.findEmployeeById(edDTO.getEmpId());

		Employee e = new Employee();

		e.setEmployeeAccount(oldE.getEmployeeAccount());
		e.setEmployeeLeaveByEmployee(oldE.getEmployeeLeaveByEmployee());
		e.setEmployeeLeaveByManager(oldE.getEmployeeLeaveByManager());
		e.setEmployeePassword(oldE.getEmployeePassword());
		e.setEmployeeWorkOvertimeByEmployee(oldE.getEmployeeWorkOvertimeByEmployee());
		e.setEmployeeWorkOvertimeByManager(oldE.getEmployeeWorkOvertimeByManager());
		e.setSystemAuthor(oldE.getSystemAuthor());

		e.setEmployeeId(edDTO.getEmpId());
		e.setEmployeeArrivalDate(edDTO.getEmployeeArrivalDate());
		e.setEmployeeBasicSalary(edDTO.getEmployeeBasicSalary());
		e.setEmployeeBirth(edDTO.getEmployeeBirth());
		e.setEmployeeContactAddress(edDTO.getEmployeeContactAddress());
		e.setEmployeeContactNumber(edDTO.getEmployeeContactNumber());
		e.setEmployeeEmail(edDTO.getEmployeeEmail());
		e.setEmployeeGender(edDTO.getEmployeeGender());
		e.setEmployeeHistoricalBaseSalary(edDTO.getEmployeeHistoricalBaseSalary());

		List<EmployeeHistoricalDepartment> edList = edDTO.getEmployeeHistoricalDepartment();
		edList.forEach(employeeHistoricalDepartment -> {
			employeeHistoricalDepartment.setEmployee(e);
		});
		e.setEmployeeHistoricalDepartment(edList);

		List<EmergencyContactPerson> ecList = edDTO.getEmergencyContactPerson();
		ecList.forEach(emergencyContactPerson -> {
			emergencyContactPerson.setEmployee(e);
		});
		e.setEmergencyContactPerson(ecList);

		List<EmployeeEducationalQualifications> eqList = edDTO.getEmployeeEducationalQualifications();
		eqList.forEach(employeeEducationalQualifications -> {
			employeeEducationalQualifications.setEmployee(e);
		});
		e.setEmployeeEducationalQualifications(eqList);

		List<EmployeeTitle> tList = edDTO.getEmployeeTitle();
		tList.forEach(employeeTitle -> {
			employeeTitle.setEmployee(e);
		});
		e.setEmployeeTitle(tList);


		e.setEmployeeIdNumber(edDTO.getEmployeeIdNumber());
		e.setEmployeePhoneNumber(edDTO.getEmployeePhoneNumber());
		e.setEmployeePhoto(edDTO.getEmployeePhoto());
		e.setEmployeeResidenceAddress(edDTO.getEmployeeResidenceAddress());
		e.setEmployeeSalaryKind(edDTO.getEmployeeSalaryKind());
		e.setEmployeeWorkExperience(edDTO.getEmployeeWorkExperience());
		e.setEmployeeName(edDTO.getEmpName());
		eService.employeeUpdateWithoutEncodePassword(e);

		return ResponseEntity.status(HttpStatus.OK).body("更新資料成功");
	}

	@PutMapping("/employee/logout")
	public ResponseEntity<?> employeeLogout(HttpSession httpSession) {

		httpSession.removeAttribute("loginEmployee");

		return ResponseEntity.status(HttpStatus.OK).body("已登出");
	}

	@ResponseBody
	@GetMapping("/employee/account")
	public List<EmployeeAccountAndPasswordDTO> getAllEmployeeAccount() {

		List<Employee> emps = eService.EmployeefindAll();
		List<EmployeeAccountAndPasswordDTO> eapList = new LinkedList<EmployeeAccountAndPasswordDTO>();

		for (Employee emp : emps) {
			EmployeeAccountAndPasswordDTO eapDTO = new EmployeeAccountAndPasswordDTO();
			eapDTO.setEmpId(emp.getEmployeeId());
			eapDTO.setEmpName(emp.getEmployeeName());
			eapDTO.setAccount(emp.getEmployeeAccount());
			eapList.add(eapDTO);
		}

		return eapList;
	}

	@ResponseBody
	@PutMapping("/employee/account/update")
	public void updateEmployeePassword(@RequestBody EmployeeAccountAndPasswordDTO eapDTO) {
		Employee e = new Employee();
		e.setEmployeePassword(eapDTO.getPassword());
		e.setEmployeeId(eapDTO.getEmpId());
		eService.updatePassword(e);
	}

}
