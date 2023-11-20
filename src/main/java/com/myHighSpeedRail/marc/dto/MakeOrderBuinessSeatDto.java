package com.myHighSpeedRail.marc.dto;

import java.util.List;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Station;

public class MakeOrderBuinessSeatDto {
	public List<ScheduleSeatStatus>  orderSeatList;
	public Schedule schedule;
	public Station startStation;
	public Station endStation;
	public Integer ticketDiscountId;
}
