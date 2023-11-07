package com.myHighSpeedRail.marc.controller;

import java.util.ArrayList;
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
import com.myHighSpeedRail.marc.dto.RailRouteDto;
import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.repository.RailRouteRepository;
import com.myHighSpeedRail.marc.repository.StationRepository;

@Controller
public class RailRouteController {
	@Autowired
	private RailRouteRepository rrDao;
	@Autowired
	private StationRepository sDao;
	
	@GetMapping(value="/getAllRoute")
	public @ResponseBody List<RailRouteDto> getAll(){
		List<RailRoute> rrl = rrDao.findAll();
		
		List<RailRouteDto> rrdl = new ArrayList<RailRouteDto>();
		for( RailRoute rr : rrl) {
			rrdl.add( new RailRouteDto(rr.getRailRouteId(), rr.getDepartStation(), rr.getDestinateStation(), rr.getStopStationCount()));
		}
		return rrdl;
	}
	
	@PostMapping(value="/insertRoute")
	public @ResponseBody List<RailRouteDto> insertRoute(
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
