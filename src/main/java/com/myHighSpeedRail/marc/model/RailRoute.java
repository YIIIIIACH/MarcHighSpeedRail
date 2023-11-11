package com.myHighSpeedRail.marc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="rail_route")
public class RailRoute{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rail_route_id",nullable=false)
	private Integer railRouteId;
	
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference  /// will avoid inifinition Loop for reference .
	@JoinColumn(name= "depart_station_id_fk",nullable=false)
	private Station departStation;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name= "destinate_station_id_fk",nullable= false)
	private Station destinateStation;
	
	@Column(name="stop_station_count", nullable=false)
	private Integer stopStationCount;
	public RailRoute() {;}
	public RailRoute(Station s1, Station s2, Integer cnt) {
		this.departStation=s1;
		this.destinateStation=s2;
		this.stopStationCount=cnt;
	}
	
	public Integer getRailRouteId() {
		return railRouteId;
	}
	public void setRailRouteId(Integer railRouteId) {
		this.railRouteId = railRouteId;
	}
	public Station getDepartStation() {
		return departStation;
	}
	public void setDepartStation(Station departStation) {
		this.departStation = departStation;
	}
	public Station getDestinateStation() {
		return destinateStation;
	}
	public void setDestinateStation(Station destinateStation) {
		this.destinateStation = destinateStation;
	}
	public Integer getStopStationCount() {
		return stopStationCount;
	}
	public void setStopStationCount(Integer stopStationCount) {
		this.stopStationCount = stopStationCount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(railRouteId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RailRoute other = (RailRoute) obj;
		return Objects.equals(railRouteId, other.railRouteId);
	}
}
