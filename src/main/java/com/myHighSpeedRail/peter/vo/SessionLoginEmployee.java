package com.myHighSpeedRail.peter.vo;

import com.myHighSpeedRail.peter.handler.EmployeeSystemAuthor;

public class SessionLoginEmployee {

	private Integer empId;
	private String empName;
	private EmployeeSystemAuthor esa;

	public SessionLoginEmployee() {

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

	public EmployeeSystemAuthor getEsa() {
		return esa;
	}

	public void setEsa(EmployeeSystemAuthor esa) {
		this.esa = esa;
	}

	@Override
	public String toString() {
		return "SessionLoginEmployee [empId=" + empId + ", empName=" + empName + ", esa=" + esa + "]";
	}
}
