package com.marc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@IdClass(RailRouteCmpsPK.class)
@Entity
@Table(name="rail_route_stop_station")
public class RailRouteStopStation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnore
	@JsonManagedReference
	@JoinColumn(name="rail_route_id_fk",nullable=false)
	private RailRoute railRoute;
	
	@Id
	@Column(name="rail_route_stop_station_sequence",nullable=false)
	private Integer railRouteStopStationSequence;
	
	@Id
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnore
	@JsonManagedReference
	@JoinColumn(name="stop_station_id_fk",nullable=false)
	private Station stopStation;
	
	public RailRouteStopStation() {;}
	
	public RailRouteStopStation(RailRoute rr, Integer rrsss, Station ss) {
		this.railRoute = rr;
		this.railRouteStopStationSequence= rrsss;
		this.stopStation = ss;
	}

	public RailRoute getRailRoute() {
		return railRoute;
	}

	public void setRailRoute(RailRoute railRoute) {
		this.railRoute = railRoute;
	}

	public Integer getRailRouteStopStationSequence() {
		return railRouteStopStationSequence;
	}

	public void setRailRouteStopStationSequence(Integer railRouteStopStationSequence) {
		this.railRouteStopStationSequence = railRouteStopStationSequence;
	}

	public Station getStopStation() {
		return stopStation;
	}

	public void setStopStation(Station stopStation) {
		this.stopStation = stopStation;
	}
	
}
