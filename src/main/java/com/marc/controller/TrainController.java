package com.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marc.model.Train;
import com.marc.model.TrainRepository;

@RestController
public class TrainController {
	@Autowired
	private TrainRepository tDao;
	
	@PostMapping(value="/insertTrain")
	public List<Train> insertTrain(@RequestBody Train t){
		tDao.save(t);
		return getAllTrain();
	}
	
	@GetMapping(value="/getAllTrain")
	public List<Train> getAllTrain(){
		return tDao.findAll();
	}
	
	@GetMapping(value="/getTrainById")
	public Train getTrainById(@RequestParam(name="tid")Integer tid) {
		return tDao.findById(tid).get();
	}
}
