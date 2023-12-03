package com.myHighSpeedRail.marc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;

public interface RailRouteSegmentRepository extends JpaRepository<RailRouteSegment,Integer>{
	@Query("from RailRouteSegment rrs where rrs.endStation.stationId=:eid and rrs.startStation.stationId=:sid and rrs.railRoute.railRouteId=:rid")
	public Optional<RailRouteSegment> findByStopStationId(Integer rid, Integer sid, Integer eid);
	
	@Query("from RailRouteSegment where railRoute.railRouteId=:rrid")
	public List<RailRouteSegment> findByRailRouteId(Integer rrid);
	
	
	// step2 select schSeg.schedule  from ScheduleSegment  schSeg where schSeg.schedule in :schList and schSeg.endStation.stationId=offStationId
	// check on and off station
	@Query(" select rrs.railRoute from RailRouteSegment as rrs where rrs.startStation.stationId=:stStId and rrs.endStation.stationId=:edStId")
	public List<RailRoute> findByStartStationEndStation(Integer stStId,Integer edStId);
	@Query("from RailRouteSegment where railRoute.railRouteId=:rrid and startStation.stationId=:ststid and endStation.stationId=:edstid")
	public List<RailRouteSegment>findByRailRouteIdStartStationEndStation(Integer rrid, Integer ststid, Integer edstid);
	@Query("from RailRouteSegment where railRoute.railRouteId=:rrid and startStation.stationId not in (:stStExcludeList) and endStation.stationId  not in (:endStExcludeList)")
	public List<RailRouteSegment> findByStartEndStationExcludeRange(Integer rrid,List<Integer> stStExcludeList, List<Integer>  endStExcludeList);
	// 20 25 , 14
	@Query("from RailRouteSegment rrs join Schedule sch on rrs.railRoute=sch.railRoute where sch.scheduleId=:schid and rrs.startStation.stationId=:stid and rrs.endStation.stationId=:edid")
	public List<RailRouteSegment> findByScheduleIdStstEdst(Integer schid,Integer  stid,Integer edid);
}
