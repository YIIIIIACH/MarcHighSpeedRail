package com.myHighSpeedRail.peter.model;

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
@Table(name="leave")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="leave_id")
	private Integer leaveId;
	
	@Column(name="leave_name")
	private String leaveName;
	
	@Column(name="available_leave_days_description")
	private String availableLeaveDaysDescription;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "leave",cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeave = new LinkedList<EmployeeLeave>();
	
	public Leave() {
	}

}
