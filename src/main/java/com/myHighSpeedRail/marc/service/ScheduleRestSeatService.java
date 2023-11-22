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
	 * orderSeatList;
	 */
	public void updateScheduleRestSeat(Integer schid, Integer discountid, Integer rrid, Integer ststid, Integer endstid,Integer ticketCnt) {
		List<RailRouteStopStation> tmp1 = rrssServ.findByRouteIdStationId(rrid, endstid);
		List<RailRouteStopStation> tmp2 = rrssServ.findByRouteIdStationId(rrid, ststid);
		RailRouteStopStation rrs1;
		RailRouteStopStation rrs2;
		if( tmp1.size()<=0 || tmp2.size()<=0)return ;
		rrs1 = tmp1.get(0);
		rrs2 = tmp2.get(0);
		Integer rrs1seq= rrs1.getRailRouteStopStationSequence();
		Integer rrs2seq =rrs2.getRailRouteStopStationSequence();
		if( rrs2seq>= rrs1seq) return ;
		if( rrs1.getStopStation().getStationId()== rrs2.getStopStation().getStationId())return ;
		List<RailRouteStopStation> startStRange = rrssServ.findByRouteIdStationSeqMaxRange(rrid, rrs1seq);
		List<RailRouteStopStation> endStRange = rrssServ.findByRouteIdStationSeqMinRange(rrid, rrs2seq);
		if(startStRange.size()==0) {
			startStRange.add(new RailRouteStopStation());
			Station tmp = new Station();
			tmp.setStationId(-1);
			startStRange.get(0).setStopStation( tmp);
		}
		if(endStRange.size()==0) {
			endStRange.add(new RailRouteStopStation());
			Station tmp = new Station();
			tmp.setStationId(-1);
			endStRange.get(0).setStopStation( tmp);
		}
		List<RailRouteSegment> effectedRRSList = rrsServ.findByRouteIdStartStEndStRange( rrid, endStRange, startStRange);
		List<Integer> effectedRRSIdList = new ArrayList<Integer>();
		for( RailRouteSegment rrs : effectedRRSList) {
			effectedRRSIdList.add(rrs.getRailRouteSegmentId());
		}
		List<RailRouteSegment> tarRRS = rrsServ.findByRailRouteIdStartStationEndStation(rrid, ststid, endstid);//
		//get the target segment rest seat amt of that schid and discountid
		List<ScheduleRestSeat> tarSchrs = schRestSeatDao.findBySchDiscSeg(schid, discountid, tarRRS.get(0).getRailRouteSegmentId());
		
		List<ScheduleRestSeat> effectedSchrs = schRestSeatDao.findBySchDisEffectedSeg(schid, discountid, effectedRRSIdList);
		
		for( ScheduleRestSeat schrs : effectedSchrs) {
			schrs.setRestSeatAmount( (schrs.getRestSeatAmount()-ticketCnt>tarSchrs.get(0).getRestSeatAmount()-ticketCnt)?  schrs.getRestSeatAmount()-ticketCnt: tarSchrs.get(0).getRestSeatAmount()-ticketCnt);
		}
		// need a new updateScheduleRestSeat( ticketCnt, schid, discountid , effectedRRSIdList);
//		return schRestSeatDao.updateScheduleRestSeat(ticketCnt,schid,discountid,effectedRRSIdList);
		schRestSeatDao.saveAll( effectedSchrs);
		return;
	}
}
