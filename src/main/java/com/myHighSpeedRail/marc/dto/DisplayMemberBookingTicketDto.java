package com.myHighSpeedRail.marc.dto;

import java.util.List;

import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.model.Seat;
import com.myHighSpeedRail.marc.model.TicketDiscount;

public class DisplayMemberBookingTicketDto {
	public List<Integer> bookingIdList;
	public Schedule ticketSchedule;
	public RailRouteSegment ticketRailRouteSegment;
	public List<Seat> seatList;
	public List<TicketDiscount> ticketDiscountList;
	public List<String> ticketStatusList;
	public List<Integer> ticketPriceList;
	public List<String> ticketQRcodeList;
	public ScheduleArrive startArrive;
	public ScheduleArrive endArrive;
}
