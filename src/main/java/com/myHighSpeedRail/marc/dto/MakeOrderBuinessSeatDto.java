package com.myHighSpeedRail.marc.dto;

import java.util.List;

import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Station;

public class MakeOrderBuinessSeatDto {
	public List<Integer>  orderSeatIdList;
	public Integer scheduleId;
	public Station startStation;
	public Station endStation;
	public String ticketDiscountName;
}
