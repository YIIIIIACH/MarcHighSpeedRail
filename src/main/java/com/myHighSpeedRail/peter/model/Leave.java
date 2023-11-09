package com.myHighSpeedRail.peter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Leave() {
	}

}
