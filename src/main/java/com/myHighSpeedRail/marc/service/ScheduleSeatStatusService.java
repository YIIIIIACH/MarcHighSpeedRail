package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
