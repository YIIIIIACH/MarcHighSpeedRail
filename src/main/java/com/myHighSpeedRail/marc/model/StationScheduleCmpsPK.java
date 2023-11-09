package com.myHighSpeedRail.marc.model;

import java.io.Serializable;
import java.util.Objects;

public class StationScheduleCmpsPK implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Station station;
	protected Schedule schedule;
	public StationScheduleCmpsPK() {;}
	public StationScheduleCmpsPK(Station st , Schedule sch) {
		this.station=st;
		this.schedule=sch;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(schedule, station);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StationScheduleCmpsPK other = (StationScheduleCmpsPK) obj;
		return Objects.equals(schedule, other.schedule) && Objects.equals(station, other.station);
	}
	
}
