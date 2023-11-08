package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.ScheduleArrive;

public interface ScheduleArriveRepository extends JpaRepository<ScheduleArrive,Integer>{
	@Query("from ScheduleArrive where schedule.scheduleId=:schId")
	public List<ScheduleArrive> findByScheduleId(Integer schId);
}
