package com.myHighSpeedRail.marc.model;

import java.io.Serializable;
import java.util.Objects;

public class ScheduleSeatCmpsPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Schedule schedule;
	private Seat seat;
	public ScheduleSeatCmpsPK() {;}
	public ScheduleSeatCmpsPK(Schedule schedule, Seat seat) {
		super();
		this.schedule = schedule;
		this.seat = seat;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(schedule, seat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleSeatCmpsPK other = (ScheduleSeatCmpsPK) obj;
		return Objects.equals(schedule, other.schedule) && Objects.equals(seat, other.seat);
	}
	
}
