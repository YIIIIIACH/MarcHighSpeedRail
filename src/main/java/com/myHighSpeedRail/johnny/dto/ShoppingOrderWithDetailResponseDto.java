package com.myHighSpeedRail.johnny.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myHighSpeedRail.johnny.model.Product;

public class ShoppingOrderWithDetailResponseDto {

	public Date orderCreationDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	public Date orderCompletionDate;
	public String orderStatus;
	public String orderNumber;
	public Integer totalPrice;
	public String member;
	public Integer orderId;
	public List<Integer> quantity;
	public List<Product> products;
	public List<String> photoData;
//	public String productName;
//	public String photoData;
}
