package com.myHighSpeedRail.marc.ApiResponse;

import java.util.List;

import com.myHighSpeedRail.marc.model.Station;

public class StationListApiResponse {
	private List<Station> stationList;
	public StationListApiResponse() {;}
	
	public StationListApiResponse(List<Station> stationList) {
		super();
		this.stationList = stationList;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}
	
}
