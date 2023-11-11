package com.myHighSpeedRail.johnny.model;

import java.io.Serializable;
import java.util.Objects;

import com.myHighSpeedRail.marc.model.Station;

public class ProductStationCmpsPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Station station;
	private Product product;
	
	public ProductStationCmpsPK() {};
	
	public ProductStationCmpsPK(Station station, Product product) {
		this.station = station;
		this.product = product;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(product, station);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductStationCmpsPK other = (ProductStationCmpsPK) obj;
		return Objects.equals(product, other.product) && Objects.equals(station, other.station);
	}
	
}
