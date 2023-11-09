package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.repository.ScheduleArriveRepository;

@Service
public class ScheduleArriveService {
	@Autowired
	private ScheduleArriveRepository saDao;
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private RailRouteStopStationService  rrssServ;
	
	public void setupScheduleArriveBySchedule(Integer scheduleId)throws Exception{
		List<ScheduleArrive> sl =saDao.findByScheduleId(scheduleId); 
		if( sl == null || sl.size()<=0 ) {
			Schedule sch = schServ.findById(scheduleId);
			List<RailRouteStopStation> rrStopStationList= rrssServ.findByRouteId(sch.getRailRoute().getRailRouteId());
			List<ScheduleArrive> res = new ArrayList<ScheduleArrive>();
			Date startTime = sch.getDepartTime();// 
			for( RailRouteStopStation ss : rrStopStationList) {
				Date tmp = new Date(startTime.getTime()+ ss.getCostTimeMinute()* 60*1000);
				res.add(new ScheduleArrive(ss.getStopStation(),sch,tmp ));
			}
			saDao.saveAll(res);			
		}
		
	}
	
	public List<ScheduleArrive> findAll(){
		return saDao.findAll();
	}
}
