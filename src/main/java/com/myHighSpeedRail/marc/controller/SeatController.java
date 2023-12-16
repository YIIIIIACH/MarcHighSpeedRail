package com.myHighSpeedRail.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.Seat;
import com.myHighSpeedRail.marc.service.SeatService;

@Controller
public class SeatController {
	@Autowired
	private SeatService seatServ;
	
	@GetMapping("/getAllSeat")
	public @ResponseBody List<Seat> getAll(){
		return seatServ.getAll();
	}
	
	@PostMapping("/updateAllSeat")
	public @ResponseBody ResponseEntity<String>updateSeat(){
		try {
			seatServ.setupSeats();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("update failed",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("update success",HttpStatus.OK);
	}
	
	@GetMapping("/getTrainSeatMaxRange/{trainId}")
	public ResponseEntity<Integer> getTrainSeatMaxRange(@PathVariable Integer trainId){
		Integer maxSeatSeq=null;
		try {
			 maxSeatSeq = seatServ.getTrainSeatMaxRange(trainId);			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer> ( -1,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer> ( maxSeatSeq,HttpStatus.OK);
	}
}
