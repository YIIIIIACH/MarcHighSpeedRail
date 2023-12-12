package com.myHighSpeedRail.marc.dto.paypalapi;

public class UnitAmount {
	public String currency_code;// TWD
	public String value; // 20.00 
	
	public UnitAmount() {;}
	public UnitAmount(String currCode, String value) {
		this.currency_code=currCode;
		this.value= value;
	}
}
