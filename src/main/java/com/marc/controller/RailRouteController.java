package com.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marc.model.RailRoute;
import com.marc.repository.RailRouteRepository;
import com.marc.repository.StationRepository;

@Controller
public class RailRouteController {
	@Autowired
	private RailRouteRepository rrDao;
	@Autowired
	private StationRepository sDao;
	
	@GetMapping(value="/getAllRoute")
	public @ResponseBody List<RailRoute> getAll(){
		List<RailRoute> rrl = rrDao.findAll();
		rrDao.flush();
		return rrl;
	}
	
	@PostMapping(value="/insertRoute")
	public @ResponseBody List<RailRoute> insertRoute(
			@RequestParam("depId") Integer depStationId
			,@RequestParam("desId") Integer desStationid
			,@RequestParam("ssCnt")Integer stopStationCnt
			){
		try {
			rrDao.save(new RailRoute( sDao.findById(depStationId).get(), sDao.findById(desStationid).get(), stopStationCnt));
		}catch(Exception e){
			System.err.println("[Error] occur in RailRouteController or Failed of Find Station by Id");
		}
		return getAll();
	}
}
