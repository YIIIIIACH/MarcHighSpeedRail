package com.myHighSpeedRail.marc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.RailRouteSegment;

public interface RailRouteSegmentRepository extends JpaRepository<RailRouteSegment,Integer>{
	@Query("from RailRouteSegment rrs where rrs.endStation.stationId=:eid and rrs.startStation.stationId=:sid and rrs.railRoute.railRouteId=:rid")
	public Optional<RailRouteSegment> findByStopStationId(Integer rid, Integer sid, Integer eid);
}
