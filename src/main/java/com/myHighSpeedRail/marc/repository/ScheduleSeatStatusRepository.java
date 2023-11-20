package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Seat;

import jakarta.transaction.Transactional;

public interface ScheduleSeatStatusRepository extends JpaRepository<ScheduleSeatStatus, Integer>{
	//(scheduleStatus & :mask)<0 and , Long mask
	@Query(value="select * from schedule_seat_status  where schedule_id_fk =:sid and schedule_status & :mask =0 ",nativeQuery = true)
	public List<ScheduleSeatStatus> getAvaible( Integer sid, Long mask, PageRequest pq);
	@Transactional
    @Modifying
	@Query(value="update schedule_seat_status set schedule_status |= :mask where schedule_id_fk=:schid and seat_id_fk=:seatid",nativeQuery=true)
	public void updateSeatBySchidSeatId(Integer schid, Long mask, Integer seatid);
	@Query(value="from ScheduleSeatStatus schss where schss.schedule=:sch and schss.seat.trainSeatSequenece between :strange and :edrange")
	public List<ScheduleSeatStatus> findByScheduleSeatRange(Schedule sch, Integer strange, Integer edrange);
	//findBySeatSchedule(Schedule sch, List<Seat> seatlist){
	@Query(value="from ScheduleSeatStatus where schedule=:sch and seat in (:seatlist)")
	public List<ScheduleSeatStatus> findBySeatSchedule(Schedule sch, List<Seat> seatlist);
}
