package com.myHighSpeedRail.johnny.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.ProductTrackingList;
import com.myHighSpeedRail.johnny.service.ProductService;
import com.myHighSpeedRail.johnny.service.ProductTrackingListService;

public class ConfirmIsTracking {
	
	@Autowired
	private ProductTrackingListService ptlServ;
	
	@Autowired
	private ProductService pService;
	
	public List<ProductAndPhotoSegmentDto> getTrackingProductID(@RequestParam("mId") String mId){
		List<ProductTrackingList> trackings = ptlServ.findByMemberId(mId);
		List<ProductAndPhotoSegmentDto> products = pService.findAllProduct();
		
		for (ProductAndPhotoSegmentDto p : products) {
	        p.isTracking = false;
	    }
		
		for(ProductTrackingList t : trackings) {
			for(ProductAndPhotoSegmentDto p : products) {
				if(t.getProduct().getProductId() == p.productId) {
					p.isTracking = true;
				}
			}
		}
		return products;
		
	}
}
