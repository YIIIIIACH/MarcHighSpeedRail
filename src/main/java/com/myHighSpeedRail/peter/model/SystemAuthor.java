package com.myHighSpeedRail.peter.model;

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
@Table(name="system_author")
public class SystemAuthor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="system_author_id")
	private Integer systemAuthorId;
	
	@Column(name="author_json")
	private String authorSson;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="employee_id_fk")
	private Employee employee;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id_fk")
	private Department department;
	
	public SystemAuthor() {
	}

}
