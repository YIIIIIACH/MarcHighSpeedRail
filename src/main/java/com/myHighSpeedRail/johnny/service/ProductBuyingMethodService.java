package com.myHighSpeedRail.johnny.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.repository.ProductBuyingMethodRepository;
import com.myHighSpeedRail.johnny.repository.ProductRepository;

@Service
public class ProductBuyingMethodService {
	
	@Autowired
	private ProductBuyingMethodRepository pbmrDao;
	
	@Autowired
	private ProductRepository pDao;
	
//	public String addBuyingMethod(Integer pId, String buyingMethod) {
//		Optional<Product> optional = pDao.findById(pId);
//		if(optional.isPresent()) {
//			Product product = optional.get();
//			List<ProductBuyingMethod> pbm = new ArrayList<ProductBuyingMethod>();
//			product.setProductBuyingMethod(pbm);
//		}
		
		
	
}
