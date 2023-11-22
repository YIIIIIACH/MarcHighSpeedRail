package com.myHighSpeedRail.marc.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@IdClass(ScheduleSeatCmpsPK.class)
@Entity
@Table(name="schedule_seat_status")
public class ScheduleSeatStatus implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="schedule_id_fk",nullable=false)
	private Schedule schedule;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seat_id_fk",nullable=false)
	private Seat seat;
	
	@Column(name="schedule_status",nullable=false)
	private Long scheduleStatus;
	public ScheduleSeatStatus() {;}
	public ScheduleSeatStatus(Schedule schedule, Seat seat, Long scheduleStatus) {
		super();
		this.schedule = schedule;
		this.seat = seat;
		this.scheduleStatus = scheduleStatus;
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

	public Long getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(Long scheduleStatus) {
		
		this.scheduleStatus = scheduleStatus;
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
		ScheduleSeatStatus other = (ScheduleSeatStatus) obj;
		return Objects.equals(schedule, other.schedule) && Objects.equals(seat, other.seat);
	}
	
}
