package com.myHighSpeedRail.peter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentDTO {

	@JsonProperty("id")
	private Integer departmentId;
	
	@JsonProperty("name")
	private String departmentName;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
}
