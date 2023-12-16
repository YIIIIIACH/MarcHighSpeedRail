package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.RailRoute;
@Repository
public interface RailRouteRepository extends JpaRepository<RailRoute, Integer>{
	@Query("from RailRoute ")
	public List<RailRoute> getAllRailRouteByHQL();
}
