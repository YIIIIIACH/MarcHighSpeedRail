package com.myHighSpeedRail.peter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.myHighSpeedRail.peter.dto.AddAuthorDTO;
import com.myHighSpeedRail.peter.dto.AddSystemAuthorDTO;
import com.myHighSpeedRail.peter.dto.EmployeeSystemAuthorDTO;
import com.myHighSpeedRail.peter.dto.SessionLoginEmployeeDTO;
import com.myHighSpeedRail.peter.dto.SystemAuthorMemberDTO;
import com.myHighSpeedRail.peter.handler.EmployeeSystemAuthor;
import com.myHighSpeedRail.peter.handler.SystemAuthorHandler;
import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.service.DepartmentService;
import com.myHighSpeedRail.peter.service.EmployeeService;
import com.myHighSpeedRail.peter.service.SystemsService;
import com.myHighSpeedRail.peter.vo.SessionLoginEmployee;

import jakarta.servlet.http.HttpSession;

@Controller
public class SystemAuthorController {

	@Autowired
	private SystemsService sService;

	@Autowired
	private EmployeeService eService;

	@Autowired
	private DepartmentService dService;

	@Autowired
	private SystemAuthorHandler saHandler;

	private HashMap<Integer, ArrayList> hashMap;

	@GetMapping("/system-author/add")
	public ResponseEntity<String> addSystemAuthor(@RequestBody SystemAuthor sa) {

		return ResponseEntity.status(HttpStatus.CREATED).body("測試成功");
	}

	@ResponseBody
	@GetMapping("/system-author/all")
	public List<EmployeeSystemAuthorDTO> getAllSystemAuthorDTOs() {

		HashMap<Integer, EmployeeSystemAuthor> esa = saHandler.getAllEmployeeSystemAccess();
		HashMap<Integer, EmployeeSystemAuthor> dsa = saHandler.getAllDepartmentSystemAccess();

		List<EmployeeSystemAuthorDTO> saDtoList = new LinkedList<EmployeeSystemAuthorDTO>();

		esa.forEach((k, v) -> {
			EmployeeSystemAuthorDTO dto = new EmployeeSystemAuthorDTO();
			dto.setEmployeeId(k);
			dto.setSystemAuthor(v);
			saDtoList.add(dto);
		});

		dsa.forEach((k, v) -> {
			EmployeeSystemAuthorDTO dto = new EmployeeSystemAuthorDTO();
			dto.setDepartmentId(k);
			dto.setSystemAuthor(v);
			saDtoList.add(dto);
		});

		return saDtoList;
	}

	@ResponseBody
	@GetMapping("/system-author-member/all")
	public List<SystemAuthorMemberDTO> getAllSystemAuthorMemberDTOs() {

		HashMap<Integer, EmployeeSystemAuthor> esa = saHandler.getAllEmployeeSystemAccess();
		HashMap<Integer, EmployeeSystemAuthor> dsa = saHandler.getAllDepartmentSystemAccess();

		List<SystemAuthorMemberDTO> samDtoList = new LinkedList<SystemAuthorMemberDTO>();

		esa.forEach((k, v) -> {
			SystemAuthorMemberDTO dto = new SystemAuthorMemberDTO();
			dto.setEmployeeId(k);
			Employee e = eService.findEmployeeById(k);
			dto.setEmployeeName(e.getEmployeeName());
			samDtoList.add(dto);
		});

		dsa.forEach((k, v) -> {
			SystemAuthorMemberDTO dto = new SystemAuthorMemberDTO();
			dto.setDepartmentId(k);
			Department d = dService.findDepartmentById(k);
			dto.setDepartmentName(d.getDepartmentName());
			samDtoList.add(dto);
		});

		return samDtoList;
	}

	@ResponseBody
	@GetMapping("/system-author/emp/{id}")
	public List<EmployeeSystemAuthorDTO> getSystemAuthorDTOByEmpId(@PathVariable("id") Integer id) {

		HashMap<Integer, EmployeeSystemAuthor> esa = saHandler.getSystemAccessByEmployeeId(id);

		List<EmployeeSystemAuthorDTO> saDtoList = new LinkedList<EmployeeSystemAuthorDTO>();

		EmployeeSystemAuthorDTO dto = new EmployeeSystemAuthorDTO();

		esa.forEach((k, v) -> {

			dto.setEmployeeId(k);
			dto.setSystemAuthor(v);
			saDtoList.add(dto);
		});

		return saDtoList;
	}

	@ResponseBody
	@PutMapping("/system-author/emp/{id}")
	public void updateSystemAuthorByEmpId(@PathVariable("id") Integer id, @RequestBody EmployeeSystemAuthorDTO esaDTO) {

		saHandler.updateSystemAccessByEmployeeId(id, esaDTO.getSystemAuthor());
	}

	@ResponseBody
	@GetMapping("/system-author/dept/{id}")
	public List<EmployeeSystemAuthorDTO> getSystemAuthorDTOByDeptId(@PathVariable("id") Integer id) {

		HashMap<Integer, EmployeeSystemAuthor> esa = saHandler.getSystemAccessByDeaprtmentId(id);

		List<EmployeeSystemAuthorDTO> saDtoList = new LinkedList<EmployeeSystemAuthorDTO>();

		EmployeeSystemAuthorDTO dto = new EmployeeSystemAuthorDTO();

		esa.forEach((k, v) -> {

			dto.setEmployeeId(k);
			dto.setSystemAuthor(v);
			saDtoList.add(dto);
		});

		return saDtoList;
	}

	@ResponseBody
	@PutMapping("/system-author/dept/{id}")
	public void updateSystemAuthorByDeptId(@PathVariable("id") Integer id,
			@RequestBody EmployeeSystemAuthorDTO esaDTO) {

		saHandler.updateSystemAccessByDepartmentId(id, esaDTO.getSystemAuthor());
	}

	@ResponseBody
	@PostMapping("/system-author/emp/add")
	public void addEmployeeSystemAuthor(@RequestBody AddAuthorDTO aaDTO) {

		String info = aaDTO.getInfo();
		String[] parts = info.split(":");

		Integer empId = Integer.valueOf(parts[0]);

		List<AddSystemAuthorDTO> asaLisst = aaDTO.getAsaLisst();

		EmployeeSystemAuthor esa = new EmployeeSystemAuthor();

		HashMap<Integer, ArrayList<Integer>> authorJson = new HashMap<Integer, ArrayList<Integer>>();

		asaLisst.forEach(asa -> {
			asa.getPermissions();
			asa.getSystemId();
			authorJson.put(asa.getSystemId(), asa.getPermissions());
		});
		esa.setAuthorJson(authorJson);

		saHandler.addEmployeeSystemAuthor(empId, esa);

	}

	@ResponseBody
	@PostMapping("/system-author/dept/add")
	public void addDepartmentSystemAuthor(@RequestBody AddAuthorDTO aaDTO) {

		String info = aaDTO.getInfo();
		String[] parts = info.split(":");

		Integer deptId = Integer.valueOf(parts[0]);

		List<AddSystemAuthorDTO> asaLisst = aaDTO.getAsaLisst();

		EmployeeSystemAuthor esa = new EmployeeSystemAuthor();

		HashMap<Integer, ArrayList<Integer>> authorJson = new HashMap<Integer, ArrayList<Integer>>();

		asaLisst.forEach(asa -> {
			asa.getPermissions();
			asa.getSystemId();
			authorJson.put(asa.getSystemId(), asa.getPermissions());
		});
		esa.setAuthorJson(authorJson);

		saHandler.addDepartmentSystemAuthor(deptId, esa);
	}

	@ResponseBody
	@DeleteMapping("/system-author/delete/emp/{id}")
	public void deleteByEmployeeId(@PathVariable("id") Integer id) {
		sService.deleteEmployeeSystemAuthor(id);
	}

	@ResponseBody
	@DeleteMapping("/system-author/delete/dept/{id}")
	public void deleteByDepartmentId(@PathVariable("id") Integer id) {
		sService.deleteDepartmentSystemAuthor(id);
	}

	@ResponseBody
	@GetMapping("/view")
	public ResponseEntity<?> auditViewAuthor(@RequestParam("name") String name, HttpSession httpSession) {

		SessionLoginEmployee emp = (SessionLoginEmployee) httpSession.getAttribute("loginEmployee");

		if (emp == null) {
			System.out.println("session attribute 空的");
			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
		}

		Boolean rightsOfView = emp.getEsa().rightsOfView(emp.getEmpId(), name);

		if (rightsOfView == false) {
			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
		} else if (rightsOfView == true) {
			return ResponseEntity.status(HttpStatus.OK).body("OK");
		}

		return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
	}

}
