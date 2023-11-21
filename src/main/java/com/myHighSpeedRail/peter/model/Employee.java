package com.myHighSpeedRail.peter.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeGender="
				+ employeeGender + ", employeeBirth=" + employeeBirth + ", employeeIdNumber=" + employeeIdNumber
				+ ", employeePhoneNumber=" + employeePhoneNumber + ", employeeContactNumber=" + employeeContactNumber
				+ ", employeeEmail=" + employeeEmail + ", employeeResidenceAddress=" + employeeResidenceAddress
				+ ", employeeContactAddress=" + employeeContactAddress + ", employeePhoto=" + employeePhoto
				+ ", employeeArrivalDate=" + employeeArrivalDate + ", employeeSalaryKind=" + employeeSalaryKind
				+ ", employeeBasicSalary=" + employeeBasicSalary + ", employeeAccount=" + employeeAccount
				+ ", employeePassword=" + employeePassword + "]";
	}

	@JsonManagedReference(value = "empSa")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<SystemAuthor> systemAuthor = new LinkedList<SystemAuthor>();

	@JsonManagedReference(value = "empEhd")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalDepartment> employeeHistoricalDepartment = new LinkedList<EmployeeHistoricalDepartment>();

	@JsonManagedReference(value = "empEwoemp")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeWorkOvertime> EmployeeWorkOvertimeByEmployee = new LinkedList<EmployeeWorkOvertime>();

	@JsonManagedReference(value = "empEwoman")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
	private List<EmployeeWorkOvertime> EmployeeWorkOvertimeByManager = new LinkedList<EmployeeWorkOvertime>();

	@JsonManagedReference(value = "empEeq")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeEducationalQualification> employeeEducationalQualification = new LinkedList<EmployeeEducationalQualification>();

	@JsonManagedReference(value = "empEcp")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmergencyContactPerson> emergencyContactPerson = new LinkedList<EmergencyContactPerson>();

	@JsonManagedReference(value = "empEwe")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeWorkExperience> employeeWorkExperience = new LinkedList<EmployeeWorkExperience>();

	@JsonManagedReference(value = "empEhbs")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalBaseSalary> employeeHistoricalBaseSalary = new LinkedList<EmployeeHistoricalBaseSalary>();

	@JsonManagedReference(value = "empElemp")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeaveByEmployee = new LinkedList<EmployeeLeave>();

	@JsonManagedReference(value = "empElman")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeaveByManager = new LinkedList<EmployeeLeave>();

	@JsonManagedReference(value = "empEt")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeTitle> employeeTitle = new LinkedList<EmployeeTitle>();

	public Employee() {
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Date getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(Date employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getEmployeeIdNumber() {
		return employeeIdNumber;
	}

	public void setEmployeeIdNumber(String employeeIdNumber) {
		this.employeeIdNumber = employeeIdNumber;
	}

	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public String getEmployeeContactNumber() {
		return employeeContactNumber;
	}

	public void setEmployeeContactNumber(String employeeContactNumber) {
		this.employeeContactNumber = employeeContactNumber;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeResidenceAddress() {
		return employeeResidenceAddress;
	}

	public void setEmployeeResidenceAddress(String employeeResidenceAddress) {
		this.employeeResidenceAddress = employeeResidenceAddress;
	}

	public String getEmployeeContactAddress() {
		return employeeContactAddress;
	}

	public void setEmployeeContactAddress(String employeeContactAddress) {
		this.employeeContactAddress = employeeContactAddress;
	}

	public String getEmployeePhoto() {
		return employeePhoto;
	}

	public void setEmployeePhoto(String employeePhoto) {
		this.employeePhoto = employeePhoto;
	}

	public Date getEmployeeArrivalDate() {
		return employeeArrivalDate;
	}

	public void setEmployeeArrivalDate(Date employeeArrivalDate) {
		this.employeeArrivalDate = employeeArrivalDate;
	}

	public String getEmployeeSalaryKind() {
		return employeeSalaryKind;
	}

	public void setEmployeeSalaryKind(String employeeSalaryKind) {
		this.employeeSalaryKind = employeeSalaryKind;
	}

	public Integer getEmployeeBasicSalary() {
		return employeeBasicSalary;
	}

	public void setEmployeeBasicSalary(Integer employeeBasicSalary) {
		this.employeeBasicSalary = employeeBasicSalary;
	}

	public String getEmployeeAccount() {
		return employeeAccount;
	}

	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public List<SystemAuthor> getSystemAuthor() {
		return systemAuthor;
	}

	public void setSystemAuthor(List<SystemAuthor> systemAuthor) {
		this.systemAuthor = systemAuthor;
	}

	public List<EmployeeHistoricalDepartment> getEmployeeHistoricalDepartment() {
		return employeeHistoricalDepartment;
	}

	public void setEmployeeHistoricalDepartment(List<EmployeeHistoricalDepartment> employeeHistoricalDepartment) {
		this.employeeHistoricalDepartment = employeeHistoricalDepartment;
	}

	public List<EmployeeWorkOvertime> getEmployeeWorkOvertimeByEmployee() {
		return EmployeeWorkOvertimeByEmployee;
	}

	public void setEmployeeWorkOvertimeByEmployee(List<EmployeeWorkOvertime> employeeWorkOvertimeByEmployee) {
		EmployeeWorkOvertimeByEmployee = employeeWorkOvertimeByEmployee;
	}

	public List<EmployeeWorkOvertime> getEmployeeWorkOvertimeByManager() {
		return EmployeeWorkOvertimeByManager;
	}

	public void setEmployeeWorkOvertimeByManager(List<EmployeeWorkOvertime> employeeWorkOvertimeByManager) {
		EmployeeWorkOvertimeByManager = employeeWorkOvertimeByManager;
	}

	public List<EmployeeEducationalQualification> getEmployeeEducationalQualification() {
		return employeeEducationalQualification;
	}

	public void setEmployeeEducationalQualification(
			List<EmployeeEducationalQualification> employeeEducationalQualification) {
		this.employeeEducationalQualification = employeeEducationalQualification;
	}

	public List<EmergencyContactPerson> getEmergencyContactPerson() {
		return emergencyContactPerson;
	}

	public void setEmergencyContactPerson(List<EmergencyContactPerson> emergencyContactPerson) {
		this.emergencyContactPerson = emergencyContactPerson;
	}

	public List<EmployeeWorkExperience> getEmployeeWorkExperience() {
		return employeeWorkExperience;
	}

	public void setEmployeeWorkExperience(List<EmployeeWorkExperience> employeeWorkExperience) {
		this.employeeWorkExperience = employeeWorkExperience;
	}

	public List<EmployeeHistoricalBaseSalary> getEmployeeHistoricalBaseSalary() {
		return employeeHistoricalBaseSalary;
	}

	public void setEmployeeHistoricalBaseSalary(List<EmployeeHistoricalBaseSalary> employeeHistoricalBaseSalary) {
		this.employeeHistoricalBaseSalary = employeeHistoricalBaseSalary;
	}

	public List<EmployeeLeave> getEmployeeLeaveByEmployee() {
		return employeeLeaveByEmployee;
	}

	public void setEmployeeLeaveByEmployee(List<EmployeeLeave> employeeLeaveByEmployee) {
		this.employeeLeaveByEmployee = employeeLeaveByEmployee;
	}

	public List<EmployeeLeave> getEmployeeLeaveByManager() {
		return employeeLeaveByManager;
	}

	public void setEmployeeLeaveByManager(List<EmployeeLeave> employeeLeaveByManager) {
		this.employeeLeaveByManager = employeeLeaveByManager;
	}

	public List<EmployeeTitle> getEmployeeTitle() {
		return employeeTitle;
	}

	public void setEmployeeTitle(List<EmployeeTitle> employeeTitle) {
		this.employeeTitle = employeeTitle;
	}


	
}
