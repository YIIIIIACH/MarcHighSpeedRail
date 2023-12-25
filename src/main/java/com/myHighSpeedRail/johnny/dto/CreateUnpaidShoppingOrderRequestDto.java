package com.myHighSpeedRail.johnny.dto;

import java.util.List;

public class CreateUnpaidShoppingOrderRequestDto {
	
	public  String memberId;
	public List<Integer> cartItemIds;
	public Integer totalPrice;
	
}
