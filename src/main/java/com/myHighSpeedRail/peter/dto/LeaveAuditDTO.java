package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeaveAuditDTO {

	@JsonProperty("elId")
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

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("resultSnadDate")
	private Date leaveAuditResultsSandingDate;
	
	@JsonProperty("leaveKind")
	private String employeeLeaveKind;

	@JsonProperty("managerId")
	private Integer managerId;

	@JsonProperty("employeeId")
	private Integer employeeId;
	
	@JsonProperty("dis")
	private String discription;
	
	@JsonProperty("count")
	private Integer count;

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

	public Date getLeaveAuditResultsSandingDate() {
		return leaveAuditResultsSandingDate;
	}

	public void setLeaveAuditResultsSandingDate(Date leaveAuditResultsSandingDate) {
		this.leaveAuditResultsSandingDate = leaveAuditResultsSandingDate;
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

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
