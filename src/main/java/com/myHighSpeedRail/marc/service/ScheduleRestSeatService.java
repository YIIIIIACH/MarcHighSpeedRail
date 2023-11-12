package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.ScheduleDetail;
import com.myHighSpeedRail.marc.model.ScheduleRestSeat;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.repository.ScheduleRestSeatRepository;

@Service
public class ScheduleRestSeatService {
	@Autowired
	private ScheduleRestSeatRepository schRestSeatDao;
	@Autowired
	private ScheduleDetailService schDServ;
	@Autowired
	private RailRouteSegmentService rrsServ;
	@Autowired
	private ScheduleService schServ;
	
	public void setupScheduleRestSeat(Integer schId)throws Exception{
		// check if schedule has set rest seat;
		List<ScheduleRestSeat> srsList = schRestSeatDao.findByScheduleId(schId);
		
		if (srsList==null || srsList.size()<=0) {
			//not set up yet 
			//get schedule detail to count total seats of each discount
			List<ScheduleRestSeat> res = new ArrayList<ScheduleRestSeat>();
			List<ScheduleDetail> schdList =  schDServ.findByScheduleId(schId);
			List<RailRouteSegment> rsList= rrsServ.findByRailRouteId( schServ.findById(schId).getRailRoute().getRailRouteId() );
			for( ScheduleDetail sd: schdList) {
				//get all segment of route	
				for( RailRouteSegment rrs: rsList) {
					res.add(new ScheduleRestSeat( 
							sd.getSchedule()
							,rrs
							,sd.getTicketDiscount()
							, sd.getSeatRangeEnd()-sd.getSeatRangeStart()+1
							));
				}
			}
			schRestSeatDao.saveAll(res);
		}
	}
	
	public List<ScheduleRestSeat> segmentDiscountRestSeatInSchedule(List<Integer> schidList, Integer ststid, Integer edstid, String tickedDiscountType) {
		return schRestSeatDao.getRestSeatByStEdStationTicketDiscount(schidList,ststid, edstid, tickedDiscountType);
	}
}
