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
@Table(name="employee_leave")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_leave_id")
	private Integer employeeLeaveId;
	
	@Column(name="employee_leave_start_time")
	private Date employeeLeaveStartTime;
	
	@Column(name="employee_leave_end_time")
	private Date employeeLeaveDndTime;
	
	@Column(name="employee_leave_reason")
	private String employeeLeaveReason;
	
	@Column(name="manager_leave_audit")
	private String managerLeaveAudit;
	
	@Column(name="leave_audit_results_sanding_date")
	private Date leaveAuditResultsSandingDate;
	
	@Column(name="leave_carry_forward_date")
	private Date leaveCarryForwardDate;
	
	@Column(name="leave_employee_confirm_date")
	private Date leaveEmployeeConfirmDate;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_leave_kind_id_fk")
	private Leave leave;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager_id_fk")
	private Employee manager;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id_fk")
	private Employee employee;
	
	public EmployeeLeave() {
	}

}
