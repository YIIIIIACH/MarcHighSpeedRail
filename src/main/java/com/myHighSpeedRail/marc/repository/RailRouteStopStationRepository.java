package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.RailRouteCmpsPK;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;

public interface RailRouteStopStationRepository extends JpaRepository<RailRouteStopStation, RailRouteCmpsPK>{
	@Query("from RailRouteStopStation where railRoute.railRouteId=:rid")
	public List<RailRouteStopStation>findByRouteId(Integer rid);
	
	@Query("from RailRouteStopStation where railRoute.railRouteId=:rid and stopStation.stationId=:sid")
	public List<RailRouteStopStation>findbyRouteIdStationId(Integer rid, Integer sid);
	
	@Query("from RailRouteStopStation where railRoute.railRouteId=:rid and railRouteStopStationSequence<=:stseqmax")
	public List<RailRouteStopStation> findByRouteIdStationSeqMaxRange(Integer rid, Integer stseqmax);
	
	@Query("from RailRouteStopStation where railRoute.railRouteId=:rid and railRouteStopStationSequence>=:stseqmin")
	public List<RailRouteStopStation> findByRouteIdStationSeqMinRange(Integer rid, Integer stseqmin);
	
	
}
