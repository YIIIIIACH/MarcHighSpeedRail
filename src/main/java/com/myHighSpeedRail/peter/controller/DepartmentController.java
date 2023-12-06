package com.myHighSpeedRail.peter.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.dto.DepartmentDTO;
import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService dService;
	
	@ResponseBody
	@GetMapping("/department/all")
	public List<DepartmentDTO> getDepartmentDTO() {
		List<Department> all = dService.getAllDepartments();
		List<DepartmentDTO> allDTO = new LinkedList<DepartmentDTO>();
		
		for(Department dept: all) {
			DepartmentDTO dDTO = new DepartmentDTO();
			dDTO.setDepartmentId(dept.getDepartmentId());
			dDTO.setDepartmentName(dept.getDepartmentName());
			allDTO.add(dDTO);
		}
		return allDTO;
	}
	
	@ResponseBody
	@GetMapping("/department/{name}")
	public DepartmentDTO getDepartmentDTOByName(@PathVariable("name") String name) {
		Department dept = dService.getDepartmentByName(name);
		DepartmentDTO dDTO = new DepartmentDTO();
		dDTO.setDepartmentId(dept.getDepartmentId());
		dDTO.setDepartmentName(dept.getDepartmentName());
		return dDTO;
	}
}
