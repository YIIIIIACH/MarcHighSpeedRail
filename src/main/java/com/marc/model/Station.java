package com.marc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="station")
public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="station_id")
	private Integer stationId;
	@Column(name="station_name")
	private String stationName;
	
	public Station() {
		;
	}

	public Station( String station_name) {
		
		this.stationName = station_name;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	
}
