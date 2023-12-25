package com.myHighSpeedRail.marc.controller;

import java.util.Date;
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

import com.myHighSpeedRail.marc.model.ScheduleTemplate;
import com.myHighSpeedRail.marc.service.SchduleTemplateService;

@Controller
public class ScheduleTemplateController {
	@Autowired
	private SchduleTemplateService stServ;
	@GetMapping("/getAllScheduleTemplate")
	public @ResponseBody List<ScheduleTemplate> getAll(){
		return stServ.getAllScheduleTemplate(); 
	}
	@SuppressWarnings("deprecation")
	@PostMapping("/insertScheduleTemplate")
	public ResponseEntity<String> insertScheduleTemplate(
			@RequestParam(value="tid")Integer trainId,
			@RequestParam(value="rrid")Integer railRouteId,
			@RequestParam(value="deptime")String depTime
			) {
		// depTime format HH:MM...
		// desTime format HH:MM
		
		Date st = new Date();
		st.setTime(0);
		st.setHours( Integer.valueOf( depTime.split(":")[0]));
		st.setMinutes(Integer.valueOf( depTime.split(":")[1]));
		try {
			stServ.insertScheduleTempalte(trainId, railRouteId, st );
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("train is not found or rail route not found");
		}
		return ResponseEntity.ok("insert was successful");
	}
	@GetMapping("/getScheduleTemplateById")
	public @ResponseBody ResponseEntity<ScheduleTemplate> getScheduleTemplateById(@RequestParam Integer schtid){
		ScheduleTemplate scht = null;
		try {
			scht = stServ.findById(schtid);			
		}catch( Exception e) {
			e.printStackTrace();
			new ResponseEntity<ScheduleTemplate> (scht, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ScheduleTemplate> (scht, HttpStatus.OK);
	}
	@PostMapping("/removeScheduleTemplate/{schtid}")
	public @ResponseBody ResponseEntity<String> removeScheduleTemplate(@PathVariable Integer schtid){
		stServ.findById(schtid);
		if(stServ.removeScheduleTemplate(schtid)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed",HttpStatus.BAD_REQUEST);
	}
	
}
