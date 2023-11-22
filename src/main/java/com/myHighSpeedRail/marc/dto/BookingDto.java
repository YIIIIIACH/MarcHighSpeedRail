package com.myHighSpeedRail.marc.dto;

import java.util.List;


public class BookingDto {
	public Integer scheduleId;
	public Integer ticketDiscountId;
	public Integer startStationId;
	public Integer endStationId;
	public List<Integer> chooseDiscounts;
}
