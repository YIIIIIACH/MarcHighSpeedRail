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
@Table(name = "employee_historical_department")
public class EmployeeHistoricalDepartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_historical_department_id")
	private Integer employeeHistoricalDepartmentId;

	// 輸入輸出都可以format
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GM+8")
	// 只有輸入可以format
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") // 轉換前端String日期格式到Java日期格式
	@Column(name = "department_effective_date")
	private Date departmentEffectiveDate;

//	@JsonIgnore
	@JsonBackReference(value = "empEhd")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	@JsonIgnore
//	@JsonBackReference(value = "depEhd")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id_fk")
	private Department department;

	public EmployeeHistoricalDepartment() {
	}

	public Integer getEmployeeHistoricalDepartmentId() {
		return employeeHistoricalDepartmentId;
	}

	public void setEmployeeHistoricalDepartmentId(Integer employeeHistoricalDepartmentId) {
		this.employeeHistoricalDepartmentId = employeeHistoricalDepartmentId;
	}

	public Date getDepartmentEffectiveDate() {
		return departmentEffectiveDate;
	}

	public void setDepartmentEffectiveDate(Date departmentEffectiveDate) {
		this.departmentEffectiveDate = departmentEffectiveDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeHistoricalDepartment [employeeHistoricalDepartmentId=" + employeeHistoricalDepartmentId
				+ ", departmentEffectiveDate=" + departmentEffectiveDate + ", employee=" + employee + ", department="
				+ department + "]";
	}

}
