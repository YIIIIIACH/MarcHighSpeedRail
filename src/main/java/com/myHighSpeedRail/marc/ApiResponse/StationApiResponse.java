package com.myHighSpeedRail.marc.ApiResponse;

import java.io.Serializable;

import com.myHighSpeedRail.marc.model.Station;

public class StationApiResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private Station station;
	public StationApiResponse() {;};
	public StationApiResponse(Station station) {
		super();
		this.station = station;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
}
