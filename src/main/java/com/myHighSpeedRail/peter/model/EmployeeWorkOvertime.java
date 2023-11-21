package com.myHighSpeedRail.peter.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@Column(name = "employee_work_overtime_start_time")
	private Date employeeWorkOvertimeStartTime;

	@Column(name = "employee_work_overtime_end_time")
	private Date employeeWorkOvertimeEndTime;

	@Column(name = "employee_work_overtime_reason")
	private String employeeWorkOvertimeReason;

	@Column(name = "manager_work_overtime_audit")
	private String managerWorkOvertimeAudit;

	@Column(name = "work_overtime_audit_results_sanding_date")
	private Date workOvertimeAuditResultsSandingDate;

	@Column(name = "work_overtime_carry_forward_date")
	private Date workOvertimeCarryForwardDate;

	@Column(name = "work_overtime_employee_confirm_date")
	private Date workOvertimeEmployeeConfirmDate;

	@JsonBackReference(value = "empEwoman")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id_fk")
	private Employee manager;

	@JsonBackReference(value = "empEwoemp")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public EmployeeWorkOvertime() {
	}

}
