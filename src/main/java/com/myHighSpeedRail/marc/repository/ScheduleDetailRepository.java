package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.ScheduleDetail;

public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail , Integer> {
	@Query("from ScheduleDetail where schedule.scheduleId=:schid")
	public List<ScheduleDetail> findByScheduleId(Integer schid);
	@Query("select schd.ticketDiscount.ticketDiscountType from ScheduleDetail as schd where schd.schedule.scheduleId=:schId group by schd.ticketDiscount.ticketDiscountType")
	public List<String> getScheduleAllDiscountType(Integer schId);
}
