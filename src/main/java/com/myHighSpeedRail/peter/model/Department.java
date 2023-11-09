package com.myHighSpeedRail.peter.model;

import java.util.LinkedList;
import java.util.List;

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
@Table(name="department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="department_id")
	private Integer departmentId;
	
	@Column(name="department_name")
	private String departmentName;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department",cascade = CascadeType.ALL)
	private List<SystemAuthor> systemAuthor = new LinkedList<SystemAuthor>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department",cascade = CascadeType.ALL)
	private List<EmployeeHistoricalDepartment> employeeHistoricalDepartment = new LinkedList<EmployeeHistoricalDepartment>();

	public Department() {
	}

}
