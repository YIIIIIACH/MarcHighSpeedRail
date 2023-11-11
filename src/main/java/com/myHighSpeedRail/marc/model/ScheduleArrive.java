package com.myHighSpeedRail.marc.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@IdClass(StationScheduleCmpsPK.class)
@Entity
@Table(name="schedule_arrive")
public class ScheduleArrive implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="station_id_fk",nullable=false)
	private Station station;
	@Id
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="schedule_id_fk",nullable=false)
	private Schedule schedule;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="arrive_time", nullable=false)
	private Date arriveTime;
	public ScheduleArrive() {;}
	public ScheduleArrive(Station st, Schedule sch, Date arr) {
		this.station=st;
		this.schedule=sch;
		this.arriveTime=arr;
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

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
}
