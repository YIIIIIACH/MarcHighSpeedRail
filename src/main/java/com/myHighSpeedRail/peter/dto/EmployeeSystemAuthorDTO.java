package com.myHighSpeedRail.peter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myHighSpeedRail.peter.handler.EmployeeSystemAuthor;

public class EmployeeSystemAuthorDTO {

	@JsonProperty("eId")
	private Integer employeeId;

	@JsonProperty("dId")
	private Integer departmentId;
	
	@JsonProperty("eName")
	private String employeeName;
	
	@JsonProperty("dName")
	private String departmentName;
	
//	@JsonProperty("saId")
//	private Integer systemAuthorId;

	@JsonProperty("saString")
	private EmployeeSystemAuthor systemAuthor;

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

//	public Integer getSystemAuthorId() {
//		return systemAuthorId;
//	}
//
//	public void setSystemAuthorId(Integer systemAuthorId) {
//		this.systemAuthorId = systemAuthorId;
//	}

	public EmployeeSystemAuthor getSystemAuthor() {
		return systemAuthor;
	}

	public void setSystemAuthor(EmployeeSystemAuthor systemAuthors) {
		this.systemAuthor = systemAuthors;
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

	@Override
	public String toString() {
		return "EmployeeSystemAuthorDTO [employeeId=" + employeeId + ", departmentId=" + departmentId
				+ ", systemAuthor=" + systemAuthor + "]";
	}

}
