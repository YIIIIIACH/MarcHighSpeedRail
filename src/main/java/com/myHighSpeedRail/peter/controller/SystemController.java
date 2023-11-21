package com.myHighSpeedRail.peter.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.service.SystemsService;

@Controller
public class SystemController {

	@Autowired
	private SystemsService sService;
	
	@PostMapping("/system/add")
	public ResponseEntity<String> addSystem(@RequestBody Systems s) {

		System.out.println(s.getSystemName());
		sService.addSystem(s);

		return ResponseEntity.status(HttpStatus.CREATED).body("新增系統成功");
	}
	
	@DeleteMapping("/system/{id}")
	public ResponseEntity<String> deleteSystemById(@PathVariable("id") Integer id){
		System.out.println(id);
		
		sService.deleteById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("刪除系統成功");
	}
	
	@PutMapping("/system/edit")
	public ResponseEntity<String> editPut(@RequestBody Systems s) {
		sService.addSystem(s);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("修改系統成功");
	}
	
	@ResponseBody
	@GetMapping("/system/{id}")
	public Systems findSystemById(@PathVariable("id") Integer id) {

		return sService.findSystemById(id);
	}
	
	
	
	
	
	
}
