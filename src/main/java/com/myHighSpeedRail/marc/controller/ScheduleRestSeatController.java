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

import com.myHighSpeedRail.marc.model.ScheduleRestSeat;
import com.myHighSpeedRail.marc.service.ScheduleRestSeatService;

@Controller
public class ScheduleRestSeatController {
	@Autowired
	private ScheduleRestSeatService schRestSeatServ;
	@PostMapping("/setupScheduleRestSeatByScheduleId")
	public @ResponseBody ResponseEntity<String> setup(@RequestParam Integer schid){
		try {
			schRestSeatServ.setupScheduleRestSeat(schid);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("set up failed",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("set up success",HttpStatus.OK);
	}
	@GetMapping("/segmentDiscountRestSeatInSchedule")
	public @ResponseBody List<ScheduleRestSeat> segmentDiscountRestSeatInSchedule(@RequestParam(value="schid")Integer schid
			, @RequestParam(value="ststid")Integer ststId,@RequestParam(value="edstid")Integer edstId,@RequestParam(value="tdtype")String tdType){
		List<Integer> slist = new ArrayList<Integer>();
		slist.add(schid);
		return schRestSeatServ.segmentDiscountRestSeatInSchedule( slist, ststId, edstId, tdType);
	}
	
	//Integer updateScheduleRestSeat(Integer schid, Integer discountid, Integer rrid, Integer ststid, Integer endstid) {
	@PostMapping("/testUpdateScheduleRestSeat")
	public @ResponseBody ResponseEntity<String> updateScheduleRestSeat(@RequestParam(value="schid")Integer schid
			,@RequestParam(value="disid") Integer discountid,@RequestParam(value="rrid") Integer rrid
			,@RequestParam(value="ststid") Integer ststid,@RequestParam(value="endstid") Integer endstid
			,@RequestParam(value="tikcnt")Integer tickCnt){
		Integer effectRow=0;
		effectRow = schRestSeatServ.updateScheduleRestSeat(schid, discountid, rrid, ststid, endstid,tickCnt);
		if(effectRow>0) {
			return new ResponseEntity<String>("update success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("not update or failed",HttpStatus.BAD_REQUEST);
	}
}
