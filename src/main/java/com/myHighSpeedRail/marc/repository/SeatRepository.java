package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.Seat;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{
	@Query("from Seat where train.trainId=:tid")
	public List<Seat> findByTrainId(Integer tid);
	
	@Query("from Seat where seatId in (:seatIdList)")
	public List<Seat> findByIdInList(List<Integer> seatIdList);
	
	@Query("select max(s.trainSeatSequenece) from Seat as s where s.train.trainId=:tid")
	public Integer getTrainSeatMaxRange( Integer tid);
}
