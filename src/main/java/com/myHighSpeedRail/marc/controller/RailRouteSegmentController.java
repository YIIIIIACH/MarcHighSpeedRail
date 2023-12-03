package com.myHighSpeedRail.marc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.service.RailRouteSegmentService;

@Controller
public class RailRouteSegmentController {
	@Autowired
	private RailRouteSegmentService rrss;
	
	@GetMapping("/createRailRouteSegmentById")
	public @ResponseBody List<RailRouteSegment> createById(@RequestParam(value="rId")Integer rId) {
		rrss.createAllSegmentByRouteId(rId);
		return new ArrayList<RailRouteSegment>();//rrss.findAll();
	}
	
	@GetMapping("/findAllRailRouteSegment")
	public @ResponseBody List<RailRouteSegment> findall() {
		
		return rrss.findAll();
	}
	@GetMapping("/findRailRouteSegmentBySchidStstEdst/{schid}/{stid}/{edid}")
	public @ResponseBody List<RailRouteSegment> findRailRouteSegmentBySchidStstEdst(@PathVariable Integer schid,@PathVariable Integer stid, @PathVariable Integer edid){
		return rrss.findByScheduleIdStstEdst(schid, stid, edid);
		
	}
}
