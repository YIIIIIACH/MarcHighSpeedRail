package com.myHighSpeedRail.marc.model;

import java.util.Date;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="schedule_template")
public class ScheduleTemplate {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="schedule_template_id", nullable=false)
	private Integer scheduleTemplateId;
	
	@JoinColumn(name="train_id_fk", nullable=false)
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonManagedReference
	private Train train;
	
	@JoinColumn(name="rail_route_id_fk", nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	private RailRoute railRoute;
	
	@Temporal(TemporalType.TIME)
	@Column(name="depart_time", nullable=false)
	private Date departTime;
	
	@Temporal(TemporalType.TIME)
	@Column(name="destinate_time",nullable=false)
	private Date destinateTime;
	
	@Column(name="cost_minute")
	private Integer costMinute;
	public ScheduleTemplate() {;}
	public ScheduleTemplate(Train train, RailRoute railRoute, Date departTime, Date destinateTime, Integer costMinute) {
		super();
		this.train = train;
		this.railRoute = railRoute;
		this.departTime = departTime;
		this.destinateTime = destinateTime;
		this.costMinute = costMinute;
	}

	public Integer getScheduleTemplateId() {
		return scheduleTemplateId;
	}

	public void setScheduleTemplateId(Integer scheduleTemplateId) {
		this.scheduleTemplateId = scheduleTemplateId;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public RailRoute getRailRoute() {
		return railRoute;
	}

	public void setRailRoute(RailRoute railRoute) {
		this.railRoute = railRoute;
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

	public Integer getCostMinute() {
		return costMinute;
	}

	public void setCostMinute(Integer costMinute) {
		this.costMinute = costMinute;
	}
}
