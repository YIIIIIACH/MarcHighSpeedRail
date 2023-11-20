package com.myHighSpeedRail.marc.dto;

import java.util.List;

import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Station;

public class BookingBuinessSeatResponseDto {
	public List<ScheduleSeatStatus> scheduleSeatStatusList;
	public List<Integer> bookedSeatIdList;
	public Station startStation;
	public Station endStation;
}
