package com.myHighSpeedRail.marc.controller;

import java.util.List;

//import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.myHighSpeedRail.marc.ApiResponse.StationApiResponse;
import com.myHighSpeedRail.marc.ApiResponse.StationListApiResponse;
import com.myHighSpeedRail.marc.model.Station;

@Controller
public class SendAPIRequestController {
	private final RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/testAPIRequest")
	public @ResponseBody Station testAPIByGetStation(){
		ResponseEntity<StationApiResponse> resEntity = restTemplate.getForEntity("http://localhost:8080/MarcHighSpeedRail/findById?id=14", StationApiResponse.class);
		if(resEntity.getBody().getStation()!=null) {
			System.out.println("get something");
		}else {
			System.out.println("get nothing");
		}
		return resEntity.getBody().getStation();
	}
	@GetMapping("/testAPIRequestList")
	public @ResponseBody List<Station> testAPIByGetStationList(){
		ResponseEntity<StationListApiResponse> resEntity = restTemplate.getForEntity("http://localhost:8080/MarcHighSpeedRail/findAllStation", StationListApiResponse.class);
		if(resEntity.getBody().getStationList()!=null) {
			System.out.println("get something");
		}else {
			System.out.println("get nothing");
		}
		return resEntity.getBody().getStationList();
	}
}
