package com.marc.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Integer>{
//	@Query(value="from station" , nativeQuery=true)
//	public List<Station> findAllStation();
//	
//	@Query(value="from station where station_id=:sid")
//	public Optional<Station> findById(@Param(value="sid")Integer id);
//	
	@Query(value="from Station where stationName=:sname")
	public List<Station> findByName(@Param(value="sname")String stationName);
	
}
