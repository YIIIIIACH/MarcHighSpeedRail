package com.myHighSpeedRail.marc.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name="rail_route_segment")
public class RailRouteSegment {
	public RailRouteSegment() {;}
	public RailRouteSegment(RailRoute railRoute, Station startStation, Station endStation,
			Integer railRouteSegmentTicketPrice, Integer railRouteSegmentDurationMinute) {
		super();
		this.railRoute = railRoute;
		this.startStation = startStation;
		this.endStation = endStation;
		this.railRouteSegmentTicketPrice = railRouteSegmentTicketPrice;
		this.railRouteSegmentDurationMinute = railRouteSegmentDurationMinute;
	}

	@Id
	@Column(name="rail_route_segment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer railRouteSegmentId;
	
	@JoinColumn(name="rail_route_id_fk" ,nullable=false)
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	private RailRoute railRoute;
	
	@JoinColumn(name="start_station_id_fk",nullable=false)
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	private Station startStation;
	
	@JoinColumn(name="end_station_id_fk",nullable=false)
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	private Station endStation;
	
	@Column(name="rail_route_segment_ticket_price", nullable=false)
	private Integer railRouteSegmentTicketPrice;
	
	@Column(name="rail_route_segment_duration_minute",nullable=false)
	private Integer railRouteSegmentDurationMinute;

	public Integer getRailRouteSegmentId() {
		return railRouteSegmentId;
	}

	public void setRailRouteSegmentId(Integer railRouteSegmentId) {
		this.railRouteSegmentId = railRouteSegmentId;
	}

	public RailRoute getRailRoute() {
		return railRoute;
	}

	public void setRailRoute(RailRoute railRoute) {
		this.railRoute = railRoute;
	}

	public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}

	public Integer getRailRouteSegmentTicketPrice() {
		return railRouteSegmentTicketPrice;
	}

	public void setRailRouteSegmentTicketPrice(Integer railRouteSegmentTicketPrice) {
		this.railRouteSegmentTicketPrice = railRouteSegmentTicketPrice;
	}

	public Integer getRailRouteSegmentDurationMinute() {
		return railRouteSegmentDurationMinute;
	}

	public void setRailRouteSegmentDurationMinute(Integer railRouteSegmentDurationMinute) {
		this.railRouteSegmentDurationMinute = railRouteSegmentDurationMinute;
	}
	@Override
	public int hashCode() {
		return Objects.hash(railRouteSegmentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RailRouteSegment other = (RailRouteSegment) obj;
		return Objects.equals(railRouteSegmentId, other.railRouteSegmentId);
	}
	
}
