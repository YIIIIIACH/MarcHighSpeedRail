package com.myHighSpeedRail.peter.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "employee_work_overtime")
public class EmployeeWorkOvertime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_work_overtime_id")
	private Integer employeeWorkOvertimeId;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "employee_work_overtime_start_time")
	private Date employeeWorkOvertimeStartTime;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "employee_work_overtime_end_time")
	private Date employeeWorkOvertimeEndTime;

	@Column(name = "employee_work_overtime_reason")
	private String employeeWorkOvertimeReason;

	@Column(name = "manager_work_overtime_audit")
	private String managerWorkOvertimeAudit;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "work_overtime_audit_results_sanding_date")
	private Date workOvertimeAuditResultsSandingDate;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "work_overtime_carry_forward_date")
	private Date workOvertimeCarryForwardDate;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "work_overtime_employee_confirm_date")
	private Date workOvertimeEmployeeConfirmDate;

//	@JsonIgnore
//	@JsonBackReference(value = "empEwoman")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id_fk")
	private Employee manager;

//	@JsonIgnore
//	@JsonBackReference(value = "empEwoemp")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public EmployeeWorkOvertime() {
	}

	public Integer getEmployeeWorkOvertimeId() {
		return employeeWorkOvertimeId;
	}

	public void setEmployeeWorkOvertimeId(Integer employeeWorkOvertimeId) {
		this.employeeWorkOvertimeId = employeeWorkOvertimeId;
	}

	public Date getEmployeeWorkOvertimeStartTime() {
		return employeeWorkOvertimeStartTime;
	}

	public void setEmployeeWorkOvertimeStartTime(Date employeeWorkOvertimeStartTime) {
		this.employeeWorkOvertimeStartTime = employeeWorkOvertimeStartTime;
	}

	public Date getEmployeeWorkOvertimeEndTime() {
		return employeeWorkOvertimeEndTime;
	}

	public void setEmployeeWorkOvertimeEndTime(Date employeeWorkOvertimeEndTime) {
		this.employeeWorkOvertimeEndTime = employeeWorkOvertimeEndTime;
	}

	public String getEmployeeWorkOvertimeReason() {
		return employeeWorkOvertimeReason;
	}

	public void setEmployeeWorkOvertimeReason(String employeeWorkOvertimeReason) {
		this.employeeWorkOvertimeReason = employeeWorkOvertimeReason;
	}

	public String getManagerWorkOvertimeAudit() {
		return managerWorkOvertimeAudit;
	}

	public void setManagerWorkOvertimeAudit(String managerWorkOvertimeAudit) {
		this.managerWorkOvertimeAudit = managerWorkOvertimeAudit;
	}

	public Date getWorkOvertimeAuditResultsSandingDate() {
		return workOvertimeAuditResultsSandingDate;
	}

	public void setWorkOvertimeAuditResultsSandingDate(Date workOvertimeAuditResultsSandingDate) {
		this.workOvertimeAuditResultsSandingDate = workOvertimeAuditResultsSandingDate;
	}

	public Date getWorkOvertimeCarryForwardDate() {
		return workOvertimeCarryForwardDate;
	}

	public void setWorkOvertimeCarryForwardDate(Date workOvertimeCarryForwardDate) {
		this.workOvertimeCarryForwardDate = workOvertimeCarryForwardDate;
	}

	public Date getWorkOvertimeEmployeeConfirmDate() {
		return workOvertimeEmployeeConfirmDate;
	}

	public void setWorkOvertimeEmployeeConfirmDate(Date workOvertimeEmployeeConfirmDate) {
		this.workOvertimeEmployeeConfirmDate = workOvertimeEmployeeConfirmDate;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
