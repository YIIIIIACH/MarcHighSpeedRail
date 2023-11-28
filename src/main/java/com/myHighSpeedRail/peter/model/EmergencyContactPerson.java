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
@Table(name = "emergency_contact_person")
public class EmergencyContactPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emergency_contact_person_id")
	private Integer emergencyContactPersonId;

	@Column(name = "emergency_contact_person_name")
	private String emergencyContactPersonName;

	@Column(name = "emergency_contact_person_relationship")
	private String emergencyContactPersonRelationship;

	@Column(name = "emergency_contact_person_phone_number")
	private String emergencyContactPersonPhoneNumber;

//	@JsonIgnore
	@JsonBackReference(value = "empEcp")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public EmergencyContactPerson() {
	}

}
