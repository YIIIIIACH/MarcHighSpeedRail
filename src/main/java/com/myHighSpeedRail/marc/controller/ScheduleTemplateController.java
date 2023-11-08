package com.myHighSpeedRail.marc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
			@RequestParam(value="deptime")String depTime,
			@RequestParam(value="destime")String desTime
			) {
		// depTime format HH:MM...
		// desTime format HH:MM
		
		Date st = new Date();
		st.setHours( Integer.valueOf( depTime.split(":")[0]));
		st.setMinutes(Integer.valueOf( depTime.split(":")[1]));
		st.setSeconds(0);
		Date dt = new Date();
		dt.setHours( Integer.valueOf( desTime.split(":")[0]));
		dt.setMinutes(Integer.valueOf( desTime.split(":")[1]));
		dt.setSeconds(0);
		Integer costTime = Long.valueOf((dt.getTime()-st.getTime())/(1000*60)).intValue();
		try {
			stServ.insertScheduleTempalte(trainId, railRouteId, st, dt ,costTime );
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("train is not found or rail route not found");
		}
		return ResponseEntity.ok("insert was successful");
	}
}
