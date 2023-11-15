package com.myHighSpeedRail.marc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.service.ScheduleSeatStatusService;

@Controller
public class ScheduleSeatStatusController {

	@Autowired
	private ScheduleSeatStatusService schSeatServ;
	
	@PostMapping("/setupScheduleSeatStatus")
	public @ResponseBody ResponseEntity<String> setupByScheduleId(@RequestParam Integer schId){
		try {
			schSeatServ.setupSchdeuleSeatStatus(schId);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("set up failed",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("setup success",HttpStatus.OK);
	}
	
	@GetMapping("getAvaibleSeat")
	public @ResponseBody List<ScheduleSeatStatus> getAvaibleSeat(@RequestParam Integer schid,@RequestParam Integer stseq, @RequestParam Integer edseq, @RequestParam Integer amt){
		Long mask = 0L;
		mask |= (1L << edseq)-1;
		mask >>= stseq;
		mask <<= stseq;
		return schSeatServ.getAvaibleSeat(schid, mask, amt);
	}
	@PostMapping("registBookedSeat")
	public @ResponseBody ResponseEntity<String> registBookedSeat(@RequestParam Integer schid,@RequestParam Integer stseq, @RequestParam Integer edseq, @RequestParam Integer amt){
		Long mask = 0L;
		mask |= (1L << edseq)-1;
		mask >>= stseq;
		mask <<= stseq;
		schSeatServ.registBookedSeat(schid, mask, amt);
		return new ResponseEntity<String>( "inserted  data", HttpStatus.OK);
	}
}
