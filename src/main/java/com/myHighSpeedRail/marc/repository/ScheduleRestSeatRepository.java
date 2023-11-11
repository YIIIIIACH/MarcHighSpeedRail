package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.ScheduleRailRouteDiscountCmpsPK;
import com.myHighSpeedRail.marc.model.ScheduleRestSeat;

public interface ScheduleRestSeatRepository extends JpaRepository<ScheduleRestSeat,ScheduleRailRouteDiscountCmpsPK>{
	@Query("from ScheduleRestSeat where schedule.scheduleId=:schid")
	public List<ScheduleRestSeat> findByScheduleId(Integer schid);
}
