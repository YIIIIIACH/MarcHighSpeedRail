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
@Table(name="educational_qualifications")
public class EmployeeEducationalQualification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="educational_qualifications_id")
	private Integer educationalQualificationsId;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="school_department")
	private String schoolDepartment;
	
	@Column(name="admission_date")
	private Date admissionDate;
	
	@Column(name="date_of_leaving_school")
	private Date dateOfLeavingSchool;
	
	@Column(name="academic_status")
	private String academicStatus;
	
	@JsonBackReference(value = "empEeq")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id_fk")
	private Employee employee;

	public EmployeeEducationalQualification() {
	}

}
