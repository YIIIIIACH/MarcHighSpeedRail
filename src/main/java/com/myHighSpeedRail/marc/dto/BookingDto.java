package com.myHighSpeedRail.marc.dto;

import java.util.List;

import com.myHighSpeedRail.marc.model.TicketDiscount;

public class BookingDto {
	public Integer scheduleId;
	public Integer ticketDiscountId;
	public Integer startStationId;
	public Integer endStationId;
	public List<Integer> chooseDiscounts;
}
