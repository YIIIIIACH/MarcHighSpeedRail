package com.myHighSpeedRail.peter.model;

import java.util.Date;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<SystemAuthor> systemAuthor = new LinkedList<SystemAuthor>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalDepartment> employeeHistoricalDepartment = new LinkedList<EmployeeHistoricalDepartment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeWorkOvertime> EmployeeWorkOvertimeByEmployee = new LinkedList<EmployeeWorkOvertime>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
	private List<EmployeeWorkOvertime> EmployeeWorkOvertimeByManager = new LinkedList<EmployeeWorkOvertime>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeEducationalQualification> employeeEducationalQualification = new LinkedList<EmployeeEducationalQualification>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmergencyContactPerson> emergencyContactPerson = new LinkedList<EmergencyContactPerson>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeWorkExperience> employeeWorkExperience = new LinkedList<EmployeeWorkExperience>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalBaseSalary> employeeHistoricalBaseSalary = new LinkedList<EmployeeHistoricalBaseSalary>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeaveByEmployee = new LinkedList<EmployeeLeave>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeaveByManager = new LinkedList<EmployeeLeave>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeTitle> employeeTitle = new LinkedList<EmployeeTitle>();

	public Employee() {
	}

}
