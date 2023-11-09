package com.myHighSpeedRail.marc.model;

import java.io.Serializable;
import java.util.Objects;

public class ScheduleRailRouteDiscountCmpsPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Schedule schedule;	
	private RailRouteSegment railRouteSegment;
	private TicketDiscount ticketDiscount;
	public ScheduleRailRouteDiscountCmpsPK() {;}
	public ScheduleRailRouteDiscountCmpsPK(Schedule schedule, RailRouteSegment railRouteSegment, TicketDiscount ticketDiscount) {
		super();
		this.schedule = schedule;
		this.railRouteSegment = railRouteSegment;
		this.ticketDiscount = ticketDiscount;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public RailRouteSegment getRailRouteSegment() {
		return railRouteSegment;
	}
	public void setRailRouteSegment(RailRouteSegment railRouteSegment) {
		this.railRouteSegment = railRouteSegment;
	}
	public TicketDiscount getTicketDiscount() {
		return ticketDiscount;
	}
	public void setTicketDiscount(TicketDiscount ticketDiscount) {
		this.ticketDiscount = ticketDiscount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(railRouteSegment, schedule, ticketDiscount);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleRailRouteDiscountCmpsPK other = (ScheduleRailRouteDiscountCmpsPK) obj;
		return railRouteSegment.getRailRouteSegmentId()==other.getRailRouteSegment().getRailRouteSegmentId() && schedule.getScheduleId()==other.getSchedule().getScheduleId()
				&& ticketDiscount.getTicketDiscountId()==other.getTicketDiscount().getTicketDiscountId();
	}
	
	
}
