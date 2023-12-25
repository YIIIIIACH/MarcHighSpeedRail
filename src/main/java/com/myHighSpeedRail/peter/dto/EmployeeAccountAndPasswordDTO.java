package com.myHighSpeedRail.peter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeAccountAndPasswordDTO {

	@JsonProperty("id")
	private Integer empId;

	@JsonProperty("name")
	private String empName;

	@JsonProperty("acc")
	private String account;

	@JsonProperty("psw")
	private String password;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeeAccountAndPasswordDTO [empId=" + empId + ", account=" + account + ", password=" + password
				+ "]";
	}

}
