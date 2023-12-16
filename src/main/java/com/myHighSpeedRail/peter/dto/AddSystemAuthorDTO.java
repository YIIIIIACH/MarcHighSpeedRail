package com.myHighSpeedRail.peter.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddSystemAuthorDTO {

	@JsonProperty("systemId")
	private Integer systemId;
	
	@JsonProperty("systemName")
	private String systemName;
	
	@JsonProperty("permissions")
	private ArrayList<Integer> permissions;

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

	public ArrayList<Integer> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<Integer> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "AddSystemAuthorDTO [systemId=" + systemId + ", systemName=" + systemName + ", permissions="
				+ permissions + "]";
	}
	
}
