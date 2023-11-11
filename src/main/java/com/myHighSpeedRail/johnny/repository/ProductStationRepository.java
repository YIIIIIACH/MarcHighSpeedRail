package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductStation;
import com.myHighSpeedRail.johnny.model.ProductStationCmpsPK;

public interface ProductStationRepository extends JpaRepository<ProductStation,ProductStationCmpsPK>{
	@Query(value=" select ps.product from ProductStation as ps where ps.station.stationId=:sid")
	public List<Product> getProductByStationId(Integer sid);
}
