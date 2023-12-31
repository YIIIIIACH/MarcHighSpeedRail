package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeLeaveApplyDTO {

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("startTime")
	private Date employeeLeaveStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("endTime")
	private Date employeeLeaveEndTime;

	@JsonProperty("reason")
	private String employeeLeaveReason;

	@JsonProperty("managerId")
	private Integer managerId;

	@JsonProperty("employeeId")
	private Integer employeeId;

	@JsonProperty("leaveKind")
	private String employeeLeaveKind;

	public Date getEmployeeLeaveStartTime() {
		return employeeLeaveStartTime;
	}

	public void setEmployeeLeaveStartTime(Date employeeLeaveStartTime) {
		this.employeeLeaveStartTime = employeeLeaveStartTime;
	}

	public Date getEmployeeLeaveEndTime() {
		return employeeLeaveEndTime;
	}

	public void setEmployeeLeaveEndTime(Date employeeLeaveEndTime) {
		this.employeeLeaveEndTime = employeeLeaveEndTime;
	}

	public String getEmployeeLeaveReason() {
		return employeeLeaveReason;
	}

	public void setEmployeeLeaveReason(String employeeLeaveReason) {
		this.employeeLeaveReason = employeeLeaveReason;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeLeaveKind() {
		return employeeLeaveKind;
	}

	public void setEmployeeLeaveKind(String employeeLeaveKind) {
		this.employeeLeaveKind = employeeLeaveKind;
	}

	
}
