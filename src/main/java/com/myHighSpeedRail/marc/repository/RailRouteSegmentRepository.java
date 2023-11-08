package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.marc.model.RailRouteSegment;

public interface RailRouteSegmentRepository extends JpaRepository<RailRouteSegment,Integer>{
	
}
