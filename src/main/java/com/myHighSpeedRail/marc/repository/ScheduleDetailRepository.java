package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.ScheduleDetail;
import com.myHighSpeedRail.marc.model.TicketDiscount;
@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail , Integer> {
	@Query("from ScheduleDetail where schedule.scheduleId=:schid")
	public List<ScheduleDetail> findByScheduleId(Integer schid);
	@Query("select schd.ticketDiscount.ticketDiscountType from ScheduleDetail as schd where schd.schedule.scheduleId=:schId group by schd.ticketDiscount.ticketDiscountType")
	public List<String> getScheduleAllDiscountType(Integer schId);
	@Query("select count(schd) from ScheduleDetail as schd where schd.schedule.scheduleId=:schId and schd.ticketDiscount.ticketDiscountType=:discType")
	public Long getScheduleDetailCountByScheduelId(Integer schId,String discType);
	@Query("from ScheduleDetail schd where schd.ticketDiscount.ticketDiscountType=:dname"+
	" and schd.schedule.scheduleId=:schid")
	public List<ScheduleDetail> getScheduleDiscountRange(Integer schid , String dname);
	@Query("select ticketDiscount from ScheduleDetail where schedule.scheduleId=:schid")
	public List<TicketDiscount> getScheduleAllTicketDiscount(Integer schid);
}
