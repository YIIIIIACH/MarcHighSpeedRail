package com.myHighSpeedRail.peter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckLoginDTO {

	@JsonProperty("acc")
	private String Account;
	
	@JsonProperty("psw")
	private String Password;

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
}
