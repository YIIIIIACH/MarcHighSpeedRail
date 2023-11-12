package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.ScheduleRailRouteDiscountCmpsPK;
import com.myHighSpeedRail.marc.model.ScheduleRestSeat;
import com.myHighSpeedRail.marc.model.Station;

public interface ScheduleRestSeatRepository extends JpaRepository<ScheduleRestSeat,ScheduleRailRouteDiscountCmpsPK>{
	@Query("from ScheduleRestSeat where schedule.scheduleId=:schid")
	public List<ScheduleRestSeat> findByScheduleId(Integer schid);
	
	@Query("select schrs from ScheduleRestSeat as schrs "
			+ "join RailRouteSegment as rrs on schrs.railRouteSegment.railRouteSegmentId=rrs.railRouteSegmentId"
			+ " join TicketDiscount as td on td.ticketDiscountId=schrs.ticketDiscount.ticketDiscountId"
			+ " where rrs.startStation.stationId=:ststid and rrs.endStation.stationId=:edstid"
			+ " and td.ticketDiscountType=:tdtype and schrs.schedule.scheduleId in :schidlist")
	public List<ScheduleRestSeat> getRestSeatByStEdStationTicketDiscount(List<Integer>schidlist,Integer ststid,Integer edstid, String tdtype);
}
