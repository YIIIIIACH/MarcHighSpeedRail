package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleDetail;
import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.repository.ScheduleDetailRepository;

@Service
public class ScheduleDetailService {
	@Autowired
	private ScheduleDetailRepository schdDao;
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private TicketDiscountService tickDisServ;
	public void setupDefaultScheduleDetail(Integer scheduleId) {
		//假設每個列車都長一樣 座位也一樣 預設除的商務艙55座 其餘一般座400席
		//public ScheduleDetail(Schedule schedule, TicketDiscount ticketDiscount, Integer seatRangeStart,
//		Integer seatRangeEnd) {
		Schedule sch = schServ.findById(scheduleId);
		schdDao.save(new ScheduleDetail(sch, tickDisServ.findById(12) ,1, 400));
		schdDao.save(new ScheduleDetail(sch, tickDisServ.findById(26) ,401, 455));
	}
	
	public Integer setupScheduleDetail( Integer scheduleId, List<Integer> rangeList, List<Integer> discIdList) {
		Schedule sch = schServ.findById(scheduleId);
		List<ScheduleDetail> sdList= new ArrayList<ScheduleDetail>();
		int seatIdx=1;
		for( int i=0; i<rangeList.size(); i++) {
			sdList.add( new ScheduleDetail( sch, tickDisServ.findById( discIdList.get(i)), seatIdx, rangeList.get(i)));
			seatIdx= rangeList.get(i)+1;
		}
		sdList = schdDao.saveAll( sdList);
		return sdList.size();
	}
	
	public List<ScheduleDetail> findByScheduleId(Integer schId){
		return schdDao.findByScheduleId(schId);
	}
	
	public List<String> getScheduleAllDiscountType( Integer schId){
		return schdDao.getScheduleAllDiscountType( schId);
	}
	public List<TicketDiscount> getScheduleAllDiscount( Integer schid){
		return schdDao.getScheduleAllTicketDiscount(schid);
	}
	public Boolean conainDiscountType(Integer schid, String discType) {
		Boolean res = (schdDao.getScheduleDetailCountByScheduelId(schid,discType)>0)? true: false;
		System.out.println( res.toString() + schid +" " + discType);
		return res;
	}
	public List<ScheduleDetail> getScheduleDiscountRange(Integer schid, String discType){
		return schdDao.getScheduleDiscountRange(schid, discType);
	}
}
