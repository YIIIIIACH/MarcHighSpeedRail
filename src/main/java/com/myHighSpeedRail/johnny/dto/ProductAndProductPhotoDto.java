package com.myHighSpeedRail.johnny.dto;

import jakarta.persistence.Column;

public class ProductAndProductPhotoDto {
	
	private String productName;
	
	private Integer productPrice;

	private String productDescription;

	private String productType;

	private Integer productInventory;
	
	private String mimeType;
	
	private String photoPath;

	public ProductAndProductPhotoDto() {
		super();
	}

	public ProductAndProductPhotoDto(String productName, Integer productPrice, String productDescription,
			String productType, Integer productInventory, String mimeType, String photoPath) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productInventory = productInventory;
		this.mimeType = mimeType;
		this.photoPath = photoPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(Integer productInventory) {
		this.productInventory = productInventory;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	
	
	
	
	
}
