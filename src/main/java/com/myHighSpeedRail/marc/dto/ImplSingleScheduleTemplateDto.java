package com.myHighSpeedRail.marc.dto;

import java.util.List;

public class ImplSingleScheduleTemplateDto {
	// scheduleTemplateDetailId
	public Integer schtId;
	public String implDate;
	// [ 100, 400 ,455] means {1-100 , 101-400 , 401-455}
	public List<Integer> seatRangeList;
	// [ 12, 13, 14] means  discId { 1-100 is discId 12, 101-400 is discId 13....
	public List<Integer> discountTypeIdList;
}
