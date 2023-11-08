package com.myHighSpeedRail.marc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.ScheduleTemplate;
import com.myHighSpeedRail.marc.model.Train;
import com.myHighSpeedRail.marc.repository.ScheduleTemplateRepository;
@Service
public class SchduleTemplateService {
	@Autowired
	private ScheduleTemplateRepository stDao;
	@Autowired
	private TrainService tServ;
	@Autowired
	private RailRouteService rrServ;
	
	public List<ScheduleTemplate> getAllScheduleTemplate(){
		return stDao.findAll();
	}
	
	public void insertScheduleTempalte(Integer trainId, Integer railRouteId, Date departTime, Date  desTime, Integer costMinute) throws Exception{
		try {
		Train t = tServ.findById(trainId);
		RailRoute rr = rrServ.rrFindById(railRouteId).get();
		if( t==null || rr==null)
			throw new Exception();
		stDao.save( new ScheduleTemplate( t, rr , departTime, desTime, costMinute) );
		}catch(Exception e){
			throw e;
		}
	}
}
