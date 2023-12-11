package com.myHighSpeedRail.peter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemAuthorMemberDTO {

	@JsonProperty("eId")
	private Integer employeeId;

	@JsonProperty("dId")
	private Integer departmentId;
	
	@JsonProperty("eName")
	private String employeeName;
	
	@JsonProperty("dName")
	private String departmentName;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
}
