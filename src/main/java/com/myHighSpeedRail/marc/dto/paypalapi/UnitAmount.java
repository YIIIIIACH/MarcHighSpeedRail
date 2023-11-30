package com.myHighSpeedRail.marc.dto.paypalapi;

public class UnitAmount {
	public String currency_code;
	public String value;
	
	public UnitAmount() {;}
	public UnitAmount(String currCode, String value) {
		this.currency_code=currCode;
		this.value= value;
	}
}
