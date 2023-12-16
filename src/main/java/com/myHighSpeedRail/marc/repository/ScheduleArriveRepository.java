package com.myHighSpeedRail.marc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.model.StationScheduleCmpsPK;
@Repository
public interface ScheduleArriveRepository extends JpaRepository<ScheduleArrive,StationScheduleCmpsPK>{
	@Query("from ScheduleArrive where schedule.scheduleId=:schId")
	public List<ScheduleArrive> findByScheduleId(Integer schId);
	
	/// step1 List<Schedule> schList = select schArr.schedule scheduleArrive as schArr 
	//where schArr.station.stationId=:onStationId and  schArr.arriveTime between :priximateTime-offset and :proximateTime+offset;
	//getScheduleByArriveStationTimeRange
	@Query("select schArr.schedule from ScheduleArrive as schArr where schArr.station.stationId=:onStationId and  schArr.arriveTime between :minTime and :maxTime")
	public List<Schedule> getScheduleByArriveStationTimeRange(Integer onStationId, Date minTime, Date maxTime);
	
	@Query("from ScheduleArrive where station.stationId=:sId and schedule.scheduleId=:schId")
	public List<ScheduleArrive> findByScheduleIdStationid(Integer schId, Integer sId);
}
