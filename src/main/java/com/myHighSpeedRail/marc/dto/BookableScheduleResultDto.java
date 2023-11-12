package com.myHighSpeedRail.marc.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.model.TicketDiscount;

public class BookableScheduleResultDto {
	/*
	 * 1.  1班次編號  2. 乘車站  3.到達乘車站時間  4. 下車站 5.下車時間 6.所花費時間 7.折價過後價格 8.可用優惠 10. 該模式下可以選擇的優惠
	// get schedule in condition --> select on get on off station to get the route_segment of that schdule-->
	 * use rail_route segment to calculate the arrive time of get on and off station . 
	 * and use rail_route_segment_ticket_price and discount type to calculate the ticket price
	 * and use rail_route_id to get all rail_route_stop_station of the rail_route .. 
	 */
	public Integer scheduleId;
	public Station getOnStation;
	public Station getOffStation;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getOnTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getOffTime;
	public Integer durationMinute;
	public Integer discountTicketPrice;  // 一般票的價格 不用計算折價
	public String selectedTicketDiscountType;
	public BookableScheduleResultDto() {;}
	public BookableScheduleResultDto(Integer scheduleId, Station getOnStation, Station getOffStation, Date getOnTime,
			Date getOffTime, Integer durationMinute, Integer discountTicketPrice,
			String selectedTicketDiscountType) {
		super();
		this.scheduleId = scheduleId;
		this.getOnStation = getOnStation;
		this.getOffStation = getOffStation;
		this.getOnTime = getOnTime;
		this.getOffTime = getOffTime;
		this.durationMinute = durationMinute;
		this.discountTicketPrice = discountTicketPrice;
		this.selectedTicketDiscountType = selectedTicketDiscountType;
	}
	
}
