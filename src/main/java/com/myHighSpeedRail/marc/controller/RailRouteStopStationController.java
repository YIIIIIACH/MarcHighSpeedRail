package com.myHighSpeedRail.marc.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.RailRouteRepository;
import com.myHighSpeedRail.marc.repository.RailRouteStopStationRepository;
import com.myHighSpeedRail.marc.service.StationService;

@Controller
public class RailRouteStopStationController {
	@Autowired
	private RailRouteStopStationRepository rrssDao;
	@Autowired
	private RailRouteRepository rrDao;
	@Autowired
	private StationService sSer;
	@PostMapping("/insertStopStation")
	public @ResponseBody List<RailRouteStopStation> insertStopStation(
			@RequestParam(value="rid")Integer rid,
			@RequestParam(value="sname")String sname,
			@RequestParam(value="seq")Integer seq){
		Optional<RailRoute> rr = rrDao.findById(rid);
		List<Station> ssl = sSer.findByUsingName(sname);
		if ( rr.isPresent() && ssl !=null && ssl.get(0)!=null) {			
			rrssDao.save( new RailRouteStopStation( rr.get(), seq, ssl.get(0)));
		}else {
			System.err.println("station Name not found or route not found");
		}
		return getAllStopStation();
	}
	
	@GetMapping("/getAllStopStation")
	public @ResponseBody List<RailRouteStopStation> getAllStopStation(){
		Map<Integer, Station> smap = sSer.getAllStationMap();
		List<RailRouteStopStation> rrssl= rrssDao.findAll();
		for(RailRouteStopStation rrss: rrssl) {
			rrss.setStopStation(new Station(14, smap.get(14).getStationName()));
		}
		return rrssl;
	}
	
	@GetMapping("/getStopStationByRouteId")
	public @ResponseBody List<RailRouteStopStation> getStopStationByRouteId(@RequestParam Integer rid){
		return rrssDao.findByRouteId(rid);
	}
}
