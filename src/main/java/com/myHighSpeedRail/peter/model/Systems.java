package com.myHighSpeedRail.peter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="systems")
public class Systems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="system_id")
	private Integer systemId;
	
	@Column(name="system_name")
	private String systemName;
	
	public Systems() {
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	

}
