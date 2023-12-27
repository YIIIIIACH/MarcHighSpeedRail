package com.myHighSpeedRail.derekwu.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FindLostDetailDTO {

	public Integer findLostId;
	public Integer lostPropertyId;
	public String simpleOutward;
	public String detailOutward;
	public String stayStation;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date findLostDate;
	public FindLostDetailDTO(Integer findLostId, Integer lostPropertyId, String simpleOutward, String detailOutward,
			String stayStation, Date findLostDate) {
		super();
		this.findLostId = findLostId;
		this.lostPropertyId = lostPropertyId;
		this.simpleOutward = simpleOutward;
		this.detailOutward = detailOutward;
		this.stayStation = stayStation;
		this.findLostDate = findLostDate;
	}
	public FindLostDetailDTO() {
		super();
	}
	public Integer getFindLostId() {
		return findLostId;
	}
	public void setFindLostId(Integer findLostId) {
		this.findLostId = findLostId;
	}
	public Integer getLostPropertyId() {
		return lostPropertyId;
	}
	public void setLostPropertyId(Integer lostPropertyId) {
		this.lostPropertyId = lostPropertyId;
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
	public String getStayStation() {
		return stayStation;
	}
	public void setStayStation(String stayStation) {
		this.stayStation = stayStation;
	}
	public Date getFindLostDate() {
		return findLostDate;
	}
	public void setFindLostDate(Date findLostDate) {
		this.findLostDate = findLostDate;
	}
	
	
}
