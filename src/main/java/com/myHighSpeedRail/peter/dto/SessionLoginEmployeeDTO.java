package com.myHighSpeedRail.peter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionLoginEmployeeDTO {

	@JsonProperty("id")
	private Integer empId;
	
	@JsonProperty("name")
	private String empName;

	public SessionLoginEmployeeDTO() {

	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	

}
