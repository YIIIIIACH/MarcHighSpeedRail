package com.myHighSpeedRail.marc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.ScheduleTemplate;
import com.myHighSpeedRail.marc.model.Station;
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
	@Autowired
	private RailRouteStopStationService rrssServ;
	public ScheduleTemplate findById(Integer id) {
		return stDao.findById(id).get();
	}
	
	public List<ScheduleTemplate> getAllScheduleTemplate(){
		return stDao.findAll();
	}
	
	public void insertScheduleTempalte(Integer trainId, Integer railRouteId, Date departTime) throws Exception{
		try {
		Train t = tServ.findById(trainId);
		RailRoute rr = rrServ.rrFindById(railRouteId).get();
		if( t==null || rr==null)
			throw new Exception("fail find train or railroute");
			Date desTime = new Date();
			desTime.setTime(0);
			Station es = rr.getDestinateStation();
			Station ss = rr.getDepartStation();
//			RailRouteSegment rrs =rrsServ.findByStopStationId(railRouteId, ss.getStationId(), es.getStationId()).get();
			RailRouteStopStation rrss = rrssServ.findByRouteIdStationId( railRouteId, es.getStationId()).get(0);
			if(rrss==null) {
				throw new Exception("fail find rail route segment");
			}
			desTime.setTime( departTime.getTime() + rrss.getCostTimeMinute()*60*1000);
			System.out.println( desTime);
			stDao.save( new ScheduleTemplate( t, rr , departTime, desTime , rrss.getCostTimeMinute()) );
		}catch(Exception e){
			throw e;
		}
	}
	
	public Boolean  removeScheduleTemplate( Integer schtid) {
		Optional<ScheduleTemplate> schtOpt = stDao.findById(schtid);
		if(schtOpt.isEmpty()) {
			return false;
		}
		stDao.delete(schtOpt.get());
		return true;
	}
}
