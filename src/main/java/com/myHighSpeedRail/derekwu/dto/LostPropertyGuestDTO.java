package com.myHighSpeedRail.derekwu.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LostPropertyGuestDTO {
	public String stationName;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date findDate;
	public String stayStation;
	public String simpleOutward;
	
	public LostPropertyGuestDTO(String stationName, Date findDate, String stayStation, String simpleOutward) {
		super();
		this.stationName = stationName;
		this.findDate = findDate;
		this.stayStation = stayStation;
		this.simpleOutward = simpleOutward;
	}

	public LostPropertyGuestDTO() {
		super();
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Date getFindDate() {
		return findDate;
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}

	public String getStayStation() {
		return stayStation;
	}

	public void setStayStation(String stayStation) {
		this.stayStation = stayStation;
	}

	public String getSimpleOutward() {
		return simpleOutward;
	}

	public void setSimpleOutward(String simpleOutward) {
		this.simpleOutward = simpleOutward;
	}

	
}
