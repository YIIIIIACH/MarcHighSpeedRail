package com.myHighSpeedRail.johnny.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.myHighSpeedRail.johnny.service.ProductTrackingListService;

public class ProductAndPhotoSegmentDto {
	

	public Integer productId;
	
	public String productName;
	
	public Integer productPrice;

	public String productDescription;

	public String productType;

	public Integer productInventory;
	
	public String photoData;
	
	public boolean isTracking;
		
}
