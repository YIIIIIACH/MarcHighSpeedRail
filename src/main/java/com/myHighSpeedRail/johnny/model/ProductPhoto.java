package com.myHighSpeedRail.johnny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_photo")
public class ProductPhoto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_photo_id", nullable = false)
	private Integer productPhotoId;
	
	@Column(name = "mime_type", nullable = false)
	private String mimeType;
	
	@Column(name = "photo_path", nullable = false)
	private String photoPath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id_fk",  nullable = false)
	@JsonIgnore
	private Product product;

	public ProductPhoto() {
		super();
	}

	public ProductPhoto(Integer productPhotoId, String mimeType, String photoPath, Product product) {
		super();
		this.productPhotoId = productPhotoId;
		this.mimeType = mimeType;
		this.photoPath = photoPath;
		this.product = product;
	}

	public Integer getProductPhotoId() {
		return productPhotoId;
	}

	public void setProductPhotoId(Integer productPhotoId) {
		this.productPhotoId = productPhotoId;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
