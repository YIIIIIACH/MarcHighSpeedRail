package com.myHighSpeedRail.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.CreateRailRouteDto;
import com.myHighSpeedRail.marc.dto.RailRouteDto;
import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.service.RailRouteSegmentService;
import com.myHighSpeedRail.marc.service.RailRouteService;
import com.myHighSpeedRail.marc.service.RailRouteStopStationService;
import com.myHighSpeedRail.marc.service.StationService;

@Controller
public class RailRouteController {
	@Autowired
	private RailRouteService rrsss;
	@Autowired
	private RailRouteStopStationService rrssServ;
	@Autowired
	private RailRouteSegmentService rrsServ;
	@GetMapping(value="/getAllRoute")
	public @ResponseBody List<RailRouteDto> getAll(){
		List<RailRouteDto> rrl = rrsss.getAll();
		
		return rrl;
	}
	
	@PostMapping(value="/insertRoute")
	public @ResponseBody List<RailRouteDto> insertRoute(
			@RequestParam("depId") Integer depStationId
			,@RequestParam("desId") Integer desStationid
			,@RequestParam("ssCnt")Integer stopStationCnt
			){
		
		return rrsss.insertRoute(depStationId, desStationid, stopStationCnt);
	}
	
	@GetMapping(value="/getAllRouteByHQL")
	public @ResponseBody List<RailRoute> getAllByHQL(){
		return rrsss.getAllByHQL();
	}
	
	@PostMapping("/backEndInsertRailRoute")
	public @ResponseBody ResponseEntity<String> insertRailRoute(@RequestBody CreateRailRouteDto dto){
		// create one railRoute
//		rrsss.insertRoute(dto.depStationId, dto.desStationId, dto.stationCnt);
		// rail route id is temporary
		List<RailRouteStopStation> rrssList = rrssServ.inserRailRouteStopStations( Integer.valueOf(1), dto.stIdList, dto.costTimeList);
		if(rrssList==null) {
			return new ResponseEntity<String>("constructing , but failed" , HttpStatus.BAD_REQUEST);
		}
		rrsServ.createAllSegment(  dto.toNextStationPriceList,rrssList);
		return new ResponseEntity<String>("constructing " , HttpStatus.OK);
	}
}
