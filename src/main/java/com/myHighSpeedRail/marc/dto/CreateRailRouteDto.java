package com.myHighSpeedRail.marc.dto;

import java.util.List;

public class CreateRailRouteDto {
	//Integer rid,List<Integer> stidList, List<Integer> costTimeList , List<Integer> toNextStationPriceList
	public List<Integer> stIdList;
	public List<Integer> costTimeList;
	public List<Integer> toNextStationPriceList;
	public Integer depStationId;
	public Integer desStationId;
	public Integer stationCnt;
}
