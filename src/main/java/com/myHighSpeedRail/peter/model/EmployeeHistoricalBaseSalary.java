package com.myHighSpeedRail.peter.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_historical_base_salary")
public class EmployeeHistoricalBaseSalary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="historical_base_salary_id")
	private Integer historicalBaseSalaryId;
	
	@Column(name="historical_base_salary_amount")
	private Integer historicalBaseSalaryAmount;
	
	@Column(name="historical_base_salary_kind")
	private String idhistoricalBaseSalaryKind;
	
	@Column(name="salary_effective_date")
	private Date salaryEffectiveDate;
	
	@JsonBackReference(value = "empEhbs")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id_fk")
	private Employee employee;
	
	
	public EmployeeHistoricalBaseSalary() {
	}

}
