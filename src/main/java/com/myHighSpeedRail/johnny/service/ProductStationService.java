package com.myHighSpeedRail.johnny.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.repository.ProductStationRepository;
@Service
public class ProductStationService {
	@Autowired
	private ProductStationRepository psDao;
	
	public List<Product> getProductByStaitonId(Integer sid) {
		return  psDao.getProductByStationId(sid);
	}
}
