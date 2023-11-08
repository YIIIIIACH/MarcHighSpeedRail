package com.myHighSpeedRail.marc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleTemplate;
import com.myHighSpeedRail.marc.repository.ScheduleRepository;

@Service
public class ScheduleService {
	@Autowired
	private ScheduleRepository schDao;
	@Autowired
	private SchduleTemplateService stServ;
	
	public void impScheduleTemplate(Date tarDate ) {
		// will create schedules on everything in schedule template
		List<ScheduleTemplate> stList = stServ.getAllScheduleTemplate();
		for( ScheduleTemplate st : stList) {
			System.out.println("here");
			
			Date tmp = st.getDepartTime();
			Date depTime = new Date(tarDate.getTime());
			depTime.setHours(tmp.getHours());
			depTime.setMinutes(tmp.getMinutes());
			System.out.println( depTime);
			Date tmp2 = st.getDestinateTime();
			Date desTime= new Date(tarDate.getTime());
			desTime.setHours(tmp2.getHours());
			desTime.setMinutes(tmp2.getMinutes());
			System.out.println( desTime);
			Schedule sch = new Schedule(st.getRailRoute(), st.getTrain(),depTime,desTime,st.getCostMinute());
			schDao.save(sch);
		}
	}
}
