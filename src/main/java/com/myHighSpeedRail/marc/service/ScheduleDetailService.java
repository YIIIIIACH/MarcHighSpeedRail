package com.myHighSpeedRail.marc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleDetail;
import com.myHighSpeedRail.marc.repository.ScheduleDetailRepository;

@Service
public class ScheduleDetailService {
	@Autowired
	private ScheduleDetailRepository schdDao;
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private TicketDiscountSerivce tickDisServ;
	public void setupDefaultScheduleDetail(Integer scheduleId) {
		//假設每個列車都長一樣 座位也一樣 預設除的商務艙55座 其餘一般座400席
		//public ScheduleDetail(Schedule schedule, TicketDiscount ticketDiscount, Integer seatRangeStart,
//		Integer seatRangeEnd) {
		Schedule sch = schServ.findById(scheduleId);
		schdDao.save(new ScheduleDetail(sch, tickDisServ.findById(12) ,1, 400));
		schdDao.save(new ScheduleDetail(sch, tickDisServ.findById(26) ,401, 455));
	}
	
	public List<ScheduleDetail> findByScheduleId(Integer schId){
		return schdDao.findByScheduleId(schId);
	}
}
