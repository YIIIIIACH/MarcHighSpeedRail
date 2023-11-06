package com.myHighSpeedRail.marc.dto;

import com.myHighSpeedRail.marc.model.Station;

public class RailRouteDto {
	public Integer railRouteId;
	public Station departStation;
	public Station destinateStation;
	public Integer stopStationCount;
	
	public RailRouteDto() {;}
	public RailRouteDto(Integer id, Station s1, Station s2, Integer scnt) {
		this.railRouteId= id;
		this.departStation= s1;
		this.destinateStation=s2;
		this.stopStationCount = scnt;
	}
}
