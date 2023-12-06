package com.myHighSpeedRail.peter.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "department_name")
	private String departmentName;

//	@JsonIgnore
	@JsonManagedReference(value = "depSa")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
	private List<SystemAuthor> systemAuthor = new LinkedList<SystemAuthor>();

	@JsonIgnore
//	@JsonBackReference(value = "ehdDep")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalDepartment> employeeHistoricalDepartment = new LinkedList<EmployeeHistoricalDepartment>();

	public Department() {
	}

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

	public List<SystemAuthor> getSystemAuthor() {
		return systemAuthor;
	}

	public void setSystemAuthor(List<SystemAuthor> systemAuthor) {
		this.systemAuthor = systemAuthor;
	}

	public List<EmployeeHistoricalDepartment> getEmployeeHistoricalDepartment() {
		return employeeHistoricalDepartment;
	}

	public void setEmployeeHistoricalDepartment(List<EmployeeHistoricalDepartment> employeeHistoricalDepartment) {
		this.employeeHistoricalDepartment = employeeHistoricalDepartment;
	}

}
