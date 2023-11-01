package com.marc.model;

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
	private Integer station_id;
	
	private String station_name;
	public Station() {
		;
	}

	public Station( String station_name) {
		
		this.station_name = station_name;
	}

	public Integer getStation_id() {
		return station_id;
	}

	public void setStation_id(Integer station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	
}
