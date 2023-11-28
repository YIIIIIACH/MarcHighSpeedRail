package com.myHighSpeedRail.peter.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "systems")
public class Systems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_id")
	private Integer systemId;

	@Column(name = "system_name")
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

	@Override
	public String toString() {
		return "Systems [systemId=" + systemId + ", systemName=" + systemName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(systemName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Systems other = (Systems) obj;
		return Objects.equals(systemName, other.systemName);
	}
	
	

}
