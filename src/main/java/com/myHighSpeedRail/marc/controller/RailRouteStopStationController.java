package com.myHighSpeedRail.marc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.service.RailRouteStopStationService;
import com.myHighSpeedRail.marc.service.ScheduleService;

@Controller
public class RailRouteStopStationController {
	@Autowired
	private RailRouteStopStationService rrssServ;
	@Autowired
	private ScheduleService schServ;
	@PostMapping("/insertStopStation")
	public @ResponseBody List<RailRouteStopStation> insertStopStation(
			@RequestParam(value="rid")Integer rid,
			@RequestParam(value="sname")String sname,
			@RequestParam(value="seq")Integer seq,
			@RequestParam(value="cost")Integer cost){
		Optional<RailRoute> rr = rrssServ.rrFindById(rid);
		List<Station> ssl = rrssServ.findByUsingName(sname);
		if ( rr.isPresent() && ssl !=null && ssl.get(0)!=null) {			
			rrssServ.save( new RailRouteStopStation( rr.get(), seq, ssl.get(0),cost));
		}else {
			System.err.println("station Name not found or route not found");
		}
//		return getAllStopStation();
		return new ArrayList<RailRouteStopStation>();
	}
	
	@GetMapping("/getAllStopStation")
	public @ResponseBody List<RailRouteStopStation> getAllStopStation(){
		List<RailRouteStopStation> rrssl= rrssServ.findAll();
		return rrssl;
	}
	
	@GetMapping("/getStopStationByRouteId")
	public @ResponseBody List<RailRouteStopStation> getStopStationByRouteId(@RequestParam Integer rid){
		return rrssServ.findByRouteId(rid);
	}
	
	@GetMapping("/getScheduleStopStationByScheduleId")
	public @ResponseBody List<RailRouteStopStation> getScheduleStopStationByScheduleId(@RequestParam Integer schid){
		// get raiRouteId from scheduledId
		Schedule sch = schServ.findById(schid);
		return getStopStationByRouteId( sch.getRailRoute().getRailRouteId());
	}
}
