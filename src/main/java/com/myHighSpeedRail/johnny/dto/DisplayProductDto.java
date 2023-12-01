package com.myHighSpeedRail.johnny.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class DisplayProductDto {
	 public  Integer productId;
	 public  String productName;
	 public  Integer productPrice;
	 public  String productDescription;
	 public  String productType;
	 public  Integer productInventory;
	 public  String PhotoData;
}
