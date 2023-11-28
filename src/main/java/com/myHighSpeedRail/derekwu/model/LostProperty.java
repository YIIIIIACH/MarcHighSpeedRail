package com.myHighSpeedRail.derekwu.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="lost_property")
public class LostProperty {
	//遺失物紀錄
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lost_property_id",nullable=false)
	private Integer lostPropertyId;
	
	@Column(name="trip_id",nullable=true)
	private Integer tripId;
	
	@Column(name="station_name",nullable=true)
	private String stationName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="find_date",nullable=false)
	private Date findDate;
	
	@Column(name="stay_station",nullable=false)
	private String stayStation;
	
	@Column(name="simple_outward",nullable=false)
	private String simpleOutward;
	
	@Column(name="detail_outward",nullable=false)
	private String detailOutward;
	
	@Column(name="lost_photo",nullable=false)
	private String lostPhoto;
	
	public LostProperty() {
		
	}
	
	public LostProperty(Integer lostPropertyId, Integer tripId, String stationName, Date findDate, String stayStation,
			String simpleOutward, String detailOutward, String lostPhoto) {
		
		this.lostPropertyId = lostPropertyId;
		this.tripId = tripId;
		this.stationName = stationName;
		this.findDate = findDate;
		this.stayStation = stayStation;
		this.simpleOutward = simpleOutward;
		this.detailOutward = detailOutward;
		this.lostPhoto = lostPhoto;
	}
	public LostProperty(Integer tripId, String stationName, Date findDate, String stayStation,
			String simpleOutward, String detailOutward, String lostPhoto) {
		
		this.tripId = tripId;
		this.stationName = stationName;
		this.findDate = findDate;
		this.stayStation = stayStation;
		this.simpleOutward = simpleOutward;
		this.detailOutward = detailOutward;
		this.lostPhoto = lostPhoto;
	}
	public LostProperty(String stationName, Date findDate, String stayStation,
			String simpleOutward) {				
		this.stationName = stationName;
		this.findDate = findDate;
		this.stayStation = stayStation;
		this.simpleOutward = simpleOutward;		
	}

	public Integer getLostPropertyId() {
		return lostPropertyId;
	}

	public void setLostPropertyId(Integer lostPropertyId) {
		this.lostPropertyId = lostPropertyId;
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

	public Date getFindDate() {
		return findDate;
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}

	public String getStayStation() {
		return stayStation;
	}

	public void setStayStation(String stayStation) {
		this.stayStation = stayStation;
	}

	public String getSimpleOutward() {
		return simpleOutward;
	}

	public void setSimpleOutward(String simpleOutward) {
		this.simpleOutward = simpleOutward;
	}

	public String getDetailOutward() {
		return detailOutward;
	}

	public void setDetailOutward(String detailOutward) {
		this.detailOutward = detailOutward;
	}

	public String getLostPhoto() {
		return lostPhoto;
	}

	public void setLostPhoto(String lostPhoto) {
		this.lostPhoto = lostPhoto;
	}

	@Override
	public String toString() {
		return "LostProperty [lostPropertyId=" + lostPropertyId + ", tripId=" + tripId + ", stationName=" + stationName
				+ ", findDate=" + findDate + ", stayStation=" + stayStation + ", simpleOutward=" + simpleOutward
				+ ", detailOutward=" + detailOutward + ", lostPhoto=" + lostPhoto + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(detailOutward, findDate, lostPhoto, lostPropertyId, simpleOutward, stationName, stayStation,
				tripId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LostProperty other = (LostProperty) obj;
		return Objects.equals(detailOutward, other.detailOutward) && Objects.equals(findDate, other.findDate)
				&& Objects.equals(lostPhoto, other.lostPhoto) && Objects.equals(lostPropertyId, other.lostPropertyId)
				&& Objects.equals(simpleOutward, other.simpleOutward) && Objects.equals(stationName, other.stationName)
				&& Objects.equals(stayStation, other.stayStation) && Objects.equals(tripId, other.tripId);
	}
	
	
	
	
	
	
	
	
	
	

}
