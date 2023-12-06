package com.myHighSpeedRail.peter.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="employee_educational_qualifications")
public class EmployeeEducationalQualifications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_educational_qualifications_id")
	private Integer employeeEducationalQualificationsId;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="school_department")
	private String schoolDepartment;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name="admission_date")
	private Date admissionDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name="date_of_leaving_school")
	private Date dateOfLeavingSchool;
	
	@Column(name="academic_status")
	private String academicStatus;
	
//	@JsonIgnore
	@JsonBackReference(value = "empEeq")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id_fk")
	private Employee employee;

	public EmployeeEducationalQualifications() {
	}

	public Integer getEmployeeEducationalQualificationsId() {
		return employeeEducationalQualificationsId;
	}

	public void setEmployeeEducationalQualificationsId(Integer employeeEducationalQualificationsId) {
		this.employeeEducationalQualificationsId = employeeEducationalQualificationsId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolDepartment() {
		return schoolDepartment;
	}

	public void setSchoolDepartment(String schoolDepartment) {
		this.schoolDepartment = schoolDepartment;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDateOfLeavingSchool() {
		return dateOfLeavingSchool;
	}

	public void setDateOfLeavingSchool(Date dateOfLeavingSchool) {
		this.dateOfLeavingSchool = dateOfLeavingSchool;
	}

	public String getAcademicStatus() {
		return academicStatus;
	}

	public void setAcademicStatus(String academicStatus) {
		this.academicStatus = academicStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
