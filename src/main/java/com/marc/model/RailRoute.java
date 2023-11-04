package com.marc.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="rail_route")
public class RailRoute{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rail_route_id")
	private Integer railRouteId;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnore  // will fix Lazy loading on many to one problem
	@JsonManagedReference  /// will avoid inifinition Loop for reference .
	@JoinColumn(name= "depart_station_id_fk")
	private Station departStation;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnore
	@JsonManagedReference
	@JoinColumn(name= "destinate_station_id_fk")
	private Station destinateStation;
	
	@Column(name="stop_station_count")
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
}
