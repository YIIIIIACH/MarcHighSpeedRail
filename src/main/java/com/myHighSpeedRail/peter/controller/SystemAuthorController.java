package com.myHighSpeedRail.peter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.service.SystemsService;

@Controller
public class SystemAuthorController {

	@Autowired
	private SystemsService sService;

	@GetMapping("/system_author/add")
	public ResponseEntity<String> addSystemAuthor(@RequestBody SystemAuthor sa) {
		
		
		System.out.println(sa.getAuthorJson());
		System.out.println("employee: " + sa.getEmployee());
		System.out.println("dapartment: " + sa.getDepartment());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("測試成功");
	}
	
	

}
