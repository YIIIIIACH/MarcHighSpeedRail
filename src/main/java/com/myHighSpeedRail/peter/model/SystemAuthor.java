package com.myHighSpeedRail.peter.model;

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
@Table(name="system_author")
public class SystemAuthor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="system_author_id")
	private Integer systemAuthorId;
	
	@Column(name="author_json")
	private String authorJson;
	

	@JsonBackReference(value="empSa")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id_fk")
	private Employee employee;
	
	
	@JsonBackReference(value="depSa")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id_fk")
	private Department department;
	
	public SystemAuthor() {
	}

	public Integer getSystemAuthorId() {
		return systemAuthorId;
	}

	public void setSystemAuthorId(Integer systemAuthorId) {
		this.systemAuthorId = systemAuthorId;
	}

	public String getAuthorJson() {
		return authorJson;
	}

	public void setAuthorJson(String authorSson) {
		this.authorJson = authorSson;
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
		return "SystemAuthor [systemAuthorId=" + systemAuthorId + ", authorJson=" + authorJson + "]";
	}

	
	

}
