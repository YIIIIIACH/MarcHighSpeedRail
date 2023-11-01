package com.marc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marc.model.Station;
import com.marc.model.StationRepository;

@RestController
public class StationController {
	@Autowired
	private StationRepository sDao;
	@GetMapping(value="/getAllStation")
	public List<Station> getAllStation() {
		return sDao.findAll();
	}
	
	@PostMapping(value="/insertStation")
	public List<Station> insert(@RequestParam("name")String name){
		sDao.save(new Station(name));
		return sDao.findAll();
	}
}
