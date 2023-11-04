package com.marc.model;

import java.io.Serializable;
import java.util.Objects;

public class RailRouteCmpsPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected RailRoute railRoute;
	protected Integer railRouteStopStationSequence;
	protected Station stopStation;
	public RailRouteCmpsPK() {;}
	public RailRouteCmpsPK(RailRoute rr, Integer rrsss, Station  ss) {
		this.railRoute=rr;
		this.railRouteStopStationSequence=rrsss;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(railRoute, railRouteStopStationSequence, stopStation);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RailRouteCmpsPK other = (RailRouteCmpsPK) obj;
		return Objects.equals(railRoute, other.railRoute)
				&& Objects.equals(railRouteStopStationSequence, other.railRouteStopStationSequence)
				&& Objects.equals(stopStation, other.stopStation);
	}
	
}
