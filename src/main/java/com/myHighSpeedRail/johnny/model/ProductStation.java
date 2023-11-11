package com.myHighSpeedRail.johnny.model;

import com.myHighSpeedRail.marc.model.Station;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@IdClass(ProductStationCmpsPK.class)
@Entity
@Table(name="product_station")
public class ProductStation {
	@Id
	@JoinColumn(name="station_id_fk",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Station station;
	@Id
	@JoinColumn(name="product_id_fk",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Product product;
	@Column(name="product_status")
	private String productStatus;
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
}
