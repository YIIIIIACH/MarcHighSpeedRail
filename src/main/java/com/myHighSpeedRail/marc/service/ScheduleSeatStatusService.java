package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Seat;
import com.myHighSpeedRail.marc.repository.ScheduleRepository;
import com.myHighSpeedRail.marc.repository.ScheduleSeatStatusRepository;
import com.myHighSpeedRail.marc.repository.SeatRepository;

@Service
public class ScheduleSeatStatusService {
	@Autowired
	private ScheduleSeatStatusRepository schSeatDao;
	@Autowired
	private ScheduleRepository schServ;
	@Autowired
	private SeatRepository seatServ;
	public void  setupSchdeuleSeatStatus(Integer scheduleId) {
		//get schedule
		Schedule sch = schServ.findById(scheduleId).get();
		//get all set of train
		List<Seat> seatList = seatServ.findByTrainId(sch.getTrain().getTrainId());
		List<ScheduleSeatStatus> resList = new ArrayList<ScheduleSeatStatus>();
		for( Seat s: seatList) {
			//public ScheduleSeatStatus(Schedule schedule, Seat seat, Long scheduleStatus) {
			resList.add( new ScheduleSeatStatus( sch, s, 0L));
		}
		schSeatDao.saveAll(resList);
	}
	public List<ScheduleSeatStatus> getAvaibleSeat(Integer schid, Long mask, Integer amt){
		return schSeatDao.getAvaible(schid ,mask, PageRequest.of(0, amt));
	}
	public void registBookedSeat( Integer schid , Long mask , Integer amt) {
		List<ScheduleSeatStatus> seatList = getAvaibleSeat( schid, mask , amt);
		for( ScheduleSeatStatus schss: seatList) {
			schSeatDao.updateSeatBySchidSeatId(schid, mask, schss.getSeat().getSeatId());
		}
		return;
	}
	public void registBookedBuinessSeat(Integer schid, Long mask, List<ScheduleSeatStatus> seatList) {
		for( ScheduleSeatStatus schss: seatList) {
			schSeatDao.updateSeatBySchidSeatId(schid, mask, schss.getSeat().getSeatId());
		}
		return;
	}
	public List<ScheduleSeatStatus> findByScheduleSeatRange(Schedule sch, Integer seatRangeStart, Integer seatRangeEnd){
		return schSeatDao.findByScheduleSeatRange(sch, seatRangeStart, seatRangeEnd);
	}
	public List<ScheduleSeatStatus> findBySeatSchedule(Schedule sch, List<Seat> seatlist){
		return schSeatDao.findBySeatSchedule(sch, seatlist);
	}
	public List<ScheduleSeatStatus> findBySchidSeatid(Integer schid,Integer seatid){
		return schSeatDao.findBySchidSeatid(schid, seatid);
	}
	public List<ScheduleSeatStatus> findBySchidInSeatid(Integer schid,List<Integer> seatidList){
		return schSeatDao.findBySchidInSeatid(schid, seatidList);
	}
	public List<ScheduleSeatStatus> findBookedSeatInSegmentInRange( Integer schid, Integer stRange, Integer edRange, Long segMask){
		return schSeatDao.findBookedSeatInSegmentInRange(schid, stRange, edRange, segMask);
	}
}
