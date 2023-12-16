package com.myHighSpeedRail.peter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddAuthorDTO {

	@JsonProperty("info")
	private String info;
	
	@JsonProperty("systems")
	private List<AddSystemAuthorDTO> asaLisst;


	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<AddSystemAuthorDTO> getAsaLisst() {
		return asaLisst;
	}

	public void setAsaLisst(List<AddSystemAuthorDTO> asaLisst) {
		this.asaLisst = asaLisst;
	}

	@Override
	public String toString() {
		return "AddAuthorDTO [info=" + info + ", asaLisst=" + asaLisst + "]";
	}

}
