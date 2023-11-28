package com.myHighSpeedRail.derekwu.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="pessenger_lost")
public class PessengerLost {
	//旅客登記遺失物
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="psg_lost_id",nullable=false)
	private Integer psgLostId;
	
	@Column(name="member_id",nullable=true)
	private Integer memberId;
	
	@Column(name="psg_name",nullable=false)
	private String psgName;
	
	@Column(name="phone_number",nullable=false)
	private String phoneNumber;
	
	@Column(name="trip_id",nullable=true)
	private Integer tripId;
	
	@Column(name="station_name",nullable=true)
	private String stationName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="begin_date",nullable=false)
	private Date beginDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_date",nullable=false)
	private Date lastDate;
	
	@Column(name="lost_outward",nullable=false)
	private String lostOutward;
	
	
	public PessengerLost() {
	}

	public PessengerLost(Integer psgLostId, Integer memberId, String psgName, String phoneNumber, Integer tripId,
			String stationName, Date beginDate, Date lastDate, String lostOutward) {
		this.psgLostId = psgLostId;
		this.memberId = memberId;
		this.psgName = psgName;
		this.phoneNumber = phoneNumber;
		this.tripId = tripId;
		this.stationName = stationName;
		this.beginDate = beginDate;
		this.lastDate = lastDate;
		this.lostOutward = lostOutward;
	}

	public Integer getPsgLostId() {
		return psgLostId;
	}

	public void setPsgLostId(Integer psgLostId) {
		this.psgLostId = psgLostId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getPsgName() {
		return psgName;
	}

	public void setPsgName(String psgName) {
		this.psgName = psgName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getLostOutward() {
		return lostOutward;
	}

	public void setLostOutward(String lostOutward) {
		this.lostOutward = lostOutward;
	}
	
	

}
