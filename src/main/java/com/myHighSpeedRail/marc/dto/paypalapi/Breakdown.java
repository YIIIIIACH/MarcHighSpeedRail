package com.myHighSpeedRail.marc.dto.paypalapi;

public class Breakdown {
	public UnitAmount item_total;
	public Breakdown() {;}
	public Breakdown(String currCode, Integer value) {
		this.item_total = new UnitAmount(currCode, String.valueOf(value)+".00");
	}
}
