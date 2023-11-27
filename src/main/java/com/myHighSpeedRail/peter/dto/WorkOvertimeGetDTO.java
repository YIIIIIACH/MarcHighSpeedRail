package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkOvertimeGetDTO {

	@JsonProperty("startTime")
	private Date employeeWorkOvertimeStartTime;

	@JsonProperty("endTime")
	private Date employeeWorkOvertimeEndTime;

	@JsonProperty("reason")
	private String employeeWorkOvertimeReason;

	@JsonProperty("audit")
	private String managerWorkOvertimeAudit;

	@JsonProperty("resultSnadDate")
	private Date workOvertimeAuditResultsSandingDate;

	@JsonProperty("CarryForwardDate")
	private Date workOvertimeCarryForwardDate;

	@JsonProperty("ConfirmDate")
	private Date workOvertimeEmployeeConfirmDate;

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

	public Date getWorkOvertimeCarryForwardDate() {
		return workOvertimeCarryForwardDate;
	}

	public void setWorkOvertimeCarryForwardDate(Date workOvertimeCarryForwardDate) {
		this.workOvertimeCarryForwardDate = workOvertimeCarryForwardDate;
	}

	public Date getWorkOvertimeEmployeeConfirmDate() {
		return workOvertimeEmployeeConfirmDate;
	}

	public void setWorkOvertimeEmployeeConfirmDate(Date workOvertimeEmployeeConfirmDate) {
		this.workOvertimeEmployeeConfirmDate = workOvertimeEmployeeConfirmDate;
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
