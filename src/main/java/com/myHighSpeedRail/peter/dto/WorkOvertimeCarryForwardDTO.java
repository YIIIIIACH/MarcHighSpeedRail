package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkOvertimeCarryForwardDTO {

	@JsonProperty("workOvertimeId")
	private Integer employeeWorkOvertimeId;

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

	@JsonProperty("audit")
	private String managerWorkOvertimeAudit;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("resultSnadDate")
	private Date workOvertimeAuditResultsSandingDate;

	@JsonProperty("managerId")
	private Integer managerId;

	@JsonProperty("employeeId")
	private Integer employeeId;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("CarryForwardDate")
	private Date workOvertimeCarryForwardDate;

	public Integer getEmployeeWorkOvertimeId() {
		return employeeWorkOvertimeId;
	}

	public void setEmployeeWorkOvertimeId(Integer employeeWorkOvertimeId) {
		this.employeeWorkOvertimeId = employeeWorkOvertimeId;
	}

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

	public String getManagerWorkOvertimeAudit() {
		return managerWorkOvertimeAudit;
	}

	public void setManagerWorkOvertimeAudit(String managerWorkOvertimeAudit) {
		this.managerWorkOvertimeAudit = managerWorkOvertimeAudit;
	}

	public Date getWorkOvertimeAuditResultsSandingDate() {
		return workOvertimeAuditResultsSandingDate;
	}

	public void setWorkOvertimeAuditResultsSandingDate(Date workOvertimeAuditResultsSandingDate) {
		this.workOvertimeAuditResultsSandingDate = workOvertimeAuditResultsSandingDate;
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

	public Date getWorkOvertimeCarryForwardDate() {
		return workOvertimeCarryForwardDate;
	}

	public void setWorkOvertimeCarryForwardDate(Date workOvertimeCarryForwardDate) {
		this.workOvertimeCarryForwardDate = workOvertimeCarryForwardDate;
	}

}
