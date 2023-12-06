package com.myHighSpeedRail.peter.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name="employee_work_experience")
public class EmployeeWorkExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_work_experience_id")
	private Integer employeeWorkExperienceId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="employment_date")
	private Date employmentDate;
	
	@Column(name="resignation_date")
	private Date resignationDate;
	
	@Column(name="salary_amount")
	private Integer salaryAmount;
	
	@Column(name="salary_kind")
	private String salaryKind;
	
//	@JsonIgnore
	@JsonBackReference(value = "empEwe")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id_fk")
	private Employee employee;
	
	public EmployeeWorkExperience() {
	}

	public Integer getEmployeeWorkExperienceId() {
		return employeeWorkExperienceId;
	}

	public void setEmployeeWorkExperienceId(Integer employeeWorkExperienceId) {
		this.employeeWorkExperienceId = employeeWorkExperienceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Date getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}

	public Integer getSalaryAmount() {
		return salaryAmount;
	}

	public void setSalaryAmount(Integer salaryAmount) {
		this.salaryAmount = salaryAmount;
	}

	public String getSalaryKind() {
		return salaryKind;
	}

	public void setSalaryKind(String salaryKind) {
		this.salaryKind = salaryKind;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
