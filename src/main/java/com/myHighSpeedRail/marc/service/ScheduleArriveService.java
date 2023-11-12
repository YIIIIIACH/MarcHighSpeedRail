package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.repository.ScheduleArriveRepository;

@Service
public class ScheduleArriveService {
	@Autowired
	private ScheduleArriveRepository saDao;
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private RailRouteStopStationService  rrssServ;
	@Autowired
	private TicketDiscountService tdServ;
	
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
	//step1 List<Schedule> schList = select schArr.schedule scheduleArrive as schArr 
	//where schArr.station.stationId=:onStationId and  schArr.arriveTime between :priximateTime-offset and :proximateTime+offset;
	public List<Schedule> getScheduleByArriveStationTimeRange(Integer onStationId, Date proximateTime,Long offsetTime ){
		Date minTime = Date.from( proximateTime.toInstant().minusSeconds(offsetTime*60));
		Date maxTime = Date.from( proximateTime.toInstant().plusSeconds(offsetTime*60));
//		System.out.println(minTime);
//		System.out.println( maxTime);
		return saDao.getScheduleByArriveStationTimeRange(onStationId, minTime, maxTime);
	}
	
	public List<Schedule> getScheduleByArriveStationTimeRangeFilterPastFilterDiscountAdvancePurchaseLimit(Integer onStationId, Date proximateTime,Long offsetTime,String discountType ){
		// get advance purchase Limit
		List<TicketDiscount> tdList = tdServ.findByDiscountType(discountType);
		Integer tmp = tdList.get(0).getPurchaseEarlyLimitDay();
		
		Date minTime = Date.from( proximateTime.toInstant().minusSeconds(offsetTime*60));
		Date maxTime = Date.from( proximateTime.toInstant().plusSeconds(offsetTime*60));
		Date now = new Date();
		minTime = (minTime.before(now))? now: minTime;
		maxTime = (maxTime.before(now))? now : maxTime;
		Date minArriveByAdPurch = Date.from( new Date().toInstant().plusSeconds(tmp*24*60*60));
		
		minTime = ( minTime.before(minArriveByAdPurch))?minArriveByAdPurch: minTime;
		maxTime = ( maxTime.before(minArriveByAdPurch))?minArriveByAdPurch: maxTime;
		List<Schedule> schList=  saDao.getScheduleByArriveStationTimeRange(onStationId, minTime, maxTime);
		return schList;
	}
	
	public ScheduleArrive findByScheduleIdStationId( Integer schId, Integer stationId) {
		List<ScheduleArrive> res = saDao.findByScheduleIdStationid(schId, stationId);
		if( res==null || res.size()==0 ) return null;
		return res.get(0);
		
		
	}
}
