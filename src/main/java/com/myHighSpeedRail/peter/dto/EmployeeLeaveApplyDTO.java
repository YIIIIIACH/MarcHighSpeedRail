package com.myHighSpeedRail.peter.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeLeaveApplyDTO {

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("startTime")
	private Date employeeLeaveStartTime;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonProperty("endTime")
	private Date employeeWorkOvertimeEndTime;

	@JsonProperty("reason")
	private String employeeLeaveReason;

	@JsonProperty("managerId")
	private Integer managerId;

	@JsonProperty("employeeId")
	private Integer employeeId;

	@JsonProperty("leaveKind")
	private String employeeLeaveKind;
	
}
