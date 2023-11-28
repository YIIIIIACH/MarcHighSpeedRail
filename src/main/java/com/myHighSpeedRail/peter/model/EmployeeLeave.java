package com.myHighSpeedRail.peter.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "employee_leave")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_leave_id")
	private Integer employeeLeaveId;

	@Column(name = "employee_leave_start_time")
	private Date employeeLeaveStartTime;

	@Column(name = "employee_leave_end_time")
	private Date employeeLeaveEndTime;

	@Column(name = "employee_leave_reason")
	private String employeeLeaveReason;

	@Column(name = "manager_leave_audit")
	private String managerLeaveAudit;

	@Column(name = "leave_audit_results_sanding_date")
	private Date leaveAuditResultsSandingDate;

	@Column(name = "leave_carry_forward_date")
	private Date leaveCarryForwardDate;

	@Column(name = "leave_employee_confirm_date")
	private Date leaveEmployeeConfirmDate;

//	@JsonIgnore
//	@JsonBackReference(value = "leaEl")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_leave_kind_id_fk")
	private Leave leave;

//	@JsonIgnore
//	@JsonBackReference(value = "empElman") 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id_fk")
	private Employee manager;

//	@JsonIgnore
//	@JsonBackReference(value = "empElemp")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public EmployeeLeave() {
	}

	public Integer getEmployeeLeaveId() {
		return employeeLeaveId;
	}

	public void setEmployeeLeaveId(Integer employeeLeaveId) {
		this.employeeLeaveId = employeeLeaveId;
	}

	public Date getEmployeeLeaveStartTime() {
		return employeeLeaveStartTime;
	}

	public void setEmployeeLeaveStartTime(Date employeeLeaveStartTime) {
		this.employeeLeaveStartTime = employeeLeaveStartTime;
	}

	public Date getEmployeeLeaveEndTime() {
		return employeeLeaveEndTime;
	}

	public void setEmployeeLeaveEndTime(Date employeeLeaveEndTime) {
		this.employeeLeaveEndTime = employeeLeaveEndTime;
	}

	public String getEmployeeLeaveReason() {
		return employeeLeaveReason;
	}

	public void setEmployeeLeaveReason(String employeeLeaveReason) {
		this.employeeLeaveReason = employeeLeaveReason;
	}

	public String getManagerLeaveAudit() {
		return managerLeaveAudit;
	}

	public void setManagerLeaveAudit(String managerLeaveAudit) {
		this.managerLeaveAudit = managerLeaveAudit;
	}

	public Date getLeaveAuditResultsSandingDate() {
		return leaveAuditResultsSandingDate;
	}

	public void setLeaveAuditResultsSandingDate(Date leaveAuditResultsSandingDate) {
		this.leaveAuditResultsSandingDate = leaveAuditResultsSandingDate;
	}

	public Date getLeaveCarryForwardDate() {
		return leaveCarryForwardDate;
	}

	public void setLeaveCarryForwardDate(Date leaveCarryForwardDate) {
		this.leaveCarryForwardDate = leaveCarryForwardDate;
	}

	public Date getLeaveEmployeeConfirmDate() {
		return leaveEmployeeConfirmDate;
	}

	public void setLeaveEmployeeConfirmDate(Date leaveEmployeeConfirmDate) {
		this.leaveEmployeeConfirmDate = leaveEmployeeConfirmDate;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
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
