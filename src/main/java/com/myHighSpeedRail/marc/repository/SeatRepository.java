package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer>{
	@Query("from Seat where train.trainId=:tid")
	public List<Seat> findByTrainId(Integer tid);
}
