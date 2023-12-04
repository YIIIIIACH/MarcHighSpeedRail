package com.myHighSpeedRail.johnny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.repository.ProductBuyingMethodRepository;

@Service
public class ProductBuyingMethodService {
	
	@Autowired
	private ProductBuyingMethodRepository pbmDao;
	
	
}
