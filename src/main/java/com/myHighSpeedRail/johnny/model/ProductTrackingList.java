package com.myHighSpeedRail.johnny.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "product_tracking_list")
public class ProductTrackingList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_tracking_list_id", nullable = false)
	private Integer productTrackingListId;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	@Column(name = "tracking_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date trackingDate;
	
	@Column(name = "member_uuid", nullable = false)
	private String memberId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id_fk", nullable = false)
	private Product product;
	
	@PrePersist
	public void onCreate() {
		if(trackingDate == null) {
			trackingDate = new Date();
		}
	}

	public ProductTrackingList() {
		super();
	}

	public ProductTrackingList(Integer productTrackingListId, Date trackingDate, String memberId, Product product) {
		super();
		this.productTrackingListId = productTrackingListId;
		this.trackingDate = trackingDate;
		this.memberId = memberId;
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

	public String getmemberId() {
		return memberId;
	}

	public void setmemberId(String memberId) {
		this.memberId = memberId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
