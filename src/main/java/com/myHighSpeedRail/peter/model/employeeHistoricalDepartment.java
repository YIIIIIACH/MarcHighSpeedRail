package com.myHighSpeedRail.peter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_historical_department")
public class employeeHistoricalDepartment {

	@Column(name="employee_historical_department_id")
	private Integer employee_historical_department_id;
	
	@Column(name="department_effective_date")
	private Integer departmentEffectiveDate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="employee_id_fk")
	private Integer employeeIdFk;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="department_id_fk")
	private Integer departmentIdFk;
	
	public employeeHistoricalDepartment() {
	}

}
