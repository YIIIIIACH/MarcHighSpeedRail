package com.myHighSpeedRail.marc.controller;

import java.util.Enumeration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FirstController {
	
	@GetMapping("/")
	public String home() {
		return "test.html";
	}
	
	@GetMapping("/getClientIP")
	public @ResponseBody ResponseEntity<String> getClientIP(HttpServletRequest req){
//		Enumeration<String> hNames = req.getHeaderNames();
////		req.getHeader("connection");
//		do {
//			System.out.println(req.getHeader(hNames.nextElement()));
//		}while( hNames.hasMoreElements());
		System.out.println( req.getHeader("host"));
		return new ResponseEntity<String>("get header names",HttpStatus.OK);
	}

}