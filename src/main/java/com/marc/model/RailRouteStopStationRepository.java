package com.marc.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RailRouteStopStationRepository extends JpaRepository<RailRouteStopStation, Integer>{
	@Query("from RailRouteStopStation where railRoute.railRouteId=:rid")
	public List<RailRouteStopStation>findByRouteId(Integer rid);
}
