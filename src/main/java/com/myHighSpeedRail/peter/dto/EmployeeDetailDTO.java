package com.myHighSpeedRail.peter.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myHighSpeedRail.peter.model.EmergencyContactPerson;
import com.myHighSpeedRail.peter.model.EmployeeEducationalQualifications;
import com.myHighSpeedRail.peter.model.EmployeeHistoricalBaseSalary;
import com.myHighSpeedRail.peter.model.EmployeeHistoricalDepartment;
import com.myHighSpeedRail.peter.model.EmployeeTitle;
import com.myHighSpeedRail.peter.model.EmployeeWorkExperience;

public class EmployeeDetailDTO {

	
	@JsonProperty("id")
	private Integer empId;
	
	@JsonProperty("name")
	private String empName;
	

	@JsonProperty("gender")
	private String employeeGender;

	
	@JsonProperty("birth")
	private Date employeeBirth;

	@JsonProperty("idNumber")
	private String employeeIdNumber;

	@JsonProperty("phoneNumber")
	private String employeePhoneNumber;

	@JsonProperty("contackNumber")
	private String employeeContactNumber;

	@JsonProperty("email")
	private String employeeEmail;

	@JsonProperty("residenceAddress")
	private String employeeResidenceAddress;

	@JsonProperty("contactAddress")
	private String employeeContactAddress;

	@JsonProperty("photo")
	private String employeePhoto;// 想要在資料庫裡寫資料夾邏輯

	@JsonProperty("arrivalDate")
	private Date employeeArrivalDate;

	@JsonProperty("salaryKind")
	private String employeeSalaryKind;

	@JsonProperty("basicSalary")
	private Integer employeeBasicSalary;

	@JsonProperty("historicalDepartment")
	private List<EmployeeHistoricalDepartment> employeeHistoricalDepartment = new LinkedList<EmployeeHistoricalDepartment>();

	@JsonProperty("educationalQualifications")
	private List<EmployeeEducationalQualifications> employeeEducationalQualifications = new LinkedList<EmployeeEducationalQualifications>();

	@JsonProperty("emergencyContactPerson")
	private List<EmergencyContactPerson> emergencyContactPerson = new LinkedList<EmergencyContactPerson>();

	@JsonProperty("workExperience")
	private List<EmployeeWorkExperience> employeeWorkExperience = new LinkedList<EmployeeWorkExperience>();

	@JsonProperty("historicalBaseSalary")
	private List<EmployeeHistoricalBaseSalary> employeeHistoricalBaseSalary = new LinkedList<EmployeeHistoricalBaseSalary>();

	@JsonProperty("title")
	private List<EmployeeTitle> employeeTitle = new LinkedList<EmployeeTitle>();

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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

	public List<EmployeeHistoricalDepartment> getEmployeeHistoricalDepartment() {
		return employeeHistoricalDepartment;
	}

	public void setEmployeeHistoricalDepartment(List<EmployeeHistoricalDepartment> employeeHistoricalDepartment) {
		this.employeeHistoricalDepartment = employeeHistoricalDepartment;
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

	public List<EmployeeTitle> getEmployeeTitle() {
		return employeeTitle;
	}

	public void setEmployeeTitle(List<EmployeeTitle> employeeTitle) {
		this.employeeTitle = employeeTitle;
	}
	
	
}
