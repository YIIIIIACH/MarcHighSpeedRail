package com.myHighSpeedRail.marc.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="schedule")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="schedule_id")
	private Integer scheduleId;
	
	@JoinColumn(name="rail_route_id_fk",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY )
	private RailRoute railRoute;
	
	@JoinColumn(name="train_id_fk",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Train train;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="depart_time",nullable=false)
	private Date departTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="desitnate_time",nullable=false)
	private Date destinateTime;
	
	@Column(name="duration_minute")
	private Integer durationTime;
	public Schedule() {;}
	public Schedule(RailRoute railRoute, Train train, Date departTime, Date destinateTime, Integer durationTime) {
		super();
		this.railRoute = railRoute;
		this.train = train;
		this.departTime = departTime;
		this.destinateTime = destinateTime;
		this.durationTime = durationTime;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public RailRoute getRailRoute() {
		return railRoute;
	}

	public void setRailRoute(RailRoute railRoute) {
		this.railRoute = railRoute;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}

	public Date getDestinateTime() {
		return destinateTime;
	}

	public void setDestinateTime(Date destinateTime) {
		this.destinateTime = destinateTime;
	}

	public Integer getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}
	@Override
	public int hashCode() {
		return Objects.hash(scheduleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return Objects.equals(scheduleId, other.scheduleId);
	}
	
}
