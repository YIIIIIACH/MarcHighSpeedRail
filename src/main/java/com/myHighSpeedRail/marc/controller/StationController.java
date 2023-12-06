package com.myHighSpeedRail.marc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.marc.ApiResponse.StationApiResponse;
import com.myHighSpeedRail.marc.ApiResponse.StationListApiResponse;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.service.StationService;

@RestController
public class StationController {
	
	@Autowired
	private StationService sSer;
	@CrossOrigin
	@GetMapping(value="/getAllStation")
	public List<Station> getAllStation() {
		return sSer.getAllStation();
	}
	@PostMapping(value="/getAllStation")
	public List<Station> getPostAllStation() {
		return sSer.getAllStation();
	}
	
	@PostMapping(value="/insertStation")
	public List<Station> insert(@RequestParam("name")String name){
		sSer.save(new Station(name));
		return sSer.getAllStation();
	}
	
	@GetMapping(value="/findByName")
	public List<Station> findByUsingName(@RequestParam("name")String name){
		return sSer.findByUsingName(name);
	}
	
	public Optional<Station> findById(Integer id){
		return sSer.findById(id);
	}
	@GetMapping(value="/findStationById")
	public ResponseEntity<StationApiResponse> findByIdTest(Integer id) {
		Station station = sSer.findById(id).get();
		return new ResponseEntity<StationApiResponse>(new StationApiResponse(station), HttpStatus.ACCEPTED);
//		return sSer.findById(id).get();
	}
	@GetMapping(value="/findAllStation")
	public ResponseEntity<StationListApiResponse> findByNameTest() {
		List<Station> stationList = sSer.getAllStation();
		return new ResponseEntity<StationListApiResponse>(new StationListApiResponse(stationList), HttpStatus.ACCEPTED);
//		return sSer.findById(id).get();
	}
}
