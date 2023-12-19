package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.ScheduleRailRouteDiscountCmpsPK;
import com.myHighSpeedRail.marc.model.ScheduleRestSeat;
import com.myHighSpeedRail.marc.model.Station;

import jakarta.transaction.Transactional;
@Repository
public interface ScheduleRestSeatRepository extends JpaRepository<ScheduleRestSeat,ScheduleRailRouteDiscountCmpsPK>{
	@Query("from ScheduleRestSeat where schedule.scheduleId=:schid")
	public List<ScheduleRestSeat> findByScheduleId(Integer schid);
	
	@Query("select schrs from ScheduleRestSeat as schrs "
			+ "join RailRouteSegment as rrs on schrs.railRouteSegment.railRouteSegmentId=rrs.railRouteSegmentId"
			+ " join TicketDiscount as td on td.ticketDiscountId=schrs.ticketDiscount.ticketDiscountId"
			+ " where rrs.startStation.stationId=:ststid and rrs.endStation.stationId=:edstid"
			+ " and td.ticketDiscountType=:tdtype and schrs.schedule.scheduleId in :schidlist")
	public List<ScheduleRestSeat> getRestSeatByStEdStationTicketDiscount(List<Integer>schidlist,Integer ststid,Integer edstid, String tdtype);
	
	@Query("from ScheduleRestSeat as schrs where schrs.schedule.scheduleId=:schid")
	public List<ScheduleRestSeat> getScheduleRestSeat(Integer schid);
	/*
	 * update schedule_rest_seat set rest_seat_amount =rest_seat_amount  where schedule_id_fk=<SCHID> and discount_id_fk=<DISCOUNTID> and rail_route_segment_id_fk in (
			select rail_route_segment_id from rail_route_segment where start_station_id_fk in (
			 	 select stop_station_id_fk from rail_route_stop_station where rail_route_id_fk=<RAILROUTEID> and 
					rail_route_stop_station_sequence
						<(select top 1 rail_route_stop_station_sequence from rail_route_stop_station 
							where rail_route_id_fk=<RAILROUTEID> and stop_station_id_fk=<ENDSTATIONID>)
			 	) 
			 and end_station_id_fk in (
			 	select stop_station_id_fk from rail_route_stop_station where rail_route_id_fk=<RAILROUTEID> and 
					rail_route_stop_station_sequence
						>(select top 1 rail_route_stop_station_sequence from rail_route_stop_station 
							where rail_route_id_fk=<RAILROUTEID> and stop_station_id_fk=<STOPSTATION>)
			 	)
			and rail_route_id_fk=<RAILROUTE>
		)
	 */
	@Transactional
	@Modifying
	@Query("update ScheduleRestSeat schrs set schrs.restSeatAmount=schrs.restSeatAmount-:modifyvalue where schrs.schedule.scheduleId=:schid and schrs.ticketDiscount.ticketDiscountId=:disid and schrs.railRouteSegment.railRouteSegmentId  in (:rrsidlist)")
	public Integer updateScheduleRestSeat(Integer modifyvalue,Integer schid,Integer disid,List<Integer> rrsidlist);
	
	//test query
	@Query("from ScheduleRestSeat where schedule.scheduleId=:schid and ticketDiscount.ticketDiscountId=:disid and railRouteSegment.railRouteSegmentId  in (:rrsidlist)")
	public List<ScheduleRestSeat> findBySchDisEffectedSeg(Integer schid,Integer disid,List<Integer> rrsidlist);
	
	@Query("from ScheduleRestSeat where schedule.scheduleId=:schid and ticketDiscount.ticketDiscountId=:discountid and railRouteSegment.railRouteSegmentId=:segid")
	public List<ScheduleRestSeat> findBySchDiscSeg( Integer schid, Integer discountid, Integer segid);
}
