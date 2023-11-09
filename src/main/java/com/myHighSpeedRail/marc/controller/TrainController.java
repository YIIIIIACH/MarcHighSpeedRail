package com.myHighSpeedRail.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.marc.model.Train;
import com.myHighSpeedRail.marc.service.TrainService;

@RestController
public class TrainController {
	@Autowired
	private TrainService tServ;
	
	@PostMapping(value="/insertTrain")
	public List<Train> insertTrain(@RequestBody Train t){
		tServ.save(t);
		return getAllTrain();
	}
	
	@GetMapping(value="/getAllTrain")
	public List<Train> getAllTrain(){
		return tServ.findAll();
	}
	
	@GetMapping(value="/getTrainById")
	public Train getTrainById(@RequestParam(name="tid")Integer tid) {
		return tServ.findById(tid);
	}
}
