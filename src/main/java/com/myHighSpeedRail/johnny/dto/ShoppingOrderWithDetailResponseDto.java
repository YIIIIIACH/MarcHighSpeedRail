package com.myHighSpeedRail.johnny.dto;

import java.util.Date;
import java.util.List;

import com.myHighSpeedRail.johnny.model.Product;

public class ShoppingOrderWithDetailResponseDto {

	public Date orderCreationDate;
	public Date orderCompletionDate;
	public String orderStatus;
	public String orderNumber;
	public Integer totalPrice;
	public String member;

	public Integer quantity;
	public List<Product> products;
	public String photoData;
}
