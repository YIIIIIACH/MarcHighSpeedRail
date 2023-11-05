package com.myHighSpeedRail.marc.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="station")
public class Station{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="station_id",nullable=false)
	private Integer stationId;
	@Column(name="station_name",nullable=false)
	private String stationName;
	
//	@OneToMany
//	List<RailRoute> railRoutes;
	
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
