package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeWorkOvertimeApplyDTO {

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("startTime")
	private Date employeeWorkOvertimeStartTime;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("endTime")
	private Date employeeWorkOvertimeEndTime;

	@JsonProperty("reason")
	private String employeeWorkOvertimeReason;

	@JsonProperty("managerId")
	private Integer managerId;

	@JsonProperty("employeeId")
	private Integer employeeId;

	public Date getEmployeeWorkOvertimeStartTime() {
		return employeeWorkOvertimeStartTime;
	}

	public void setEmployeeWorkOvertimeStartTime(Date employeeWorkOvertimeStartTime) {
		this.employeeWorkOvertimeStartTime = employeeWorkOvertimeStartTime;
	}

	public Date getEmployeeWorkOvertimeEndTime() {
		return employeeWorkOvertimeEndTime;
	}

	public void setEmployeeWorkOvertimeEndTime(Date employeeWorkOvertimeEndTime) {
		this.employeeWorkOvertimeEndTime = employeeWorkOvertimeEndTime;
	}

	public String getEmployeeWorkOvertimeReason() {
		return employeeWorkOvertimeReason;
	}

	public void setEmployeeWorkOvertimeReason(String employeeWorkOvertimeReason) {
		this.employeeWorkOvertimeReason = employeeWorkOvertimeReason;
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
	

}
