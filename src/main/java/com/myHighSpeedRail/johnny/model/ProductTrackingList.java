package com.myHighSpeedRail.johnny.model;

import java.util.Date;

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
@Table(name = "product_tracking_list")
public class ProductTrackingList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_tracking_list_id", nullable = false)
	private Integer productTrackingListId;
	
	@Column(name = "tracking_date", nullable = false)
	private Date trackingDate;
	
	@Column(name = "member_uuid", nullable = false)
	private String member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id_fk", nullable = false)
	private Product product;

	public ProductTrackingList() {
		super();
	}

	public ProductTrackingList(Integer productTrackingListId, Date trackingDate, String member, Product product) {
		super();
		this.productTrackingListId = productTrackingListId;
		this.trackingDate = trackingDate;
		this.member = member;
		this.product = product;
	}

	public Integer getProductTrackingListId() {
		return productTrackingListId;
	}

	public void setProductTrackingListId(Integer productTrackingListId) {
		this.productTrackingListId = productTrackingListId;
	}

	public Date getTrackingDate() {
		return trackingDate;
	}

	public void setTrackingDate(Date trackingDate) {
		this.trackingDate = trackingDate;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
