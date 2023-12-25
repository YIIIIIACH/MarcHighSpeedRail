package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeReadLeaveDTO {

	@JsonProperty("leaveId")
	private Integer employeeLeaveId;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("startTime")
	private Date employeeLeaveStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("endTime")
	private Date employeeLeaveEndTime;

	@JsonProperty("reason")
	private String employeeLeaveReason;

	@JsonProperty("audit")
	private String managerLeaveAudit;

	@JsonProperty("employeeId")
	private Integer employeeId;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("readTime")
	private Date employeeReadTime;

	public Integer getEmployeeLeaveId() {
		return employeeLeaveId;
	}

	public void setEmployeeLeaveId(Integer employeeLeaveId) {
		this.employeeLeaveId = employeeLeaveId;
	}

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

	public String getManagerLeaveAudit() {
		return managerLeaveAudit;
	}

	public void setManagerLeaveAudit(String managerLeaveAudit) {
		this.managerLeaveAudit = managerLeaveAudit;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getEmployeeReadTime() {
		return employeeReadTime;
	}

	public void setEmployeeReadTime(Date employeeReadTime) {
		this.employeeReadTime = employeeReadTime;
	}

}
