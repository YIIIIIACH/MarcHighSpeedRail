package com.myHighSpeedRail.peter.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_data")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "employee_gender")
	private String employeeGender;
	
	@Column(name = "employee_birth")
	private Date employeeBirth;
	
	@Column(name = "employee_id_number")
	private String employeeIdNumber;
	
	@Column(name = "employee_phone_number")
	private String employeePhoneNumber;
	
	@Column(name = "employee_contact_number")
	private String employeeContactNumber;
	
	@Column(name = "employee_email")
	private String employeeEmail;
	
	@Column(name = "employee_residence_address")
	private String employeeResidenceAddress;
	
	@Column(name = "employee_contact_address")
	private String employeeContactAddress;
	
	@Column(name = "employee_photo")
	private String employeePhoto;// 想要在資料庫裡寫資料夾邏輯
	
	@Column(name = "employee_arrival_date")
	private Date employeeArrivalDate;
	
	@Column(name = "employee_salary_kind")
	private String employeeSalaryKind;
	
	@Column(name = "employee_basic_salary")
	private Integer employeeBasicSalary;
	
	@Column(name = "employee_account")
	private String employeeAccount;
	
	@Column(name = "employee_password")
	private String employeePassword;

	public Employee() {
	}
	
	

}
