package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
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
	@Autowired
	private RailRouteStopStationService rrssServ;
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
	
	/*
	 * update schedule_rest_seat set rest_seat_amount =rest_seat_amount  where schedule_id_fk=<SCHID> and discount_id_fk=<DISCOUNTID> and rail_route_segment_id_fk in (
			findByRouteIdStartStEndStRange( rrid, findByRouteIdStationSeqMinRange(rrid, rrs1.getxxSeq()) , findByRouteIdStationSeqMinRange(rrid, rrs1.getxxSeq()))
		)
	 * 
	 */
	public Integer updateScheduleRestSeat(Integer schid, Integer discountid, Integer rrid, Integer ststid, Integer endstid) {
		RailRouteStopStation rrs1 = rrssServ.findByRouteIdStationId(rrid, endstid).get(0);
		RailRouteStopStation rrs2 = rrssServ.findByRouteIdStationId(rrid, ststid).get(0);
		List<RailRouteStopStation> startStRange = rrssServ.findByRouteIdStationSeqMaxRange(rrid, rrs1.getRailRouteStopStationSequence());
		List<RailRouteStopStation> endStRange = rrssServ.findByRouteIdStationSeqMinRange(rrid, rrs2.getRailRouteStopStationSequence());
		List<RailRouteSegment> effectedRRSList = rrsServ.findByRouteIdStartStEndStRange( rrid, endStRange, startStRange);
		List<Integer> effectedRRSIdList = new ArrayList<Integer>();
		for( RailRouteSegment rrs : effectedRRSList) {
			effectedRRSIdList.add(rrs.getRailRouteSegmentId());
		}
		return schRestSeatDao.updateScheduleRestSeat(400,schid,discountid,effectedRRSIdList);
	}
}
