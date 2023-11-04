package com.marc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marc.model.RailRoute;
import com.marc.model.RailRouteRepository;
import com.marc.model.RailRouteStopStation;
import com.marc.model.RailRouteStopStationRepository;
import com.marc.model.Station;
import com.marc.model.StationRepository;

@Controller
public class RailRouteStopStationController {
	@Autowired
	private RailRouteStopStationRepository rrssDao;
	@Autowired
	private RailRouteRepository rrDao;
	@Autowired
	private StationRepository sDao;
	@PostMapping("/insertStopStation")
	public @ResponseBody List<RailRouteStopStation> insertStopStation(
			@RequestParam(value="rid")Integer rid,
			@RequestParam(value="sname")String sname,
			@RequestParam(value="seq")Integer seq){
		Optional<RailRoute> rr = rrDao.findById(rid);
		List<Station> ssl = sDao.findByName(sname);
		if ( rr.isPresent() && ssl !=null && ssl.get(0)!=null) {			
			rrssDao.save( new RailRouteStopStation( rr.get(), seq, ssl.get(0)));
		}else {
			System.err.println("station Name not found or route not found");
		}
		return getAllStopStation();
	}
	
	@GetMapping("/getAllStopStation")
	public @ResponseBody List<RailRouteStopStation> getAllStopStation(){
		List<RailRouteStopStation> rrssl= rrssDao.findAll();
		rrDao.flush();
		return rrssl;
	}
	
	@GetMapping("/getStopStationByRouteId")
	public @ResponseBody List<RailRouteStopStation> getStopStationByRouteId(@RequestParam Integer rid){
		return rrssDao.findByRouteId(rid);
	}
}
