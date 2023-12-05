package com.myHighSpeedRail.johnny.dto;

import java.util.List;

import com.myHighSpeedRail.johnny.model.ProductBuyingMethod;

public class PostProductDto {
	
	public Integer productId;
	
	public String productName;
	
	public Integer productPrice;

	public String productDescription;

	public String productType;

	public Integer productInventory;
	
	public String photoData;
	
	public List<String> buyingMethod;

}
