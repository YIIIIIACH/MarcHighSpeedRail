package com.myHighSpeedRail.johnny.model;

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
@Table(name="product_photo_segment")
public class ProductPhotoSegment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_photo_segment_id")
	private Integer productPhotoSegmentId;
	
//	@ManyToOne(fetch= FetchType.LAZY)
//	@JoinColumn(name="product_photo_id_fk",nullable=false)
//	private ProductPhoto productPhoto;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="product_id_fk",nullable=false)
	private Product product;
	
	@Column(name="segment_seq")
	private Integer sequence;
	
	@Column(name="photo_segment")
	private byte[]  photoSegment;
	
	public ProductPhotoSegment() {;}
	public ProductPhotoSegment(Product product, Integer sequence, byte[] photoSegment) {
		this.product = product;
		this.sequence = sequence;
		this.photoSegment = photoSegment;
	}

	public Integer getProductPhotoSegmentId() {
		return productPhotoSegmentId;
	}

	public void setProductPhotoSegmentId(Integer productPhotoSegmentId) {
		this.productPhotoSegmentId = productPhotoSegmentId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public byte[] getPhotoSegment() {
		return photoSegment;
	}

	public void setPhotoSegment(byte[] photoSegment) {
		this.photoSegment = photoSegment;
	}

}
