package com.myHighSpeedRail.peter.model;

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
@Table(name = "employee_title")
public class EmployeeTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_title_id")
	private Integer employee_title_id;

//	@JsonIgnore
	@JsonBackReference(value = "empEt")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

//	@JsonIgnore
//	@JsonManagedReference(value = "titEt")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id_fk")
	private Title title;

	public EmployeeTitle() {
	}

	public Integer getEmployee_title_id() {
		return employee_title_id;
	}

	public void setEmployee_title_id(Integer employee_title_id) {
		this.employee_title_id = employee_title_id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
	
	

}
