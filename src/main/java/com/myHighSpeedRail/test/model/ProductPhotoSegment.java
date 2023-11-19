package com.myHighSpeedRail.test.model;

import com.myHighSpeedRail.johnny.model.ProductPhoto;

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
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="product_photo_id_fk",nullable=false)
	private ProductPhoto productPhoto;
	
	@Column(name="segment_seq")
	private Integer sequence;
	
	@Column(name="photo_segment")
	private byte[]  photoSegment;
	
	public ProductPhotoSegment() {;}
	public ProductPhotoSegment(ProductPhoto productPhoto, Integer sequence, byte[] photoSegment) {
		this.productPhoto = productPhoto;
		this.sequence = sequence;
		this.photoSegment = photoSegment;
	}

	public Integer getProductPhotoSegmentId() {
		return productPhotoSegmentId;
	}

	public void setProductPhotoSegmentId(Integer productPhotoSegmentId) {
		this.productPhotoSegmentId = productPhotoSegmentId;
	}

	public ProductPhoto getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(ProductPhoto productPhoto) {
		this.productPhoto = productPhoto;
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
