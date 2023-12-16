package com.myHighSpeedRail.peter.model;

import java.util.LinkedList;
import java.util.List;

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
@Table(name = "leave")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_id")
	private Integer leaveId;

	@Column(name = "leave_name")
	private String leaveName;

	@Column(name = "available_leave_days_description")
	private String availableLeaveDaysDescription;

	@JsonIgnore
//	@JsonManagedReference(value = "leaEl")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "leave", cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeave = new LinkedList<EmployeeLeave>();

	public Leave() {
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getAvailableLeaveDaysDescription() {
		return availableLeaveDaysDescription;
	}

	public void setAvailableLeaveDaysDescription(String availableLeaveDaysDescription) {
		this.availableLeaveDaysDescription = availableLeaveDaysDescription;
	}

	public List<EmployeeLeave> getEmployeeLeave() {
		return employeeLeave;
	}

	public void setEmployeeLeave(List<EmployeeLeave> employeeLeave) {
		this.employeeLeave = employeeLeave;
	}

}
