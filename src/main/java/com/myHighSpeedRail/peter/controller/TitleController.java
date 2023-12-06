package com.myHighSpeedRail.peter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.peter.model.Title;
import com.myHighSpeedRail.peter.service.TitleService;

@Controller
public class TitleController {

	@Autowired
	private TitleService tService;
	
	@ResponseBody
	@GetMapping("/title/all")
	public List<Title> getAllTitles(){
		return tService.findAll();
	}
	
	@ResponseBody
	@GetMapping("/title/{name}")
	public Title getTitleByName(@PathVariable("name") String name){
		return tService.findByName(name);
	}
}
