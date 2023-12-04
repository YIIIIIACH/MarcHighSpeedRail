package com.myHighSpeedRail.marc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleDetail;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.service.RailRouteSegmentService;
import com.myHighSpeedRail.marc.service.RailRouteStopStationService;
import com.myHighSpeedRail.marc.service.ScheduleDetailService;
import com.myHighSpeedRail.marc.service.ScheduleSeatStatusService;
import com.myHighSpeedRail.marc.service.ScheduleService;

@Controller
public class ScheduleSeatStatusController {

	@Autowired
	private ScheduleSeatStatusService schSeatServ;
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private RailRouteStopStationService rrssServ;
	@Autowired
	private RailRouteSegmentService rrsServ;
	@Autowired
	private ScheduleDetailService schdServ;
	
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
	@GetMapping("/getSchSeatStatus")
	public @ResponseBody List<ScheduleSeatStatus> getSchSeatStatus(@RequestParam Integer schid, @RequestParam Integer seatid) {
		return schSeatServ.findBySchidSeatid(schid, seatid);
	}
	// will return the booked seat id of schedule in segment
	@GetMapping("/getScheduleBookedBuinessSeatidInSegment/{schid}/{ststid}/{edstid}")
	public @ResponseBody List<Integer> getScheduleBookedSeatidInSegment(@PathVariable Integer schid, @PathVariable Integer ststid,@PathVariable  Integer edstid){
		Schedule sch = schServ.findById(schid);
		// get stseq edseq by stid edid and
		Integer stseq = rrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(), ststid).get(0).getRailRouteStopStationSequence();
		Integer edseq =rrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(), edstid).get(0).getRailRouteStopStationSequence();
		// get schedule detail . and find out the buiness seat range;
		List<ScheduleDetail> schdList = schdServ.getScheduleDiscountRange(schid, "商務票");
		if( schdList.size()<=0 || stseq>=edseq) {
			System.out.println(schdList.size());
			return new ArrayList<Integer>();
		}
		// if seatid in schdList.get(0).getSeatRangeStart();  schdList.get(0).getSeatRangeEnd();
		// create Mask;
		Long mask = 0L;
		mask |= (1L << edseq)-1;
		mask >>= stseq;
		mask <<= stseq;
		return schSeatServ.findBookedSeatInSegmentInRange(schid, schdList.get(0).getSeatRangeStart(), schdList.get(0).getSeatRangeEnd(), mask).stream().map((schss)->schss.getSeat().getSeatId()).toList();
		
	}

}
