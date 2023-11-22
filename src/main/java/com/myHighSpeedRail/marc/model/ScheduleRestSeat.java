package com.myHighSpeedRail.marc.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@IdClass(ScheduleRailRouteDiscountCmpsPK.class)
@Entity
@Table(name="schedule_rest_seat")
public class ScheduleRestSeat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn( name="schedule_id_fk",nullable=false)
	private Schedule schedule;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="rail_route_segment_id_fk",nullable=false)
	private RailRouteSegment railRouteSegment;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="discount_id_fk",nullable=false)
	private TicketDiscount ticketDiscount;
	
	@Column(name="rest_seat_amount",nullable=false)
	private Integer restSeatAmount;

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

	public Integer getRestSeatAmount() {
		return restSeatAmount;
	}

	public void setRestSeatAmount(Integer restSeatAmount) {
		this.restSeatAmount = restSeatAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ScheduleRestSeat() {;}
	public ScheduleRestSeat(Schedule schedule, RailRouteSegment railRouteSegment, TicketDiscount ticketDiscount,
			Integer restSeatAmount) {
		super();
		this.schedule = schedule;
		this.railRouteSegment = railRouteSegment;
		this.ticketDiscount = ticketDiscount;
		this.restSeatAmount = restSeatAmount;
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
		ScheduleRestSeat other = (ScheduleRestSeat) obj;
		return Objects.equals(railRouteSegment, other.railRouteSegment) && Objects.equals(schedule, other.schedule)
				&& Objects.equals(ticketDiscount, other.ticketDiscount);
	}
	
	
}
