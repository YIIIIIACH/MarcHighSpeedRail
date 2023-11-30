package com.myHighSpeedRail.johnny.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "product_price", nullable = false)
	private Integer productPrice;

	@Column(name = "product_description", nullable = true)
	private String productDescription;

	@Column(name = "product_type", nullable = false)
	private String productType;

	@Column(name = "product_inventory", nullable = false)
	private Integer productInventory;

//	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<ProductPhoto> photoSegment;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"product"}) //忽略 ProductPhotoSegment Entity 內的 product 成員
	private List<ProductPhotoSegment> photoSegment;

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productDescription=" + productDescription + ", productType=" + productType + ", productInventory="
				+ productInventory + ", photoSegment=" + photoSegment + "]";
	}

	public Product() {
		super();
	}

	public Product(Integer productId, String productName, Integer productPrice, String productDescription,
			String productType, Integer productInventory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productInventory = productInventory;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public List<ProductPhotoSegment> getPhotoSegment() {
		return photoSegment;
	}

	public void setPhotoSegment(List<ProductPhotoSegment> photoSegment) {
		this.photoSegment = photoSegment;
	}

}
