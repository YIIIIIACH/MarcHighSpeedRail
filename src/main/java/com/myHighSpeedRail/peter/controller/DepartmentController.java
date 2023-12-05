package com.myHighSpeedRail.peter.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService dService;
	
	@ResponseBody
	@GetMapping("/department/all")
	public List<Department> getDepartments() {
		return dService.getAllDepartments();
	}
	
	@ResponseBody
	@GetMapping("/department/{name}")
	public Department getDepartmentByDepartment(@PathVariable("name") String name) {
		return dService.getDepartmentByName(name);
	}
}
