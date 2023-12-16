package com.myHighSpeedRail.peter.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
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

//	@JsonIgnore
	@JsonManagedReference(value = "empSa")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<SystemAuthor> systemAuthor = new LinkedList<SystemAuthor>();

//	@JsonIgnore
	@JsonManagedReference(value = "empEhd")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalDepartment> employeeHistoricalDepartment = new LinkedList<EmployeeHistoricalDepartment>();

//	@JsonManagedReference(value = "empEwoemp")
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeWorkOvertime> employeeWorkOvertimeByEmployee = new LinkedList<EmployeeWorkOvertime>();

//	@JsonManagedReference(value = "empEwoman")
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
	private List<EmployeeWorkOvertime> employeeWorkOvertimeByManager = new LinkedList<EmployeeWorkOvertime>();

//	@JsonIgnore
	@JsonManagedReference(value = "empEeq")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeEducationalQualifications> employeeEducationalQualifications = new LinkedList<EmployeeEducationalQualifications>();

//	@JsonIgnore
	@JsonManagedReference(value = "empEcp")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmergencyContactPerson> emergencyContactPerson = new LinkedList<EmergencyContactPerson>();

	//	@JsonIgnore
	@JsonManagedReference(value = "empEwe")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeWorkExperience> employeeWorkExperience = new LinkedList<EmployeeWorkExperience>();

//	@JsonIgnore
	@JsonManagedReference(value = "empEhbs")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeHistoricalBaseSalary> employeeHistoricalBaseSalary = new LinkedList<EmployeeHistoricalBaseSalary>();

//	@JsonManagedReference(value = "empElemp")
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeaveByEmployee = new LinkedList<EmployeeLeave>();

//	@JsonManagedReference(value = "empElman")
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeaveByManager = new LinkedList<EmployeeLeave>();

//	@JsonIgnore
	@JsonManagedReference(value = "empEt")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeTitle> employeeTitle = new LinkedList<EmployeeTitle>();

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
		return employeeWorkOvertimeByEmployee;
	}

	public void setEmployeeWorkOvertimeByEmployee(List<EmployeeWorkOvertime> employeeWorkOvertimeByEmployee) {
		this.employeeWorkOvertimeByEmployee = employeeWorkOvertimeByEmployee;
	}

	public List<EmployeeWorkOvertime> getEmployeeWorkOvertimeByManager() {
		return employeeWorkOvertimeByManager;
	}

	public void setEmployeeWorkOvertimeByManager(List<EmployeeWorkOvertime> employeeWorkOvertimeByManager) {
		this.employeeWorkOvertimeByManager = employeeWorkOvertimeByManager;
	}

	public List<EmployeeEducationalQualifications> getEmployeeEducationalQualifications() {
		return employeeEducationalQualifications;
	}

	public void setEmployeeEducationalQualifications(
			List<EmployeeEducationalQualifications> employeeEducationalQualifications) {
		this.employeeEducationalQualifications = employeeEducationalQualifications;
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

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeGender="
				+ employeeGender + ", employeeBirth=" + employeeBirth + ", employeeIdNumber=" + employeeIdNumber
				+ ", employeePhoneNumber=" + employeePhoneNumber + ", employeeContactNumber=" + employeeContactNumber
				+ ", employeeEmail=" + employeeEmail + ", employeeResidenceAddress=" + employeeResidenceAddress
				+ ", employeeContactAddress=" + employeeContactAddress + ", employeePhoto=" + employeePhoto
				+ ", employeeArrivalDate=" + employeeArrivalDate + ", employeeSalaryKind=" + employeeSalaryKind
				+ ", employeeBasicSalary=" + employeeBasicSalary + ", employeeAccount=" + employeeAccount
				+ ", employeePassword=" + employeePassword + ", systemAuthor=" + systemAuthor
				+ ", employeeHistoricalDepartment=" + employeeHistoricalDepartment + ", employeeWorkOvertimeByEmployee="
				+ employeeWorkOvertimeByEmployee + ", employeeWorkOvertimeByManager=" + employeeWorkOvertimeByManager
				+ ", employeeEducationalQualifications=" + employeeEducationalQualifications
				+ ", emergencyContactPerson=" + emergencyContactPerson + ", employeeWorkExperience="
				+ employeeWorkExperience + ", employeeHistoricalBaseSalary=" + employeeHistoricalBaseSalary
				+ ", employeeLeaveByEmployee=" + employeeLeaveByEmployee + ", employeeLeaveByManager="
				+ employeeLeaveByManager + ", employeeTitle=" + employeeTitle + ", getEmployeeId()=" + getEmployeeId()
				+ ", getEmployeeName()=" + getEmployeeName() + ", getEmployeeGender()=" + getEmployeeGender()
				+ ", getEmployeeBirth()=" + getEmployeeBirth() + ", getEmployeeIdNumber()=" + getEmployeeIdNumber()
				+ ", getEmployeePhoneNumber()=" + getEmployeePhoneNumber() + ", getEmployeeContactNumber()="
				+ getEmployeeContactNumber() + ", getEmployeeEmail()=" + getEmployeeEmail()
				+ ", getEmployeeResidenceAddress()=" + getEmployeeResidenceAddress() + ", getEmployeeContactAddress()="
				+ getEmployeeContactAddress() + ", getEmployeePhoto()=" + getEmployeePhoto()
				+ ", getEmployeeArrivalDate()=" + getEmployeeArrivalDate() + ", getEmployeeSalaryKind()="
				+ getEmployeeSalaryKind() + ", getEmployeeBasicSalary()=" + getEmployeeBasicSalary()
				+ ", getEmployeeAccount()=" + getEmployeeAccount() + ", getEmployeePassword()=" + getEmployeePassword()
				+ ", getSystemAuthor()=" + getSystemAuthor() + ", getEmployeeHistoricalDepartment()="
				+ getEmployeeHistoricalDepartment() + ", getEmployeeWorkOvertimeByEmployee()="
				+ getEmployeeWorkOvertimeByEmployee() + ", getEmployeeWorkOvertimeByManager()="
				+ getEmployeeWorkOvertimeByManager() + ", getEmployeeEducationalQualifications()="
				+ getEmployeeEducationalQualifications() + ", getEmergencyContactPerson()="
				+ getEmergencyContactPerson() + ", getEmployeeWorkExperience()=" + getEmployeeWorkExperience()
				+ ", getEmployeeHistoricalBaseSalary()=" + getEmployeeHistoricalBaseSalary()
				+ ", getEmployeeLeaveByEmployee()=" + getEmployeeLeaveByEmployee() + ", getEmployeeLeaveByManager()="
				+ getEmployeeLeaveByManager() + ", getEmployeeTitle()=" + getEmployeeTitle() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


}
