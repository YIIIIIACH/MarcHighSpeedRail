package com.myHighSpeedRail.marc.model;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="station")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Station{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="station_id",nullable=false)
	private Integer stationId;
	@Column(name="station_name",nullable=false)
	private String stationName;
	
//	@OneToMany(mappedBy="stopStation" ,cascade=CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	List<RailRoute> railRoutes;
//	@OneToMany(mappedBy="stopStation" ,cascade=CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference	
//	List<RailRouteStopStation> railRouteStopStations;
	
	
	public Station() {
		;
	}

	public Station( String station_name) {
		
		this.stationName = station_name;
	}
	public Station(Integer sid, String stationName) {
		this.stationId=sid;
		this.stationName= stationName;
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

	@Override
	public int hashCode() {
		return Objects.hash(stationId, stationName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		return this.stationId==other.getStationId();
	}
	
	
}
