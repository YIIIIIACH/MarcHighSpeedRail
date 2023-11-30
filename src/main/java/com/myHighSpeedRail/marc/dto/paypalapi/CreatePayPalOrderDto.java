package com.myHighSpeedRail.marc.dto.paypalapi;

import java.util.List;

public class CreatePayPalOrderDto {
	public String intent;
	public List<Unit> purchase_units;
	public AppContext application_context;
}
