package com.myHighSpeedRail.peter.model;

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
@Table(name = "employee_title")
public class EmployeeTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_title_id")
	private Integer employee_title_id;

	@JsonBackReference(value = "empEt")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	@JsonBackReference(value = "titEt")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id_fk")
	private Title title;

	public EmployeeTitle() {
	}

}
